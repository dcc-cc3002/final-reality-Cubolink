package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;

/**
 * A class to define the paralysis effect.
 */
public class Paralyzed implements IStatus {
    private int turns_to_disappear;
    private final EnumStatus status;

    /**
     * Instantiates the Paralyzed status effect, with a counter of 1 turn to disable this status.
     */
    public Paralyzed(){
        this.turns_to_disappear = 1;
        status = EnumStatus.paralyzed;
    }

    /**
     * Affects a character, disabling its capacity to attack.
     * @param character the character to affect.
     */
    @Override
    public void effect(ICharacter character) {
        if (turns_to_disappear>0){
            // disable attack
            character.setAttack_enabled(false);
            turns_to_disappear -= 1;
        } else {
            disable_effect(character);
        }

    }

    /**
     * Enables the attack capacity of the character and then removes the status from the character.
     * @param character from which to remove the effect.
     */
    @Override
    public void disable_effect(ICharacter character) {
        // enable attack
        character.setAttack_enabled(true);

        character.dropStatus(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean almostEquals(IStatus status) {
        if (this == status){
            return true;
        }
        return status instanceof Paralyzed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean greaterThan(IStatus status) {
        final Paralyzed that = (Paralyzed) status;
        return (turns_to_disappear > that.turns_to_disappear);
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Paralyzed)){
            return false;
        }

        final Paralyzed paralyzed = (Paralyzed) o;
        return turns_to_disappear == paralyzed.turns_to_disappear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turns_to_disappear, status);
    }
}
