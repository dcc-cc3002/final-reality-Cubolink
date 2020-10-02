package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.AbstractCharacterClass;
import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;

public class Knight extends AbstractCharacterClass {
    public Knight(String classname) {
        super(classname);
    }

    public Knight(AbstractWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        this.equippedWeapon = weapon;
    }
}
