package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

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

    // An interface for using weapon constructor as lambda functions
    interface IWeaponConstructor {
        IWeapon get(String name, int physicalDamage, double weight);
    }
    // Generates and compares multiple variation branches of a weapon
    protected void ramificationEqualsTest(final IWeapon expectedWeapon, IWeaponConstructor weaponConstructor) {
        IWeapon newExpectedWeapon = weaponConstructor.get(expectedWeapon.getName()+"0",
                expectedWeapon.getPhysicalDamage(),
                expectedWeapon.getWeight() + 0);

        IWeapon weaponVariation;

        // we make binary combinations, 000, 001, 010, 011, ..., 111.
        // and we add those bits to each constructor parameter
        // then we generate all ramifications for a weapon variation

        for (int i = 0; i<8; i++){
            weaponVariation = weaponConstructor.get(expectedWeapon.getName() + ((i / 4) % 2),
                                            expectedWeapon.getPhysicalDamage() + ((i / 2) % 2),
                                            expectedWeapon.getWeight() + ((i % 2)));
            if (i == 0){
                assertEquals(newExpectedWeapon, weaponVariation);
            } else {
                assertNotEquals(newExpectedWeapon, weaponVariation);
            }
        }
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