package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

public interface ICharacterClass {
    /**
     * @param weapon equip this weapon to the class
     */
    void equip(GenericWeapon weapon);

    /**
     * @return the weapon this character is using
     */
    GenericWeapon getEquippedWeapon();

    /**
     * Attacks a character
     * @param character the character which this entity is attacking
     */
    void attack(ICharacter character);

    String getClassname();
}
