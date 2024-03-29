package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.Objects;

/**
 * Engineer class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class Engineer extends AbstractCharacterClass {

    public Engineer() {
        super("Ingeniero");
    }

    /**
     * {@inheritDoc}
     * @param weapon
     */
    @Override
    public void equip(IWeapon weapon) {
        if (weapon.isWearableByEngineer()){
            this.equippedWeapon = weapon;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEngineer() {
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
        return o instanceof Engineer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlackMage(), isWhiteMage(), isEngineer(), isKnight(), isThief());
    }
}
