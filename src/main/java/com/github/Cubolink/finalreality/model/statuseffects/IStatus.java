package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

/**
 * Interface that represent what a Status Effect is.
 */
public interface IStatus {
    /**
     * Affects somehow the character.
     * @param character the character to affect.
     */
    void effect(ICharacter character);

    /**
     * Disable the status effect on the character.
     * @param character from which to remove the effect.
     */
    void disable_effect(ICharacter character);

    /**
     * Sees if two status are of the same kind.
     * @param status the other status to compare types.
     * @return true if they're of the same kind, and false otherwise.
     */
    boolean almostEquals(IStatus status);

    /**
     * Sees which of the two status, (both of the same kind), is 'greater', comparing parameters.
     * @param status the status to compare.
     * @return true if this is greater than the other status.
     */
    boolean greaterThan(IStatus status);
}
