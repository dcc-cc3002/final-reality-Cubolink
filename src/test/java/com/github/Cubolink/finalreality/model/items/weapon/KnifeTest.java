package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Knife;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnifeTest extends AbstractWeaponTest{
    private Knife knifeTest;

    @BeforeEach
    void setUp(){
        knifeTest = new Knife("Cuchilla", 8, 5);
    }

    @Test
    void testEquals() {
        var same_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());
        var other_knife = new Knife("Bisturi", 12, 2);
        var sword = new Sword("Espada de madera", 3, 5);

        checkEquals(knifeTest, same_knife, other_knife, sword);

        ramificationEqualsTest(knifeTest, Knife::new);
    }

    @Test
    void testHashCode() {
        var same_knife = new Knife(knifeTest.getName(), knifeTest.getPhysicalDamage(), knifeTest.getWeight());
        var other_knife = new Knife("Bisturi", 12, 2);
        var sword = new Sword("Espada de madera", 3, 5);

        checkHashCode(knifeTest, same_knife, other_knife, sword);
    }
}