package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AxeTest extends AbstractWeaponTest{
    private AbstractWeapon axeTest;

    @BeforeEach
    void setUp(){
        axeTest = new Axe("Hacha", 15, 5);
    }

    @Test
    void testEquals() {
        var sword = new Sword("Espada de madera", 3, 5);
        var same_axe = new Axe(axeTest.getName(), axeTest.getPhysicalDamage(), axeTest.getWeight());
        var other_axe = new Axe("Hacha sin filo", 0, 5);

        checkEquals(axeTest, same_axe, other_axe, sword);

    }

    @Test
    void testHashCode() {
        var sword = new Sword("Espada de madera", 3, 5);
        var same_axe = new Axe(axeTest.getName(), axeTest.getPhysicalDamage(), axeTest.getWeight());
        var other_axe = new Axe("Hacha sin filo", 0, 5);

        checkHashCode(axeTest, same_axe, other_axe, sword);

    }
}