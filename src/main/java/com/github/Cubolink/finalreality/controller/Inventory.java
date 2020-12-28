package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.items.IItem;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.*;

/**
 * Inventory class. Manages the Items that the player has.
 */
public class Inventory implements IInventory {
    private final Map<IItem, Integer> inventory = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeItem(IItem item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item)+1);
        } else {
            inventory.put(item, 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IItem> getItemSet() {
        return inventory.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IItem takeItem(IItem item) {
        if (inventory.containsKey(item)){
            dropItem(item);
            return item;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dropItem(IItem item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item)-1);
            if (inventory.get(item) == 0){
                inventory.remove(item);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IWeapon> getWeaponList() {
        Set<IItem> itemSet = inventory.keySet();
        List<IWeapon> weaponList = new ArrayList<>();
        for (IItem item: itemSet) {
            if (item.isAWearableItem()) {
                weaponList.add((IWeapon) item);
            }
        }
        return weaponList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }
}
