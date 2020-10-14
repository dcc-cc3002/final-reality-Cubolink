package com.github.Cubolink.finalreality.model.weapon;

/**
 * Interface to define a weapon over a common wearable item.
 */
public interface IWeapon {
    /**
     * @return physical damage made by the weapon
     */
    int getPhysicalDamage();

    /**
     * @return magical damage made by the weapon
     */
    int getMagicalDamage();

}
