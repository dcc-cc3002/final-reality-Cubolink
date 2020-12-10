package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class White_MageTest extends AbstractCharacterClassTest {
    private White_Mage white_mageTest;
    private ICharacter opponent;
    protected BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();

        staff = new Staff("Báculo", 5, 20, 6);
        white_mageTest = new White_Mage();
        white_mageTest.equip(staff);

        opponent = new Enemy(turns, "Enemigo", 40, 0, 10, 5);
    }

    @Test
    void testConstruction(){
        ICharacterClass white_mage = new White_Mage();
        checkConstruction(white_mage, white_mageTest.getClassname());
        assertTrue(white_mageTest.getMana() > 0);
    }

    @Test
    void equip() {
        white_mageTest = new White_Mage();
        equipTestSetUp();

        white_mageTest.equip(knife);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(sword);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(axe);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(bow);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(staff);
        assertEquals(white_mageTest.getEquippedWeapon(), staff);
    }

    void wasteMana() {
        IPlayerCharacter powerfulPlayer =
                new PlayerCharacter(turns, "Saitama", 999, 999, 999, new Knight());

        int initialMana = white_mageTest.getMana();
        for (int i=0; i<initialMana; i++) {
            white_mageTest.poison(powerfulPlayer);
            white_mageTest.paralyze(powerfulPlayer);
            white_mageTest.cure(powerfulPlayer);
        }

    }

    @Test
    void notEnoughManaTest() {
        wasteMana();

        PlayerCharacter ally = new PlayerCharacter(turns, "AliadoRandom", 100, 0, 0,
                new Engineer());
        assertEquals(ally.getHp(), 100);
        opponent.attack(ally);
        assertTrue(ally.getHp() < 100);

        // Test Cure not healing
        int previousHp = ally.getHp();
        white_mageTest.cure(ally);
        assertEquals(previousHp, ally.getHp());

        // Test Poison not poisoning
        previousHp = opponent.getHp();
        white_mageTest.poison(opponent);
        opponent.applyStatuses();
        assertEquals(opponent.getHp(), previousHp);

        // Test Paralyze not paralyzing
        white_mageTest.paralyze(opponent);
        opponent.applyStatuses();
        assertEquals(opponent.getHp(), previousHp);

    }

    @Test
    void cure() {
        PlayerCharacter ally = new PlayerCharacter(turns, "AliadoRandom", 100, 0, 0,
                new Engineer());
        assertEquals(ally.getHp(), 100);
        opponent.attack(ally);
        opponent.attack(ally);
        opponent.attack(ally);
        opponent.attack(ally);
        assertEquals(ally.getHp(), 60);
        white_mageTest.cure(ally);
        assertEquals(ally.getHp(), 90);
        white_mageTest.cure(ally);
        assertEquals(ally.getHp(), 100);
    }

    @Test
    void poison() {
        white_mageTest.poison(opponent);
        int dmg = white_mageTest.getEquippedWeapon().getMagicalDamage()/3;
        opponent.applyStatuses();
        assertEquals(opponent.getHp(), opponent.getMaxHp()-dmg);
    }

    @Test
    void paralyze() {
        white_mageTest.paralyze(opponent);
        opponent.applyStatuses();
        assertFalse(opponent.isAttack_enabled());
    }

    @Test
    void testEquals() {
        ICharacterClass same_whiteMage = new White_Mage();
        same_whiteMage.equip(white_mageTest.getEquippedWeapon());

        ICharacterClass sameClass_diffWeapon = new White_Mage();
        IWeapon other_staff = new Staff("Vara", 10, 5, 5);
        sameClass_diffWeapon.equip(other_staff);

        ICharacterClass other_character_class = new Black_Mage();
        other_character_class.equip(white_mageTest.getEquippedWeapon());

        checkEquals(white_mageTest, same_whiteMage, sameClass_diffWeapon, other_character_class);
    }

    @Test
    void testHashCode() {
        ICharacterClass same_whiteMage = new White_Mage();
        same_whiteMage.equip(white_mageTest.getEquippedWeapon());

        ICharacterClass sameClass_diffWeapon = new White_Mage();
        IWeapon other_staff = new Staff("Vara", 10, 5, 5);
        sameClass_diffWeapon.equip(other_staff);

        ICharacterClass other_character_class = new Black_Mage();
        other_character_class.equip(white_mageTest.getEquippedWeapon());

        checkHashCode(white_mageTest, same_whiteMage, sameClass_diffWeapon, other_character_class);
    }
}