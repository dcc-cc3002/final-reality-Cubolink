package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.IWeapon;

import java.util.Objects;

/**
 * Thief class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class Thief extends AbstractCharacterClass {

    public Thief() {
        super("Ladron", EnumCharacterClass.thief);
    }

    /**
     * {@inheritDoc}
     * @param weapon
     */
    @Override
    public void equip(IWeapon weapon) {
        if (weapon.isWearableByThief()){
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
        if (!(o instanceof Thief)) {
            return false;
        }

        final Thief that = (Thief) o;
        return classEnum == that.classEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classEnum);
    }
}
