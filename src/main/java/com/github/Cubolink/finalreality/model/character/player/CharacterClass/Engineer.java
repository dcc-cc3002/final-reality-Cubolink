package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

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
}
