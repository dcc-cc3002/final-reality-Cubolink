package com.github.Cubolink.finalreality.model.weapon;

import com.github.Cubolink.finalreality.model.AbstractItem;
import com.github.Cubolink.finalreality.model.IWearableItem;

import java.util.Objects;

public class GenericWeapon extends AbstractItem implements IWeapon, IWearableItem {
    protected final int physical_damage;

    public GenericWeapon(String name, int physical_damage, double weight){
        super(name, weight);
        this.physical_damage = physical_damage;
    }

    public String getName(){
        return name;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public int getPhysicalDamage() {
        return physical_damage;
    }

    @Override
    public int getMagicalDamage() {
        return 0;
    }

    @Override
    public boolean isWearableByEngineer() {
        return false;
    }

    @Override
    public boolean isWearableByKnight() {
        return false;
    }

    @Override
    public boolean isWearableByThief() {
        return false;
    }

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
        // I don't know if this instantiates AbstractWeapon. I guess we'll learn it soon.
        final GenericWeapon weapon = (GenericWeapon) o;
        return getPhysicalDamage() == weapon.getPhysicalDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getWeight());
    }
}
