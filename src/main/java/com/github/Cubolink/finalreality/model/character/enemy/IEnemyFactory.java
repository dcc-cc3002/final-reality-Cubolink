package com.github.Cubolink.finalreality.model.character.enemy;

import java.util.Random;

public interface IEnemyFactory {
    /**
     * Creates a random Enemy.
     * @param random object used to choose the enemy that will be created.
     * @return the enemy generated.
     */
    Enemy createEnemy(Random random);
}
