package com.github.Cubolink.finalreality.model.character;

import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class AbstractCharacterTest {
    protected BlockingQueue<ICharacter> turns;
    protected List<ICharacter> testCharacters;
    protected AbstractWeapon testWeapon;

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
    void addToQueue() {
    }

}