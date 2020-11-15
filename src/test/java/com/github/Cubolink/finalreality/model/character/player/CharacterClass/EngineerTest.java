package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineerTest extends AbstractCharacterClassTest{
    private Engineer engineerTest;

    @BeforeEach
    void setUp(){
        axe = new Axe("Hacha", 20, 15);
        engineerTest = new Engineer();
        engineerTest.equip(axe);
    }

    @Test
    void equipTest() {
        engineerTest = new Engineer();
        equipTestSetUp();

        engineerTest.equip(knife);
        assertNull(engineerTest.getEquippedWeapon());

        engineerTest.equip(sword);
        assertNull(engineerTest.getEquippedWeapon());

        engineerTest.equip(staff);
        assertNull(engineerTest.getEquippedWeapon());

        engineerTest.equip(axe);
        assertEquals(engineerTest.getEquippedWeapon(), axe);

        engineerTest.equip(bow);
        assertEquals(engineerTest.getEquippedWeapon(), bow);
    }

    @Test
    void testEquals() {
        Engineer same_engineer = new Engineer();
        same_engineer.equip(engineerTest.getEquippedWeapon());

        Engineer sameClass_diffWeapon = new Engineer();
        Axe other_axe = new Axe("Hacha de Lenador", 10, 55);
        sameClass_diffWeapon.equip(other_axe);

        ICharacterClass other_character_class = new Knight();
        other_character_class.equip(engineerTest.getEquippedWeapon());

        checkEquals(engineerTest, same_engineer, sameClass_diffWeapon, other_character_class);
    }

    @Test
    void testHashCode() {
        Engineer same_engineer = new Engineer();
        same_engineer.equip(engineerTest.getEquippedWeapon());

        Engineer sameClass_diffWeapon = new Engineer();
        Axe other_axe = new Axe("Hacha de Lenador", 10, 55);
        sameClass_diffWeapon.equip(other_axe);

        ICharacterClass other_character_class = new Knight();
        other_character_class.equip(engineerTest.getEquippedWeapon());

        checkHashCode(engineerTest, same_engineer, sameClass_diffWeapon, other_character_class);
    }
}