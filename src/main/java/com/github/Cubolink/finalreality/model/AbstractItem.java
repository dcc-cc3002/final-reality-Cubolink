package com.github.Cubolink.finalreality.model;

public abstract class AbstractItem implements  IItem{
    protected final String name;
    protected final double weight;

    public AbstractItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }
}
