package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.statuseffects.IStatus;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    protected final int defense;
    protected final int resistance;
    private boolean alive;
    private boolean attack_enabled;
    private final ArrayList<IStatus> statuses;
    private Iterator<IStatus> statusIterator;
    private boolean inIteration = false;
    private final PropertyChangeSupport characterDefeatedEvent = new PropertyChangeSupport(this);
    private final PropertyChangeSupport characterReadyInQueueEvent = new PropertyChangeSupport(this);

    /**
     * @param turnsQueue The queue with the characters ready.
     * @param name The name of the character.
     * @param maxHp The maximum Hp of the enemy. The Hp if initialized at this value too.
     * @param defense to resist physicals attacks.
     * @param resistance to resist magicals attacks.
     */
    protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                final @NotNull String name,
                                final int maxHp, final int defense, final int resistance) {
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

    @Override
    public abstract boolean isPlayable();

    /**
     * Adds this character to the turns queue.
     */
    protected void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
        characterReadyInQueueEvent.firePropertyChange(getName()+" ready. ", false, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAttack_enabled() {
        return attack_enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttack_enabled(boolean attack_enabled) {
        this.attack_enabled = attack_enabled;
    }

    /**
     * {@inheritDoc}
     * Verifies if the status already is on the character before adding it.
     * If there's a status of the same kind, then the character stays with the worse (longer duration or greater damage).
     */
    @Override
    public void addStatus(IStatus status) {
        IStatus status_i;
        for (int i=0; i<statuses.size(); i++){
            status_i = statuses.get(i);

            if ( status_i.almostEquals(status) ) {  // We have an instance of that kind of state
                if ( status.greaterThan(status_i) ) {  // the newcomer is greater
                    statuses.set(i, status);  // so we replace the weaker
                }
                return;
            }
        }
        // We didn't found a status almost Equals to the newcomer, so we simply add it
        statuses.add(status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dropStatus(IStatus status) {
        if (inIteration){
            statusIterator.remove();
        } else {
            statuses.remove(status);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyStatuses() {
        // Must use iterator, because status.effect() may remove one element, so it would be removing while iterating
        statusIterator = statuses.iterator();

        inIteration = true;
        IStatus status_i;
        while(statusIterator.hasNext()) {
            status_i = statusIterator.next();
            status_i.effect(this);
        }
        inIteration = false;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    abstract public void waitTurn();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    abstract public double getWeight();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        if (alive && hp<=0){
            alive = false;
            characterDefeatedEvent.firePropertyChange(getName()+" defeated.", true, false);
        }

        return alive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void attack(ICharacter character);

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveDamage(int dmg){
        hp -= dmg;
        if(!isAlive()){
            hp = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bePhysicallyAttacked(int physical_damage) {
        physical_damage -= defense;
        if (physical_damage>0){
            receiveDamage(physical_damage);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beMagicallyAttacked(int magical_damage) {
        magical_damage -= resistance;
        if (magical_damage>0){
            receiveDamage(magical_damage);
        }
    }

    @Override
    public void addDefeatEventListener(PropertyChangeListener listener) {
        characterDefeatedEvent.addPropertyChangeListener(listener);
    }

    @Override
    public void addReadyInQueueEventListener(PropertyChangeListener listener) {
        characterReadyInQueueEvent.addPropertyChangeListener(listener);
    }
}
