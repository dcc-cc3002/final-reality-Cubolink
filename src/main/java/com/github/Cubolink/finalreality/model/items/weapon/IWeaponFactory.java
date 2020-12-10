package com.github.Cubolink.finalreality.model.items.weapon;

import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

/**
 * Interface that defines what is a Weapon Factory, what things can create.
 */
public interface IWeaponFactory {

    /**
     * Creates a Bronze Axe
     * @return Axe with defined attack, weight and name.
     */
    IWeapon createBronzeAxe();

    /**
     * Creates an Iron Axe
     * @return Axe with defined attack, weight and name.
     */
    IWeapon createIronAxe();

    /**
     * Creates a Steel Axe
     * @return Axe with defined attack, weight and name.
     */
    IWeapon createSteelAxe();

    /**
     * Creates a Silver Axe
     * @return Axe with defined attack, weight and name.
     */
    IWeapon createSilverAxe();


    /**
     * Creates a Bronze Bow
     * @return Bow with defined attack, weight and name.
     */
    IWeapon createBronzeBow();

    /**
     * Creates an Iron Bow
     * @return Bow with defined attack, weight and name.
     */
    IWeapon createIronBow();

    /**
     * Creates a Steel Bow
     * @return Bow with defined attack, weight and name.
     */
    IWeapon createSteelBow();

    /**
     * Creates a Silver Bow
     * @return Bow with defined attack, weight and name.
     */
    IWeapon createSilverBow();


    /**
     * Creates a Bronze Knife
     * @return Knife with defined attack, weight and name.
     */
    IWeapon createBronzeKnife();
    /**
     * Creates an Iron Knife
     * @return Knife with defined attack, weight and name.
     */
    IWeapon createIronKnife();
    /**
     * Creates a Steel Knife
     * @return Knife with defined attack, weight and name.
     */
    IWeapon createSteelKnife();
    /**
     * Creates a Silver Knife
     * @return Knife with defined attack, weight and name.
     */
    IWeapon createSilverKnife();

    /**
     * Creates a Bronze Sword
     * @return Sword with defined attack, weight and name.
     */
    IWeapon createBronzeSword();

    /**
     * Creates an Iron Sword
     * @return Sword with defined attack, weight and name.
     */
    IWeapon createIronSword();

    /**
     * Creates an Iron Sword
     * @return Sword with defined attack, weight and name.
     */
    IWeapon createSteelSword();

    /**
     * Creates a Silver Sword
     * @return Sword with defined attack, weight and name.
     */
    IWeapon createSilverSword();

    /**
     * Creates a Staff
     * @return Sword with defined attack, weight and name.
     */
    IWeapon createNormalStaff();
}
