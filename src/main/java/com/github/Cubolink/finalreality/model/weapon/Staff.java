package com.github.Cubolink.finalreality.model.weapon;

public class Staff extends AbstractWeapon{
    protected final int magical_damage;

    public Staff(String name, int physical_damage, double weight, int magical_damage) {
        super(name, physical_damage, weight);
        this.magical_damage = magical_damage;
    }

    @Override
    public int getMagical_damage(){
        return magical_damage;
    }
}
