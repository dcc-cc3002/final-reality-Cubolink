package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EndGameHandler implements PropertyChangeListener {
    private final GameController controller;

    public EndGameHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.end();
    }
}
