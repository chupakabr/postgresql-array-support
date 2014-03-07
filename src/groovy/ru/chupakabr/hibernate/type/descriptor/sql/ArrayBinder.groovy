package ru.chupakabr.hibernate.type.descriptor.sql

import org.hibernate.type.descriptor.JdbcTypeNameMapper
import org.hibernate.type.descriptor.ValueBinder
import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.JavaTypeDescriptor
import org.hibernate.type.descriptor.sql.BasicBinder
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.sql.PreparedStatement
import java.sql.SQLException

/**
 * Created by myltik on 07/03/2014.
 */
class ArrayBinder <J> implements ValueBinder<J> {

    /**
     * Logger instance
     * {@link org.slf4j.Logger}
     */
    private static final Logger log = LoggerFactory.getLogger(BasicBinder.class)

    private static final String BIND_MSG_TEMPLATE = "Binding parameter [%d] as [%s] - %s"
    private static final String NULL_BIND_MSG_TEMPLATE = "Binding parameter [%d] as [%s] - <null>"

    /**
     * {@link SqlTypeDescriptor}
     */
    private final SqlTypeDescriptor sqlDescriptor

    /**
     * @param sqlDescriptor    {@link SqlTypeDescriptor}
     */
    public ArrayBinder(SqlTypeDescriptor sqlDescriptor) {
        this.sqlDescriptor = sqlDescriptor
    }

    /**
     * {@inheritDoc}
     */
    public SqlTypeDescriptor getSqlDescriptor() {
        return sqlDescriptor
    }

    /**
     * {@inheritDoc}
     */
    public JavaTypeDescriptor<J> getJavaDescriptor(PreparedStatement st, int index) {
        final String typename = st.getMetaData().getColumnTypeName(index)
        if (typename == null) {
            throw new IllegalStateException("Typename of column with index $index cannot be null")
        }

        if (!ArrayTypeDescriptor.typeDescriptorsMap.containsKey(typename)) {
            throw new IllegalStateException("Unsupported PostgreSQL array type name '$typename', column index is $index")
        }

        return (JavaTypeDescriptor<J>) ArrayTypeDescriptor.typeDescriptorsMap.get(typename)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void bind(PreparedStatement stmt, J value, int index, WrapperOptions options) throws SQLException {
        if (value == null) {
            if (log.isTraceEnabled()) {
                log.trace(String.format(
                        NULL_BIND_MSG_TEMPLATE,
                        index,
                        JdbcTypeNameMapper.getTypeName(sqlDescriptor.getSqlType())
                ))
            }
            stmt.setNull(index, sqlDescriptor.getSqlType())
        }

        if (log.isTraceEnabled()) {
            log.trace(String.format(
                    BIND_MSG_TEMPLATE,
                    index,
                    JdbcTypeNameMapper.getTypeName(sqlDescriptor.getSqlType()),
                    getJavaDescriptor().extractLoggableRepresentation(value)
            ))
        }
        doBind(stmt, value, index, options)
    }

    /**
     * Perform the binding.
     * Safe to assume that value is not null.
     *
     * @param stmt The prepared statement
     * @param value The value to bind (not null).
     * @param index The index at which to bind
     * @param options The binding options
     *
     * @throws SQLException Indicates a problem binding to the prepared statement.
     */
    protected void doBind(PreparedStatement stmt, J value, int index, WrapperOptions options) throws SQLException {
        stmt.setString(index, getJavaDescriptor(stmt, index).unwrap(value, String.class, options))
    }
}
