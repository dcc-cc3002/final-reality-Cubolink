package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

public class WaitNextTurnPhase extends AbstractPhase{


    public WaitNextTurnPhase(GameController gameController) {
        super(gameController, null);
    }

    @Override
    public void nextPhase() {
        gameController.nextCharacterInQueue();
        if (gameController.getCurrentCharacter() == null) {
            return;
        }

        System.out.println("current: " + gameController.getCurrentCharacter().getName());

        if (gameController.getCurrentCharacter().isPlayable()) {
            gameController.setCurrentGamePhase(new SelectPlayerActionPhase(gameController));

        } else {
            gameController.setCurrentGamePhase(new EnemyActionPhase(gameController));
        }
    }

    @Override
    public boolean isWaitingPhase() {
        return true;
    }

    @Override
    public String getPhaseInfo() {
        return "Waiting the next turn.";
    }

    @Override
    public String[] getPhaseOptions() {
        String[] s = new String[1];
        s[0] = "No acton required";
        return s;
    }
}
