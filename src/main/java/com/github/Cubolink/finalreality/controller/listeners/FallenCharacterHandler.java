package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.IGameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Event Handler for the controller. Handles the event of a character being defeated.
 */
public class FallenCharacterHandler implements PropertyChangeListener {
    private final IGameController controller;
    private final PropertyChangeSupport characterDefeatedEvent = new PropertyChangeSupport(this);

    /**
     * Instantiates the handler.
     * @param controller to which it is attached.
     * @param endGameListener which listens the events this handler manages.
     */
    public FallenCharacterHandler(IGameController controller, PropertyChangeListener endGameListener) {
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
