package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest extends AbstractCharacterClassTest{
    private Knight knightTest;

    @BeforeEach
    void setUp(){
        sword = new Sword("Espada", 15, 10);
        knightTest = new Knight();
        knightTest.equip(sword);
    }

    @Test
    void testConstruction(){
        ICharacterClass knight = new Knight();
        checkConstruction(knight, knightTest.getClassname());
    }

    @Test
    void equip() {
        knightTest = new Knight();
        equipTestSetUp();

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
        ICharacterClass same_knight = new Knight();
        same_knight.equip(knightTest.getEquippedWeapon());

        ICharacterClass sameClass_diffWeapon = new Knight();
        IWeapon other_sword = new Sword("Espadon", 10, 25);
        sameClass_diffWeapon.equip(other_sword);

        ICharacterClass other_character_class = new Thief();
        other_character_class.equip(knightTest.getEquippedWeapon());

        checkEquals(knightTest, same_knight, sameClass_diffWeapon, other_character_class);
    }

    @Test
    void testHashCode() {
        ICharacterClass same_knight = new Knight();
        same_knight.equip(knightTest.getEquippedWeapon());

        ICharacterClass sameClass_diffWeapon = new Knight();
        IWeapon other_sword = new Sword("Espadon", 10, 25);
        sameClass_diffWeapon.equip(other_sword);

        ICharacterClass other_character_class = new Thief();
        other_character_class.equip(knightTest.getEquippedWeapon());

        checkHashCode(knightTest, same_knight, sameClass_diffWeapon, other_character_class);
    }
}