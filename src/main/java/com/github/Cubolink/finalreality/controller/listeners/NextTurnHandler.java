package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NextTurnHandler implements PropertyChangeListener {
    private final GameController controller;

    public NextTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (controller.thereAreCharactersWaiting() && controller.inWaitingPhase()) {

            controller.next();  // nop, because next() does nothing if is a waiting phase
        }
    }
}
