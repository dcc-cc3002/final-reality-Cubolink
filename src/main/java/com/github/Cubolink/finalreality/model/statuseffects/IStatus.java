package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;

public interface IStatus {
    void effect(AbstractCharacter character);
    void disable_effect(AbstractCharacter character);
}
