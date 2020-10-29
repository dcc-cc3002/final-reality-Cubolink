package com.github.Cubolink.finalreality.model.weapon;

import com.github.Cubolink.finalreality.model.IWearableItem;

/**
 * Interface to define a weapon over a common wearable item.
 */
public interface IWeapon extends IWearableItem {
    /**
     * @return physical damage made by the weapon
     */
    int getPhysicalDamage();

    /**
     * @return magical damage made by the weapon
     */
    int getMagicalDamage();



}
