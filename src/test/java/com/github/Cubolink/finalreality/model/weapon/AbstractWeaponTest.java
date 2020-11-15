package com.github.Cubolink.finalreality.model.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWeaponTest {
    IWeapon weapon;

    @Test
    public void check(){
        String name = "Zangetsu";
        int physical_damage = 10;
        int weight = 2;
        weapon  = new Sword(name, physical_damage, weight);

        assertEquals(weapon.getName(), name);
        assertEquals(weapon.getPhysicalDamage(), physical_damage);
        assertEquals(weapon.getMagicalDamage(), 0);
        assertEquals(weapon.getWeight(), weight);
    }
}