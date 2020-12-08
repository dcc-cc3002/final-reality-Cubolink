package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.statuseffects.IStatus;

import java.beans.PropertyChangeListener;

/**
 * Interface of what represents a character in the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public interface ICharacter {

    /**
     * Checks if the character is playable or not
     */
    boolean isPlayable();

    /**
     * Add a status effect to the character.
     * @see IStatus
     *
     * @param status to add
     */
    void addStatus(IStatus status);

    /**
     * Removes a status effect from the character.
     * @see IStatus
     *
     * @param status to remove.
     */
    void dropStatus(IStatus status);

    /**
     * Apply all the statuses the character has.
     * @see IStatus
     */
    void applyStatuses();

    /**
     * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}.
     * seconds before adding the character to the queue.
     */
    void waitTurn();

    /**
     * Attacks other entity. This entity's attack is executed only if is alive and its attack is enabled.
     * After attacking the character waits.
     * @param character The character which this entity is attacking.
     */
    void attack(ICharacter character);

    /**
     * Alters this entity's Hp
     * @param dmg Damage taken by the entity
     */
    void receiveDamage(int dmg);

    /**
     * Computes the received damage when being attacked physically, and calls receiveDamage.
     * @param physical_damage The base physical damage used to attack this entity.
     */
    void bePhysicallyAttacked(int physical_damage);

    /**
     * Computes the received damage when being attacked magically, and calls receiveDamage.
     * @param magical_damage The base magical damage used to attack this entity.
     */
    void beMagicallyAttacked(int magical_damage);





    /**
     * @return true if the character can attack, false otherwise.
     */
    boolean isAttack_enabled();

    /**
     * Enable/Disable the attack capacity.
     * @param attack_enabled true to enable, false to disable.
     */
    void setAttack_enabled(boolean attack_enabled);

    /**
     * Returns this character's name.
     */
    String getName();

    /**
     * @return its current Hp.
     */
    int getHp();

    /**
     * @return the Maximum hp that the unit supports.
     */
    int getMaxHp();

    /**
     * Checks if the entity is alive, updates its status if it's required.
     * Fires a property change if the character defeat event happens.
     * @return true if the entity is alive, and false otherwise.
     */
    boolean isAlive();

    /**
     * Adds a listener to the character's defeat event
     * @param listener who will listen the event
     */
    void addDefeatEventListener(PropertyChangeListener listener);

    void addReadyInQueueEventListener(PropertyChangeListener listener);
}
