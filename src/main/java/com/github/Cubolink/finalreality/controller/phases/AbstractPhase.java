package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

import java.util.ArrayList;

public abstract class AbstractPhase implements IGamePhase{
    protected GameController gameController;
    protected IGamePhase previousPhase;

    public AbstractPhase(GameController gameController, IGamePhase previousPhase) {
        this.gameController = gameController;
        gameController.setCurrentGamePhase(this);
        this.previousPhase = previousPhase;
    }

    @Override
    public abstract void nextPhase();

    @Override
    public void prevPhase() {
        if (previousPhase != null) {
            gameController.setCurrentGamePhase(previousPhase);
        }
    }

    @Override
    public boolean isWaitingPhase() {
        return false;
    }

    @Override
    public abstract String getPhaseInfo();

    @Override
    public abstract String[] getPhaseOptions();

    protected int getModuleOfIndexPointedByCursor(int module) {
        if (module == 0) {
            return 0;
        }
        int x = (gameController.getIndexPointedByCursor() % module);
        return x  >= 0 ? x: x+module;
    }
}
