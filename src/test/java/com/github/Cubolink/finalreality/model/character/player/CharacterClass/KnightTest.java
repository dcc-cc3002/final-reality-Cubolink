package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Knight knightTest;
    private GenericWeapon sword;
    private ICharacter opponent;

    @BeforeEach
    void setUp(){
        sword = new Sword("Espada", 15, 10);
        knightTest = new Knight("Caballero", sword);
    }

    @Test
    void equip() {
        knightTest = new Knight("Caballero");

        GenericWeapon axe = new Axe("Hacha", 20, 15);
        GenericWeapon staff = new Staff("Báculo", 5, 20, 6);
        GenericWeapon bow = new Bow("Arco de Hierro", 15, 6);
        GenericWeapon knife = new Knife("Cuchillito", 10, 3);
        GenericWeapon sword = new Sword("Espada", 15, 10);
        GenericWeapon genericWeapon = new GenericWeapon("Piedra", 5, 1);

        knightTest.equip(genericWeapon);
        assertNull(knightTest.getEquippedWeapon());

        knightTest.equip(staff);
        assertNull(knightTest.getEquippedWeapon());

        knightTest.equip(bow);
        assertNull(knightTest.getEquippedWeapon());

        knightTest.equip(sword);
        assertEquals(knightTest.getEquippedWeapon(), sword);

        knightTest.equip(axe);
        assertEquals(knightTest.getEquippedWeapon(), axe);

        knightTest.equip(knife);
        assertEquals(knightTest.getEquippedWeapon(), knife);
    }

    @Test
    void testEquals() {
        Sword other_sword = new Sword("Espadon", 10, 25);
        Knight other_knight = new Knight("Burrero");
        Knight same_class_name_same_weapon = new Knight(
                knightTest.getClassname(), knightTest.getEquippedWeapon());
        Knight same_class_name_diff_weapon = new Knight(
                knightTest.getClassname(), other_sword);
        ICharacterClass other_character_class = new Thief("Lanza", knightTest.getEquippedWeapon());

        assertEquals(knightTest, knightTest);
        assertNotEquals(knightTest, other_knight);
        assertEquals(knightTest, same_class_name_same_weapon);
        assertEquals(knightTest, same_class_name_diff_weapon);
        assertNotEquals(knightTest, other_character_class);
    }

    @Test
    void testHashCode() {
        Sword other_sword = new Sword("Espadon", 10, 25);
        Knight other_knigth = new Knight("Dark Knight");
        Knight same_class_name_same_weapon = new Knight(
                knightTest.getClassname(), knightTest.getEquippedWeapon());
        Knight same_class_name_diff_weapon = new Knight(
                knightTest.getClassname(), other_sword);
        ICharacterClass other_character_class = new Thief("Lanza", knightTest.getEquippedWeapon());

        assertEquals(knightTest.hashCode(), knightTest.hashCode());
        assertNotEquals(knightTest.hashCode(), other_knigth.hashCode());
        assertEquals(knightTest.hashCode(), same_class_name_same_weapon.hashCode());
        assertEquals(knightTest.hashCode(), same_class_name_diff_weapon.hashCode());
        assertNotEquals(knightTest.hashCode(), other_character_class.hashCode());
    }
}