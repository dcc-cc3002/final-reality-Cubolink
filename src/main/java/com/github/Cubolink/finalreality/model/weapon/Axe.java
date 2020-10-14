package com.github.Cubolink.finalreality.model.weapon;

import java.util.Objects;

public class Axe extends GenericWeapon{
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
        if (this == o) return true;
        if (!(o instanceof Axe)) return false;

        final Axe axe = (Axe) o;
        return getName().equals(axe.getName())
                && getPhysicalDamage() == axe.getPhysicalDamage()
                && getWeight() == axe.getWeight()
                && isWearableByEngineer() == axe.isWearableByEngineer()
                && isWearableByKnight() == axe.isWearableByKnight()
                && isWearableByMage() == axe.isWearableByMage()
                && isWearableByThief() == axe.isWearableByThief();
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),
                isWearableByEngineer(), isWearableByKnight(), isWearableByMage(), isWearableByThief());
    }
}
