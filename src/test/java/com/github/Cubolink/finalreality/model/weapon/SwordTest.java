package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
    private Sword swordTest;

    @BeforeEach
    void setUp(){
        swordTest = new Sword("Espada", 20, 10);
    }

    @Test
    void testEquals() {
        assertEquals(swordTest, swordTest);

        var notSword = new GenericWeapon(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());
        var sable = new Sword("Sable", 25, 15);
        var altered_sword = new Sword(swordTest.getName(), swordTest.getPhysicalDamage()-10, swordTest.getWeight());
        var same_sword = new Sword(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());

        assertNotEquals(notSword, swordTest);
        assertNotEquals(sable, swordTest);
        assertNotEquals(altered_sword, swordTest);
        assertEquals(same_sword, swordTest);

    }

    @Test
    void testHashCode() {
        assertEquals(swordTest.hashCode(), swordTest.hashCode());

        var notSword = new GenericWeapon("piedra", 3, 1);
        var sable = new Sword("Sable", 25, 15);
        var altered_sword = new Sword(swordTest.getName(), swordTest.getPhysicalDamage()-10, swordTest.getWeight());
        var same_sword = new Sword(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());

        assertNotEquals(notSword.hashCode(), swordTest.hashCode());
        assertNotEquals(sable.hashCode(), swordTest.hashCode());
        assertNotEquals(altered_sword.hashCode(), swordTest.hashCode());
        assertEquals(same_sword.hashCode(), swordTest.hashCode());
    }
}