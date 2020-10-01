package com.github.Cubolink.finalreality.model.weapon;

public class Staff extends AbstractWeapon{
    protected final double magical_damage;

    public Staff(String name, double physical_damage, double weight, double magical_damage) {
        super(name, physical_damage, weight);
        this.magical_damage = magical_damage;
    }

    @Override
    public double getMagical_damage(){
        return magical_damage;
    }
}
