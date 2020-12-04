package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.*;

/**
 * A Weapon Factory. Creates weapon objects.
 */
public class WeaponFactory implements IWeaponFactory {
    // Material String constants names
    private final String BRONZE_NAME = "Bronze";
    private final String IRON_NAME = "Iron";
    private final String STEEL_NAME = "Steel";
    private final String SILVER_NAME = "Silver";
    // Material Base Damage constants
    private final int BRONZE_BASE_DMG = 4;
    private final int IRON_BASE_DMG = 5;
    private final int STEEL_BASE_DMG = 6;
    private final int SILVER_BASE_DMG = 7;
    // Material Base Weight constants
    private final int BRONZE_BASE_WEIGHT = 8;
    private final int IRON_BASE_WEIGHT = 6;
    private final int STEEL_BASE_WEIGHT = 7;
    private final int SILVER_BASE_WEIGHT = 10;

    // Weapon String constants name
    private final String AXE_NAME = "Axe";
    private final String BOW_NAME = "Box";
    private final String KNIFE_NAME = "Knife";
    private final String STAFF_NAME = "Staff";
    private final String SWORD_NAME = "Sword";
    // Weapon Factor
    private final double AXE_FACTOR = 1.1;
    private final double BOW_FACTOR = 0.6;
    private final double KNIFE_FACTOR = 0.4;
    private final double STAFF_FACTOR = 1;
    private final double SWORD_FACTOR = 0.9;

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
        return new Staff(STAFF_NAME, 10, 10, 10);
    }


}
