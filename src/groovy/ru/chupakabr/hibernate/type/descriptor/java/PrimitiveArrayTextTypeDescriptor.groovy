package ru.chupakabr.hibernate.type.descriptor.java

/**
 * Created by myltik on 07/03/2014.
 */
class PrimitiveArrayTextTypeDescriptor extends PrimitiveArrayCommonTypeDescriptor {

    /**
     * Descriptor instance
     * {@link PrimitiveArrayTextTypeDescriptor}
     */
    public static final PrimitiveArrayTextTypeDescriptor INSTANCE = new PrimitiveArrayTextTypeDescriptor()

    @SuppressWarnings([ "unchecked" ])
    protected PrimitiveArrayTextTypeDescriptor() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List fromString(String string) {
        if (string == null) {
            return null
        }

        // TODO

        throw new IllegalStateException("Not implemented")
    }
}
