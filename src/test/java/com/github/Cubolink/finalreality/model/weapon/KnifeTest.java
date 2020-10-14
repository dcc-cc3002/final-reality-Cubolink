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

        var notKnife = new GenericWeapon(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());
        var cooking_knife = new Knife("Cuchillo", 5, 2);
        var altered_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage()+5, knifeTest.getWeight());
        var same_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());
        var heavy_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight()+10);

        assertNotEquals(notKnife, knifeTest);
        assertNotEquals(cooking_knife, knifeTest);
        assertNotEquals(altered_knife, knifeTest);
        assertNotEquals(heavy_knife, knifeTest);
        assertEquals(same_knife, knifeTest);

    }

    @Test
    void testHashCode() {
        assertEquals(knifeTest.hashCode(), knifeTest.hashCode());

        var notKnife = new GenericWeapon("piedra", 3, 1);
        var cooking_knife = new Knife("Cuchillo", 5, 2);
        var altered_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage()+5, knifeTest.getWeight());
        var same_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());

        assertNotEquals(notKnife.hashCode(), knifeTest.hashCode());
        assertNotEquals(cooking_knife.hashCode(), knifeTest.hashCode());
        assertNotEquals(altered_knife.hashCode(), knifeTest.hashCode());
        assertEquals(same_knife.hashCode(), knifeTest.hashCode());
    }
}