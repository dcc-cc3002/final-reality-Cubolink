package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.IGameController;

public class EndGamePhase extends AbstractPhase{

    public EndGamePhase(IGameController gameController) {
        super(gameController, null);
    }

    @Override
    public void nextPhase() {
        // there's no next phase.
    }

    @Override
    public String getPhaseInfo() {
        return "Game Over";
    }

    @Override
    public String[] getPhaseOptions() {
        return new String[]{gameController.getWinner()};
    }
}
