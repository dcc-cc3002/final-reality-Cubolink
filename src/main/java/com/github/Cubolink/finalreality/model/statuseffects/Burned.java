package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.Objects;

public class Burned implements IStatus {
    private final int magic_damage;

    /**
     * Instantiates Burned
     * @param magic_damage the magic_damage of the weapon that caused the status
     */
    public Burned(int magic_damage){
        this.magic_damage = magic_damage;
    }

    @Override
    public void effect(ICharacter character) {
        character.receiveDamage(magic_damage/2);
    }

    @Override
    public void disable_effect(ICharacter character){
        character.dropStatus(this);
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof Burned)){
            return false;
        }

        final Burned burned = (Burned) o;
        return magic_damage == burned.magic_damage;
    }

    @Override
    public int hashCode(){
        return Objects.hash(magic_damage);
    }
}
