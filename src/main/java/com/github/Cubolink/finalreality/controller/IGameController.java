package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.controller.phases.IGamePhase;
import com.github.Cubolink.finalreality.gui.CharacterSpriteGroup;
import com.github.Cubolink.finalreality.gui.CursorSprite;
import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.items.IItem;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

/**
 * Interface for the Game Controller. Controls the model and interacts with the GUI and the enemy IA.
 * The Game Controller uses a turns queue where puts characters.
 * It stores the current character as the last one that left the queue.
 */
public interface IGameController {
    /**
     * Initiates the game with determined parameters for the controller.
     */
    void setUp();

// Controller user methods
    /**
     * Do next action
     */
    void next();

    /**
     * Go back
     */
    void prev();

    /**
     * Moves the cursor right.
     */
    void moveCursorRight();

    /**
     * Moves the cursor left.
     */
    void moveCursorLeft();


// Controller flow control methods

    /**
     * Put all the characters into the queue to start the game
     */
    void start();

    /**
     * Ends the game
     */
    void end();

    /**
     * Identifies which character is next in the queue.
     * Sets the character as the current character.
     */
    void nextCharacterInQueue();

    /**
     * Changes the controller phase.
     * @param newPhase the phase that the controller will be at.
     */
    void setCurrentGamePhase(IGamePhase newPhase);

    /**
     * @return the current position pointed by the player's cursor.
     */
    short getIndexPointedByCursor();

    /**
     * Resets the position pointed by the player's cursor.
     */
    void resetIndexPointedByCursor();

    /**
     * Updates the cursor's sprite position.
     */
    void updateCursorSpritePosition();

    /**
     * Updates the information about which characters are alive and which are not.
     */
    void updateAliveCharacters();

//  General game information

    /**
     * @return the list of characters that are alive
     */
    List<ICharacter> getAliveCharactersList();

    /**
     * @return the list of the player characters
     */
    List<IPlayerCharacter> getCharacterPlayerList();

    /**
     * @return the list of enemies
     */
    List<Enemy> getEnemyList();

    /**
     * @return an array of Strings with the information of each player character in the game.
     */
    String[] getPlayerCharactersInfo();

    /**
     * @return an array of Strings with the information of each enemy in the game.
     */
    String[] getEnemyCharactersInfo();

    /**
     * @return the number of characters that are in the game, alive of not.
     */
    int getTotalNumberOfCharacters();

    /**
     * @return the max number of player characters allowed in the game.
     */
    int getMaxPlayerCharacterNum();

    /**
     * @return the max number of enemies allowed in the game.
     */
    int getMaxEnemyCharacterNum();



//  Game temporal information


    /**
     * @return a String with the current phase description.
     */
    String getPhaseInfo();

    /**
     * @return an array of Strings with action options that the player can choose to do.
     */
    String[] getPhaseOptions();

    /**
     * A character who gets in the queue call this method
     */
    void aCharacterIsWaiting();

    /**
     * @return true if there are characters waiting in the turns queue, false otherwise.
     */
    boolean thereAreCharactersWaiting();

    /**
     * Checks if the controller is at an enemy turn.
     * @return true if that is the case.
     */
    boolean inEnemyTurn();

    /**
     * Check if the game has come to an end.
     */
    boolean isTheGameFinished();

    /**
     * @return the current character
     */
    ICharacter getCurrentCharacter();

    double getSpriteCharacterCx(int i_sprite);
    double getSpriteCharacterCy(int i_sprite);

    /**
     * Update the information of each character sprite.
     */
    void updateCharacterSpritesInformation();


//  Character actions


    /**
     * Makes the current character to wait
     */
    void waitCharacter();

    /**
     * Makes the current character to attack another
     * @param objectiveCharacter to be attacked
     */
    void attackCharacter(ICharacter objectiveCharacter);

    /**
     * Makes the current character to equip a weapon
     * @param weapon to equip
     * @param playerCharacter who equips
     */
    void equipWeaponToCharacter(IWeapon weapon, IPlayerCharacter playerCharacter);




// Item management

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
     * @return a list with all the weapons in the inventory.
     */
    List<IWeapon> getWeaponList();


// Creation methods


    // Character creation methods


    /**
     * Creates an enemy and puts in a Queue
     */
    void createEnemy();

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
    void createEngineerPlayer();
    /**
     * Creates a Player Character whose class/job is a Knight
     */
    void createKnightPlayer();
    /**
     * Creates a Player Character whose class/job is a Thief
     */
    void createThiefPlayer();

    /**
     * Receives a cursor sprite, then the controller links it with its not graphic cursor.
     * @param cursorSprite the sprite of the cursor to link.
     */
    void linkCursorSprite(CursorSprite cursorSprite);

    /**
     * Receives an array of sprites, then the controller links each of them to a character in the game.
     * @param characterSpritesArray the sprite list to link.
     */
    void linkCharacterSprites(CharacterSpriteGroup[] characterSpritesArray, double player_cy, double enemy_cy) throws FileNotFoundException;


//    Weapon creation methods

    /**
     * Creates and stores a Bronze Axe
     */
    void createBronzeAxe();
    /**
     * Creates and stores an Iron Axe
     */
    void createIronAxe();
    /**
     * Creates and stores a Steel Axe
     */
    void createSteelAxe();
    /**
     * Creates and stores a Silver Axe
     */
    void createSilverAxe();


    /**
     * Creates and stores a Bronze Bow
     */
    void createBronzeBow();
    /**
     * Creates and stores an Iron Bow
     */
    void createIronBow();
    /**
     * Creates and stores a Steel Bow
     */
    void createSteelBow();
    /**
     * Creates and stores a Silver Bow
     */
    void createSilverBow();


    /**
     * Creates and stores a Bronze Knife
     */
    void createBronzeKnife();
    /**
     * Creates and stores an Iron Knife
     */
    void createIronKnife();
    /**
     * Creates and stores a Steel Knife
     */
    void createSteelKnife();
    /**
     * Creates and stores a Silver Knife
     */
    void createSilverKnife();

    /**
     * Creates and stores a Bronze Sword
     */
    void createBronzeSword();

    /**
     * Creates and stores an Iron Sword
     */
    void createIronSword();
    /**
     * Creates and stores an Iron Sword
     */
    void createSteelSword();
    /**
     * Creates and stores a Silver Sword
     */
    void createSilverSword();
    /**
     * Creates and stores a Staff
     */
    void createNormalStaff();
}
