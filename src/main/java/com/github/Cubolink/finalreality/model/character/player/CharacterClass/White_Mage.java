package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.statuseffects.Poisoned;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.Objects;

/**
 * White Mage class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class White_Mage extends AbstractCharacterClass implements IMage {
    private int mana = 50;

    public White_Mage() {
        super("Mago Blanco");
    }

    @Override
    public int getMana() {
        return mana;
    }

    /**
     * {@inheritDoc}
     * @param weapon
     */
    @Override
    public void equip(IWeapon weapon) {
        if (weapon.isWearableByMage()){
            this.equippedWeapon = weapon;
        }
    }

    /**
     * Uses magic on a character if the class have enough mana.
     * Recovers 30% of the objective character maximum hp.
     * Spend 15 mana.
     * @param character the character to cure.
     */
    public void cure(IPlayerCharacter character) {
        if (mana>=15){
            mana -= 15;
            character.heal(character.getMaxHp()*3/10);
        }
    }

    /**
     * Uses magic on a character if the class have enough mana.
     * Poisons a character
     * Spend 40 mana.
     * @see Poisoned
     *
     * @param character the character to poison.
     */
    public void poison(ICharacter character) {
        if (mana>=40){
            mana -= 40;

            // add status poisoned to character
            character.addStatus(new Poisoned(equippedWeapon.getMagicalDamage()));
        }
    }

    /**
     * Uses magic on a character if the class have enough mana.
     * Paralyzes a character
     * Spend 25 mana.
     * @see Paralyzed
     *
     * @param character the character to paralyze.
     */
    public void paralyze(ICharacter character) {
        if (mana>=25){
            mana -= 25;

            // add status paralyzed to character
            character.addStatus(new Paralyzed());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWhiteMage() {
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
        return o instanceof White_Mage;

    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlackMage(), isWhiteMage(), isEngineer(), isKnight(), isThief());
    }
}
