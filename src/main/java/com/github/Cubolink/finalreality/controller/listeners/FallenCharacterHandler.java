package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Event Handler for the controller. Handles the event of a character being defeated.
 */
public class FallenCharacterHandler implements PropertyChangeListener {
    private final GameController controller;
    private final PropertyChangeSupport characterDefeatedEvent = new PropertyChangeSupport(this);

    public FallenCharacterHandler(GameController controller, PropertyChangeListener endGameListener) {
        this.controller = controller;
        characterDefeatedEvent.addPropertyChangeListener(endGameListener);
    }

    /**
     * This method gets called when some character is defeated.
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (controller.isTheGameFinished()) {
            characterDefeatedEvent.firePropertyChange("Game Over", false, true);
        }
    }
}
