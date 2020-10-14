package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Knight;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest extends AbstractCharacterTest{
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        testCharacters = new ArrayList<>();
        testCharacters.add(new Enemy(turns, "Slime", 30, 10, 30, 10, 10));

    }

    @Test
    void checkConstructor(){
        String name = "Slime";
        int maxHp = 30, defense = 10, resistance = 10, attack_damage=10;
        double weight = 10;

        Enemy enemy = new Enemy(turns, name, maxHp, defense, resistance, attack_damage, weight);
        assertEquals(name, enemy.getName());
        assertEquals(maxHp, enemy.getHp());
        assertEquals(defense, enemy.defense);
        assertEquals(resistance, enemy.resistance);
        assertEquals(weight, enemy.getWeight());

    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    public void waitTurnTest() {
        assertTrue(turns.isEmpty());

        testCharacters.get(0).waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            assertEquals(0, turns.size());
            Thread.sleep(200);
            assertEquals(1, turns.size());
            assertEquals(testCharacters.get(0), turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void damageTest(){
        int e1_Hp=30, e1_atk=10, e1_def=10;
        int dmg = 20;
        Enemy enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, 10, e1_atk, 12);
        enemy1.receiveDamage(dmg);
        assertEquals(enemy1.getHp(), e1_Hp-dmg);

        enemy1.receiveDamage(enemy1.getHp()-1);
        assertEquals(enemy1.getHp(), 1);

        assertTrue(enemy1.isAlive());
        enemy1.receiveDamage(1000);
        assertEquals(enemy1.getHp(), 0);
        assertFalse(enemy1.isAlive());


    }

    @Test
    void attack() {
        int p_Hp = 60, p_def = 5;
        int e1_Hp=30, e1_atk=10, e1_def=10;
        int e2_Hp=30, e2_atk=15, e2_def=15;

        Enemy enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, 10, e1_atk, 12);
        Enemy enemy2 = new Enemy(turns, "Goblin", e2_Hp, e2_def, 0, e2_atk, 12);
        ICharacter playerCharacter = new PlayerCharacter(turns, "Ark", p_Hp, p_def, 5,
                new Knight("Caballero del Zodiaco"));


        while (p_Hp-(e1_atk-p_def)>0){
            enemy1.attack(playerCharacter);
            assertEquals(playerCharacter.getHp(), p_Hp-(e1_atk-p_def));
            p_Hp = playerCharacter.getHp();
        }
        enemy1.attack(playerCharacter);
        assertEquals(playerCharacter.getHp(), 0);

    }

    @Test
    void testEquals() {
        int e1_Hp=30, e1_atk=10, e1_def=10, e1_res=10, e1_weight=12;
        int e2_Hp=30, e2_atk=15, e2_def=15, e2_res=0, e2_weight=15;
        var enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_res, e1_atk, e1_weight);
        var enemy1copy = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_res, e1_atk, e1_weight);
        var enemy2 = new Enemy(turns, "Goblin", e2_Hp, e2_def, e2_res, e2_atk, e2_weight);
        ICharacter playerCharacter = new PlayerCharacter(turns, "Ark", 45, 5, 5,
                new Knight("Caballero del Zodiaco"));

        assertEquals(enemy1, enemy1);
        assertEquals(enemy1, enemy1copy);
        assertNotEquals(enemy1, enemy2);
        assertNotEquals(enemy1, playerCharacter);

    }

    @Test
    void testHashCode() {
        int e1_Hp=30, e1_atk=10, e1_def=10, e1_res=10, e1_weight=12;
        int e2_Hp=30, e2_atk=15, e2_def=15, e2_res=0, e2_weight=15;
        var enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_res, e1_atk, e1_weight);
        var enemy1copy = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_res, e1_atk, e1_weight);
        var enemy2 = new Enemy(turns, "Goblin", e2_Hp, e2_def, e2_res, e2_atk, e2_weight);
        ICharacter playerCharacter = new PlayerCharacter(turns, "Ark", 45, 5, 5,
                new Knight("Caballero del Zodiaco"));

        assertEquals(enemy1.hashCode(), enemy1.hashCode());
        assertEquals(enemy1.hashCode(), enemy1copy.hashCode());
        assertNotEquals(enemy1.hashCode(), enemy2.hashCode());
        assertNotEquals(enemy1.hashCode(), playerCharacter.hashCode());
    }

}