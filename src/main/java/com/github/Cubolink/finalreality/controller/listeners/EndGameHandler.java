package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.IGameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Event Handler for the controller. Handles the event of a Fallen character event that
 * meant all the characters of a team were defeated.
 */
public class EndGameHandler implements PropertyChangeListener {
    private final IGameController controller;

    /**
     * Instantiates the handler.
     * @param controller to which it is attached.
     */
    public EndGameHandler(IGameController controller) {
        this.controller = controller;
    }

    /**
     * This method is called when all the characters of a team were defeated
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.end();
    }
}
