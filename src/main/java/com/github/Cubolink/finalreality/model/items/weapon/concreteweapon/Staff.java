package com.github.Cubolink.finalreality.model.items.weapon.concreteweapon;

import com.github.Cubolink.finalreality.model.items.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * A class to define a Staff weapon.
 */
public class Staff extends AbstractWeapon {
    protected final int magical_damage;

    /**
     * Instantiates a Staff
     * @param name The name of the Staff.
     * @param physical_damage The base damage of the weapon.
     * @param magical_damage The base damage of the weapon when using magic.
     * @param weight The weight of the weapon.
     */
    public Staff(String name, int physical_damage, int magical_damage, double weight) {
        super(name, physical_damage, weight);
        this.magical_damage = magical_damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMagicalDamage(){
        return magical_damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWearableByMage() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }

        final Staff staff = (Staff) o;
        return getName().equals(staff.getName())
                && getMagicalDamage() == staff.getMagicalDamage()
                && getPhysicalDamage() == staff.getPhysicalDamage()
                && getWeight() == staff.getWeight()
                && isAWearableItem() == staff.isAWearableItem()
                && isWearableByEngineer() == staff.isWearableByEngineer()
                && isWearableByKnight() == staff.isWearableByKnight()
                && isWearableByMage() == staff.isWearableByMage()
                && isWearableByThief() == staff.isWearableByThief();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getMagicalDamage(), getWeight(),
                isAWearableItem(), isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
