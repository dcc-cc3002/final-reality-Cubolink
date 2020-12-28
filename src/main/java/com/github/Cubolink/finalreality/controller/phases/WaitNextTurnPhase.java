package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

/**
 * Wait Next Turn Phase. When the controller is in this phase,
 * neither the enemy nor the player can do anything other than wait until a character is ready to start its turn.
 */
public class WaitNextTurnPhase extends AbstractPhase{

    /**
     * Instantiates a WaitNextTurnPhase.
     * @param gameController which will be in this phase.
     */
    public WaitNextTurnPhase(GameController gameController) {
        super(gameController, null);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWaitingPhase() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhaseInfo() {
        return "Waiting the next turn.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPhaseOptions() {
        String[] s = new String[1];
        s[0] = "No acton required";
        return s;
    }
}
