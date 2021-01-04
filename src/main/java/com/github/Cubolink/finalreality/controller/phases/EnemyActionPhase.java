package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.EnemyIA;
import com.github.Cubolink.finalreality.controller.IGameController;

/**
 * Enemy Action Phase. The controller being in this phase indicates that it's the enemy turn.
 * The EnemyIA can decide what to do in its turn.
 */
public class EnemyActionPhase extends AbstractPhase {
    private final EnemyIA enemyIA;

    /**
     * Instantiates an Enemy Action Phase.
     * @param gameController which will be in this phase.
     */
    public EnemyActionPhase(IGameController gameController) {
        super(gameController, null);
         enemyIA = new EnemyIA(gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnemyPhase() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        enemyIA.action();
        gameController.setCurrentGamePhase(new WaitNextTurnPhase(gameController));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhaseInfo() {
        return "¡The Enemy (" + gameController.getCurrentCharacter().getName() + ") wants to attack!";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPhaseOptions() {
        String[] s = new String[1];
        s[0] = "";
        return s;
    }
}
