package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectWeaponPhaseTest {
    IGameController gameController = new GameController();

    @BeforeEach
    void setUp() throws InterruptedException {
        gameController.createKnightPlayer();
        gameController.createSilverAxe();
        gameController.createEnemy();
        gameController.start();

        Thread.sleep(5000);

        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.moveCursorRight();
        gameController.next();


    }

    @Test
    void playerSelectWeaponTest() {
        assertNull(((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon());

        // check when we equip the weapon, our character doesn't ends its turn
        gameController.next();
        assertTrue(gameController.getCurrentCharacter().isPlayable());

        // check the weapon was equipped
        assertNotNull((((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon()));

        // check nothing strange happens when we try to equip from an empty weapon list
        gameController.moveCursorRight();
        gameController.next();
        gameController.next();
        gameController.prev();
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        assertNotNull((((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon()));

    }

}