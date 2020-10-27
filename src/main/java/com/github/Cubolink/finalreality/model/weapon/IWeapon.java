package com.github.Cubolink.finalreality.model.weapon;

/**
 * Interface to define a weapon over a common wearable item.
 */
public interface IWeapon {
    // From IItem
    /**
     * @return the item's weight.
     */
    double getWeight();

    // From IWearableItem
    /**
     * @return the item's name.
     */
    String getName();

    /**
     * @return true if is wearable by an Engineer.
     */
    boolean isWearableByEngineer();

    /**
     * @return true if is wearable by a Knight.
     */
    boolean isWearableByKnight();

    /**
     * @return true if is wearable by a Thief.
     */
    boolean isWearableByThief();

    /**
     * @return true if is wearable by a Mage.
     */
    boolean isWearableByMage();

    // From IWeapon
    /**
     * @return physical damage made by the weapon
     */
    int getPhysicalDamage();

    /**
     * @return magical damage made by the weapon
     */
    int getMagicalDamage();



}
