package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.ICharacterClass;
import com.github.Cubolink.finalreality.model.weapon.IWeapon;

/**
 * Interface which represent the things that those characters controlled by the player can do, that enemies can't.
 */
public interface IPlayerCharacter {
    /**
     * Equips a weapon to the character.
     * @param weapon the weapon to equip
     */
    void equip(IWeapon weapon);

    /**
     * Heals a number of points to the current Hp
     * @param points to heal
     */
    void heal(int points);

    /**
     * @return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

    /**
     * @return this character's class.
     */
    ICharacterClass getCharacterClass();
}
