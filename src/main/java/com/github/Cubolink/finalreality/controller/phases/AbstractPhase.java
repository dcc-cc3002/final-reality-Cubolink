package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.IGameController;

import java.util.List;

/**
 * Abstract class with common Game Phases behaviour.
 */
public abstract class AbstractPhase implements IGamePhase{
    protected IGameController gameController;
    protected IGamePhase previousPhase;

    /**
     * Default constructor for game phases. Instantiates a gamePhase and tells the controller to set its phase as this.
     * @param gameController which will be on the instantiated phase.
     * @param previousPhase the previous phase.
     */
    public AbstractPhase(IGameController gameController, IGamePhase previousPhase) {
        this.gameController = gameController;
        this.previousPhase = previousPhase;
        gameController.setCurrentGamePhase(this);
    }

    /**
     * Gets the controller index pointed by cursor and returns the module.
     * @param module Usually the length of an array. This parameter must not be zero (the array can't be empty).
     * @return module integer of the index pointed by cursor.
     */
    protected int getModuleOfIndexPointedByCursor(int module) {
        if (module == 0) {
            return -1;
        }
        int x = (gameController.getIndexPointedByCursor() % module);
        return x  >= 0 ? x: x+module;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void nextPhase();

    /**
     * {@inheritDoc}
     */
    @Override
    public void prevPhase() {
        if (previousPhase != null) {
            gameController.setCurrentGamePhase(previousPhase);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWaitingPhase() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnemyPhase() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String getPhaseInfo();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String[] getPhaseOptions();
}
