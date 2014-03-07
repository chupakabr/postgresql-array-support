package ru.chupakabr.hibernate.type.descriptor.java

import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor
import org.hibernate.type.descriptor.java.ArrayMutabilityPlan

/**
 * Created by myltik on 07/03/2014.
 */
class PrimitiveArrayCommonTypeDescriptor extends AbstractTypeDescriptor<List> {

    /**
     * Descriptor instance
     * {@link PrimitiveArrayCommonTypeDescriptor}
     */
    public static final PrimitiveArrayCommonTypeDescriptor COMMON_INSTANCE = new PrimitiveArrayCommonTypeDescriptor()

    @SuppressWarnings([ "unchecked" ])
    protected PrimitiveArrayCommonTypeDescriptor() {
        super(List.class, ArrayMutabilityPlan.INSTANCE)
    }

    /**
     * Convert list value to string
     *
     * @param value List of type {@link List}
     * @return {@link String}
     */
    public String toString(List value) {
        return value.toArray()
    }

    /**
     * Convert string value to list
     *
     * @param string {@link String}
     * @return {@link List}
     */
    public List fromString(String string) {
        throw new IllegalStateException("Must be overridden by child class")
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areEqual(List one, List another) {
        return one == another || ( one != null && another != null && one.equals(another) )
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int extractHashCode(List elems) {
        int hashCode = 1;
        for (def e : this) {
            hashCode = 31*hashCode + (e == null ? 0 : e.hashCode())
        }
        return hashCode
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings([ "unchecked" ])
    public <X> X unwrap(List value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null
        } else if (List.class.isAssignableFrom(type)) {
            return (X) value
        } else if (String.class.isAssignableFrom(type)) {
            return (X) toString(value)
        }

        throw unknownUnwrap(type)
    }

    /**
     * {@inheritDoc}
     */
    public <X> List wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null
        } else if (List.class.isInstance(value)) {
            return (List) value
        } else if (String.class.isInstance(value)) {
            return fromString((String) value)
        }

        throw unknownWrap(value.getClass())
    }
}
