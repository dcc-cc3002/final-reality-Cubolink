package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.items.IItem;

import java.util.Set;

public interface IInventory {
    /**
     * Stores an item into the inventory
     * @param item which to store
     */
    void storeItem(IItem item);

    Set<IItem> getItemSet();

    /**
     * Takes an item from the inventory.
     * @param item to take form the inventory
     * @return the item that was looked for.
     */
    IItem takeItem(IItem item);

    /**
     * Drops an item from the inventory.
     * @param item to take from the inventory and drop
     */
    void dropItem(IItem item);

    /**
     * @return true if the Inventory is empty. False otherwise.
     */
    boolean isEmpty();
}
