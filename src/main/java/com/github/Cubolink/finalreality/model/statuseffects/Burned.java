package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;

public class Burned implements IStatus {
    private final int magic_damage;

    public Burned(int magic_damage){
        this.magic_damage = magic_damage;
    }

    @Override
    public void effect(AbstractCharacter character) {
        character.takeDamage(magic_damage/2, false);
    }

    @Override
    public void disable_effect(AbstractCharacter character){}
}
