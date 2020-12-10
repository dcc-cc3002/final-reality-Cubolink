package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CharacterReadyInQueueHandler implements PropertyChangeListener {
    private final GameController controller;

    public CharacterReadyInQueueHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.aCharacterIsWaiting();
    }
}
