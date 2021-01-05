package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.items.weapon.IWeaponFactory;
import com.github.Cubolink.finalreality.model.items.weapon.WeaponFactory;
import com.github.Cubolink.finalreality.model.items.IItem;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventoryTest;
    private IItem itemTest1;
    private IItem itemTest2;
    private IItem itemTest3;
    private IItem itemTest4;

    @BeforeEach
    void setUp() {
        inventoryTest = new Inventory();
        IWeaponFactory weaponFactoryAux = new WeaponFactory();
        itemTest1 = weaponFactoryAux.createBronzeSword();
        itemTest2 = weaponFactoryAux.createIronAxe();
        itemTest3 = weaponFactoryAux.createIronBow();
        itemTest4 = weaponFactoryAux.createSilverBow();
    }

    @Test
    void storeItem() {
        // First we store an item one, then we check the item is stored and that the others aren't.
        // Then we store the next one
        assertTrue(inventoryTest.isEmpty());

        inventoryTest.storeItem(itemTest1);
        assertTrue(inventoryTest.getItemSet().contains(itemTest1));
        assertFalse(inventoryTest.getItemSet().contains(itemTest2));
        assertFalse(inventoryTest.getItemSet().contains(itemTest3));
        assertFalse(inventoryTest.getItemSet().contains(itemTest4));
        assertFalse(inventoryTest.isEmpty());

        inventoryTest.storeItem(itemTest2);
        assertTrue(inventoryTest.getItemSet().contains(itemTest1));
        assertTrue(inventoryTest.getItemSet().contains(itemTest2));
        assertFalse(inventoryTest.getItemSet().contains(itemTest3));
        assertFalse(inventoryTest.getItemSet().contains(itemTest4));
        assertFalse(inventoryTest.isEmpty());

        inventoryTest.storeItem(itemTest3);
        assertTrue(inventoryTest.getItemSet().contains(itemTest1));
        assertTrue(inventoryTest.getItemSet().contains(itemTest2));
        assertTrue(inventoryTest.getItemSet().contains(itemTest3));
        assertFalse(inventoryTest.getItemSet().contains(itemTest4));
        assertFalse(inventoryTest.isEmpty());

        inventoryTest.storeItem(itemTest4);
        assertTrue(inventoryTest.getItemSet().contains(itemTest1));
        assertTrue(inventoryTest.getItemSet().contains(itemTest2));
        assertTrue(inventoryTest.getItemSet().contains(itemTest3));
        assertTrue(inventoryTest.getItemSet().contains(itemTest4));
        assertFalse(inventoryTest.isEmpty());


        // Now we check that we can store multiple times an item
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);
        assertTrue(inventoryTest.getItemSet().contains(itemTest1));
    }

    @Test
    void takeItem() {
        // First we check that once we store an item, we can't take items that aren't stored
        // then we check that we can take the stored item once, and if we try to take it again we won't get it.
        assertTrue(inventoryTest.isEmpty());
        assertNull(inventoryTest.takeItem(itemTest1));

        inventoryTest.storeItem(itemTest1);
        assertFalse(inventoryTest.isEmpty());
        assertNull(inventoryTest.takeItem(itemTest2));
        assertNull(inventoryTest.takeItem(itemTest3));
        assertNull(inventoryTest.takeItem(itemTest4));

        assertEquals(itemTest1, inventoryTest.takeItem(itemTest1));
        assertNull(inventoryTest.takeItem(itemTest1));
        assertTrue(inventoryTest.isEmpty());


        // Now we check when we have multiple items that we can take them once but not twice

        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest2);
        inventoryTest.storeItem(itemTest3);
        inventoryTest.storeItem(itemTest4);
        assertFalse(inventoryTest.isEmpty());
        assertEquals(itemTest1, inventoryTest.takeItem(itemTest1));
        assertEquals(itemTest2, inventoryTest.takeItem(itemTest2));
        assertEquals(itemTest3, inventoryTest.takeItem(itemTest3));
        assertEquals(itemTest4, inventoryTest.takeItem(itemTest4));

        assertTrue(inventoryTest.isEmpty());
        assertNotEquals(itemTest1, inventoryTest.takeItem(itemTest1));
        assertNotEquals(itemTest2, inventoryTest.takeItem(itemTest2));
        assertNotEquals(itemTest3, inventoryTest.takeItem(itemTest3));
        assertNotEquals(itemTest4, inventoryTest.takeItem(itemTest4));

        // Now we check when we have stored multiple equal items,
        // we can take them multiple times until we've taken them all
        assertTrue(inventoryTest.isEmpty());
        for (int i = 0; i < 10; i++) {
            inventoryTest.storeItem(itemTest1);
        }
        IItem takenItem;
        for (int i = 0; i < 10; i++) {
            takenItem = inventoryTest.takeItem(itemTest1);

            assertEquals(itemTest1, takenItem);
            assertNotNull(takenItem);
        }
        takenItem = inventoryTest.takeItem(itemTest1);
        assertNotEquals(itemTest1, takenItem);
        assertNull(takenItem);
    }

    @Test
    void dropItem() {
        // First we check that we can't drop items that aren't stored.
        // then we check that we can drop the stored item.
        assertTrue(inventoryTest.isEmpty());
        inventoryTest.dropItem(itemTest1);

        inventoryTest.storeItem(itemTest1);
        assertFalse(inventoryTest.isEmpty());
        inventoryTest.dropItem(itemTest2);
        assertFalse(inventoryTest.isEmpty());
        inventoryTest.dropItem(itemTest1);
        assertTrue(inventoryTest.isEmpty());


        // Now we check when we have multiple items that we can take them once but not twice

        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest2);
        inventoryTest.storeItem(itemTest3);
        inventoryTest.storeItem(itemTest4);

        assertFalse(inventoryTest.isEmpty());

        inventoryTest.dropItem(itemTest1);
        assertFalse(inventoryTest.isEmpty());

        inventoryTest.dropItem(itemTest2);
        assertFalse(inventoryTest.isEmpty());

        inventoryTest.dropItem(itemTest3);
        assertFalse(inventoryTest.isEmpty());

        inventoryTest.dropItem(itemTest4);
        assertTrue(inventoryTest.isEmpty());

        // Now we check when we have stored multiple equal items,
        // we can drop them multiple times until we've taken them all


        for (int i = 0; i < 10; i++) {
            inventoryTest.storeItem(itemTest1);
        }
        for (int i = 0; i < 10; i++) {
            assertFalse(inventoryTest.isEmpty());

            inventoryTest.dropItem(itemTest1);
            inventoryTest.dropItem(itemTest2);
            inventoryTest.dropItem(itemTest3);
            inventoryTest.dropItem(itemTest4);
        }
        assertTrue(inventoryTest.isEmpty());
    }

    @Test
    void testEmptiness() {
        // Check initial emptiness
        assertTrue(inventoryTest.getItemSet().isEmpty());
        assertTrue(inventoryTest.isEmpty());

        // Check emptiness when storing different items, one of each
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest2);
        inventoryTest.storeItem(itemTest3);
        inventoryTest.storeItem(itemTest4);

        assertFalse(inventoryTest.getItemSet().isEmpty());
        assertFalse(inventoryTest.isEmpty());

        // Check emptiness when storing multiple items of one kind
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);
        inventoryTest.dropItem(itemTest2);
        inventoryTest.dropItem(itemTest3);
        inventoryTest.dropItem(itemTest4);

        assertFalse(inventoryTest.getItemSet().isEmpty());
        assertFalse(inventoryTest.isEmpty());

        // Checking emptiness after dropping or taking all
        inventoryTest.dropItem(itemTest1);
        inventoryTest.takeItem(itemTest1);
        inventoryTest.dropItem(itemTest1);

        assertTrue(inventoryTest.getItemSet().isEmpty());
        assertTrue(inventoryTest.isEmpty());

        // Checking emptiness when dropping or taking items when it was already empty
        inventoryTest.dropItem(itemTest1);
        inventoryTest.takeItem(itemTest2);
        inventoryTest.dropItem(itemTest3);
        inventoryTest.takeItem(itemTest4);

        assertTrue(inventoryTest.getItemSet().isEmpty());
        assertTrue(inventoryTest.isEmpty());
    }

    @Test
    void testGetWeaponList() {
        inventoryTest.storeItem(itemTest1);
        assertEquals(inventoryTest.getWeaponList().size(), 1);
        inventoryTest.storeItem(itemTest2);
        assertEquals(inventoryTest.getWeaponList().size(), 2);
        inventoryTest.storeItem(itemTest3);
        assertEquals(inventoryTest.getWeaponList().size(), 3);
        inventoryTest.storeItem(itemTest4);
        assertEquals(inventoryTest.getWeaponList().size(), 4);

        // store multiple times a weapon shouldn't alter our weapon list
        inventoryTest.storeItem(itemTest3);
        inventoryTest.storeItem(itemTest4);
        inventoryTest.storeItem(itemTest4);
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);
        inventoryTest.storeItem(itemTest1);

        assertEquals(inventoryTest.getWeaponList().size(), 4);

        // as we've stored only weapons, the weaponList should be equals to the item set
        Set<IItem> iSet = inventoryTest.getItemSet();
        List<IWeapon> weaponList = inventoryTest.getWeaponList();
        assertEquals(iSet.size(), weaponList.size());
        for (IItem item: iSet) {
            assertTrue(weaponList.contains(item));
        }


        // At last, all the weapons in the weaponList should be in the item set
        for (IWeapon weapon: weaponList) {
            assertTrue(iSet.contains(weapon));
        }
    }
}