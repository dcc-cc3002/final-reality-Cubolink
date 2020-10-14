package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

public class Bow extends GenericWeapon{
    public Bow(String name, int physical_damage, double weight) {
        super(name, physical_damage, weight);
    }

    @Override
    public boolean isWearableByEngineer() {
        return true;
    }

    @Override
    public boolean isWearableByThief() {
        return true;
    }

    @Override
    public boolean equals(final Object o){
        if (this == o) return true;
        if (!(o instanceof Bow)) return false;
        final Bow bow = (Bow) o;
        return getName().equals(bow.getName())
                && getPhysicalDamage() == bow.getPhysicalDamage()
                && getWeight() == bow.getWeight()
                && isWearableByEngineer() == bow.isWearableByEngineer()
                && isWearableByKnight() == bow.isWearableByKnight()
                && isWearableByMage() == bow.isWearableByMage()
                && isWearableByThief() == bow.isWearableByThief();
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),
                isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
