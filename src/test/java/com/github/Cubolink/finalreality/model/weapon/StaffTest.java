package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {
    private Staff staffTest;

    @BeforeEach
    void setUp(){
        staffTest = new Staff("Vara", 3, 15, 3);
    }

    @Test
    void testEquals() {
        assertEquals(staffTest, staffTest);

        var sword = new Sword("Espada de madera", 3, 5);
        var rod = new Staff("Baculo", 5, 20, 5);
        var altered_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                            staffTest.getMagicalDamage()+3, staffTest.getWeight());
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());

        assertNotEquals(staffTest, sword);
        assertNotEquals(staffTest, rod);
        assertNotEquals(staffTest, altered_staff);
        assertEquals(staffTest, same_staff);
    }

    @Test
    void testHashCode() {
        assertEquals(staffTest.hashCode(), staffTest.hashCode());

        var sword = new Sword("Espada de madera", 3, 5);
        var rod = new Staff("Baculo", 5, 20, 5);
        var altered_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage()+3, staffTest.getWeight());
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());

        assertNotEquals(staffTest.hashCode(), sword.hashCode());
        assertNotEquals(staffTest.hashCode(), rod.hashCode());
        assertNotEquals(staffTest.hashCode(), altered_staff.hashCode());
        assertEquals(staffTest.hashCode(), same_staff.hashCode());
    }

}