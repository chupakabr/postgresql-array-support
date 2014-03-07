package ru.chupakabr.sql.driver.types

import org.apache.commons.dbcp.DelegatingConnection
import org.hibernate.usertype.UserType
import org.postgresql.core.BaseConnection

import java.sql.Connection
import java.sql.PreparedStatement

/**
 * Created by myltik on 07/03/2014.
 */
abstract class AbstractPostgresType implements UserType {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object assemble(Serializable cached, Object owner) {
        return cached
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object deepCopy(Object o) {
        return o
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable disassemble(Object value)  {
        return value
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object x, Object y)  {
        return x.equals(y)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(Object o) {
        return o.hashCode()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMutable() {
        return false
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object replace(Object original, Object target, Object owner) {
        return original
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnedClass() {
        return List
    }

    /**
     * Get backend connection.
     * Unwrap connection if it is wrapped.
     *
     * @param statement {@link java.sql.PreparedStatement}
     * @return {@link java.sql.Connection}
     */
    protected Connection getConnection(PreparedStatement statement) {
        Connection connection = statement.connection

        if (connection instanceof DelegatingConnection) {
            Connection innerConnection = connection.getInnermostDelegate()
            if (innerConnection == null) {
                innerConnection = connection.unwrap(BaseConnection)
            }

            connection = innerConnection
        }

        return connection
    }
}
