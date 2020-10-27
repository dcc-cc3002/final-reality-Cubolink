package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.AbstractCharacterTest;
import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Black_Mage;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Knight;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Thief;
import com.github.Cubolink.finalreality.model.statuseffects.Burned;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.statuseffects.Poisoned;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCharacterTest extends AbstractCharacterTest {
    private BlockingQueue<ICharacter> turns;
    private List<PlayerCharacter> testCharacters;
    private GenericWeapon testWeapon;

    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        testCharacters = new ArrayList<>();
        testCharacters.add(new PlayerCharacter(turns, "Saitama", 9999, 400, 10,
                new Knight("Caballero")));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    public void waitTurnTest() {
        assertTrue(turns.isEmpty());

        testCharacters.get(0).equip(new Sword("Templed Sword", 20, 10));
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
    void attack() {
        int atk = 15;
        int hp = 100, def=10, res=0;

        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 10, 5,
                new Thief("Picaro", new Bow("Arco", atk, 7)));

        Black_Mage class2 = new Black_Mage("Hechicero",
                new Staff("Baculo escrito", atk, atk*2, 4));
        PlayerCharacter pcharact2 = new PlayerCharacter(turns, "Soren", 50, 5, 15,
                class2);

        ICharacter enemy = new Enemy(turns, "Malladus", hp, 10, 3, 12);


        assertEquals(enemy.getHp(), hp);
        pcharact1.attack(enemy);
        assertEquals(enemy.getHp(), hp - (pcharact1.getEquippedWeapon().getPhysicalDamage()-def));
        hp -= pcharact1.getEquippedWeapon().getPhysicalDamage()-def;

        ((Black_Mage) pcharact2.getCharacterClass()).fire(enemy);
        assertEquals(enemy.getHp(), hp - (pcharact2.getEquippedWeapon().getMagicalDamage())-res);

        // Try to attack when is defeated

        assertTrue(pcharact1.isAlive());
        assertTrue(pcharact2.isAlive());
        assertTrue(pcharact1.isAttack_enabled());
        assertTrue(pcharact2.isAttack_enabled());
        int previousHp2 = pcharact2.getHp();
        assertTrue(previousHp2>0);
        // attack pcharact1 to death
        while (pcharact1.isAlive()){
            pcharact2.attack(pcharact1);
        }
        assertTrue(pcharact1.isAttack_enabled());
        assertFalse(pcharact1.isAlive());
        assertEquals(pcharact1.getHp(), 0);
        // pcharact1, who is defeated, tries to attack
        pcharact1.attack(pcharact2);
        assertEquals(pcharact2.getHp(), previousHp2);

         // Try to attack when attack is disabled

        pcharact2.setAttack_enabled(false);
        assertTrue(enemy.isAlive());
        previousHp2 = enemy.getHp();
        pcharact2.attack(enemy);
        assertEquals(enemy.getHp(), previousHp2);
        pcharact2.setAttack_enabled(true);
        pcharact2.attack(enemy);
        assertTrue(enemy.getHp() < previousHp2);

    }

    @Test
    void heal() {
        int maxhp = 50;
        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", maxhp, 7, 5,
                new Thief("Picaro", new Bow("Arco", 15, 7)));
        assertEquals(pcharact1.getHp(), maxhp);
        pcharact1.receiveDamage(maxhp /2);
        pcharact1.heal(maxhp /4);
        assertEquals(pcharact1.getHp(), 3* maxhp /4);
        pcharact1.heal(10*maxhp);
        assertEquals(pcharact1.getHp(), maxhp);

        pcharact1.receiveDamage(maxhp);
        assertFalse(pcharact1.isAlive());
        pcharact1.heal(10*maxhp);
        assertFalse(pcharact1.isAlive());
        assertEquals(pcharact1.getHp(), 0);
    }

    @Test
    void getWeight() {
        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Picaro", new Bow("Arco", 15, 7)));
        assertEquals(pcharact1.getWeight(), pcharact1.getEquippedWeapon().getWeight());
    }

    @Test
    void testEquip() {
        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Picaro"));
        assertNull(pcharact1.getEquippedWeapon());
        pcharact1.equip(new Staff("Baculo", 5, 15, 5));
        assertNull(pcharact1.getEquippedWeapon());
        pcharact1.equip(new Knife("Cuchillo", 10, 3));
        assertEquals(new Knife("Cuchillo", 10, 3), pcharact1.getEquippedWeapon());

        // Try to equip when is defeated

        // Two different weapons that pcharact1 can equip
        testWeapon = pcharact1.getEquippedWeapon();
        GenericWeapon testWeapon2 = new Knife("Bisturi", 20, 2);
        assertNotEquals(testWeapon, testWeapon2);
        pcharact1.equip(testWeapon2);
        assertEquals(pcharact1.getEquippedWeapon(), testWeapon2);
        pcharact1.equip(testWeapon);
        assertEquals(pcharact1.getEquippedWeapon(), testWeapon);
        assertNotEquals(pcharact1.getEquippedWeapon(), testWeapon2);
        // Kill pcharact1
        assertTrue(pcharact1.isAlive());
        pcharact1.receiveDamage(pcharact1.getHp());
        assertFalse(pcharact1.isAlive());
        // Now he shouldn't be able to change its weapon
        pcharact1.equip(testWeapon2);
        assertNotEquals(pcharact1.getEquippedWeapon(), testWeapon2);
    }

    @Test
    void testAddStatus(){
        ICharacter playerCharacter = new PlayerCharacter(
                turns, "Boshy", 100, 3, 0, new Thief("Pistolero"));

        assertTrue(playerCharacter.isAttack_enabled());
        assertEquals(playerCharacter.getHp(), 100);
        playerCharacter.addStatus(new Burned(10));
        playerCharacter.addStatus(new Poisoned(15));
        playerCharacter.addStatus(new Paralyzed());
        playerCharacter.applyStatuses();

        assertFalse(playerCharacter.isAttack_enabled());
        assertEquals(playerCharacter.getHp(), 90);  // 100-(10/2)-(15/3)
        playerCharacter.addStatus(new Burned(10));  // burned & poisoned are equals to existent status
        playerCharacter.addStatus(new Poisoned(15));  // so they shouldn't be added to the statuses list
        playerCharacter.addStatus(new Paralyzed());  // paralyzed should overwrite, because now we have one more turn
        playerCharacter.applyStatuses();

        assertFalse(playerCharacter.isAttack_enabled());
        assertEquals(playerCharacter.getHp(), 80);  // 90-(10/2)-(15/3)
        playerCharacter.addStatus(new Burned(0));  // burned shouldn't overwrite, because is 'minor' than the previous burned
        playerCharacter.addStatus(new Paralyzed());  // paralyzed once again should overwrite
        playerCharacter.addStatus(new Poisoned(45));  // poisoned should overwrite, because is 'greaterThan'
        playerCharacter.applyStatuses();

        assertFalse(playerCharacter.isAttack_enabled());
        assertEquals(playerCharacter.getHp(), 60);  // 80-(10/2)-(45/3)
        playerCharacter.addStatus(new Burned(20));
        // we don't overwrite paralyze, to check if it will go out in the next
        playerCharacter.addStatus(new Poisoned(60));
        playerCharacter.applyStatuses();

        assertTrue(playerCharacter.isAttack_enabled());  // now paralyzed should have gone
        assertEquals(playerCharacter.getHp(), 30);  // 60-(20/2)-(60/3)
        var burned_copy = new Burned(20);
        burned_copy.disable_effect(playerCharacter);
        var poisoned_copy = new Poisoned(60);
        poisoned_copy.disable_effect(playerCharacter);
        playerCharacter.applyStatuses();

        assertEquals(playerCharacter.getHp(), 30);  // there shouldn't be status altering the hp




    }

    @Test
    void testEquals() {
        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Picaro"));
        var pcharact1_copy = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Picaro"));
        var pcharact2 = new PlayerCharacter(turns, "Volke", 50, 7, 5,
                new Thief("Picaro"));
        var pcharact3 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Ladron"));
        ICharacter enemy = new Enemy(turns, "Sothe", 50, 7, 3, 12);

        assertEquals(pcharact1, pcharact1);
        assertEquals(pcharact1, pcharact1_copy);
        assertNotEquals(pcharact1, pcharact2);
        assertEquals(pcharact1, pcharact3);
        assertNotEquals(pcharact1, enemy);

    }

    @Test
    void testHashCode() {
        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Picaro"));
        var pcharact1_copy = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Picaro"));
        var pcharact2 = new PlayerCharacter(turns, "Volke", 50, 7, 5,
                new Thief("Picaro"));
        var pcharact3 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief("Ladron"));
        ICharacter enemy = new Enemy(turns, "Sothe", 50, 7, 3, 12);

        assertEquals(pcharact1.hashCode(), pcharact1.hashCode());
        assertEquals(pcharact1.hashCode(), pcharact1_copy.hashCode());
        assertNotEquals(pcharact1.hashCode(), pcharact2.hashCode());
        assertEquals(pcharact1.hashCode(), pcharact3.hashCode());
        assertNotEquals(pcharact1.hashCode(), enemy.hashCode());
    }
}