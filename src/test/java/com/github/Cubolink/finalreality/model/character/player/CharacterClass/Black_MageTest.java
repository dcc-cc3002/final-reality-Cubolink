package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class Black_MageTest{
    //private LinkedBlockingQueue turns;
    //private List<ICharacter> testCharacters;
    private Black_Mage blackMageTest;
    private ICharacter opponent;
    protected BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();

        GenericWeapon staff = new Staff("Báculo", 5, 20, 6);
        blackMageTest = new Black_Mage("Kuro mado-shi", staff);
        opponent = new Enemy(turns, "Enemigo", 40, 0, 0, 10, 5);
    }

    @Test
    void equip() {
        blackMageTest = new Black_Mage("Kuro mado-shi");

        GenericWeapon axe = new Axe("Hacha", 20, 15);
        GenericWeapon staff = new Staff("Báculo", 5, 20, 6);
        GenericWeapon bow = new Bow("Arco de Hierro", 15, 6);
        GenericWeapon knife = new Knife("Cuchillito", 10, 3);
        GenericWeapon sword = new Sword("Espada", 15, 10);
        GenericWeapon genericWeapon = new GenericWeapon("Piedra", 5, 1);

        blackMageTest.equip(axe);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(bow);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(knife);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(sword);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(genericWeapon);
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
        Black_Mage other_blackMage = new Black_Mage("Mago negro avanzado");
        Black_Mage same_class_name_same_weapon = new Black_Mage(
                blackMageTest.getClassname(),blackMageTest.getEquippedWeapon());
        Black_Mage same_class_name_diff_weapon = new Black_Mage(
                blackMageTest.getClassname(), other_staff);

        assertEquals(blackMageTest, blackMageTest);
        assertNotEquals(other_blackMage, blackMageTest);
        assertEquals(same_class_name_same_weapon, blackMageTest);
        assertEquals(same_class_name_diff_weapon, blackMageTest);
    }

    @Test
    void testHashCode() {
        Staff other_staff = new Staff("Vara", 10, 5, 5);
        Black_Mage other_blackMage = new Black_Mage("Mago negro avanzado");
        Black_Mage same_class_name_same_weapon = new Black_Mage(
                blackMageTest.getClassname(),blackMageTest.getEquippedWeapon());
        Black_Mage same_class_name_diff_weapon = new Black_Mage(
                blackMageTest.getClassname(), other_staff);

        assertEquals(blackMageTest.hashCode(), blackMageTest.hashCode());
        assertNotEquals(other_blackMage.hashCode(), blackMageTest.hashCode());
        assertEquals(same_class_name_same_weapon.hashCode(), blackMageTest.hashCode());
        assertEquals(same_class_name_diff_weapon.hashCode(), blackMageTest.hashCode());
    }
}