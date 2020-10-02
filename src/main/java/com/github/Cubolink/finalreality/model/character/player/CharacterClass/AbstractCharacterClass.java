package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;

/**
 * Enumeration of the classes a player character may have.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public abstract class AbstractCharacterClass {
    protected AbstractWeapon equippedWeapon;
    protected String classname;

    public AbstractCharacterClass(String classname){
        this(null, classname);
    }

    public AbstractCharacterClass(AbstractWeapon weapon, String classname){
        this.equippedWeapon = weapon;
        this.classname = classname;
    }
    abstract public void equip(AbstractWeapon weapon);

    public AbstractWeapon getEquippedWeapon() {
        return equippedWeapon;
    }


    public String getClassname() {
        return this.classname;
    }
}
