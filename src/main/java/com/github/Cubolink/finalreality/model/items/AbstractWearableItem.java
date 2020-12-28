package com.github.Cubolink.finalreality.model.items;

/**
 * Abstract Class with the common behavior of wearable items: being wearable.
 */
public abstract class AbstractWearableItem extends AbstractItem{

    /**
     * Default constructor for wearable items.
     * @param name of the wearable item.
     * @param weight of the wearable item.
     */
    public AbstractWearableItem(String name, double weight) {
        super(name, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAWearableItem(){
        return true;
    }
}
