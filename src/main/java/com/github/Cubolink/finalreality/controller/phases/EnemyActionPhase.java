package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.EnemyIA;
import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeSupport;

public class EnemyActionPhase extends AbstractPhase {
    private final EnemyIA enemyIA;

    public EnemyActionPhase(GameController gameController) {
        super(gameController, null);
         enemyIA = new EnemyIA(gameController);
    }

    @Override
    public boolean isEnemyPhase() {
        return true;
    }

    @Override
    public void nextPhase() {
        enemyIA.action();
        gameController.setCurrentGamePhase(new WaitNextTurnPhase(gameController));
    }

    @Override
    public String getPhaseInfo() {
        return "¡The Enemy (" + gameController.getCurrentCharacter().getName() + ") wants to attack!";
    }

    @Override
    public String[] getPhaseOptions() {
        String[] s = new String[1];
        s[0] = "";
        return s;
    }
}
