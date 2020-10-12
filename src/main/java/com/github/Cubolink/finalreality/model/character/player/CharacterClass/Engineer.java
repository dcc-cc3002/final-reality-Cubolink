package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

import java.util.Objects;

public class Engineer extends AbstractCharacterClass {

    public Engineer(String classname) {
        super("Engineer");
    }

    public Engineer(GenericWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(GenericWeapon weapon) {
        if (weapon.isWearableByEngineer()){
            this.equippedWeapon = weapon;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;

        final Engineer that = (Engineer) o;
        return getClassname().equals(that.getClassname());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getClassname());
    }
}
