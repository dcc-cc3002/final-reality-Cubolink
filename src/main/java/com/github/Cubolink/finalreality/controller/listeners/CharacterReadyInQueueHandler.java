package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Event Handler for the controller. Handles the events of a player getting into the queue,
 * being ready to execute its next action.
 */
public class CharacterReadyInQueueHandler implements PropertyChangeListener {
    private final GameController controller;

    /**
     * Instantiates the handler.
     * @param controller to which it is attached.
     */
    public CharacterReadyInQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * This method is called when a character got in the queue and is ready for his next action
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.aCharacterIsWaiting();
    }
}
