package com.github.Cubolink.finalreality.model.weapon;

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
}
