package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class Black_MageTest{
    //private LinkedBlockingQueue turns;
    //private List<ICharacter> testCharacters;
    private Black_Mage blackMageTest;
    private ICharacter opponent;
    private IWeapon staff;
    protected BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();

        staff = new Staff("Báculo", 5, 20, 6);
        blackMageTest = new Black_Mage("Kuro mado-shi", staff);
        opponent = new Enemy(turns, "Enemigo", 40, 0, 10, 5);
    }

    @Test
    void equip() {
        blackMageTest = new Black_Mage("Kuro mado-shi");

        IWeapon axe = new Axe("Hacha", 20, 15);
        IWeapon staff = new Staff("Báculo", 5, 20, 6);
        IWeapon bow = new Bow("Arco de Hierro", 15, 6);
        IWeapon knife = new Knife("Cuchillito", 10, 3);
        IWeapon sword = new Sword("Espada", 15, 10);

        blackMageTest.equip(axe);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(bow);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(knife);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(sword);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(staff);
        assertEquals(blackMageTest.getEquippedWeapon(), staff);
    }

    @Test
    void thunder() {
        int op_hp = opponent.getHp();
        blackMageTest.thunder(opponent);
        assertEquals(opponent.getHp(), op_hp-blackMageTest.getEquippedWeapon().getMagicalDamage());
    }

    @Test
    void fire() {
        int op_hp = opponent.getHp();
        blackMageTest.fire(opponent);
        assertEquals(opponent.getHp(), op_hp-blackMageTest.getEquippedWeapon().getMagicalDamage());
    }

    @Test
    void testEquals() {
        Staff other_staff = new Staff("Vara", 10, 5, 5);
        Black_Mage other_blackMage = new Black_Mage("Mago negro avanzado", staff);
        Black_Mage same_class_name_same_weapon = new Black_Mage(
                blackMageTest.getClassname(),blackMageTest.getEquippedWeapon());
        Black_Mage same_class_name_diff_weapon = new Black_Mage(
                blackMageTest.getClassname(), other_staff);
        ICharacterClass other_character_class = new White_Mage("Mago blanco", blackMageTest.getEquippedWeapon());

        assertEquals(blackMageTest, blackMageTest);
        assertNotEquals(blackMageTest, other_blackMage);
        assertEquals(blackMageTest, same_class_name_same_weapon);
        assertEquals(blackMageTest, same_class_name_diff_weapon);
        assertNotEquals(blackMageTest, other_character_class);
    }

    @Test
    void testHashCode() {
        Staff other_staff = new Staff("Vara", 10, 5, 5);
        Black_Mage other_blackMage = new Black_Mage("Mago negro avanzado", staff);
        Black_Mage same_class_name_same_weapon = new Black_Mage(
                blackMageTest.getClassname(),blackMageTest.getEquippedWeapon());
        Black_Mage same_class_name_diff_weapon = new Black_Mage(
                blackMageTest.getClassname(), other_staff);
        ICharacterClass other_character_class = new White_Mage("Mago blanco", blackMageTest.getEquippedWeapon());

        assertEquals(blackMageTest.hashCode(), blackMageTest.hashCode());
        assertNotEquals(blackMageTest.hashCode(), other_blackMage.hashCode());
        assertEquals(blackMageTest.hashCode(), same_class_name_same_weapon.hashCode());
        assertEquals(blackMageTest.hashCode(), same_class_name_diff_weapon.hashCode());
        assertNotEquals(blackMageTest.hashCode(), other_character_class.hashCode());
    }
}