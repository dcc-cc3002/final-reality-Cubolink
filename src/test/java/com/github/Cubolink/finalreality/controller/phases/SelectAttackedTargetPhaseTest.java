package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectAttackedTargetPhaseTest {

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
    void playerSelectTargetTransitionTest() throws InterruptedException {
        // player attacks enemy
        gameController.next();
        gameController.moveCursorRight();
        gameController.next();

        // after attacking, the current character should have changed
        Thread.sleep(2000);
        assertFalse(gameController.getCurrentCharacter().isPlayable());

        // the enemy should have received the hit
        assertTrue(gameController.getCurrentCharacter().getHp() < gameController.getCurrentCharacter().getMaxHp());

    }

}