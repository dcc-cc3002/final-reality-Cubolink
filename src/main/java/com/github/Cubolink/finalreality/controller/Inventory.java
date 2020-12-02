package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.IItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Inventory implements IInventory {

    /**
     * A class to store a stock of equal items.
     * Stores the item, and the amount of equals items in the counter stock.
     *
     * Example: When storing two Equal Iron Swords, this Stock will have
     *          the Iron Sword as the item, and the counter stock value in 2.
     *
     * This way we add one the stock counter instead of having duplicated Items.
     */

    private class Stock{
        private final IItem item;
        private int stock;

        /**
         * Instantiates a Stock. It must be initialized with an Item, and start with the counter stock = 1.
         * Particularly these stocks doesn't make sense with 0 items.
         * @param item which defines what stores this stock
         */
        private Stock(IItem item){
            this.item = item;
            stock = 1;
        }

        private void addItem(IItem item){
            if (!(this.item.equals(item))){
                return;
            }
            stock++;
        }

        /**
         * Takes an Item from the stock. Decrease the stock counter.
         * @return that Item. Return null if the counter is zero, the stock has 0 items.
         */
        private IItem takeItem(){
            if (stock > 0){
                stock--;
                return item;
            }
            return null;
        }

        /**
         * Drops an Item from the stock. Decrease the stock counter.
         */
        private void dropItem(){
            if (stock > 0){
                stock--;
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Stock)) {
                return false;
            }
            final Stock that = (Stock) o;
            return this.item.equals(that.item);  // The stock doesn't matter
        }
    }

    //private static final Map<Stock, Integer> inventory = new HashMap<>();
    private static final Map<IItem, Integer> inventory = new HashMap<>();
    private int index = 0;

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
        inventory.replace(item, inventory.get(item)-1);
        if (inventory.get(item) == 0){
            inventory.remove(item);
        }
    }
}
