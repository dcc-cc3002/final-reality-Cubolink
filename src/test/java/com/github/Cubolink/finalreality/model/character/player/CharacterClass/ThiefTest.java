package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest extends AbstractCharacterClassTest {
    private Thief thiefTest;

    @BeforeEach
    void setUp(){
        knife = new Knife("Cuchillo", 10, 3);
        thiefTest = new Thief();
        thiefTest.equip(knife);
    }

    @Test
    void equip() {
        thiefTest = new Thief();
        equipTestSetUp();

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
        Thief same_thief = new Thief();
        same_thief.equip(thiefTest.getEquippedWeapon());

        Thief sameClass_diffWeapon = new Thief();
        Knife other_knife = new Knife("Navaja", 10, 1);
        sameClass_diffWeapon.equip(other_knife);

        ICharacterClass other_character_class = new Knight();
        other_character_class.equip(thiefTest.getEquippedWeapon());

        checkEquals(thiefTest, same_thief, sameClass_diffWeapon, other_character_class);
    }

    @Test
    void testHashCode() {
        Thief same_thief = new Thief();
        same_thief.equip(thiefTest.getEquippedWeapon());

        Thief sameClass_diffWeapon = new Thief();
        Knife other_knife = new Knife("Navaja", 10, 1);
        sameClass_diffWeapon.equip(other_knife);

        ICharacterClass other_character_class = new Knight();
        other_character_class.equip(thiefTest.getEquippedWeapon());

        checkHashCode(thiefTest, same_thief, sameClass_diffWeapon, other_character_class);
    }

}