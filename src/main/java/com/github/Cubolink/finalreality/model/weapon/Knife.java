package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class to define a Knife weapon.
 */
public class Knife extends GenericWeapon{

    /**
     * Instantiates a Knife.
     * @param name The name of the knife.
     * @param physical_damage The base damage of the weapon.
     * @param weight The base weight of the weapon.
     */
    public Knife(String name, int physical_damage, double weight) {
        super(name, physical_damage, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByKnight() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByThief() {
        return true;
    }

    @Override
    public boolean equals(final Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife knife = (Knife) o;
        return getName().equals(knife.getName())
                && getPhysicalDamage() == knife.getPhysicalDamage()
                && getWeight() == knife.getWeight()
                && isWearableByEngineer() == knife.isWearableByEngineer()
                && isWearableByKnight() == knife.isWearableByKnight()
                && isWearableByMage() == knife.isWearableByMage()
                && isWearableByThief() == knife.isWearableByThief();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),
                isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
