package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

import java.util.Objects;

public class Knight extends AbstractCharacterClass {

    public Knight(String classname) {
        super(classname);
    }

    public Knight(GenericWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(GenericWeapon weapon) {
        if (weapon.isWearableByKnight()){
            this.equippedWeapon = weapon;
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Knight)) return false;

        final Knight that = (Knight) o;
        return getClassname().equals(that.getClassname());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getClassname());
    }
}
