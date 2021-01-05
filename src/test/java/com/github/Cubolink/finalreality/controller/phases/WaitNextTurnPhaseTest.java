package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitNextTurnPhaseTest {
    IGameController gameController = new GameController();

    @BeforeEach
    void setUp() throws InterruptedException {
        gameController.createKnightPlayer();
        gameController.createSilverAxe();
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
    void waitTurnTransitionsTest() throws InterruptedException {
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.next();
        gameController.next();
        // check that even if we next() fast, we actually don't get a new character until they are ready, so we don't change phase
        assertNull(gameController.getCurrentCharacter());
        gameController.next();
        gameController.next();
        assertNull(gameController.getCurrentCharacter());

        // check prev() doesn't do anything in waiting phases
        gameController.prev();
        assertNull(gameController.getCurrentCharacter());

        // chek after a while, we have a current character
        Thread.sleep(3000);
        assertNotNull(gameController.getCurrentCharacter());
    }

    @Test
    void informationTest() {
        WaitNextTurnPhase dummyWaitNextTurnPhase = new WaitNextTurnPhase(new GameController());
        // check we don't have multiple options written in a waiting phase
        assertTrue(dummyWaitNextTurnPhase.getPhaseOptions().length <= 1);

        // check the word wait is in the information
        assertTrue(dummyWaitNextTurnPhase.getPhaseInfo().contains("wait") || dummyWaitNextTurnPhase.getPhaseInfo().contains("Wait"));
    }

}