package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Black_Mage;
import com.github.Cubolink.finalreality.model.character.player.CharacterClass.Knight;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractCharacterTest {
    protected BlockingQueue<ICharacter> turns;
    protected List<ICharacter> testCharacters;

    @BeforeEach
    public void preSetUp(){
        turns = new LinkedBlockingQueue<>();
        testCharacters = new ArrayList<>();

    }

    boolean checkStringArrayEquals(String[] s1, String[] s2) {
        if (s1.length != s2.length) {
            return false;
        }
        for (int i=0; i<s1.length; i++) {
            if ( !s1[i].equals(s2[i]) ) {
                return false;
            }
        }
        return true;
    }

    @Test
    void spriteFileNameTest() {

        IPlayerCharacter playerCharacter1 = new PlayerCharacter(turns, "Ridia", 15, 3, 4, new Black_Mage());
        IPlayerCharacter playerCharacter2 = new PlayerCharacter(turns, "Cecil", 15, 3, 4, new Knight());
        Enemy enemy = new Enemy(turns, "Golbez", 300, 50, 30, 20);

        // check when we don't set the sprite filename, it returns a generic sprite filename.
        assertTrue(checkStringArrayEquals(playerCharacter1.getSpriteFileNames(), playerCharacter2.getSpriteFileNames()));
        assertTrue(checkStringArrayEquals(playerCharacter2.getSpriteFileNames(), enemy.getSpriteFileNames()));

        // check consistent get/set sprite file names
        String[] fileNames = new String[]{"file1", "file2", "file3", "pikachu.png"};
        playerCharacter1.setSpriteFileNames(fileNames);
        assertEquals(fileNames, playerCharacter1.getSpriteFileNames());

    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    protected abstract void waitTurnTest();

    @Test
    protected  void checkWaitTurn(){
        assertTrue(turns.isEmpty());

        testCharacters.get(0).waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            assertEquals(0, turns.size());
            Thread.sleep(200);
            assertEquals(1, turns.size());
            assertEquals(testCharacters.get(0), turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addToQueue() {
    }

}