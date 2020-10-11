package com.github.Cubolink.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Joaquín Cruz Cancino.
 */
public class Enemy extends AbstractCharacter {
  private final GenericWeapon weapon;
  private final double weight;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue,
               @NotNull final String name,
               int defense, int resistance, int maxHp,
               final double weight, final int attack_dmg) {
    super(turnsQueue, name, maxHp, defense, resistance);
    this.weight = weight;
    this.weapon = new GenericWeapon("Whole Body", attack_dmg, 0);
  }

  @Override
  public void waitTurn(){
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    var enemy = (Enemy) this;
    scheduledExecutor
            .schedule(this::addToQueue, (int) enemy.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public void attack(ICharacter character) {
    character.bePhysicallyAttackedBy(weapon);
  }

  @Override
  public double getWeight() {
    return weight;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
