package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest {
    private Thief thiefTest;
    private GenericWeapon knife;
    private ICharacter opponent;

    @BeforeEach
    void setUp(){
        knife = new Knife("Cuchillo", 10, 3);
        thiefTest = new Thief("Ladron", knife);
    }

    @Test
    void equip() {
        thiefTest = new Thief("Lanza");

        GenericWeapon axe = new Axe("Hacha", 20, 15);
        GenericWeapon staff = new Staff("Báculo", 5, 20, 6);
        GenericWeapon bow = new Bow("Arco de Hierro", 15, 6);
        GenericWeapon knife = new Knife("Cuchillito", 10, 3);
        GenericWeapon sword = new Sword("Espada", 15, 10);
        GenericWeapon genericWeapon = new GenericWeapon("Piedra", 5, 1);

        thiefTest.equip(genericWeapon);
        assertNull(thiefTest.getEquippedWeapon());

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

        assertEquals(thiefTest, thiefTest);
        assertNotEquals(other_thief, thiefTest);
        assertEquals(same_class_name_same_weapon, thiefTest);
        assertEquals(same_class_name_diff_weapon, thiefTest);
    }

    @Test
    void testHashCode() {
        Knife other_knife = new Knife("Navaja", 10, 1);
        Thief other_thief = new Thief("Lanza");
        Thief same_class_name_same_weapon = new Thief(
                thiefTest.getClassname(), thiefTest.getEquippedWeapon());
        Thief same_class_name_diff_weapon = new Thief(
                thiefTest.getClassname(), other_knife);

        assertEquals(thiefTest.hashCode(), thiefTest.hashCode());
        assertNotEquals(other_thief.hashCode(), thiefTest.hashCode());
        assertEquals(same_class_name_same_weapon.hashCode(), thiefTest.hashCode());
        assertEquals(same_class_name_diff_weapon.hashCode(), thiefTest.hashCode());
    }

}