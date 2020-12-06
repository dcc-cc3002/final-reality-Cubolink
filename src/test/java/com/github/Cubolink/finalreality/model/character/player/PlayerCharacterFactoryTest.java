package com.github.Cubolink.finalreality.model.character.player;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCharacterFactoryTest {
    private final BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();
    PlayerCharacterFactory playerCharacterFactory = new PlayerCharacterFactory(turnsQueue);

    private IPlayerCharacter whiteMageCharacter;
    private IPlayerCharacter blackMageCharacter;
    private IPlayerCharacter engineerCharacter;
    private IPlayerCharacter knightCharacter;
    private IPlayerCharacter thiefCharacter;

    @BeforeEach
    void setUp() {
        whiteMageCharacter = playerCharacterFactory.createWhiteMageCharacter("Celestine");
        blackMageCharacter = playerCharacterFactory.createBlackMageCharacter("Ammonia");
        engineerCharacter = playerCharacterFactory.createEngineerCharacter("Lead");
        knightCharacter = playerCharacterFactory.createKnightCharacter("Tungsteno");
        thiefCharacter = playerCharacterFactory.createThiefCharacter("Kleptomanium");
    }

    @Test
    void createWhiteMageCharacterTest() {
        assertEquals(whiteMageCharacter, playerCharacterFactory.createWhiteMageCharacter(whiteMageCharacter.getName()));
        assertEquals(whiteMageCharacter.getCharacterClass(),
                playerCharacterFactory.createWhiteMageCharacter("Hello Kitty").getCharacterClass());

        assertEquals(whiteMageCharacter.getCharacterClass(), new White_Mage());

        assertEquals(whiteMageCharacter.getCharacterClass(), whiteMageCharacter.getCharacterClass());
        assertNotEquals(whiteMageCharacter.getCharacterClass(), blackMageCharacter.getCharacterClass());
        assertNotEquals(whiteMageCharacter.getCharacterClass(), engineerCharacter.getCharacterClass());
        assertNotEquals(whiteMageCharacter.getCharacterClass(), knightCharacter.getCharacterClass());
        assertNotEquals(whiteMageCharacter.getCharacterClass(), thiefCharacter.getCharacterClass());
    }

    @Test
    void createBlackMageCharacterTest() {
        assertEquals(blackMageCharacter, playerCharacterFactory.createBlackMageCharacter(blackMageCharacter.getName()));
        assertEquals(blackMageCharacter.getCharacterClass(),
                playerCharacterFactory.createBlackMageCharacter("Hello Kitty").getCharacterClass());

        assertEquals(blackMageCharacter.getCharacterClass(), new Black_Mage());

        assertEquals(blackMageCharacter.getCharacterClass(), blackMageCharacter.getCharacterClass());
        assertNotEquals(blackMageCharacter.getCharacterClass(), whiteMageCharacter.getCharacterClass());
        assertNotEquals(blackMageCharacter.getCharacterClass(), engineerCharacter.getCharacterClass());
        assertNotEquals(blackMageCharacter.getCharacterClass(), knightCharacter.getCharacterClass());
        assertNotEquals(blackMageCharacter.getCharacterClass(), thiefCharacter.getCharacterClass());
    }

    @Test
    void createEngineerCharacterTest() {
        assertEquals(engineerCharacter, playerCharacterFactory.createEngineerCharacter(engineerCharacter.getName()));
        assertEquals(engineerCharacter.getCharacterClass(),
                playerCharacterFactory.createEngineerCharacter("Hello Kitty").getCharacterClass());

        assertEquals(engineerCharacter.getCharacterClass(), new Engineer());

        assertEquals(engineerCharacter.getCharacterClass(), engineerCharacter.getCharacterClass());
        assertNotEquals(engineerCharacter.getCharacterClass(), whiteMageCharacter.getCharacterClass());
        assertNotEquals(engineerCharacter.getCharacterClass(), blackMageCharacter.getCharacterClass());
        assertNotEquals(engineerCharacter.getCharacterClass(), knightCharacter.getCharacterClass());
        assertNotEquals(engineerCharacter.getCharacterClass(), thiefCharacter.getCharacterClass());
    }

    @Test
    void createKnightCharacterTest() {
        assertEquals(knightCharacter, playerCharacterFactory.createKnightCharacter(knightCharacter.getName()));
        assertEquals(knightCharacter.getCharacterClass(),
                playerCharacterFactory.createKnightCharacter("Hello Kitty").getCharacterClass());

        assertEquals(knightCharacter.getCharacterClass(), new Knight());

        assertEquals(knightCharacter.getCharacterClass(), knightCharacter.getCharacterClass());
        assertNotEquals(knightCharacter.getCharacterClass(), whiteMageCharacter.getCharacterClass());
        assertNotEquals(knightCharacter.getCharacterClass(), blackMageCharacter.getCharacterClass());
        assertNotEquals(knightCharacter.getCharacterClass(), engineerCharacter.getCharacterClass());
        assertNotEquals(knightCharacter.getCharacterClass(), thiefCharacter.getCharacterClass());
    }

    @Test
    void createThiefCharacterTest() {
        assertEquals(thiefCharacter, playerCharacterFactory.createThiefCharacter(thiefCharacter.getName()));
        assertEquals(thiefCharacter.getCharacterClass(),
                playerCharacterFactory.createThiefCharacter("Hello Kitty").getCharacterClass());

        assertEquals(thiefCharacter.getCharacterClass(), new Thief());

        assertEquals(thiefCharacter.getCharacterClass(), thiefCharacter.getCharacterClass());
        assertNotEquals(thiefCharacter.getCharacterClass(), whiteMageCharacter.getCharacterClass());
        assertNotEquals(thiefCharacter.getCharacterClass(), blackMageCharacter.getCharacterClass());
        assertNotEquals(thiefCharacter.getCharacterClass(), engineerCharacter.getCharacterClass());
        assertNotEquals(thiefCharacter.getCharacterClass(), knightCharacter.getCharacterClass());
    }
}