package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.statuseffects.IStatus;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
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
    protected ArrayList<IStatus> statuses;
    protected Iterator<IStatus> statusIterator;
    protected boolean inIteration = false;

    protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                @NotNull String name,
                                int maxHp, int defense, int resistance) {
        this.turnsQueue = turnsQueue;
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.defense = defense;
        this.resistance = resistance;
        this.statuses = new ArrayList<>();  // limited number of simultaneous status effects
        this.attack_enabled = true;
        this.alive = true;
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
    @Override
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
        IStatus status_i;
        for (int i=0; i<statuses.size(); i++){
            status_i = statuses.get(i);

            if(status_i.almostEquals(status)){  // We have an instance of that kind of state
                if (status.greaterThan(status_i)){  // the newcomer is greater
                    statuses.set(i, status);  // so we replace the weaker
                }
                return;
            }
        }
        // We didn't found a status almost Equals to the newcomer, so we simply add it
        statuses.add(status);
    }

    @Override
    public void dropStatus(IStatus status) {
        if (inIteration){
            statusIterator.remove();
        } else {
            statuses.remove(status);
        }

    }

    @Override
    public void applyStatuses() {
        // Must use iterator, because status.effect() may remove one element, so it would be removing while iterating
        statusIterator = statuses.iterator();

        inIteration = true;
        IStatus status_i;
        while(statusIterator.hasNext()){
            status_i = statusIterator.next();
            status_i.effect(this);
        }
        inIteration = false;

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
    public int getMaxHp() {
        return maxHp;
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
    public void bePhysicallyAttacked(int physical_damage) {
        physical_damage -= defense;
        if (physical_damage>0){
            receiveDamage(physical_damage);
        }
    }

    @Override
    public void beMagicallyAttacked(int magical_damage) {
        magical_damage -= resistance;
        if (magical_damage>0){
            receiveDamage(magical_damage);
        }
    }

}
