package com.github.Cubolink.finalreality.model.items.weapon.concreteweapon;

import com.github.Cubolink.finalreality.model.items.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * A class to define a Sword weapon.
 */
public class Sword extends AbstractWeapon {

    /**
     * Instantiates a Sword.
     * @param name The name of the Sword.
     * @param physical_damage The base damage of the weapon.
     * @param weight The weight of the weapon.
     */
    public Sword(String name, int physical_damage, double weight) {
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sword)) return false;
        final Sword sword = (Sword) o;
        return getName().equals(sword.getName())
                && getPhysicalDamage() == sword.getPhysicalDamage()
                && getMagicalDamage() == sword.getMagicalDamage()
                && getWeight() == sword.getWeight()
                && isAWearableItem() == sword.isAWearableItem()
                && isWearableByEngineer() == sword.isWearableByEngineer()
                && isWearableByKnight() == sword.isWearableByKnight()
                && isWearableByMage() == sword.isWearableByMage()
                && isWearableByThief() == sword.isWearableByThief();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getMagicalDamage(), getWeight(),
                isAWearableItem(), isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
