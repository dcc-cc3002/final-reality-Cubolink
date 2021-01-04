package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.gui.spritegroups.CursorSprite;

/**
 * Interface to define what represents a Game Phase.
 * A Game Phase can move to other game phases,
 * and can provide basic information about the phase and its transitions options.
 */
public interface IGamePhase {
    /**
     * Do a phase transition from the current to the next one.
     */
    void nextPhase();

    /**
     * Do a phase transition from the current to the previous, if possible.
     */
    void prevPhase();

    /**
     * @return true if the phase is a waiting-kind phase.
     */
    boolean isWaitingPhase();

    /**
     * @return true if the phase is an enemy phase.
     */
    boolean isEnemyPhase();

    /**
     * @return a string with the information of the phase.
     */
    String getPhaseInfo();

    /**
     * @return a string array with the actions that the phase can take.
     */
    String[] getPhaseOptions();

    /**
     * Sets the controller's cursor position.
     */
    void setCursorSpritePosition(CursorSprite cursorSprite);
}
