package com.github.Cubolink.finalreality.model.weapon;

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
}
