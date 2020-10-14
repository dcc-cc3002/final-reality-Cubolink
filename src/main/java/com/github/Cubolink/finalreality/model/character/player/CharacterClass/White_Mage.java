package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.statuseffects.Poisoned;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;

import java.util.Objects;

public class White_Mage extends AbstractCharacterClass implements IMage{
    private int mana=50;
    public White_Mage(String classname) {
        super(classname);
    }

    public White_Mage(String classname, GenericWeapon weapon) {
        super(classname, weapon);
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
            character.heal(character.getMaxHp()*3/10);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof White_Mage)) return false;

        final White_Mage that = (White_Mage) o;
        return getClassname().equals(that.getClassname());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getClassname());
    }
}
