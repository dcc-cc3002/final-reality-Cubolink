package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectPlayerActionPhase extends AbstractPhase {
    private final ArrayList<IGamePhase> possibleActions =
            new ArrayList<>(Arrays.asList(
                    new SelectAttackedTargetPhase(gameController, this),
                    new SelectWeaponPhase(gameController, this),
                    new WaitNextTurnPhase(gameController)));

    public SelectPlayerActionPhase(GameController gameController) {
        super(gameController, null);
    }

    @Override
    public void nextPhase() {
        IGamePhase nextGamePhase = possibleActions.get(gameController.getIndexPointedByCursor() % possibleActions.size());
        gameController.setCurrentGamePhase(nextGamePhase);
    }
}
