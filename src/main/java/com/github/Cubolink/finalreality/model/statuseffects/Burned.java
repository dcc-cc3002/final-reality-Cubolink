package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.ICharacter;

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
}
