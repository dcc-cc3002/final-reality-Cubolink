package com.github.Cubolink.finalreality.model.statuseffects;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;

public class Poisoned implements IStatus {
    private final int magic_damage;

    public Poisoned(int magic_damage){
        this.magic_damage = magic_damage;
    }

    @Override
    public void effect(AbstractCharacter character) {
        character.takeDamage(magic_damage/3, false);
    }

    @Override
    public void disable_effect(AbstractCharacter character){}
}
