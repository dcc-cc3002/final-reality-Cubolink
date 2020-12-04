package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Bow;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowTest extends AbstractWeaponTest{
    private Bow bowTest;

    @BeforeEach
    void setUp(){
        bowTest = new Bow("Arco", 10, 5);
    }

    @Test
    void testEquals() {
        var samebow = new Bow(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());
        var other_bow = new Bow("Bayesta", 15, 10);
        var sword = new Sword("Espada de madera", 3, 5);

        checkEquals(bowTest, samebow, other_bow, sword);

        ramificationEqualsTest(bowTest, Bow::new);
    }

    @Test
    void testHashCode() {
        var samebow = new Bow(bowTest.getName(), bowTest.getPhysicalDamage(), bowTest.getWeight());
        var other_bow = new Bow("Bayesta", 15, 10);
        var sword = new Sword("Espada de madera", 3, 5);

        checkHashCode(bowTest, samebow, other_bow, sword);

    }
}