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

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Bow)){
            return false;
        }

        final Bow bow = (Bow) o;
        return getName().equals(bow.getName())
                && getPhysicalDamage() == bow.getPhysicalDamage()
                && getWeight() == bow.getWeight();
    }
}
