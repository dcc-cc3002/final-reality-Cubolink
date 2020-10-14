package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

import java.util.Objects;

/**
 * Engineer class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class Engineer extends AbstractCharacterClass {

    public Engineer(String classname) {
        super("Engineer");
    }

    public Engineer(String classname, GenericWeapon weapon) {
        super(classname, weapon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equip(GenericWeapon weapon) {
        if (weapon.isWearableByEngineer()){
            this.equippedWeapon = weapon;
        }
    }

    /**
     * Equals definition is set as same object, or instance with name.
     *
     * @param o the object to compare equality.
     * @return true if the objects are equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }

        final Engineer that = (Engineer) o;
        return getClassname().equals(that.getClassname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassname());
    }
}
