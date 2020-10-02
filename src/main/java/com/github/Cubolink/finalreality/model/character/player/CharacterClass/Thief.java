package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;

public class Thief extends AbstractCharacterClass {

    public Thief(String classname) {
        super(classname);
    }

    public Thief(AbstractWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        this.equippedWeapon = weapon;
    }
}
