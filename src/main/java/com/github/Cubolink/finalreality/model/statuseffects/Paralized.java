package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;

public class Paralized implements IStatus {

    @Override
    public void effect(AbstractCharacter character) {
        // disable attack
    }

    @Override
    public void disable_effect(AbstractCharacter character) {
        // enable attack
    }
}
