package com.github.Cubolink.finalreality.model;

public interface IWearableItem {
    /**
     * @return true if is wearable by an Engineer
     */
    boolean isWearableByEngineer();

    /**
     * @return true if is wearable by a Knight
     */
    boolean isWearableByKnight();

    /**
     * @return true if is wearable by a Thief
     */
    boolean isWearableByThief();

    /**
     * @return true if is wearable by a Mage
     */
    boolean isWearableByMage();
}
