package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaffTest extends AbstractWeaponTest{
    private Staff staffTest;

    @BeforeEach
    void setUp(){
        staffTest = new Staff("Vara", 3, 15, 3);
    }

    @Test
    void testEquals() {
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());
        var other_staff = new Staff("Baculo", 5, 20, 5);
        var sword = new Sword("Espada de madera", 3, 5);

        checkEquals(staffTest, same_staff, other_staff, sword);
    }

    @Test
    void testHashCode() {
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());
        var other_staff = new Staff("Baculo", 5, 20, 5);
        var sword = new Sword("Espada de madera", 3, 5);

        checkHashCode(staffTest, same_staff, other_staff, sword);
    }

}