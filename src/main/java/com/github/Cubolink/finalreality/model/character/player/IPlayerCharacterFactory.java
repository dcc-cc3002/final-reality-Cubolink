package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;

public interface IPlayerCharacterFactory {
    /**
     * Creates a Player Character whose class/job is White Mage.
     * @param characterName The name of the player character.
     * @return the player character generated.
     */
    IPlayerCharacter createWhiteMageCharacter(String characterName);

    /**
     * Creates a Player Character whose class/job is Black Mage.
     * @param characterName The name of the player character.
     * @return the player character generated.
     */
    IPlayerCharacter createBlackMageCharacter(String characterName);

    /**
     * Creates a Player Character whose class/job is Engineer.
     * @param characterName The name of the player character.
     * @return the player character generated.
     */
    IPlayerCharacter createEngineerCharacter(String characterName);

    /**
     * Creates a Player Character whose class/job is Knight.
     * @param characterName The name of the player character.
     * @return the player character generated.
     */
    IPlayerCharacter createKnightCharacter(String characterName);

    /**
     * Creates a Player Character whose class/job is Thief.
     * @param characterName The name of the player character.
     * @return the player character generated.
     */
    IPlayerCharacter createThiefCharacter(String characterName);
}
