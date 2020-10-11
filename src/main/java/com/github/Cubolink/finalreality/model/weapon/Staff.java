package com.github.Cubolink.finalreality.model.weapon;

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
}
