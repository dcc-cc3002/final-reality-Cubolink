package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

/**
 * Abstract class with common behavior of status effects.
 */
public abstract class AbstractStatus implements IStatus{

    @Override
    public abstract void effect(ICharacter character);

    @Override
    public abstract void disable_effect(ICharacter character);

    @Override
    public boolean isBurnedStatus() {
        return false;
    }

    @Override
    public boolean isParalyzedStatus() {
        return false;
    }

    @Override
    public boolean isPoisonedStatus() {
        return false;
    }

    @Override
    public abstract boolean almostEquals(IStatus status);

    @Override
    public abstract boolean greaterThan(IStatus status);


}
