package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.*;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractCharacterClassTest {
    protected IWeapon axe, bow, knife, staff, sword;
    protected BlockingQueue<ICharacter> turns;

    void equipTestSetUp(){
        axe = new Axe("Hacha", 20, 15);
        staff = new Staff("Báculo", 5, 20, 6);
        bow = new Bow("Arco de Hierro", 15, 6);
        knife = new Knife("Cuchillito", 10, 3);
        sword = new Sword("Espada", 15, 10);
    }

    void checkConstruction(ICharacterClass characterClass, String expectedClassName){
        assertEquals(characterClass.getClassname(), expectedClassName);
        assertNull(characterClass.getEquippedWeapon());
    }

    void checkEquals(final ICharacterClass expectedClass,
                     final ICharacterClass equalClass,
                     final ICharacterClass sameClassDifferentWeapon,
                     final ICharacterClass otherClass){

        assertEquals(expectedClass, expectedClass);
        assertEquals(expectedClass, equalClass);
        assertEquals(expectedClass, sameClassDifferentWeapon);
        assertNotEquals(expectedClass, otherClass);

    }

    void checkHashCode(final ICharacterClass expectedClass,
                       final ICharacterClass equalClass,
                       final ICharacterClass sameClassDifferentWeapon,
                       final ICharacterClass otherClass){
        assertEquals(expectedClass.hashCode(), expectedClass.hashCode());
        assertEquals(expectedClass.hashCode(), equalClass.hashCode());
        assertEquals(expectedClass.hashCode(), sameClassDifferentWeapon.hashCode());
        assertNotEquals(expectedClass.hashCode(), otherClass.hashCode());
    }


}