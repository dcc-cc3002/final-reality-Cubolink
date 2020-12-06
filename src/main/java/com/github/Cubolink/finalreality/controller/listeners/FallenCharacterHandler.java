package com.github.Cubolink.finalreality.controller.listeners;

import com.github.Cubolink.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FallenCharacterHandler implements PropertyChangeListener {
    private final GameController controller;

    public FallenCharacterHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * This method gets called when some character is defeated.
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.checkEndGame();
    }
}
