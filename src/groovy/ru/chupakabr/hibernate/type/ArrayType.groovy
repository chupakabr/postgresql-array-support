package ru.chupakabr.hibernate.type

import org.hibernate.type.AbstractSingleColumnStandardBasicType
import ru.chupakabr.hibernate.type.descriptor.java.PrimitiveArrayCommonTypeDescriptor
import ru.chupakabr.hibernate.type.descriptor.sql.ArrayTypeDescriptor

/**
 * Created by myltik on 07/03/2014.
 */
class ArrayType extends AbstractSingleColumnStandardBasicType<List> {

    /**
     * {@link ArrayType}
     */
    public static final ArrayType INSTANCE = new ArrayType()

    public ArrayType() {
        super(ArrayTypeDescriptor.INSTANCE, PrimitiveArrayCommonTypeDescriptor.COMMON_INSTANCE)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return ArrayType.class.getName()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getRegistrationKeys() {
        return [getName()] as String[]
    }
}
