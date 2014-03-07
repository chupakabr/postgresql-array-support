package ru.chupakabr.sql.driver

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.chupakabr.hibernate.type.ArrayType
import ru.chupakabr.sql.driver.types.DoubleArray
import ru.chupakabr.sql.driver.types.IntegerArray
import ru.chupakabr.sql.driver.types.LongArray
import ru.chupakabr.sql.driver.types.TextArray

import java.sql.Types

/**
 * Created by myltik on 07/03/2014.
 */
class PostgreSQLDialect extends org.hibernate.dialect.PostgreSQLDialect {

    /**
     * Logger instance
     */
    private static final Logger log = LoggerFactory.getLogger(PostgreSQLDialect.class)

    /**
     * Register PostgreSQL dialect
     */
    public PostgreSQLDialect() {
        super()

        log.info "Adding custom Hibernate types..."
        registerHibernateType(Types.ARRAY, ArrayType.INSTANCE.getName())
        registerHibernateType(IntegerArray.SQL_TYPES[0], ArrayType.INSTANCE.getName())
        registerHibernateType(DoubleArray.SQL_TYPES[0], ArrayType.INSTANCE.getName())
        registerHibernateType(LongArray.SQL_TYPES[0], ArrayType.INSTANCE.getName())
        registerHibernateType(TextArray.SQL_TYPES[0], ArrayType.INSTANCE.getName())

        log.debug "Adding custom column types..."
        registerColumnType(IntegerArray.SQL_TYPES[0], "integer[]")
        registerColumnType(DoubleArray.SQL_TYPES[0], "double precision[]")
        registerColumnType(LongArray.SQL_TYPES[0], "bigint[]")
        registerColumnType(TextArray.SQL_TYPES[0], "text[]")

        log.debug "PostgreSQL dialect with array support has been successfully registered"
    }

    /**
     * @param insertString    Query string
     * @return Passed query string with " RETURNING id" suffix in the end
     */
    public String appendIdentitySelectToInsert(String insertString) {
        return insertString + " RETURNING id";
    }
}
