package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.AbstractCharacterClass;
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
  private static final GenericWeapon[] inventory = new GenericWeapon[10];
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
  public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                         @NotNull String name,
                         int maxHp, int defense, int resistance,
                         final AbstractCharacterClass characterClass) {
    super(turnsQueue, name, maxHp, defense, resistance);
    this.characterClass = characterClass;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, (int) (getEquippedWeapon().getWeight() / 10), TimeUnit.SECONDS);
  }

  @Override
  public void attack(ICharacter character) {
    characterClass.attack(character);
  }

  @Override
  public void heal(int points){
    if (isAlive()){
      hp += points;
      if (hp>maxHp){
        hp = maxHp;
      }
    }
  }

  @Override
  public double getWeight() {
    // Do stuff with the equipped inventory and get the total weight
    return getEquippedWeapon().getWeight();
  }

  @Override
  public void equip(GenericWeapon weapon){
    characterClass.equip(weapon);
  }
  @Override
  public GenericWeapon getEquippedWeapon() {
    return characterClass.getEquippedWeapon();
  }

  @Override
  public AbstractCharacterClass getCharacterClass() {
    return characterClass;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof PlayerCharacter)) return false;
    final PlayerCharacter that = (PlayerCharacter) o;
    return getName().equals(that.getName());  // Yup, only the name differentiates a playerCharacter
  }

  @Override
  public int hashCode(){
    return Objects.hash(getName());
  }
}
