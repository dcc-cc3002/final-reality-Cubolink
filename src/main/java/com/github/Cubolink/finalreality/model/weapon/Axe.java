package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class to define an Axe weapon.
 */
public class Axe extends AbstractWeapon {

    /**
     * Instantiates an Axe.
     * @param name The name of the Axe.
     * @param physical_damage The base damage of the weapon.
     * @param weight The base weight of the weapon.
     */
    public Axe(String name, int physical_damage, double weight) {
        super(name, physical_damage, weight);
    }

    @Override
    public boolean isWearableByEngineer() {
        return true;
    }

    @Override
    public boolean isWearableByKnight() {
        return true;
    }

    @Override
    public boolean equals(final Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }

        final Axe axe = (Axe) o;
        return getName().equals(axe.getName())
                && getPhysicalDamage() == axe.getPhysicalDamage()
                && getMagicalDamage() == axe.getMagicalDamage()
                && getWeight() == axe.getWeight()
                && isWearableByEngineer() == axe.isWearableByEngineer()
                && isWearableByKnight() == axe.isWearableByKnight()
                && isWearableByMage() == axe.isWearableByMage()
                && isWearableByThief() == axe.isWearableByThief();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getMagicalDamage(), getWeight(),
                isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
