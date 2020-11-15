package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnifeTest {
    private Knife knifeTest;

    @BeforeEach
    void setUp(){
        knifeTest = new Knife("Cuchilla", 8, 5);
    }

    @Test
    void testEquals() {

        assertEquals(knifeTest, knifeTest);

        var sword = new Sword("Espada de madera", 3, 5);
        var cooking_knife = new Knife("Cuchillo", 5, 2);
        var altered_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage()+5, knifeTest.getWeight());
        var same_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());
        var heavy_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight()+10);

        assertNotEquals(knifeTest, sword);
        assertNotEquals(knifeTest, cooking_knife);
        assertNotEquals(knifeTest, altered_knife);
        assertNotEquals(knifeTest, heavy_knife);
        assertEquals(knifeTest, same_knife);

    }

    @Test
    void testHashCode() {
        assertEquals(knifeTest.hashCode(), knifeTest.hashCode());

        var sword = new Sword("Espada de madera", 3, 5);
        var cooking_knife = new Knife("Cuchillo", 5, 2);
        var altered_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage()+5, knifeTest.getWeight());
        var same_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());

        assertNotEquals(knifeTest.hashCode(), sword.hashCode());
        assertNotEquals(knifeTest.hashCode(), cooking_knife.hashCode());
        assertNotEquals(knifeTest.hashCode(), altered_knife.hashCode());
        assertEquals(knifeTest.hashCode(), same_knife.hashCode());
    }
}