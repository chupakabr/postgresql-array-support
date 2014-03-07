package ru.chupakabr.hibernate.type.descriptor.sql

import org.hibernate.type.descriptor.ValueExtractor
import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.JavaTypeDescriptor
import org.hibernate.type.descriptor.sql.BasicExtractor
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.sql.ResultSet
import java.sql.SQLException

/**
 * Created by myltik on 07/03/2014.
 */
class ArrayExtractor<J> implements ValueExtractor<J> {

    /**
     * Logger instance
     * {@link org.slf4j.Logger}
     */
    private static final Logger log = LoggerFactory.getLogger(BasicExtractor.class)

    /**
     * {@link SqlTypeDescriptor}
     */
    private final SqlTypeDescriptor sqlDescriptor

    /**
     * @param sqlDescriptor    {@link SqlTypeDescriptor}
     */
    public ArrayExtractor(SqlTypeDescriptor sqlDescriptor) {
        this.sqlDescriptor = sqlDescriptor
    }

    /**
     * {@inheritDoc}
     */
    protected JavaTypeDescriptor<J> getJavaDescriptor(ResultSet rs, final String name) {
        final String typename = rs.getMetaData().getColumnTypeName(rs.findColumn(name))
        if (typename == null) {
            throw new IllegalStateException("Typename of column '$name' cannot be null")
        }

        if (!ArrayTypeDescriptor.typeDescriptorsMap.containsKey(typename)) {
            throw new IllegalStateException("Unsupported PostgreSQL array type with name '$typename' for column '$name'")
        }

        return (JavaTypeDescriptor<J>) ArrayTypeDescriptor.typeDescriptorsMap.get(typename)
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
    @Override
    public J extract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
        final J value = doExtract(rs, name, options)
        if (value == null || rs.wasNull()) {
            if (log.isTraceEnabled()) {
                log.trace("Found [null] as column [{}]", name)
            }
            return null
        }

        if (log.isTraceEnabled()) {
            log.trace("Found [{}] as column [{}]", getJavaDescriptor(rs, name).extractLoggableRepresentation(value), name)
        }
        return value
    }

    /**
     * Perform the extraction of array.
     *
     * Called from {@link #extract}.
     * Null checking of the value (as well as consulting {@link ResultSet#wasNull}) is done there.
     *
     * @param rs The result set
     * @param name The value name in the result set
     * @param options The binding options
     *
     * @return The extracted value.
     *
     * @throws SQLException Indicates a problem access the result set
     */
    protected J doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
        def result = rs.getArray(name)
        if (result) {
            return ((java.sql.Array)result).getArray()
        }

        return null
    }
}
