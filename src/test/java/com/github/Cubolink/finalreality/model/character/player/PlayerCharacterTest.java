package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.AbstractCharacterTest;
import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Black_Mage;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Knight;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Thief;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.White_Mage;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.*;
import com.github.Cubolink.finalreality.model.statuseffects.Burned;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.statuseffects.Poisoned;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCharacterTest extends AbstractCharacterTest {

    @BeforeEach
    void setUp() {
        preSetUp();

        IPlayerCharacter character = new PlayerCharacter(turns, "Saitama", 9999, 400, 10,
                new Knight());
        character.equip(new Sword("Templed Sword", 20, 10));
        testCharacters.add(character);
    }

    @Test
    void checkConstructor(){
        String name = "Ark";
        int maxHp = 30, defense = 10, resistance = 0, attack_damage=10;
        double weight = 10;

        IPlayerCharacter playerCharacter2 = new PlayerCharacter(turns, name, maxHp, defense, resistance, new Knight());
        assertTrue(playerCharacter2.isPlayable());
        assertEquals(name, playerCharacter2.getName());
        assertEquals(maxHp, playerCharacter2.getMaxHp());
        assertEquals(maxHp, playerCharacter2.getHp());

    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    public void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void attack() {
        int atk = 15;
        int hp = 100, def=10, res=0;

        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 10, 5,
                new Thief());
        pcharact1.equip(new Bow("Arco", atk, 7));

        PlayerCharacter pcharact2 = new PlayerCharacter(turns, "Soren", 50, 5, 15,
                new Black_Mage());
        pcharact2.equip(new Staff("Baculo escrito", atk, atk*2, 4));

        ICharacter enemy = new Enemy(turns, "Malladus", hp, 10, 3, 12);
        ICharacter strongPlayer = new PlayerCharacter(turns, "Zelgius", hp, 99999, 555, new Knight());

        // Test character without an equipped weapon can't attack
        PlayerCharacter pcharactNoWeapon = new PlayerCharacter(turns, "Citizen", 10, 1, 1,
                new White_Mage());
        assertEquals(enemy.getHp(), hp);
        pcharactNoWeapon.attack(enemy);
        assertEquals(enemy.getHp(), hp);

        // Test normal physical attack to a strong player
        pcharact1.attack(strongPlayer);
        assertEquals(strongPlayer.getHp(), hp);
        // Test magical attack (fire) to a strong player
        ((Black_Mage) pcharact2.getCharacterClass()).fire(strongPlayer, new Random());
        assertEquals(strongPlayer.getHp(), hp);

        // Test normal physical attack
        pcharact1.attack(enemy);
        assertEquals(enemy.getHp(), hp - (pcharact1.getEquippedWeapon().getPhysicalDamage()-def));
        hp -= pcharact1.getEquippedWeapon().getPhysicalDamage()-def;
        // Test magical attack (fire)
        ((Black_Mage) pcharact2.getCharacterClass()).fire(enemy, new Random());
        assertEquals(enemy.getHp(), hp - (pcharact2.getEquippedWeapon().getMagicalDamage())-res);

        // Test trying to attack when is defeated
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

        // Test trying to attack when attack is disabled

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
                new Thief());
        pcharact1.equip(new Bow("Arco", 15, 7));
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
                new Thief());
        assertEquals(pcharact1.getWeight(), 0);

        pcharact1.equip(new Bow("Arco", 15, 7));
        assertEquals(pcharact1.getWeight(), pcharact1.getEquippedWeapon().getWeight());
    }

    @Test
    void testEquip() {
        PlayerCharacter pcharact1 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief());
        assertNull(pcharact1.getEquippedWeapon());
        pcharact1.equip(new Staff("Baculo", 5, 15, 5));
        assertNull(pcharact1.getEquippedWeapon());
        pcharact1.equip(new Knife("Cuchillo", 10, 3));
        assertEquals(new Knife("Cuchillo", 10, 3), pcharact1.getEquippedWeapon());

        // Try to equip when is defeated

        // Two different weapons that pcharact1 can equip
        IWeapon testWeapon = pcharact1.getEquippedWeapon();
        IWeapon testWeapon2 = new Knife("Bisturi", 20, 2);
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
                turns, "Boshy", 100, 3, 0, new Thief());

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
                new Thief());
        var pcharact1_copy = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief());
        var pcharact2 = new PlayerCharacter(turns, "Volke", 50, 7, 5,
                new Thief());
        var pcharact3 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief());
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
                new Thief());
        var pcharact1_copy = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief());
        var pcharact2 = new PlayerCharacter(turns, "Volke", 50, 7, 5,
                new Thief());
        var pcharact3 = new PlayerCharacter(turns, "Sothe", 50, 7, 5,
                new Thief());
        ICharacter enemy = new Enemy(turns, "Sothe", 50, 7, 3, 12);

        assertEquals(pcharact1.hashCode(), pcharact1.hashCode());
        assertEquals(pcharact1.hashCode(), pcharact1_copy.hashCode());
        assertNotEquals(pcharact1.hashCode(), pcharact2.hashCode());
        assertEquals(pcharact1.hashCode(), pcharact3.hashCode());
        assertNotEquals(pcharact1.hashCode(), enemy.hashCode());
    }
}