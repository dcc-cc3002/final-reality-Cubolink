package com.github.Cubolink.finalreality.model;

/**
 * Interface to define what is an item.
 */
public interface IItem {
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
