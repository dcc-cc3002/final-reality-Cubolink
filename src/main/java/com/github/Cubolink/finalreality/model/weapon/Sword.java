package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

public class Sword extends GenericWeapon{
    public Sword(String name, int physical_damage, double weight) {
        super(name, physical_damage, weight);
    }

    @Override
    public boolean isWearableByKnight() {
        return true;
    }

    @Override
    public boolean isWearableByThief() {
        return true;
    }

    @Override
    public boolean equals(final Object o){
        if (this == o) return true;
        if (!(o instanceof Sword)) return false;
        final Sword sword = (Sword) o;
        return getName().equals(sword.getName())
                && getPhysicalDamage() == sword.getPhysicalDamage()
                && getWeight() == sword.getWeight()
                && isWearableByEngineer() == sword.isWearableByEngineer()
                && isWearableByKnight() == sword.isWearableByKnight()
                && isWearableByMage() == sword.isWearableByMage()
                && isWearableByThief() == sword.isWearableByThief();
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),
                isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
