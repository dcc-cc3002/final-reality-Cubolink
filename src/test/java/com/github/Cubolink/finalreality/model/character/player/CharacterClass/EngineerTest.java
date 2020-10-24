package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineerTest {
    private Engineer engineerTest;
    private GenericWeapon axe;
    private ICharacter opponent;

    @BeforeEach
    void setUp(){
        axe = new Axe("Hacha", 20, 15);
        engineerTest = new Engineer("Ingeniero", axe);
    }

    @Test
    void equip() {
        engineerTest = new Engineer("Kuro mado-shi");

        GenericWeapon axe = new Axe("Hacha", 20, 15);
        GenericWeapon staff = new Staff("Báculo", 5, 20, 6);
        GenericWeapon bow = new Bow("Arco de Hierro", 15, 6);
        GenericWeapon knife = new Knife("Cuchillito", 10, 3);
        GenericWeapon sword = new Sword("Espada", 15, 10);
        GenericWeapon genericWeapon = new GenericWeapon("Piedra", 5, 1);

        engineerTest.equip(knife);
        assertNull(engineerTest.getEquippedWeapon());

        engineerTest.equip(sword);
        assertNull(engineerTest.getEquippedWeapon());

        engineerTest.equip(genericWeapon);
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
        Axe other_axe = new Axe("Hacha de Lenador", 10, 55);
        Engineer other_engineer = new Engineer("Ingeniero con Magister");
        Engineer same_class_name_same_weapon = new Engineer(
                engineerTest.getClassname(), engineerTest.getEquippedWeapon());
        Engineer same_class_name_diff_weapon = new Engineer(
                engineerTest.getClassname(), other_axe);

        assertEquals(engineerTest, engineerTest);
        assertNotEquals(other_engineer, engineerTest);
        assertEquals(same_class_name_same_weapon, engineerTest);
        assertEquals(same_class_name_diff_weapon, engineerTest);
    }

    @Test
    void testHashCode() {
        Axe other_axe = new Axe("Hacha de lenador", 10, 55);
        Engineer other_engineer = new Engineer("Ingeniero con Magister");
        Engineer same_class_name_same_weapon = new Engineer(
                engineerTest.getClassname(), engineerTest.getEquippedWeapon());
        Engineer same_class_name_diff_weapon = new Engineer(
                engineerTest.getClassname(), other_axe);

        assertEquals(engineerTest.hashCode(), engineerTest.hashCode());
        assertNotEquals(other_axe.hashCode(), engineerTest.hashCode());
        assertEquals(same_class_name_same_weapon.hashCode(), engineerTest.hashCode());
        assertEquals(same_class_name_diff_weapon.hashCode(), engineerTest.hashCode());
    }
}