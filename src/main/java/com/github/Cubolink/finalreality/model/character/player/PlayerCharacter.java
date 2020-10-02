package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.AbstractCharacterClass;
import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;
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
  private static final AbstractWeapon[] inventory = new AbstractWeapon[10];
  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param characterClass
   *     the class of this character
   */
  public PlayerCharacter(@NotNull String name,
                         @NotNull BlockingQueue<ICharacter> turnsQueue,
                         final AbstractCharacterClass characterClass) {
    super(turnsQueue, name);
    this.characterClass = characterClass;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, (int) (equippedWeapon.getWeight() / 10), TimeUnit.SECONDS);
  }

  @Override
  public int getWeight() {
    // Do stuff with the inventory and get the total weight
    return 0;
  }

  @Override
  public void equip(AbstractWeapon weapon){
    characterClass.equip(weapon);
  }
  @Override
  public AbstractWeapon getEquippedWeapon() {
    return characterClass.getEquippedWeapon();
  }

  @Override
  public AbstractCharacterClass getCharacterClass() {
    return characterClass;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
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
    return getCharacterClass() == that.getCharacterClass()
        && getName().equals(that.getName());
  }
}
