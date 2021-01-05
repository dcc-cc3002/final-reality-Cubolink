package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.IGameController;

public class EndGamePhase extends AbstractPhase{

    public EndGamePhase(IGameController gameController) {
        super(gameController, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        // there's no next phase.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhaseInfo() {
        return "Game Over";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPhaseOptions() {
        return new String[]{gameController.getWinner()};
    }
}
