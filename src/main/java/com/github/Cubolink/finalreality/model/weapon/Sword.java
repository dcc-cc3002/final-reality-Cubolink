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

    @Override
    public boolean equals(final Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof Sword)){
            return false;
        }

        final Sword sword = (Sword) o;
        return getName().equals(sword.getName())
                && getPhysicalDamage() == sword.getPhysicalDamage()
                && getWeight() == sword.getWeight();
    }
}
