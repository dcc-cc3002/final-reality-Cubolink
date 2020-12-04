package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.Objects;

/**
 * Thief class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class Thief extends AbstractCharacterClass {

    public Thief() {
        super("Ladron");
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
     * {@inheritDoc}
     */
    @Override
    public boolean isThief() {
        return true;
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
        return that.isBlackMage() == isBlackMage()
                && that.isWhiteMage() == isWhiteMage()
                && that.isEngineer() == isEngineer()
                && that.isKnight() == isKnight()
                && that.isThief() == isThief();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlackMage(), isWhiteMage(), isEngineer(), isKnight(), isThief());
    }
}
