package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.AbstractCharacterClass;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.ICharacterClass;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public class PlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

    // While Player has its Inventory, is the characterClass which store the equipped stuff
    private final AbstractCharacterClass characterClass;
    //private static final GenericWeapon[] inventory = new GenericWeapon[10];

    /**
     * Creates a character for the player.
     * {@inheritDoc}
     * @param characterClass The class/job of the player.
     */
    public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                           @NotNull String name,
                           int maxHp, int defense, int resistance,
                           final AbstractCharacterClass characterClass) {
        super(turnsQueue, name, maxHp, defense, resistance);
        this.characterClass = characterClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, (int) (getEquippedWeapon().getWeight() / 10), TimeUnit.SECONDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack(ICharacter character) {
        if (isAlive() && isAttack_enabled()){
            characterClass.attack(character);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void heal(int points) {
        if (isAlive()){
            hp += points;
            if (hp>maxHp){
                hp = maxHp;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWeight() {
        // Do stuff with the equipped inventory and get the total weight
        return getEquippedWeapon().getWeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equip(GenericWeapon weapon) {
        if (isAlive()){
            characterClass.equip(weapon);  // A player character only can equip weapons if he's alive
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericWeapon getEquippedWeapon() {
        return characterClass.getEquippedWeapon();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public ICharacterClass getCharacterClass() {
        return characterClass;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerCharacter)) {
            return false;
        }
        final PlayerCharacter that = (PlayerCharacter) o;
        return getName().equals(that.getName());  // Yup, only the name differentiates a playerCharacter
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
