package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

import java.util.Objects;

public class Thief extends AbstractCharacterClass {

    public Thief(String classname) {
        super(classname);
    }

    public Thief(GenericWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(GenericWeapon weapon) {
        if (weapon.isWearableByThief()){
            this.equippedWeapon = weapon;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Thief)) return false;

        final Thief that = (Thief) o;
        return getClassname().equals(that.getClassname());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getClassname());
    }
}
