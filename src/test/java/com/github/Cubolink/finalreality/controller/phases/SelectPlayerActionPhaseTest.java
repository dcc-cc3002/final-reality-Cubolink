package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectPlayerActionPhaseTest {
    IGameController gameController = new GameController();

    @BeforeEach
    void setUp() throws InterruptedException {
        gameController.createKnightPlayer();
        gameController.createSilverAxe();
        gameController.createEnemy();
        gameController.start();

        Thread.sleep(5000);


    }

    @Test
    void playerActionTransitionTest() throws InterruptedException {

        assertTrue(gameController.getCurrentCharacter().isPlayable());

        // trying to go to the previous phase should be meaningless in the main selection phase
        gameController.prev();
        assertTrue(gameController.getCurrentCharacter().isPlayable());

        Thread.sleep(50);
        // going to the attack phase and get back shouldn't alter anything
        gameController.next();
        Thread.sleep(50);
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.prev();
        Thread.sleep(50);
        assertTrue(gameController.getCurrentCharacter().isPlayable());

        // going to the equip phase and get back shouldn't alter anything
        gameController.moveCursorRight();
        Thread.sleep(50);
        gameController.next();
        Thread.sleep(50);
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.prev();
        Thread.sleep(50);
        assertTrue(gameController.getCurrentCharacter().isPlayable());

        // going to the waiting phase should change the current character
        gameController.moveCursorRight();
        Thread.sleep(50);
        gameController.moveCursorRight();
        Thread.sleep(50);
        gameController.next();
        Thread.sleep(100);
        assertFalse(gameController.getCurrentCharacter().isPlayable());
    }

    @Test
    void informationTest() throws InterruptedException {
        GameController gameController = new GameController();
        gameController.createKnightPlayer();
        gameController.createBlackMagePlayer();
        gameController.createEnemy();
        gameController.start();
        Thread.sleep(3000);

        SelectPlayerActionPhase dummySelectPlayerActionPhase = new SelectPlayerActionPhase(gameController);

        // check we have space for three basic character actions: attack, equip and wait
        assertEquals(dummySelectPlayerActionPhase.getPhaseOptions().length, 3);

        // check the word weapon is in the information
        assertTrue(dummySelectPlayerActionPhase.getPhaseInfo().contains("select") || dummySelectPlayerActionPhase.getPhaseInfo().contains("Select"));
    }

}