package ru.chupakabr.hibernate.type.descriptor.java

/**
 * Created by myltik on 07/03/2014.
 */
class PrimitiveArrayLongTypeDescriptor extends PrimitiveArrayCommonTypeDescriptor {

    /**
     * Descriptor instance
     * {@link PrimitiveArrayLongTypeDescriptor}
     */
    public static final PrimitiveArrayIntegerTypeDescriptor INSTANCE = new PrimitiveArrayIntegerTypeDescriptor()

    @SuppressWarnings([ "unchecked" ])
    protected PrimitiveArrayLongTypeDescriptor() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> fromString(String string) {
        if (string == null) {
            return null
        }

        List<Long> results = new ArrayList<Long>()
        String[] items = string.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\s+", "").split(",")
        for (int i = 0; i < items.length; ++i) {
            try {
                results.add(Long.valueOf(items[i]))
            } catch (NumberFormatException nfe) {
                // TODO
            }
        }

        return results
    }
}
