package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.statuseffects.IStatus;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public interface ICharacter {

    boolean isAttack_enabled();

    void setAttack_enabled(boolean attack_enabled);

  /**
   * Add a status effect to the character
   * @param status to add
   */
  void addStatus(IStatus status);

  /**
   * Removes a status effect from the character
   * @param status to remove
   */
  void dropStatus(IStatus status);

  /**
   * Apply all the statuses the character has
   */
  void applyStatuses();

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * @return the Hp
   */
  int getHp();

  /**
   * @return the Maximum hp that the unit supports
   */
  int getMaxHp();

  /**
   * This entity attacks other entity
   * @param character The character which this entity is attacking
   */
  void attack(ICharacter character);

  /**
   * Alters this entity's Hp
   * @param dmg Damage taken by the entity
   */
  void receiveDamage(int dmg);

  /**
   * Computes the received damage when being attacked physically, and calls receiveDamage
   * @param physical_damage The base physical damage used to attack this entity
   */
  void bePhysicallyAttacked(int physical_damage);

  /**
   * Computes the received damage when being attacked magically, and calls receiveDamage
   * @param magical_damage The base magical damage used to attack this entity
   */
  void beMagicallyAttacked(int magical_damage);

  /**
   * @return true if the entity is alive, and false otherwise
   */
  boolean isAlive();
}
