package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhaseTransitionTest {
    IGameController gameController = new GameController();

    @BeforeEach
    void setUp() throws InterruptedException {
        gameController.createKnightPlayer();
        gameController.createSilverAxe();
        gameController.createEnemy();
        gameController.start();

        Thread.sleep(3000);


    }

    @Test
    void transitionTest() throws InterruptedException {
        assertTrue(gameController.thereAreCharactersWaiting());
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        // equip weapon
        gameController.moveCursorRight();
        gameController.next();

        assertTrue(gameController.getCurrentCharacter().isPlayable());
        assertNull(((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon());
        gameController.next();

        assertTrue(gameController.getCurrentCharacter().isPlayable());
        assertNotNull(((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon());

        // wait
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.moveCursorRight();
        gameController.moveCursorRight();
        gameController.next();
        assertNull(gameController.getCurrentCharacter());
        // attack until end
        while(!gameController.isTheGameFinished()) {
            Thread.sleep(100);
            if (gameController.getCurrentCharacter() != null) {
                if (gameController.getCurrentCharacter().isPlayable()) {
                    gameController.next();
                    Thread.sleep(100);
                } else {
                    assertTrue(gameController.inEnemyTurn());
                }
            }
            gameController.next();

        }
    }
}