package com.github.Cubolink.finalreality.model.weapon;

import com.github.Cubolink.finalreality.model.AbstractItem;
import com.github.Cubolink.finalreality.model.IWearableItem;

import java.util.Objects;

/**
 * An class with common things that weapon have, having the capacity to instantiate it.
 */
public class GenericWeapon extends AbstractItem implements IWeapon, IWearableItem {
    private final int physical_damage;

    /**
     * Instantiate a generic weapon.
     * @param name its name.
     * @param physical_damage its base damage when attacking physically.
     * @param weight its weight.
     */
    public GenericWeapon(String name, int physical_damage, double weight){
        super(name, weight);
        this.physical_damage = physical_damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPhysicalDamage() {
        return physical_damage;
    }

    /**
     * {@inheritDoc}
     */
    public int getMagicalDamage(){
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByEngineer() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByKnight(){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByThief() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByMage() {
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenericWeapon)) {
            return false;
        }
        final GenericWeapon weapon = (GenericWeapon) o;
        return getName().equals(weapon.getName())
                && getPhysicalDamage() == weapon.getPhysicalDamage()
                && getWeight() == weapon.getWeight()
                && isWearableByEngineer() == weapon.isWearableByEngineer()
                && isWearableByKnight() == weapon.isWearableByKnight()
                && isWearableByMage() == weapon.isWearableByMage()
                && isWearableByThief() == weapon.isWearableByThief();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),
                isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
