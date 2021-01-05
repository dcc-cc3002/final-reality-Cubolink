package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndGamePhaseTest {
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
    void endGameTransitionsTest() {
        gameController.getEnemyList().get(0).receiveDamage(99999);
        gameController.getAliveCharactersList();
        assertTrue(gameController.isTheGameFinished());

        new EndGamePhase(gameController);
        gameController.next();
        gameController.next();
        assertTrue(gameController.isTheGameFinished());
        gameController.prev();
        gameController.prev();
        assertTrue(gameController.isTheGameFinished());
    }

    @Test
    void informationTest() {
        EndGamePhase dummyEndGamePhase = new EndGamePhase(new GameController());
        // check we don't have multiple options written in a waiting phase
        assertTrue(dummyEndGamePhase.getPhaseOptions().length <= 1);

        // check the word wait is in the information
        assertTrue(dummyEndGamePhase.getPhaseInfo().contains("over") || dummyEndGamePhase.getPhaseInfo().contains("Over"));
    }
}