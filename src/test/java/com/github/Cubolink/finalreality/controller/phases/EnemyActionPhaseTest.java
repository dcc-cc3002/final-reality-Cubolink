package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyActionPhaseTest {

    IGameController gameController = new GameController();

    @BeforeEach
    void setUp() throws InterruptedException {
        gameController.createBlackMagePlayer();
        gameController.createNormalStaff();
        gameController.createEnemy();
        gameController.start();

        Thread.sleep(5000);

        // equip a weapon before
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.moveCursorRight();
        gameController.next();
        gameController.next();
        assertNotNull(((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon());

    }

    @Test
    void testEnemyPhaseTransition() throws InterruptedException {
        // we only have to check that the enemy does something, so first we do the player wait

        // our player character has the maximum hp
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        assertEquals(gameController.getCurrentCharacter().getHp(), gameController.getCurrentCharacter().getMaxHp());
        // then, we wait
        gameController.moveCursorRight();
        gameController.moveCursorRight();
        gameController.next();
        assertNull(gameController.getCurrentCharacter());

        // now we check he eventually have an enemy as current character
        Thread.sleep(3000);
        assertFalse(gameController.getCurrentCharacter().isPlayable());

        // and finally, we check the enemy attacked the player
        Thread.sleep(2000);
        // so the player's life should have decreased
        assertNotEquals(gameController.getCurrentCharacter().getHp(), gameController.getCurrentCharacter().getMaxHp());

    }

    @Test
    void informationTest() throws InterruptedException {
        GameController gameController = new GameController();
        gameController.createKnightPlayer();
        gameController.createBlackMagePlayer();
        gameController.createEnemy();
        gameController.start();
        Thread.sleep(3000);

        EnemyActionPhase dummyEnemyActionPhase = new EnemyActionPhase(gameController);
        // check we don't have multiple options written in a waiting phase
        assertTrue(dummyEnemyActionPhase.getPhaseOptions().length <= 1);

        // check the word enemy is in the information
        assertTrue(dummyEnemyActionPhase.getPhaseInfo().contains("enemy") || dummyEnemyActionPhase.getPhaseInfo().contains("Enemy"));
    }

}