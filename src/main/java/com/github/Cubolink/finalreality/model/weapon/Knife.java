package com.github.Cubolink.finalreality.model.weapon;

public class Knife extends GenericWeapon{
    public Knife(String name, int physical_damage, double weight) {
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
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Knife)){
            return false;
        }

        final Knife knife = (Knife) o;
        return getName().equals(knife.getName())
                && getPhysicalDamage() == knife.getPhysicalDamage()
                && getWeight() == knife.getWeight();
    }
}
