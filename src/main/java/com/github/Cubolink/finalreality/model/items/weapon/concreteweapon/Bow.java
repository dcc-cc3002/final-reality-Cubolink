package com.github.Cubolink.finalreality.model.items.weapon.concreteweapon;

import com.github.Cubolink.finalreality.model.items.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * A class to define a Bow weapon.
 */
public class Bow extends AbstractWeapon {

    /**
     * Instantiates a Bow.
     * @param name The name of the Bow.
     * @param physical_damage The base damage of the weapon.
     * @param weight The base weight of the weapon.
     */
    public Bow(String name, int physical_damage, double weight) {
        super(name, physical_damage, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByEngineer() {
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
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow bow = (Bow) o;
        return getName().equals(bow.getName())
                && getPhysicalDamage() == bow.getPhysicalDamage()
                // && getMagicalDamage() == bow.getMagicalDamage()
                && getWeight() == bow.getWeight();
                /*
                && isAWearableItem() == bow.isAWearableItem()
                && isWearableByEngineer() == bow.isWearableByEngineer()
                && isWearableByKnight() == bow.isWearableByKnight()
                && isWearableByMage() == bow.isWearableByMage()
                && isWearableByThief() == bow.isWearableByThief();*/
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),
                isAWearableItem(), isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
