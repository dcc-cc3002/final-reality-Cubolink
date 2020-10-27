package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class White_MageTest {
    private White_Mage white_mageTest;
    private GenericWeapon staff;
    private ICharacter opponent;
    protected BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();

        staff = new Staff("Báculo", 5, 20, 6);
        white_mageTest = new White_Mage("Mago Blanco", staff);
        opponent = new Enemy(turns, "Enemigo", 40, 0, 10, 5);
    }

    @Test
    void equip() {
        white_mageTest = new White_Mage("Shiro mado-shi");

        GenericWeapon axe = new Axe("Hacha", 20, 15);
        GenericWeapon staff = new Staff("Báculo", 5, 20, 6);
        GenericWeapon bow = new Bow("Arco de Hierro", 15, 6);
        GenericWeapon knife = new Knife("Cuchillito", 10, 3);
        GenericWeapon sword = new Sword("Espada", 15, 10);
        GenericWeapon genericWeapon = new GenericWeapon("Piedra", 5, 1);

        white_mageTest.equip(knife);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(sword);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(genericWeapon);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(axe);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(bow);
        assertNull(white_mageTest.getEquippedWeapon());

        white_mageTest.equip(staff);
        assertEquals(white_mageTest.getEquippedWeapon(), staff);
    }

    @Test
    void cure() {
        PlayerCharacter ally = new PlayerCharacter(turns, "AliadoRandom", 100, 0, 0,
                new Engineer("Ingeniero"));
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
        int hp = opponent.getHp();
        int dmg = white_mageTest.getEquippedWeapon().getMagicalDamage()/3;
        opponent.applyStatuses();
        assertEquals(opponent.getHp(), opponent.getMaxHp()-dmg);
    }

    @Test
    void paralyze() {
        white_mageTest.paralyze(opponent);
        int hp = opponent.getHp();
        int dmg = white_mageTest.getEquippedWeapon().getMagicalDamage()/2;
        opponent.applyStatuses();
        assertFalse(opponent.isAttack_enabled());
    }

    @Test
    void testEquals() {
        Staff other_staff = new Staff("Vara", 10, 5, 5);
        White_Mage other_whiteMage = new White_Mage("Cleric");
        White_Mage same_class_name_same_weapon = new White_Mage(
                white_mageTest.getClassname(), white_mageTest.getEquippedWeapon());
        White_Mage same_class_name_diff_weapon = new White_Mage(
                white_mageTest.getClassname(), other_staff);
        ICharacterClass other_character_class = new Black_Mage("Mago Negro", white_mageTest.getEquippedWeapon());

        assertEquals(white_mageTest, white_mageTest);
        assertNotEquals(white_mageTest, other_whiteMage);
        assertEquals(white_mageTest, same_class_name_same_weapon);
        assertEquals(white_mageTest, same_class_name_diff_weapon);
        assertNotEquals(white_mageTest, other_character_class);
    }

    @Test
    void testHashCode() {
        Staff other_staff = new Staff("Vara", 10, 5, 5);
        White_Mage other_whiteMage = new White_Mage("Cleric");
        White_Mage same_class_name_same_weapon = new White_Mage(
                white_mageTest.getClassname(), white_mageTest.getEquippedWeapon());
        White_Mage same_class_name_diff_weapon = new White_Mage(
                white_mageTest.getClassname(), other_staff);
        ICharacterClass other_character_class = new Black_Mage("Mago Negro", white_mageTest.getEquippedWeapon());

        assertEquals(white_mageTest.hashCode(), white_mageTest.hashCode());
        assertNotEquals(white_mageTest.hashCode(), other_whiteMage.hashCode());
        assertEquals(white_mageTest.hashCode(), same_class_name_same_weapon.hashCode());
        assertEquals(white_mageTest.hashCode(), same_class_name_diff_weapon.hashCode());
        assertNotEquals(white_mageTest.hashCode(), other_character_class.hashCode());
    }
}