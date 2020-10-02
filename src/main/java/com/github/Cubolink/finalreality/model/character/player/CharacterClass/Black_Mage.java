package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;


public class Black_Mage extends AbstractCharacterClass implements IMage{
    private int mana;
    public Black_Mage(String classname) {
        super(classname);
    }

    public Black_Mage(AbstractWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void trueno(AbstractCharacter character){
        if (mana>=15){
            mana -= 15;
            character.takeDamage(equippedWeapon.getMagical_damage(), false);
        }
    }

    public void fuego(AbstractCharacter character){
        if (mana>=15){
            mana -= 15;
            character.takeDamage(equippedWeapon.getMagical_damage(), false);
        }
    }
}
