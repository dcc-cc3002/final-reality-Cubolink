package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AxeTest {
    private AbstractWeapon axeTest;

    @BeforeEach
    void setUp(){
        axeTest = new Axe("Hacha", 15, 5);
    }

    @Test
    void testEquals() {

        assertEquals(axeTest, axeTest);

        var sword = new Sword("Espada de madera", 3, 5);
        var axe2 = new Axe("Hacha sin filo", 0, 5);
        var axe3 = new Axe(axeTest.getName(), axeTest.getPhysicalDamage(), axeTest.getWeight());
        var axe4 = new Axe(axeTest.getName(), axeTest.getPhysicalDamage()+10, axeTest.getWeight());

        assertNotEquals(axeTest, sword);
        assertNotEquals(axeTest, axe2);
        assertEquals(axeTest, axe3);
        assertNotEquals(axeTest, axe4);

    }

    @Test
    void testHashCode() {
        assertEquals(axeTest.hashCode(), axeTest.hashCode());

        var sword = new Sword(axeTest.getName(), axeTest.getPhysicalDamage(), axeTest.getWeight());

        assertNotEquals(sword.hashCode(), axeTest.hashCode());

        var axe2 = new Axe("Hacha sin filo", 0, 5);
        assertNotEquals(axeTest.hashCode(), axe2.hashCode());

        var axe3 = new Axe(axeTest.getName(), axeTest.getPhysicalDamage()+10, axeTest.getWeight());
        assertNotEquals(axeTest.hashCode(), axe3.hashCode());

        var axe4 = new Axe(axeTest.getName(), axeTest.getPhysicalDamage(), axeTest.getWeight());
        assertEquals(axeTest.hashCode(), axe4.hashCode());

    }
}