package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

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
    void equip(IWeapon weapon);

    /**
     * @return the weapon that this class is equipping.
     */
    IWeapon getEquippedWeapon();

    /**
     * @return the name of the class/job.
     */
    String getClassname();

    /**
     * @return the mana amount of the class/job.
     */
    int getMana();

    /**
     * @return true if the character class is Black Mage
     */
    boolean isBlackMage();

    /**
     * @return true if the character class is White Mage
     */
    boolean isWhiteMage();

    /**
     * @return true if the character class is Knight
     */
    boolean isKnight();

    /**
     * @return true if the character class is Thief
     */
    boolean isThief();

    /**
     * @return true if the character class is Engineer
     */
    boolean isEngineer();

}
