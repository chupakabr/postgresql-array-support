package ru.chupakabr.hibernate.type.descriptor.java

/**
 * Created by myltik on 07/03/2014.
 */
class PrimitiveArrayDoubleTypeDescriptor extends PrimitiveArrayCommonTypeDescriptor {

    /**
     * Descriptor instance
     * {@link PrimitiveArrayDoubleTypeDescriptor}
     */
    public static final PrimitiveArrayIntegerTypeDescriptor INSTANCE = new PrimitiveArrayIntegerTypeDescriptor()

    @SuppressWarnings([ "unchecked" ])
    protected PrimitiveArrayDoubleTypeDescriptor() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Double> fromString(String string) {
        if (string == null) {
            return null
        }

        List<Double> results = new ArrayList<Double>()
        String[] items = string.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\s+", "").split(",")
        for (int i = 0; i < items.length; ++i) {
            try {
                results.add(Double.valueOf(items[i]))
            } catch (NumberFormatException nfe) {
                // TODO
            }
        }

        return results
    }
}
