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

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Axe)){
            return false;
        }

        final Axe axe = (Axe) o;
        return getName().equals(axe.getName())
                && getPhysicalDamage() == axe.getPhysicalDamage()
                && getWeight() == axe.getWeight();
    }
}
