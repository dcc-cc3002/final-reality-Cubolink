package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

/**
 * Interface for the the classes that a player have as a job. (A player only can have one).
 */
public interface ICharacterClass {

    /**
     * The job/class attacks another character with its weapon/tool
     * @param character that one which this entity is attacking.
     */
    void attack(ICharacter character);

    /**
     * Try to equip a weapon to the class.
     * @param weapon the weapon to equip.
     */
    void equip(GenericWeapon weapon);

    /**
     * @return the weapon that this class is equipping.
     */
    GenericWeapon getEquippedWeapon();

    /**
     * @return the name of the class/job.
     */
    String getClassname();
}
