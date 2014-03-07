package ru.chupakabr.hibernate.type.descriptor.sql

import org.hibernate.type.descriptor.ValueBinder
import org.hibernate.type.descriptor.ValueExtractor
import org.hibernate.type.descriptor.java.JavaTypeDescriptor
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor
import ru.chupakabr.hibernate.type.PostgreSQLTypes
import ru.chupakabr.hibernate.type.descriptor.java.PrimitiveArrayCommonTypeDescriptor
import ru.chupakabr.hibernate.type.descriptor.java.PrimitiveArrayDoubleTypeDescriptor
import ru.chupakabr.hibernate.type.descriptor.java.PrimitiveArrayIntegerTypeDescriptor
import ru.chupakabr.hibernate.type.descriptor.java.PrimitiveArrayLongTypeDescriptor
import ru.chupakabr.hibernate.type.descriptor.java.PrimitiveArrayTextTypeDescriptor

import java.sql.Types

/**
 * Created by myltik on 07/03/2014.
 */
class ArrayTypeDescriptor implements SqlTypeDescriptor {

    /**
     * Descriptor instance
     * {@link ArrayTypeDescriptor}
     */
    public static final ArrayTypeDescriptor INSTANCE = new ArrayTypeDescriptor()

    /**
     * Available descriptor types
     * {@link ArrayTypeDescriptor}
     */
    public static final Map< String, PrimitiveArrayCommonTypeDescriptor > typeDescriptorsMap
    static {
        typeDescriptorsMap = new HashMap< String, PrimitiveArrayCommonTypeDescriptor >()
        typeDescriptorsMap.put(PostgreSQLTypes.Names.INTEGER_ARRAY, PrimitiveArrayIntegerTypeDescriptor.INSTANCE)
        typeDescriptorsMap.put(PostgreSQLTypes.Names.DOUBLE_ARRAY, PrimitiveArrayDoubleTypeDescriptor.INSTANCE)
        typeDescriptorsMap.put(PostgreSQLTypes.Names.BIGINT_ARRAY, PrimitiveArrayLongTypeDescriptor.INSTANCE)
        typeDescriptorsMap.put(PostgreSQLTypes.Names.TEXT_ARRAY, PrimitiveArrayTextTypeDescriptor.INSTANCE)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSqlType() {
        return Types.ARRAY
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new ArrayBinder<X>(this)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new ArrayExtractor<X>(this)
    }
}
