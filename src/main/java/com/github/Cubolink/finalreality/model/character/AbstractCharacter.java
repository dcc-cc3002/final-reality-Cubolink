package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.statuseffects.IStatus;
import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Joaquín Cruz Cancino.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected int hp, maxhp;
  protected int defense, resistance;
  protected boolean alive;
  protected AbstractWeapon equippedWeapon = null;
  protected IStatus[] statuses;
  protected ScheduledExecutorService scheduledExecutor;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.statuses = new IStatus[3];  // limited number of simultaneous status effects
  }

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  protected void addStatus(){
    // add to statuses
  }

  abstract public void waitTurn();

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getHp(){
    return hp;
  }

  @Override
  public boolean is_alive() {
    if (hp <= 0){
      alive = false;
    }
    return alive;
  }

  abstract public int getWeight();

  public void takeDamage(int dmg, boolean physical){
    int real_damage=0;
    if (physical){
      if (dmg > defense){
        real_damage = (dmg - defense);
      }
    } else {
      if (dmg > resistance){
        real_damage = (dmg - resistance);
      }
    }
    hp -= real_damage;
  }

  public void heal(int points){
    if (hp+points <= maxhp){
      hp += points;
    } else  if (hp < maxhp){
      hp = maxhp;
    }
  }
}
