package com.github.Cubolink.finalreality.model.character.enemy;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {
    private final BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();
    EnemyFactory enemyFactory = new EnemyFactory(turnsQueue);

    @Test
    void enemyCreationTest() {
        Random R = new Random(0);
        Enemy newEnemy;
        ArrayList<Enemy> enemies = new ArrayList<>();

        int differentEnemies = 0;

        for (int i = 0; i<500; i++) {
            newEnemy = enemyFactory.createEnemy(R);
            if (!enemies.contains(newEnemy)) {
                enemies.add(newEnemy);
                differentEnemies++;
            }
        }

        assertEquals(differentEnemies, 4);

    }
}