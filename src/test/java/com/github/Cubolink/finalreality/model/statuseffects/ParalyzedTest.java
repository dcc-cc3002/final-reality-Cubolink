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
        Enemy enemy = new Enemy(turns, "Talus", enemy_hp, 30, 4, 10);
        PlayerCharacter player =
                new PlayerCharacter(turns, "Lyshithea", player_hp, 2, 10, new Black_Mage());
        player.equip(new Staff("Baculo", 3, 15, 5));
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
    void testAlmostEquals(){
        var sameParalyzed = new Paralyzed();
        var oldParalyzed = new Paralyzed();
        IStatus otherEffect = new Poisoned(15);

        assertTrue(paralyzedTest.almostEquals(paralyzedTest));
        assertTrue(paralyzedTest.almostEquals(sameParalyzed));
        assertTrue(paralyzedTest.almostEquals(oldParalyzed));
        assertFalse(paralyzedTest.almostEquals(otherEffect));
    }

    @Test
    void testEquals() {
        var sameParalyzed = new Paralyzed();
        var oldParalyzed = new Paralyzed();
        Enemy enemy = new Enemy(turns, "Talus", 50, 30, 4, 10);
        oldParalyzed.effect(enemy);  // oldParalyzed has its turns to disappear in 0
        IStatus otherEffect = new Poisoned(15);

        assertEquals(paralyzedTest, paralyzedTest);
        assertEquals(paralyzedTest, sameParalyzed);
        assertNotEquals(paralyzedTest, oldParalyzed);
        assertNotEquals(paralyzedTest, otherEffect);
    }

    @Test
    void testHashCode() {
        var sameParalyzed = new Paralyzed();
        var oldParalyzed = new Paralyzed();
        Enemy enemy = new Enemy(turns, "Talus", 50, 30, 4, 10);
        oldParalyzed.effect(enemy);  // oldParalyzed has its turns to disappear in 0
        IStatus otherEffect = new Poisoned(15);

        assertEquals(paralyzedTest.hashCode(), paralyzedTest.hashCode());
        assertEquals(paralyzedTest.hashCode(), sameParalyzed.hashCode());
        assertNotEquals(paralyzedTest.hashCode(), oldParalyzed.hashCode());
        assertNotEquals(paralyzedTest.hashCode(), otherEffect.hashCode());
    }
}