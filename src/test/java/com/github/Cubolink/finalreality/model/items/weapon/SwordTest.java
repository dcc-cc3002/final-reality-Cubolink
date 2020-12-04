package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Bow;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SwordTest extends AbstractWeaponTest{
    private Sword swordTest;

    @BeforeEach
    void setUp(){
        swordTest = new Sword("Espada", 20, 10);
    }

    @Test
    void testEquals() {
        var same_sword = new Sword(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());
        var other_sword = new Sword("Sable", 25, 15);
        var bow = new Bow(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());

        checkEquals(swordTest, same_sword, other_sword, bow);

        ramificationEqualsTest(swordTest, Sword::new);
    }

    @Test
    void testHashCode() {
        var same_sword = new Sword(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());
        var other_sword = new Sword("Sable", 25, 15);
        var bow = new Bow(swordTest.getName(), swordTest.getPhysicalDamage(), swordTest.getWeight());

        checkHashCode(swordTest, same_sword, other_sword, bow);
    }
}