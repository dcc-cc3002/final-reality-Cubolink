package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Staff;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StaffTest extends AbstractWeaponTest{
    private Staff staffTest;

    @BeforeEach
    void setUp(){
        staffTest = new Staff("Vara", 3, 15, 3);
    }

    interface IWeaponConstructor {
        IWeapon get(String name, int physicalDamage, double weight);
    }

    @Test
    void testEquals() {
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());
        var other_staff = new Staff("Baculo", 5, 20, 5);
        var sword = new Sword("Espada de madera", 3, 5);

        checkEquals(staffTest, same_staff, other_staff, sword);

        IWeapon newExpectedWeapon = new Staff(staffTest.getName()+"0",
                staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(),
                staffTest.getWeight() + 0);
        IWeapon weaponVariation;

        for (int i = 0; i<16; i++){
            weaponVariation = new Staff(staffTest.getName() + ((i / 8) % 2),
                    staffTest.getPhysicalDamage() + ((i / 4) % 2),
                    staffTest.getMagicalDamage() + ((i / 2) % 2),
                    staffTest.getWeight() + ((i % 2)));
            if (i == 0){
                assertEquals(newExpectedWeapon, weaponVariation);
            } else {
                assertNotEquals(newExpectedWeapon, weaponVariation);
            }
        }
    }

    @Test
    void testHashCode() {
        var same_staff = new Staff(staffTest.getName(), staffTest.getPhysicalDamage(),
                staffTest.getMagicalDamage(), staffTest.getWeight());
        var other_staff = new Staff("Baculo", 5, 20, 5);
        var sword = new Sword("Espada de madera", 3, 5);

        checkHashCode(staffTest, same_staff, other_staff, sword);
    }

}