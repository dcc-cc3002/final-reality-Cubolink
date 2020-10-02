package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.AbstractCharacterClass;
import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;

public interface IPlayerCharacter {
    /**
     * Equips a weapon to the character.
     */
    void equip(AbstractWeapon weapon);

    /**
     * Return this character's equipped weapon.
     */
    AbstractWeapon getEquippedWeapon();

    /**
     * Returns this character's class.
     * @return
     */
    AbstractCharacterClass getCharacterClass();
}
