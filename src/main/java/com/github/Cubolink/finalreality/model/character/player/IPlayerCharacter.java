package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.AbstractCharacterClass;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.ICharacterClass;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

/**
 * Interface which represent the things that those characters controlled by the player can do, that enemies can't.
 */
public interface IPlayerCharacter {
    /**
     * Equips a weapon to the character.
     * @param weapon the weapon to equip
     */
    void equip(GenericWeapon weapon);

    /**
     * Heals a number of points to the current Hp
     * @param points to heal
     */
    void heal(int points);

    /**
     * @return this character's equipped weapon.
     */
    GenericWeapon getEquippedWeapon();

    /**
     * @return this character's class.
     */
    ICharacterClass getCharacterClass();
}
