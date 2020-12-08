package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.items.IItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Inventory implements IInventory {
    private final Map<IItem, Integer> inventory = new HashMap<>();

    @Override
    public void storeItem(IItem item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item)+1);
        } else {
            inventory.put(item, 1);
        }
    }

    @Override
    public Set<IItem> getItemSet() {
        return inventory.keySet();
    }

    @Override
    public IItem takeItem(IItem item) {
        if (inventory.containsKey(item)){
            dropItem(item);
            return item;
        }
        return null;
    }

    @Override
    public void dropItem(IItem item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item)-1);
            if (inventory.get(item) == 0){
                inventory.remove(item);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }
}
