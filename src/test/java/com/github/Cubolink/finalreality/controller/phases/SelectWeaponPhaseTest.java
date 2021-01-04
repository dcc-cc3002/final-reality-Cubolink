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

        Thread.sleep(3000);

        assertTrue(gameController.getCurrentCharacter().isPlayable());
        gameController.moveCursorRight();
        gameController.next();


    }

    @Test
    void playerSelectWeaponTest() throws InterruptedException {
        assertNull(((IPlayerCharacter) gameController.getCurrentCharacter()).getEquippedWeapon());

        // check when we equip the weapon, our character doesn't ends its turn
        gameController.next();
        assertTrue(gameController.getCurrentCharacter().isPlayable());
        Thread.sleep(100);

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

    @Test
    void informationTest() throws InterruptedException {
        GameController gameController = new GameController();
        gameController.createKnightPlayer();
        gameController.createBlackMagePlayer();
        gameController.createEnemy();
        gameController.createBronzeBow();
        gameController.createIronSword();
        gameController.createBronzeAxe();
        gameController.start();
        Thread.sleep(3000);

        SelectWeaponPhase dummySelectWeaponPhase = new SelectWeaponPhase(gameController, null);

        // check we have as many options as the size of the weaponList
        assertEquals(gameController.getWeaponList().size(), dummySelectWeaponPhase.getPhaseOptions().length);
        // even when we add more weapons
        gameController.createSilverAxe();
        assertEquals(gameController.getWeaponList().size(), dummySelectWeaponPhase.getPhaseOptions().length);
        // or we add weapons that were on the inventory
        gameController.createBronzeBow();
        assertEquals(gameController.getWeaponList().size(), dummySelectWeaponPhase.getPhaseOptions().length);

        // check the word weapon is in the information
        assertTrue(dummySelectWeaponPhase.getPhaseInfo().contains("Weapon") || dummySelectWeaponPhase.getPhaseInfo().contains("weapon"));
    }

}