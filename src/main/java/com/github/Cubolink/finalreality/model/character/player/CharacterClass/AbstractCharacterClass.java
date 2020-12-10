package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

/**
 * Abstract class for the classes that a player have as a job. (A player only can have one).
 * The classes/jobs have both name and an equipped weapon, this last one being like their job tool.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public abstract class AbstractCharacterClass implements  ICharacterClass {
    protected String classname;
    protected IWeapon equippedWeapon;

    /**
     * Constructor for the player class/job. Initialize the class without a weapon equipped.
     * @param classname name of the class.
     */
    public AbstractCharacterClass(String classname) {
        this.classname = classname;
        this.equippedWeapon = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack(ICharacter character) {
        if (equippedWeapon == null){
            return;
        }
        character.bePhysicallyAttacked(equippedWeapon.getPhysicalDamage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMana() {
        return 0;
    }

    /**
     * Try to equip a weapon to the class, asking the weapon if it can be equipped by this class (double dispatch).
     * @param weapon the one to equip,
     */
    @Override
    public abstract void equip(IWeapon weapon);

    /**
     * {@inheritDoc}
     */
    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassname() {
        return this.classname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlackMage() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWhiteMage() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isKnight() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isThief() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEngineer() {
        return false;
    }
}
