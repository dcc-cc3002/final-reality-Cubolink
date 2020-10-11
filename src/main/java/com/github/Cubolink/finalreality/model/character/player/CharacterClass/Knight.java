package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

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
}
