package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import java.util.Objects;
import java.util.Random;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.statuseffects.Burned;
import com.github.Cubolink.finalreality.model.statuseffects.Paralyzed;
import com.github.Cubolink.finalreality.model.weapon.GenericWeapon;


public class Black_Mage extends AbstractCharacterClass implements IMage{
    private int mana;

    public Black_Mage(String classname) {
        super(classname);
    }

    public Black_Mage(GenericWeapon weapon, String classname) {
        super(weapon, classname);
    }

    @Override
    public void equip(GenericWeapon weapon) {
        if (weapon.isWearableByMage()){
            this.equippedWeapon = weapon;
        }
    }

    public void thunder(ICharacter character){
        if (mana>=15){
            mana -= 15;
            character.receiveDamage(equippedWeapon.getMagicalDamage());
            Random R = new Random();
            if (R.nextDouble() < 0.3){
                character.addStatus(new Paralyzed());
            }
        }
    }

    public void fire(ICharacter character){
        if (mana>=15){
            mana -= 15;
            character.beMagicallyAttackedBy(equippedWeapon);
            Random R = new Random();
            if (R.nextDouble() < 0.2){
                character.addStatus(new Burned(equippedWeapon.getMagicalDamage()));
            }
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Black_Mage)) return false;

        final Black_Mage that = (Black_Mage) o;
        return getClassname().equals(that.getClassname());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getClassname());
    }
}
