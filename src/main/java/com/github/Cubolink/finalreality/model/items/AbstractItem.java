package com.github.Cubolink.finalreality.model.items;

/**
 * Abstract class with common things that an Item can do.
 */
public abstract class AbstractItem implements  IItem{
    protected final String name;
    protected final double weight;

    public AbstractItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public abstract boolean isAWearableItem();

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
}
