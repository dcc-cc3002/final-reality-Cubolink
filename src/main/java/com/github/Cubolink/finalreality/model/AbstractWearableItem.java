package com.github.Cubolink.finalreality.model;

public abstract class AbstractWearableItem extends AbstractItem{

    public AbstractWearableItem(String name, double weight) {
        super(name, weight);
    }

    @Override
    public boolean isAWearableItem(){
        return true;
    }
}
