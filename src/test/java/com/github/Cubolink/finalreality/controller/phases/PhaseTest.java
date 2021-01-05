package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhaseTest {

    @Test
    void testGetModule() {
        GameController gameController = new GameController();
        WaitNextTurnPhase waitNextTurnPhase = new WaitNextTurnPhase(gameController);

        assertTrue(waitNextTurnPhase.getModuleOfIndexPointedByCursor(0) < 0);

        gameController.moveCursorRight();
        gameController.moveCursorRight();
        assertTrue(waitNextTurnPhase.getModuleOfIndexPointedByCursor(1) >= 0);

        gameController.moveCursorLeft();
        gameController.moveCursorLeft();
        gameController.moveCursorLeft();
        assertTrue(waitNextTurnPhase.getModuleOfIndexPointedByCursor(1) >= 0);
    }
}
