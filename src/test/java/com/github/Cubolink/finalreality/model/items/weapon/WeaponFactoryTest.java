package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponFactoryTest {
    IWeaponFactory weaponFactoryTest = new WeaponFactory();
    IWeapon bronzeAxe;
    IWeapon ironAxe;
    IWeapon steelAxe;
    IWeapon silverAxe;

    IWeapon bronzeBow;
    IWeapon ironBow;
    IWeapon steelBow;
    IWeapon silverBow;

    IWeapon bronzeSword;
    IWeapon ironSword;
    IWeapon steelSword;
    IWeapon silverSword;

    IWeapon bronzeKnife;
    IWeapon ironKnife;
    IWeapon steelKnife;
    IWeapon silverKnife;

    IWeapon normalStaff;

    @BeforeEach
    void setUp() {
        bronzeAxe = weaponFactoryTest.createBronzeAxe();
        ironAxe = weaponFactoryTest.createIronAxe();
        steelAxe = weaponFactoryTest.createSteelAxe();
        silverAxe = weaponFactoryTest.createSilverAxe();

        bronzeBow = weaponFactoryTest.createBronzeBow();
        ironBow = weaponFactoryTest.createIronBow();
        steelBow = weaponFactoryTest.createSteelBow();
        silverBow = weaponFactoryTest.createSilverBow();

        bronzeSword = weaponFactoryTest.createBronzeSword();
        ironSword = weaponFactoryTest.createIronSword();
        steelSword = weaponFactoryTest.createSteelSword();
        silverSword = weaponFactoryTest.createSilverSword();

        bronzeKnife = weaponFactoryTest.createBronzeKnife();
        ironKnife = weaponFactoryTest.createIronKnife();
        steelKnife = weaponFactoryTest.createSteelKnife();
        silverKnife = weaponFactoryTest.createSilverKnife();

        normalStaff = weaponFactoryTest.createNormalStaff();
    }

    void physicalDamageHierarchyTest(IWeapon[] orderedByPDamageWeapons){
        for (int i=0; i<orderedByPDamageWeapons.length - 1; i++) {
            assertTrue(orderedByPDamageWeapons[i].getPhysicalDamage() <= orderedByPDamageWeapons[i+1].getPhysicalDamage());
        }
    }

    void putInOrder(IWeapon[] array, IWeapon weapon0, IWeapon weapon1, IWeapon weapon2, IWeapon weapon3) {
        array[0] = weapon0;
        array[1] = weapon1;
        array[2] = weapon2;
        array[3] = weapon3;
    }

    @Test
    void hierarchyTest() {
        IWeapon[] orderedWeapons = new IWeapon[4];

        putInOrder(orderedWeapons, bronzeAxe, ironAxe, steelAxe, silverAxe);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, bronzeBow, ironBow, steelBow, silverBow);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, bronzeKnife, ironKnife, steelKnife, silverKnife);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, bronzeSword, ironSword, steelSword, silverSword);
        physicalDamageHierarchyTest(orderedWeapons);

        // check weapon hierarchy

        putInOrder(orderedWeapons, normalStaff, bronzeKnife, bronzeBow, bronzeSword);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, bronzeKnife, bronzeBow, bronzeSword, bronzeAxe);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, ironKnife, ironBow, ironSword, ironAxe);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, steelKnife, steelBow, steelSword, steelAxe);
        physicalDamageHierarchyTest(orderedWeapons);

        putInOrder(orderedWeapons, silverKnife, silverBow, silverSword, silverAxe);
        physicalDamageHierarchyTest(orderedWeapons);



    }

    @Test
    void creationTest() {
        assertEquals(bronzeAxe, weaponFactoryTest.createBronzeAxe());
        assertEquals(ironAxe, weaponFactoryTest.createIronAxe());
        assertEquals(steelAxe, weaponFactoryTest.createSteelAxe());
        assertEquals(silverAxe, weaponFactoryTest.createSilverAxe());

        assertEquals(bronzeBow, weaponFactoryTest.createBronzeBow());
        assertEquals(ironBow, weaponFactoryTest.createIronBow());
        assertEquals(steelBow, weaponFactoryTest.createSteelBow());
        assertEquals(silverBow, weaponFactoryTest.createSilverBow());

        assertEquals(bronzeSword, weaponFactoryTest.createBronzeSword());
        assertEquals(ironSword, weaponFactoryTest.createIronSword());
        assertEquals(steelSword, weaponFactoryTest.createSteelSword());
        assertEquals(silverSword, weaponFactoryTest.createSilverSword());

        assertEquals(bronzeKnife, weaponFactoryTest.createBronzeKnife());
        assertEquals(ironKnife, weaponFactoryTest.createIronKnife());
        assertEquals(steelKnife, weaponFactoryTest.createSteelKnife());
        assertEquals(silverKnife, weaponFactoryTest.createSilverKnife());

        assertEquals(normalStaff, weaponFactoryTest.createNormalStaff());
    }

    @Test
    void testEqualsAndHashCode() {
        IWeaponFactory otherWeaponFactoryTest = new WeaponFactory();
        assertEquals(weaponFactoryTest, weaponFactoryTest);
        assertEquals(weaponFactoryTest, otherWeaponFactoryTest);

        assertEquals(weaponFactoryTest.hashCode(), weaponFactoryTest.hashCode());
        assertEquals(weaponFactoryTest.hashCode(), otherWeaponFactoryTest.hashCode());

    }


}