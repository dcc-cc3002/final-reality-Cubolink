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

        Thread.sleep(10);
        // going to the attack phase and get back shouldn't alter anything
        gameController.next();
        Thread.sleep(10);
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.prev();
        Thread.sleep(10);
        assertTrue(gameController.getCurrentCharacter().isPlayable());

        // going to the equip phase and get back shouldn't alter anything
        gameController.moveCursorRight();
        Thread.sleep(10);
        gameController.next();
        Thread.sleep(10);
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.prev();
        Thread.sleep(10);
        assertTrue(gameController.getCurrentCharacter().isPlayable());

        // going to the waiting phase should change the current character
        gameController.moveCursorRight();
        Thread.sleep(10);
        gameController.moveCursorRight();
        Thread.sleep(10);
        gameController.next();
        Thread.sleep(100);
        assertFalse(gameController.getCurrentCharacter().isPlayable());
    }

}