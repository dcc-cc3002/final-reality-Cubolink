package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.AbstractWearableItem;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

/**
 * An abstract class with common things that weapon have.
 */
public abstract class AbstractWeapon extends AbstractWearableItem implements IWeapon {
    private final int physical_damage;

    /**
     * Instantiate a generic weapon.
     * @param name its name.
     * @param physical_damage its base damage when attacking physically.
     * @param weight its weight.
     */
    public AbstractWeapon(String name, int physical_damage, double weight){
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
    @Override
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
}
