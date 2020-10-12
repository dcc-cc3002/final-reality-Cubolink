package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

public class Staff extends GenericWeapon{
    protected final int magical_damage;

    public Staff(String name, int physical_damage, double weight, int magical_damage) {
        super(name, physical_damage, weight);
        this.magical_damage = magical_damage;
    }

    @Override
    public int getMagicalDamage(){
        return magical_damage;
    }

    @Override
    public boolean isWearableByMage() {
        return true;
    }

    @Override
    public boolean equals(final Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof  Staff)){
            return false;
        }

        final Staff staff = (Staff) o;
        return getName().equals(staff.getName())
                && getMagicalDamage() == staff.getMagicalDamage()
                && getPhysicalDamage() == staff.getPhysicalDamage();

    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getPhysicalDamage(), getMagicalDamage(), getWeight());
    }
}
