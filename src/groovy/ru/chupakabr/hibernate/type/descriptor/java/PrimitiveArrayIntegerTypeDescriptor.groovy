package ru.chupakabr.hibernate.type.descriptor.java

/**
 * Created by myltik on 07/03/2014.
 */
class PrimitiveArrayIntegerTypeDescriptor extends PrimitiveArrayCommonTypeDescriptor {

    /**
     * Descriptor instance
     * {@link PrimitiveArrayIntegerTypeDescriptor}
     */
    public static final PrimitiveArrayIntegerTypeDescriptor INSTANCE = new PrimitiveArrayIntegerTypeDescriptor()

    @SuppressWarnings([ "unchecked" ])
    protected PrimitiveArrayIntegerTypeDescriptor() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> fromString(String string) {
        if (string == null) {
            return null
        }

        List<Integer> results = new ArrayList<Integer>()
        String[] items = string.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\s+", "").split(",")
        for (int i = 0; i < items.length; ++i) {
            try {
                results.add(Integer.valueOf(items[i]))
            } catch (NumberFormatException nfe) {
                // TODO
            }
        }

        return results
    }
}
