package com.github.Cubolink.finalreality.model.character;

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