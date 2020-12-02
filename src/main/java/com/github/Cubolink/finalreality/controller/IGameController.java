package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.IItem;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.weapon.IWeapon;

import java.util.Set;

/**
 * Interface for the Game Controller.
 * The Game Controller uses a turns queue where puts characters.
 * It stores the current character as the last one that left the queue.
 */
public interface IGameController {
    /**
     * Check if the game has come to an end, and in that case sees if the player won or lost
     */
    void checkEndGame(/*ICharacter character*/);  // I'm very unsure about what parameter to give the function

    /**
     * Makes a player character to equip a weapon
     * @param weapon to equip
     * @param playerCharacter who equips
     */
    void equipWeaponToCharacter(IWeapon weapon, IPlayerCharacter playerCharacter);

    /**
     * Creates an enemy and puts in a Queue
     */
    void createEnemy();

    /**
     * Identifies which character is next in the queue.
     * Sets the character as the current character.
     */
    void nextCharacterInQueue();

    /**
     * Shows the current character info.
     */
    void getCharacterInfo();

    /**
     * Makes the current character to attack another
     * @param objectiveCharacter to be attacked
     */
    void playerAttackCharacter(ICharacter objectiveCharacter);

    /**
     * Makes the current character to wait
     */
    void waitCharacter();

    /**
     * Stores an item
     * @param item to store
     */
    void storeItem(IItem item);

    /**
     * @return Item set of the inventory
     */
    Set<IItem> getItemSet();

    /**
     * Takes an item from the inventory, removing it and then returning it.
     * @param item to take from the inventory.
     * @return the item taken.
     */
    IItem takeItem(IItem item);

    /**
     * Takes an item from the inventory, removing it but not doing anything.
     * @param item to drop from the inventory.
     */
    void dropItem(IItem item);

    /**
     * Creates a Player Character whose class/job is a White Mage
     */
    void createWhiteMagePlayer();

    /**
     * Creates a Player Character whose class/job is a Black Mage
     */
    void createBlackMagePlayer();

    /**
     * Creates a Player Character whose class/job is an Engineer
     */
    void createEngineer();

    /**
     * Creates a Player Character whose class/job is a Knight
     */
    void createKnightPlayer();

    /**
     * Creates a Player Character whose class/job is a Thief
     */
    void createThiefPlayer();
}
