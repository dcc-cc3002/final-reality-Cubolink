package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.AbstractCharacter;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.statuseffects.IStatus;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.statuseffects.Poisoned;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

public class White_Mage extends AbstractCharacterClass implements IMage{
    private int mana;
    public White_Mage(String classname) {
        super(classname);
    }

    public White_Mage(GenericWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(GenericWeapon weapon) {
        if (weapon.isWearableByMage()){
            this.equippedWeapon = weapon;
        }
    }

    public void cure(PlayerCharacter character){
        if (mana>=15){
            mana -= 15;
            character.heal(character.getHp());
        }
    }

    public void poison(ICharacter character){
        if (mana>=40){
            mana -= 40;

            // add status poisoned to character
            character.addStatus(new Poisoned(equippedWeapon.getMagicalDamage()));
        }
    }

    public void paralyze(ICharacter character){
        if (mana>=25){
            mana -= 25;

            // add status paralyzed to character
            character.addStatus(new Paralyzed());
        }
    }
}
