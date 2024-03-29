package com.github.Cubolink.finalreality.model.character.enemy;

import com.github.Cubolink.finalreality.model.character.AbstractCharacterTest;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Knight;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest extends AbstractCharacterTest {
    @BeforeEach
    void setUp() {
        preSetUp();
        testCharacters.add(new Enemy(turns, "Slime", 30, 10, 10, 10));

    }

    @Test
    void checkConstructor(){
        String name = "Slime";
        int maxHp = 30, defense = 10, attack_damage=10;
        double weight = 10;

        Enemy enemy = new Enemy(turns, name, maxHp, defense, attack_damage, weight);
        assertFalse(enemy.isPlayable());
        assertEquals(name, enemy.getName());
        assertEquals(maxHp, enemy.getHp());
        assertEquals(weight, enemy.getWeight());

    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    public void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void damageTest(){
        int e1_Hp=30, e1_atk=10, e1_def=10;
        int dmg = 20;
        Enemy enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, 12);
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
        int e3_Hp=30, e3_atk=999, e3_def=20;

        Enemy enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, 12);
        Enemy enemy2 = new Enemy(turns, "Goblin", e2_Hp, e2_def, e2_atk, 12);
        Enemy enemy3 = new Enemy(turns, "Orc", e3_Hp, e3_def, e3_atk, 12);
        ICharacter playerCharacter = new PlayerCharacter(turns, "Ark", p_Hp, p_def, 5,
                new Knight());


        while (p_Hp-(e1_atk-p_def)>0){
            assertTrue(playerCharacter.isAlive());
            enemy1.attack(playerCharacter);
            assertEquals(playerCharacter.getHp(), p_Hp-(e1_atk-p_def));
            p_Hp = playerCharacter.getHp();
        }
        assertTrue(playerCharacter.isAlive());
        enemy1.attack(playerCharacter);
        assertEquals(playerCharacter.getHp(), 0);
        assertFalse(playerCharacter.isAlive());

        // Try to attack while defeated

        int previousHp = enemy1.getHp();
        assertTrue(enemy1.isAlive());
        assertTrue(enemy2.isAlive());
        enemy2.attack(enemy1);  // 2 is able to attack 1
        assertTrue(enemy1.getHp() < previousHp);
        previousHp = enemy1.getHp();

        enemy2.receiveDamage(enemy2.getHp());  // 2 is defeated and now is unable to attack 1
        assertFalse(enemy2.isAlive());
        enemy2.attack(enemy1);
        assertEquals(previousHp, enemy1.getHp());

        // Try to attack when attack disabled
        assertTrue(enemy1.isAlive());
        assertTrue(enemy3.isAlive());
        enemy3.setAttack_enabled(false);
        previousHp = enemy1.getHp();
        enemy3.attack(enemy1);
        assertEquals(enemy1.getHp(), previousHp);
        assertTrue(enemy1.isAlive());


    }

    @Test
    void testEquals() {
        int e1_Hp=30, e1_atk=10, e1_def=5, e1_weight=12;
        int e2_Hp=30, e2_atk=15, e2_def=15, e2_weight=15;
        var enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, e1_weight);
        var enemy1copy = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, e1_weight);
        var enemy2 = new Enemy(turns, "Goblin", e2_Hp, e2_def, e2_atk, e2_weight);
        var enemyVarHp = new Enemy(turns, "Slime", e1_Hp+10, e1_def, e1_atk, e1_weight);
        var enemyVarDef = new Enemy(turns, "Slime", e1_Hp, e1_def+10, e1_atk, e1_weight);
        var enemyVarAtk = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk+10, e1_weight);
        var enemyVarWeight = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, e1_weight+10);
        ICharacter playerCharacter = new PlayerCharacter(turns, "Ark", 45, 5, 5,
                new Knight());

        assertEquals(enemy1, enemy1);
        assertEquals(enemy1, enemy1copy);
        enemy1.attack(enemy1copy);
        assertNotEquals(enemy1, enemy1copy);
        assertNotEquals(enemy1, enemy2);
        assertNotEquals(enemy1, enemyVarHp);
        assertNotEquals(enemy1, enemyVarDef);
        assertNotEquals(enemy1, enemyVarAtk);
        assertNotEquals(enemy1, enemyVarWeight);
        assertNotEquals(enemy1, playerCharacter);

    }

    @Test
    void testHashCode() {
        int e1_Hp=30, e1_atk=10, e1_def=10, e1_weight=12;
        int e2_Hp=30, e2_atk=15, e2_def=15, e2_weight=15;
        var enemy1 = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, e1_weight);
        var enemy1copy = new Enemy(turns, "Slime", e1_Hp, e1_def, e1_atk, e1_weight);
        var enemy2 = new Enemy(turns, "Goblin", e2_Hp, e2_def, e2_atk, e2_weight);
        ICharacter playerCharacter = new PlayerCharacter(turns, "Ark", 45, 5, 5,
                new Knight());

        assertEquals(enemy1.hashCode(), enemy1.hashCode());
        assertEquals(enemy1.hashCode(), enemy1copy.hashCode());
        assertNotEquals(enemy1.hashCode(), enemy2.hashCode());
        assertNotEquals(enemy1.hashCode(), playerCharacter.hashCode());
    }

}