package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.*;

import java.util.Objects;

/**
 * A Weapon Factory. Creates weapon objects.
 */
public final class WeaponFactory implements IWeaponFactory {
    // Material String constants names
    private static final String BRONZE_NAME = "Bronze";
    private static final String IRON_NAME = "Iron";
    private static final String STEEL_NAME = "Steel";
    private static final String SILVER_NAME = "Silver";
    // Material Base Damage constants
    private static final int BRONZE_BASE_DMG = 4;
    private static final int IRON_BASE_DMG = 5;
    private static final int STEEL_BASE_DMG = 6;
    private static final int SILVER_BASE_DMG = 7;
    // Material Base Weight constants
    private static final int BRONZE_BASE_WEIGHT = 8;
    private static final int IRON_BASE_WEIGHT = 6;
    private static final int STEEL_BASE_WEIGHT = 7;
    private static final int SILVER_BASE_WEIGHT = 10;

    // Weapon String constants name
    private static final String AXE_NAME = "Axe";
    private static final String BOW_NAME = "Bow";
    private static final String KNIFE_NAME = "Knife";
    private static final String STAFF_NAME = "Staff";
    private static final String SWORD_NAME = "Sword";
    // Weapon Factor
    private static final double AXE_FACTOR = 1.3;
    private static final double BOW_FACTOR = 0.8;
    private static final double KNIFE_FACTOR = 0.6;
    private static final double STAFF_FACTOR = 0.4;
    private static final double SWORD_FACTOR = 1;


    private String calculateName(String materialName, String weaponName) {
        return materialName + " " + weaponName;
    }

    private int calculateDamage(int materialBaseDmg, double weaponFactor) {
        return (int) (materialBaseDmg * weaponFactor);
    }

    private int calculateWeight(int materialBaseWeight, double weaponFactor) {
        return (int) (materialBaseWeight * weaponFactor);
    }



    private IWeapon createAxe(String materialName, int materialBaseDmg, int materialBaseWeight) {
        return new Axe(calculateName(materialName, AXE_NAME),
                calculateDamage(materialBaseDmg, AXE_FACTOR),
                calculateWeight(materialBaseWeight, AXE_FACTOR));
    }

    @Override
    public IWeapon createBronzeAxe() {
        return createAxe(BRONZE_NAME, BRONZE_BASE_DMG, BRONZE_BASE_WEIGHT);
    }

    @Override
    public IWeapon createIronAxe() {
        return createAxe(IRON_NAME, IRON_BASE_DMG, IRON_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSteelAxe() {
        return createAxe(STEEL_NAME, STEEL_BASE_DMG, STEEL_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSilverAxe() {
        return createAxe(SILVER_NAME, SILVER_BASE_DMG, SILVER_BASE_WEIGHT);
    }


    private IWeapon createBow(String materialName, int materialBaseDmg, int materialBaseWeight) {
        return new Bow(calculateName(materialName, BOW_NAME),
                calculateDamage(materialBaseDmg, BOW_FACTOR),
                calculateWeight(materialBaseWeight, BOW_FACTOR));
    }

    @Override
    public IWeapon createBronzeBow() {
        return createBow(BRONZE_NAME, BRONZE_BASE_DMG, BRONZE_BASE_WEIGHT);
    }

    @Override
    public IWeapon createIronBow() {
        return createBow(IRON_NAME, IRON_BASE_DMG, IRON_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSteelBow() {
        return createBow(STEEL_NAME, STEEL_BASE_DMG, STEEL_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSilverBow() {
        return createBow(SILVER_NAME, SILVER_BASE_DMG, SILVER_BASE_WEIGHT);
    }


    private IWeapon createKnife(String materialName, int materialBaseDmg, int materialBaseWeight) {
        return new Knife(calculateName(materialName, KNIFE_NAME),
                calculateDamage(materialBaseDmg, KNIFE_FACTOR),
                calculateWeight(materialBaseWeight, KNIFE_FACTOR));
    }

    @Override
    public IWeapon createBronzeKnife() {
        return createKnife(BRONZE_NAME, BRONZE_BASE_DMG, BRONZE_BASE_WEIGHT);
    }

    @Override
    public IWeapon createIronKnife() {
        return createKnife(IRON_NAME, IRON_BASE_DMG, IRON_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSteelKnife() {
        return createKnife(STEEL_NAME, STEEL_BASE_DMG, STEEL_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSilverKnife() {
        return createKnife(SILVER_NAME, SILVER_BASE_DMG, SILVER_BASE_WEIGHT);
    }


    private IWeapon createSword(String materialName, int materialBaseDmg, int materialBaseWeight) {
        return new Sword(calculateName(materialName, SWORD_NAME),
                calculateDamage(materialBaseDmg, SWORD_FACTOR),
                calculateWeight(materialBaseWeight, SWORD_FACTOR));
    }

    @Override
    public IWeapon createBronzeSword() {
        return createSword(BRONZE_NAME, BRONZE_BASE_DMG, BRONZE_BASE_WEIGHT);
    }

    @Override
    public IWeapon createIronSword() {
        return createSword(IRON_NAME, IRON_BASE_DMG, IRON_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSteelSword() {
        return createSword(STEEL_NAME, STEEL_BASE_DMG, STEEL_BASE_WEIGHT);
    }

    @Override
    public IWeapon createSilverSword() {
        return createSword(SILVER_NAME, SILVER_BASE_DMG, SILVER_BASE_WEIGHT);
    }

    @Override
    public IWeapon createNormalStaff() {
        return new Staff(STAFF_NAME, 2, 10, 10);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BRONZE_NAME, IRON_NAME, STEEL_NAME, SILVER_NAME,
                BRONZE_BASE_DMG, IRON_BASE_DMG, STEEL_BASE_DMG, SILVER_BASE_DMG,
                BRONZE_BASE_WEIGHT, IRON_BASE_WEIGHT, STEEL_BASE_WEIGHT, SILVER_BASE_WEIGHT,
                AXE_NAME, BOW_NAME, KNIFE_NAME, STAFF_NAME, SWORD_NAME,
                AXE_FACTOR, BOW_FACTOR, KNIFE_FACTOR, STAFF_FACTOR, SWORD_FACTOR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof WeaponFactory;
    }
}
