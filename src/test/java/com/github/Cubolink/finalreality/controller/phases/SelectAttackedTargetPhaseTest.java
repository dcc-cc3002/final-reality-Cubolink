package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectAttackedTargetPhaseTest {

    IGameController gameController;

    @BeforeEach
    void setUp() throws InterruptedException {
        gameController = new GameController();
        gameController.createKnightPlayer();
        gameController.createSilverAxe();
        gameController.createEnemy();
        gameController.start();

        Thread.sleep(5000);

        // equip a weapon before
        gameController.prev();
        gameController.prev();
        Thread.sleep(100);
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.moveCursorRight();
        Thread.sleep(100);
        gameController.next();
        Thread.sleep(100);
        gameController.next();
        assertNotNull(((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon());
        Thread.sleep(100);


    }

    @Test
    void playerSelectTargetTransitionTest() throws InterruptedException {
        // player attacks enemy
        gameController.next();
        Thread.sleep(100);
        gameController.moveCursorRight();
        gameController.next();

        // after attacking, the current character should have changed
        Thread.sleep(2000);
        assertFalse(gameController.getCurrentCharacter().isPlayable());

        // the enemy should have received the hit
        assertTrue(gameController.getCurrentCharacter().getHp() < gameController.getCurrentCharacter().getMaxHp());

    }

    @Test
    void informationTest() throws InterruptedException {
        GameController gameController = new GameController();
        gameController.createKnightPlayer();
        gameController.createBlackMagePlayer();
        gameController.createEnemy();
        gameController.start();
        Thread.sleep(3000);

        SelectAttackedTargetPhase dummySelectAttackedTargetPhase = new SelectAttackedTargetPhase(gameController, null);
        // check we don't have multiple options written in a waiting phase
        assertEquals(gameController.getAliveCharactersList().size(), dummySelectAttackedTargetPhase.getPhaseOptions().length);

        // check the word wait is in the information
        assertTrue(dummySelectAttackedTargetPhase.getPhaseInfo().contains("attack") || dummySelectAttackedTargetPhase.getPhaseInfo().contains("Attack"));
    }

}