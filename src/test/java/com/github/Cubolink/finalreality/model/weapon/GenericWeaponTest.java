package com.github.Cubolink.finalreality.model.weapon;

import com.github.Cubolink.finalreality.model.statuseffects.Burned;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericWeaponTest {
    private GenericWeapon genericWeaponTest;

    @BeforeEach
    public void setUp(){
        genericWeaponTest = new GenericWeapon("piedra", 3, 1);
    }

    @Test
    void testEquals() {
        var alteredWeapon = new GenericWeapon(
                genericWeaponTest.getName(), genericWeaponTest.getPhysicalDamage()+1, genericWeaponTest.getWeight());
        var sameWeapon = new GenericWeapon(
                genericWeaponTest.getName(), genericWeaponTest.getPhysicalDamage(), genericWeaponTest.getWeight());
        var otherWeapon = new Axe("Hacha", 15, 15);

        assertEquals(genericWeaponTest, genericWeaponTest);
        assertNotEquals(alteredWeapon, genericWeaponTest);
        assertEquals(sameWeapon, genericWeaponTest);
        assertNotEquals(otherWeapon, genericWeaponTest);
    }

    @Test
    void testHashCode() {
        var alteredWeapon = new GenericWeapon(
                genericWeaponTest.getName(), genericWeaponTest.getPhysicalDamage()+1, genericWeaponTest.getWeight());
        var sameWeapon = new GenericWeapon(
                genericWeaponTest.getName(), genericWeaponTest.getPhysicalDamage(), genericWeaponTest.getWeight());
        var otherWeapon = new Axe(genericWeaponTest.getName(), genericWeaponTest.getPhysicalDamage(), genericWeaponTest.getWeight());
        var otherThing = new Burned(15);

        assertEquals(genericWeaponTest.hashCode(), genericWeaponTest.hashCode());
        assertNotEquals(alteredWeapon.hashCode(), genericWeaponTest.hashCode());
        assertEquals(sameWeapon.hashCode(), genericWeaponTest.hashCode());
        assertNotEquals(otherWeapon.hashCode(), genericWeaponTest.hashCode());
        assertNotEquals(otherThing.hashCode(), genericWeaponTest.hashCode());
    }
}