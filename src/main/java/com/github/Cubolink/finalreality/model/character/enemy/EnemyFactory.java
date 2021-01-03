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
        Enemy enemy = new Enemy(turnsQueue, "Slime", 30, 4, 5, 8);
        enemy.setSpriteFileNames(new String[]{"src/main/resources/slime1.png", "src/main/resources/slime2.png"});
        return enemy;

    }

    /**
     * @return a skeleton Enemy.
     */
    private Enemy createSkeleton(){
        Enemy enemy = new Enemy(turnsQueue, "Skeleton", 50, 8, 8, 10);
        enemy.setSpriteFileNames(
                new String[]{
                        "src/main/resources/skeleton1.png",
                        "src/main/resources/skeleton2.png",
                        "src/main/resources/skeleton3.png"});
        return enemy;
    }

    /**
     * @return a dark knight Enemy.
     */
    private Enemy createDarkKnight(){
        Enemy enemy = new Enemy(turnsQueue, "Dark Knight", 60, 12, 15, 15);
        enemy.setSpriteFileNames(
                new String[]{
                        "src/main/resources/darkKnight1.png",
                        "src/main/resources/darkKnight2.png",
                        "src/main/resources/darkKnight3.png"});
        return enemy;
    }

    /**
     * @return a dragon Enemy.
     */
    private Enemy createDragon(){
        Enemy enemy = new Enemy(turnsQueue, "Dragon", 180, 25, 50, 30);
        enemy.setSpriteFileNames(
                new String[]{
                        "src/main/resources/dragon1.png",
                        "src/main/resources/dragon2.png",
                        "src/main/resources/dragon3.png"});
        return enemy;
    }

}
