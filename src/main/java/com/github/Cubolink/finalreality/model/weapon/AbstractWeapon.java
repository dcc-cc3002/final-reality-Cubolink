package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

public class AbstractWeapon {
    protected final String name;
    protected final double physical_damage;
    protected final double weight;

    public AbstractWeapon(String name, double physical_damage, double weight){
        this.name = name;
        this.physical_damage = physical_damage;
        this.weight = weight;
    }

    public String getName(){
        return name;
    }
    public double getDamage(){
        return physical_damage;
    }
    public double getWeight(){
        return weight;
    }

    public double getMagical_damage(){
        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractWeapon)) {
            return false;
        }
        // I don't know if this instantiates AbstractWeapon. I guess we'll learn it soon.
        final AbstractWeapon weapon = (AbstractWeapon) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight());
    }
}
