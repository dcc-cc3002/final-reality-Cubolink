package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.statuseffects.IStatus;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;
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

  protected ScheduledExecutorService scheduledExecutor;
  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected final int maxHp;
  protected int hp;
  protected int defense, resistance;
  protected boolean alive;
  protected boolean attack_enabled;
  protected GenericWeapon equippedWeapon = new GenericWeapon("Fist", 1, 0);
  protected IStatus[] statuses;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name,
                              int maxHp, int defense, int resistance) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.maxHp = maxHp;
    this.hp = maxHp;
    this.defense = defense;
    this.resistance = resistance;
    this.statuses = new IStatus[3];  // limited number of simultaneous status effects
    this.attack_enabled = true;
  }

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * @return true if the character has enable the capacity of attacking, and false if not
   */
  public boolean isAttack_enabled() {
    return attack_enabled;
  }

  /**
   * enables or disables the capacity of the character to attack
   * @param attack_enabled the value to set
   */
  @Override
  public void setAttack_enabled(boolean attack_enabled) {
    this.attack_enabled = attack_enabled;
  }

  @Override
  public void addStatus(IStatus status){

    // add to statuses
  }

  @Override
  public void dropStatus(IStatus status) {
    // iterates over the list 'statuses', and if the status is equals to the status on the list, then removes it
  }

  @Override
  public void applyStatuses() {
    // iterates over the list 'statuses', using .effect(this)
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

  /**
   * @return the weight of the entity
   */
  abstract public double getWeight();

  @Override
  public boolean isAlive() {
    if (hp <= 0){
      alive = false;
    }
    return alive;
  }

  @Override
  public abstract void attack(ICharacter character);

  @Override
  public void receiveDamage(int dmg){
    hp -= dmg;
    if(!isAlive()){
      hp = 0;
    }
  }

  @Override
  public void bePhysicallyAttackedBy(GenericWeapon weapon) {
    int dmg = weapon.getPhysicalDamage();
    dmg -= defense;
    if (dmg>0){
      receiveDamage(dmg);
    }
  }

  @Override
  public void beMagicallyAttackedBy(GenericWeapon weapon) {
    int dmg = weapon.getMagicalDamage();
    dmg -= resistance;
    if (dmg>0){
      receiveDamage(dmg);
    }

  }

}
