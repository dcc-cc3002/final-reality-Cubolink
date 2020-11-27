package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.IWeapon;

import java.util.Objects;

/**
 * Knight class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class Knight extends AbstractCharacterClass {

    public Knight() {
        super("Caballero", EnumCharacterClass.knight);
    }

    /**
     * {@inheritDoc}
     * @param weapon
     */
    @Override
    public void equip(IWeapon weapon) {
        if (weapon.isWearableByKnight()){
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
        if (!(o instanceof Knight)) {
            return false;
        }

        final Knight that = (Knight) o;
        return classEnum == that.classEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classEnum);
    }
}
