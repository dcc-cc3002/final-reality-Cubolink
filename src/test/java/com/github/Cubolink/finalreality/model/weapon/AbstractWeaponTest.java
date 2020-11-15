package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractWeaponTest {

    protected void checkEquals(final IWeapon expectedWeapon,
                                  final IWeapon equalWeapon,
                                  final IWeapon sameClassDifferentWeapon,
                                  final IWeapon differentClassWeapon){
        assertEquals(expectedWeapon, expectedWeapon);
        assertEquals(expectedWeapon, equalWeapon);
        assertNotEquals(expectedWeapon, sameClassDifferentWeapon);
        assertNotEquals(expectedWeapon, differentClassWeapon);

    }

    protected void checkHashCode(final IWeapon expectedWeapon,
                                 final IWeapon equalWeapon,
                                 final IWeapon sameClassDifferentWeapon,
                                 final IWeapon differentClassWeapon){
        assertEquals(expectedWeapon.hashCode(), expectedWeapon.hashCode());
        assertEquals(expectedWeapon.hashCode(), equalWeapon.hashCode());
        assertNotEquals(expectedWeapon.hashCode(), sameClassDifferentWeapon.hashCode());
        assertNotEquals(expectedWeapon.hashCode(), differentClassWeapon.hashCode());

    }
}