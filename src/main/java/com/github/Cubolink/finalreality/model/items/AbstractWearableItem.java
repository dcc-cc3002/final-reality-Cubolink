package com.github.Cubolink.finalreality.model.items;

/**
 * Abstract Class with the common behavior of wearable items: being wearable.
 */
public abstract class AbstractWearableItem extends AbstractItem{

    public AbstractWearableItem(String name, double weight) {
        super(name, weight);
    }

    @Override
    public boolean isAWearableItem(){
        return true;
    }
}
