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

class BurnedTest {
    private Burned burnedTest;
    private final int magic_damage = 10;
    private BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        burnedTest = new Burned(magic_damage);
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
        enemy.addStatus(burnedTest);
        player.addStatus(burnedTest);

        while(enemy_hp>0){
            assertEquals(enemy_hp, enemy.getHp());
            enemy.applyStatuses();
            enemy_hp-=magic_damage/2;
            if(enemy_hp<0) enemy_hp=0;
        }
        assertEquals(enemy_hp, enemy.getHp());
        assertFalse(enemy.isAlive());
        while(player_hp>0){
            assertEquals(player_hp, player.getHp());
            player.applyStatuses();
            player_hp-=magic_damage/2;
            if(player_hp<0) player_hp=0;
        }
        assertEquals(player_hp, player.getHp());
        assertFalse(player.isAlive());
    }

    @Test
    void testEquals() {
        var otherBurned = new Burned(15);
        var sameBurned = new Burned(magic_damage);
        IStatus otherStatus = new Paralyzed();

        assertEquals(burnedTest, burnedTest);
        assertNotEquals(otherBurned, burnedTest);
        assertEquals(sameBurned, burnedTest);
        assertNotEquals(otherStatus, burnedTest);
    }

    @Test
    void testHashCode() {
        var otherBurned = new Burned(15);
        var sameBurned = new Burned(10);
        IStatus otherStatus = new Paralyzed();

        assertEquals(burnedTest.hashCode(), burnedTest.hashCode());
        assertNotEquals(otherBurned.hashCode(), burnedTest.hashCode());
        assertEquals(sameBurned.hashCode(), burnedTest.hashCode());
        assertNotEquals(otherStatus.hashCode(), burnedTest.hashCode());
    }
}