package com.github.Cubolink.finalreality.model.items;

/**
 * Interface to define what is an item.
 */
public interface IItem {
    /**
     * @return true if the item is wearable. False otherwise.
     */
    boolean isAWearableItem();

    /**
     * @return the item's weight.
     */
    double getWeight();

    /**
     * @return the item's name.
     */
    String getName();
}
