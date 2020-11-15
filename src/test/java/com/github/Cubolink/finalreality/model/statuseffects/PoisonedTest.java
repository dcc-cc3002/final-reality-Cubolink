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

class PoisonedTest {
    private Poisoned poisonedTest;
    private final int magic_damage = 10;
    private BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        poisonedTest = new Poisoned(magic_damage);
    }

    @Test
    void testEffect(){
        int enemy_hp = 50;
        int player_hp = 25;
        Enemy enemy = new Enemy(turns, "Talus", enemy_hp, 30, 4, 10);
        PlayerCharacter player =
                new PlayerCharacter(turns, "Lyshithea", player_hp, 2, 10, new Black_Mage());
        player.equip(new Staff("Baculo", 3, 15, 5));

        enemy.addStatus(poisonedTest);
        player.addStatus(poisonedTest);

        while(enemy_hp>0){
            assertEquals(enemy_hp, enemy.getHp());
            enemy.applyStatuses();
            enemy_hp-=magic_damage/3;
            if(enemy_hp<0) enemy_hp=0;
        }
        assertEquals(enemy_hp, enemy.getHp());
        assertFalse(enemy.isAlive());
        while(player_hp>0){
            assertEquals(player_hp, player.getHp());
            player.applyStatuses();
            player_hp-=magic_damage/3;
            if(player_hp<0) player_hp=0;
        }
        assertEquals(player_hp, player.getHp());
        assertFalse(player.isAlive());
    }

    @Test
    void testAlmostEquals(){
        var majorPoisoned = new Poisoned(25);
        var samePoisoned = new Poisoned(magic_damage);
        IStatus otherStatus = new Burned(magic_damage);

        assertTrue(poisonedTest.almostEquals(poisonedTest));
        assertTrue(poisonedTest.almostEquals(majorPoisoned));
        assertTrue(poisonedTest.almostEquals(samePoisoned));
        assertFalse(poisonedTest.almostEquals(otherStatus));
    }

    @Test
    void testEquals() {
        var majorPoisoned = new Poisoned(25);
        var samePoisoned = new Poisoned(magic_damage);
        IStatus otherStatus = new Burned(magic_damage);

        assertEquals(poisonedTest, poisonedTest);
        assertNotEquals(poisonedTest, majorPoisoned);
        assertEquals(poisonedTest, samePoisoned);
        assertNotEquals(poisonedTest, otherStatus);
    }

    @Test
    void testHashCode() {
        var majorPoisoned = new Poisoned(25);
        var samePoisoned = new Poisoned(magic_damage);
        IStatus otherStatus = new Burned(magic_damage);

        assertEquals(poisonedTest.hashCode(), poisonedTest.hashCode());
        assertNotEquals(poisonedTest.hashCode(), majorPoisoned.hashCode());
        assertEquals(poisonedTest.hashCode(), samePoisoned.hashCode());
        assertNotEquals(poisonedTest.hashCode(), otherStatus.hashCode());

    }
}