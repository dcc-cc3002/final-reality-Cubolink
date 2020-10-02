package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.weapon.AbstractWeapon;

public class White_Mage extends AbstractCharacterClass implements IMage{
    private int mana;
    public White_Mage(String classname) {
        super(classname);
    }

    public White_Mage(AbstractWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(AbstractWeapon weapon) {
        this.equippedWeapon = weapon;
    }

    public void cura(AbstractCharacter character){
        if (mana>=15){
            mana -= 15;
            character.heal(character.getHp());
        }
    }

    public void veneno(AbstractCharacter character){
        if (mana>=40){
            mana -= 40;

            // add status poisoned to character
        }
    }

    public void paralisis(AbstractCharacter character){
        if (mana>=25){
            mana -= 25;
            // add status paralized to character
        }
    }
}
