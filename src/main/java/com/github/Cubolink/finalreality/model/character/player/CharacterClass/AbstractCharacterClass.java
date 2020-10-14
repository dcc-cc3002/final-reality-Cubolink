package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

/**
 * Abstract class for the classes that a player have as a job. (A player only can have one).
 * The classes/jobs have both name and an equipped weapon, this last one being like their job tool.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public abstract class AbstractCharacterClass implements  ICharacterClass {
    protected GenericWeapon equippedWeapon;
    protected String classname;

    /**
     * Constructor for the player class/job. Initialize the class without a weapon equipped.
     * @param classname name of the class.
     */
    public AbstractCharacterClass(String classname) {
        this(classname, null);
    }

    /**
     * Default constructor for the player class/job. Initialize the class with a weapon equipped.
     * @param classname name of the class.
     * @param weapon equipped weapon.
     */
    public AbstractCharacterClass(String classname, GenericWeapon weapon) {
        this.equippedWeapon = weapon;
        this.classname = classname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack(ICharacter character) {
        character.bePhysicallyAttacked(equippedWeapon.getPhysicalDamage());
    }

    /**
     * Try to equip a weapon to the class, asking the weapon if it can be equipped by this class (double dispatch).
     * @param weapon the one to equip,
     */
    @Override
    public abstract void equip(GenericWeapon weapon);

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassname() {
        return this.classname;
    }
}
