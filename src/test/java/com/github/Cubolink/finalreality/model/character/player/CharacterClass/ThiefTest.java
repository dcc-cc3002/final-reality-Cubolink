package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest {
    private Thief thiefTest;
    private IWeapon knife;

    @BeforeEach
    void setUp(){
        knife = new Knife("Cuchillo", 10, 3);
        thiefTest = new Thief("Ladron", knife);
    }

    @Test
    void equip() {
        thiefTest = new Thief("Lanza");

        IWeapon axe = new Axe("Hacha", 20, 15);
        IWeapon staff = new Staff("Báculo", 5, 20, 6);
        IWeapon bow = new Bow("Arco de Hierro", 15, 6);
        IWeapon sword = new Sword("Espada", 15, 10);

        thiefTest.equip(staff);
        assertNull(thiefTest.getEquippedWeapon());

        thiefTest.equip(axe);
        assertNull(thiefTest.getEquippedWeapon());

        thiefTest.equip(bow);
        assertEquals(thiefTest.getEquippedWeapon(), bow);

        thiefTest.equip(sword);
        assertEquals(thiefTest.getEquippedWeapon(), sword);

        thiefTest.equip(knife);
        assertEquals(thiefTest.getEquippedWeapon(), knife);
    }

    @Test
    void testEquals() {
        Knife other_knife = new Knife("Navaja", 10, 1);
        Thief other_thief = new Thief("Lanza");
        Thief same_class_name_same_weapon = new Thief(
                thiefTest.getClassname(), thiefTest.getEquippedWeapon());
        Thief same_class_name_diff_weapon = new Thief(
                thiefTest.getClassname(), other_knife);
        ICharacterClass other_character_class = new Knight("Cabashero", thiefTest.getEquippedWeapon());

        assertEquals(thiefTest, thiefTest);
        assertNotEquals(thiefTest, other_thief);
        assertEquals(thiefTest, same_class_name_same_weapon);
        assertEquals(thiefTest, same_class_name_diff_weapon);
        assertNotEquals(thiefTest, other_character_class);
    }

    @Test
    void testHashCode() {
        Knife other_knife = new Knife("Navaja", 10, 1);
        Thief other_thief = new Thief("Lanza");
        Thief same_class_name_same_weapon = new Thief(
                thiefTest.getClassname(), thiefTest.getEquippedWeapon());
        Thief same_class_name_diff_weapon = new Thief(
                thiefTest.getClassname(), other_knife);
        ICharacterClass other_character_class = new Knight("Cabashero", thiefTest.getEquippedWeapon());

        assertEquals(thiefTest.hashCode(), thiefTest.hashCode());
        assertNotEquals(thiefTest.hashCode(), other_thief.hashCode());
        assertEquals(thiefTest.hashCode(), same_class_name_same_weapon.hashCode());
        assertEquals(thiefTest.hashCode(), same_class_name_diff_weapon.hashCode());
        assertNotEquals(thiefTest.hashCode(), other_character_class.hashCode());
    }

}