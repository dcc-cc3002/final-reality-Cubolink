package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import java.util.Objects;
import java.util.Random;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.statuseffects.Burned;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;


/**
 * Black Mage class/job that a character can have.
 * @see AbstractCharacterClass
 */
public class Black_Mage extends AbstractCharacterClass implements IMage{
    private int mana=50;

    public Black_Mage() {
        super("Mago Negro");
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
     * Attacks a character with magical damage.
     * Spend 15 mana.
     * It may paralyze the objective character (probability = 30%)
     * @see Paralyzed
     *
     * @param character the character to attack.
     */
    public void thunder(ICharacter character, Random R) {
        if (mana>=15){
            mana -= 15;
            character.beMagicallyAttacked(equippedWeapon.getMagicalDamage());

            if (R.nextDouble() < 0.3){
                character.addStatus(new Paralyzed());
            }
        }
    }

    /**
     * Uses magic on a character if the class have enough mana.
     * Recovers 30% of the objective character maximum hp.
     * Spend 15 mana.
     * It may burn the objective character (probability = 20%)
     * @see Burned
     *
     * @param character the character to attack.
     */
    public void fire(ICharacter character, Random R) {
        if (mana >= 15){
            mana -= 15;
            character.beMagicallyAttacked(equippedWeapon.getMagicalDamage());
            if (R.nextDouble() < 0.2){
                character.addStatus(new Burned(equippedWeapon.getMagicalDamage()));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlackMage() {
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
        return o instanceof Black_Mage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlackMage(), isWhiteMage(), isEngineer(), isKnight(), isThief());
    }
}
