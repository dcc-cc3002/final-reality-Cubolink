package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

/**
 * Enumeration of the classes a player character may have.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public abstract class AbstractCharacterClass implements  ICharacterClass {
    protected GenericWeapon equippedWeapon;
    protected String classname;

    public AbstractCharacterClass(String classname){
        this(classname, null);
    }

    public AbstractCharacterClass(String classname, GenericWeapon weapon){
        this.equippedWeapon = weapon;
        this.classname = classname;
    }

    @Override
    public abstract void equip(GenericWeapon weapon);

    @Override
    public void attack(ICharacter character){
        character.bePhysicallyAttacked(equippedWeapon.getPhysicalDamage());
    }

    @Override
    public GenericWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    @Override
    public String getClassname() {
        return this.classname;
    }
}
