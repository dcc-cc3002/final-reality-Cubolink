package com.github.Cubolink.finalreality.model.weapon;

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
}
