package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;

public class Engineer extends AbstractCharacterClass {

    public Engineer(String classname) {
        super("Engineer");
    }

    public Engineer(AbstractWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        this.equippedWeapon = weapon;
    }
}
