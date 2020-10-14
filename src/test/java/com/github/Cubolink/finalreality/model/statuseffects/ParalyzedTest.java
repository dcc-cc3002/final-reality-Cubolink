package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Black_Mage;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ParalyzedTest {
    private Paralyzed paralyzedTest;
    private BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        paralyzedTest = new Paralyzed();
    }

    @Test
    void testEffect(){
        int enemy_hp = 50;
        int player_hp = 25;
        Enemy enemy = new Enemy(turns, "Talus", enemy_hp, 30, 0, 4, 10);
        PlayerCharacter player =
                new PlayerCharacter(turns, "Lyshithea", player_hp, 2, 10,
                        new Black_Mage("Mago",
                                new Staff("Baculo", 3, 15, 5)));
        enemy.addStatus(paralyzedTest);

        assertTrue(enemy.isAttack_enabled());
        enemy.applyStatuses();
        assertFalse(enemy.isAttack_enabled());
        enemy.applyStatuses();
        assertTrue(enemy.isAttack_enabled());

        paralyzedTest = new Paralyzed();
        player.addStatus(paralyzedTest);

        assertTrue(player.isAttack_enabled());
        player.applyStatuses();
        assertFalse(player.isAttack_enabled());
        player.applyStatuses();
        assertTrue(player.isAttack_enabled());
    }

    @Test
    void testEquals() {
        var sameParalyzed = new Paralyzed();
        var oldParalyzed = new Paralyzed();
        IStatus otherEffect = new Poisoned(15);

        assertEquals(paralyzedTest, paralyzedTest);
        assertEquals(sameParalyzed, paralyzedTest);

        assertNotEquals(otherEffect, paralyzedTest);
    }

    @Test
    void testHashCode() {
        var sameParalyzed = new Paralyzed();
        var oldParalyzed = new Paralyzed();
        IStatus otherEffect = new Poisoned(15);

        assertEquals(paralyzedTest.hashCode(), paralyzedTest.hashCode());
        assertEquals(sameParalyzed.hashCode(), paralyzedTest.hashCode());
        assertNotEquals(otherEffect.hashCode(), paralyzedTest.hashCode());
    }
}