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

        var notStaff = new GenericWeapon("piedra", 3, 1);
        var rod = new Staff("Baculo", 5, 20, 5);
        var altered_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                            staffTest.getMagicalDamage()+3, staffTest.getWeight());
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());

        assertNotEquals(notStaff, staffTest);
        assertNotEquals(rod, staffTest);
        assertNotEquals(altered_staff, staffTest);
        assertEquals(same_staff, staffTest);
    }

    @Test
    void testHashCode() {
        assertEquals(staffTest.hashCode(), staffTest.hashCode());

        var notStaff = new GenericWeapon("piedra", 3, 1);
        var rod = new Staff("Baculo", 5, 20, 5);
        var altered_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage()+3, staffTest.getWeight());
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());

        assertNotEquals(notStaff.hashCode(), staffTest.hashCode());
        assertNotEquals(rod.hashCode(), staffTest.hashCode());
        assertNotEquals(altered_staff.hashCode(), staffTest.hashCode());
        assertEquals(same_staff.hashCode(), staffTest.hashCode());
    }

}