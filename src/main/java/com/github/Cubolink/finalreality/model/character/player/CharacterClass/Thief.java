package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

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
}
