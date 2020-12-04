package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.*;

import java.util.concurrent.BlockingQueue;

public class PlayerCharacterFactory implements IPlayerCharacterFactory {
    private final BlockingQueue<ICharacter> turnsQueue;

    public PlayerCharacterFactory(BlockingQueue<ICharacter> turnsQueue) {
        this.turnsQueue = turnsQueue;
    }

    @Override
    public IPlayerCharacter createWhiteMageCharacter(String characterName) {
        return new PlayerCharacter(turnsQueue, characterName, 20, 1, 10,
                    new White_Mage());
    }

    @Override
    public IPlayerCharacter createBlackMageCharacter(String characterName) {
        return new PlayerCharacter(turnsQueue, characterName, 20, 2, 10,
                    new Black_Mage());
    }

    @Override
    public IPlayerCharacter createEngineerCharacter(String characterName) {
        return new PlayerCharacter(turnsQueue, characterName, 40, 5, 4,
                    new Engineer());
    }

    @Override
    public IPlayerCharacter createKnightCharacter(String characterName) {
        return new PlayerCharacter(turnsQueue, characterName, 50, 8, 2,
                    new Knight());
    }

    @Override
    public IPlayerCharacter createThiefCharacter(String characterName) {
        return new PlayerCharacter(turnsQueue, characterName, 20, 2, 4,
                    new Thief());
    }

}
