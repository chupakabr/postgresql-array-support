package ru.chupakabr.sql.driver.types

import org.postgresql.core.Oid
import org.postgresql.jdbc4.Jdbc4Array
import ru.chupakabr.hibernate.type.PostgreSQLTypes

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * Support for PostgreSQL text[] type
 *
 * Created by myltik on 07/03/2014.
 */
class TextArray extends AbstractPostgresType {

    /**
     * JDBC type definition
     */
    public static final SQL_TYPES = [PostgreSQLTypes.ARRAY_TEXT] as int[]

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] sqlTypes() {
        return SQL_TYPES
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) {
        def result = rs.getArray(names[0])
        if (result) {
            return ((java.sql.Array)result).getArray()
        }

        return null
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nullSafeSet(PreparedStatement stmt, Object value, int index) {
        def makeArray = {
            StringBuffer sb = new StringBuffer()
            sb.append("{")
            if (value) {
                value.each {
                    Jdbc4Array.escapeArrayElement(sb, it)
                    sb.append(",")
                }
                sb.deleteCharAt(sb.size()-1)
            }
            sb.append("}")
            sb.toString()
        }

        java.sql.Array arrayValue = new Jdbc4Array(
                getConnection(stmt),
                (int) Oid.TEXT_ARRAY,
                (String)(value == null ? null : makeArray()))
        stmt.setArray(index, arrayValue)
    }
}
