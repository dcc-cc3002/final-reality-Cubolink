package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;

/**
 * A class to define the Poison Status Effect.
 */
public class Poisoned implements IStatus {
    private final int magic_damage;
    private final EnumStatus status;

    /**
     * Instantiates Poisoned
     * @param magic_damage the magic_damage of the weapon that caused the status
     */
    public Poisoned(int magic_damage) {
        this.magic_damage = magic_damage;
        status = EnumStatus.poisoned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void effect(ICharacter character) {
        character.receiveDamage(magic_damage/3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable_effect(ICharacter character) {
        character.dropStatus(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean almostEquals(IStatus status) {
        if (this == status) {
            return true;
        }
        return status instanceof Poisoned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean greaterThan(IStatus status) {
        final Poisoned that = (Poisoned) status;
        return (magic_damage > that.magic_damage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Poisoned)) {
            return false;
        }

        final Poisoned poisoned = (Poisoned) o;
        return magic_damage == poisoned.magic_damage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(magic_damage, status);
    }
}
