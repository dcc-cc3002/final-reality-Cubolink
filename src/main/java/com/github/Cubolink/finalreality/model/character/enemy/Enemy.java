package com.github.Cubolink.finalreality.model.character.enemy;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Joaquín Cruz Cancino.
 */
public class Enemy extends AbstractCharacter {
    private final double weight;
    private final int attack_damage;

    /**
     * Creates a new enemy with a name, maximum and current Hp, defense, resistance (magic defense), attack and weight,
     * and the queue with the characters ready to
     * play.
     *
     * @param turnsQueue The queue with the characters ready.
     * @param name The name of the enemy.
     * @param maxHp The maximum Hp of the enemy. The Hp if initialized at this value too.
     * @param defense to resist physicals attacks.
     * @param attack_damage base attack points for attacking other characters.
     * @param weight of the enemy.
     */
    public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                 @NotNull final String name,
                 final int maxHp, final int defense,
                 final int attack_damage, final double weight) {
        super(turnsQueue, name, maxHp, defense, 0);  // Enemies don't resist magic attacks according to the requirements.
        this.weight = weight;
        this.attack_damage = attack_damage;
    }

    @Override
    public boolean isPlayable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        //var enemy = (Enemy) this;
        scheduledExecutor
                .schedule(this::addToQueue, (int) (this.getWeight() * 100), TimeUnit.MILLISECONDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack(ICharacter character) {
        if (isAlive() && isAttack_enabled()){
            character.bePhysicallyAttacked(attack_damage);
        }
    }

    /**
     * {@inheritDoc}
     */
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
        return getName().equals(enemy.getName())
                && getMaxHp() == enemy.getMaxHp()
                && getHp() == enemy.getHp()
                && attack_damage == enemy.attack_damage
                && defense == enemy.defense
                && getWeight() == enemy.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMaxHp(), getHp(), attack_damage, defense, getWeight());
    }
}
