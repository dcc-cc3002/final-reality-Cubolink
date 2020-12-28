package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

/**
 * Abstract class with common Game Phases behaviour.
 */
public abstract class AbstractPhase implements IGamePhase{
    protected GameController gameController;
    protected IGamePhase previousPhase;

    /**
     * Default constructor for game phases. Instantiates a gamePhase and tells the controller to set its phase as this.
     * @param gameController which will be on the instantiated phase.
     * @param previousPhase the previous phase.
     */
    public AbstractPhase(GameController gameController, IGamePhase previousPhase) {
        this.gameController = gameController;
        this.previousPhase = previousPhase;
        gameController.setCurrentGamePhase(this);
    }

    /**
     * Gets the controller index pointed by cursor and returns the module.
     * @param module Usually the length of an array.
     * @return the module of the index pointed by cursor, in order to the number not be out of range.
     */
    protected int getModuleOfIndexPointedByCursor(int module) {
        if (module == 0) {
            return 0;
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
