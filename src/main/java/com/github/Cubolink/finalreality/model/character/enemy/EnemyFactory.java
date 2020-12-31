package com.github.Cubolink.finalreality.model.character.enemy;

import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class EnemyFactory implements IEnemyFactory {
    private final BlockingQueue<ICharacter> turnsQueue;
    public EnemyFactory(BlockingQueue<ICharacter> turnsQueue){
        this.turnsQueue = turnsQueue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy createEnemy(Random random){
        double randomValue = random.nextDouble();

        if (randomValue < 0.5) {
            return createSlime();
        } else if (randomValue < 0.75) {
            return createSkeleton();
        } else if (randomValue < 0.92) {
            return createDarkKnight();
        } else {
            return createDragon();
        }

    }

    /**
     * @return a slime Enemy.
     */
    private Enemy createSlime(){
        return new Enemy(turnsQueue, "Slime", 30, 4, 5, 8);
    }

    /**
     * @return a skeleton Enemy.
     */
    private Enemy createSkeleton(){
        return new Enemy(turnsQueue, "Skeleton", 50, 8, 8, 10);
    }

    /**
     * @return a dark knight Enemy.
     */
    private Enemy createDarkKnight(){
        return new Enemy(turnsQueue, "Dark Knight", 60, 12, 15, 15);
    }

    /**
     * @return a dragon Enemy.
     */
    private Enemy createDragon(){
        return new Enemy(turnsQueue, "Dragon", 180, 25, 50, 30);
    }

}
