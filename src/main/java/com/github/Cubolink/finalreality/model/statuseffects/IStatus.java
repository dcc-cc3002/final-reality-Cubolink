package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

public interface IStatus {
    /**
     * Affects somehow the character
     * @param character the character to affect
     */
    void effect(ICharacter character);

    /**
     * Disable the status effect on the character
     * @param character from which to remove the effect
     */
    void disable_effect(ICharacter character);
}
