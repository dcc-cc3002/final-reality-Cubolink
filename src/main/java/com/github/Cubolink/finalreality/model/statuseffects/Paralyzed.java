package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;

public class Paralyzed implements IStatus {
    private int turns_to_disappear = 1;
    private final EnumStatus status = EnumStatus.paralyzed;

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

    @Override
    public void disable_effect(ICharacter character) {
        // enable attack
        character.setAttack_enabled(true);

        character.dropStatus(this);
    }

    @Override
    public boolean almostEquals(IStatus status) {
        if (this == status){
            return true;
        }
        return status instanceof Paralyzed;
    }

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
    public int hashCode(){
        return Objects.hash(turns_to_disappear, status);
    }
}
