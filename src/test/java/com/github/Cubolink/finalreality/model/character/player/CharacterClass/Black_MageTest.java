package com.github.Cubolink.finalreality.model.character.player.CharacterClass;

import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class Black_MageTest extends AbstractCharacterClassTest {
    //private LinkedBlockingQueue turns;
    //private List<ICharacter> testCharacters;
    private Black_Mage blackMageTest;
    private ICharacter opponent;
    protected BlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();

        blackMageTest = new Black_Mage();
        staff = new Staff("Báculo", 5, 20, 6);
        blackMageTest.equip(staff);

        opponent = new Enemy(turns, "Enemigo", 40, 0, 10, 5);
    }

    @Test
    void testConstruction(){
        ICharacterClass blackMage = new Black_Mage();
        checkConstruction(blackMage, blackMageTest.getClassname());
    }

    @Test
    void equip() {
        blackMageTest = new Black_Mage();
        equipTestSetUp();

        blackMageTest.equip(axe);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(bow);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(knife);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(sword);
        assertNull(blackMageTest.getEquippedWeapon());

        blackMageTest.equip(staff);
        assertEquals(blackMageTest.getEquippedWeapon(), staff);
    }

    @RepeatedTest(15)
    void thunder() {
        int op_hp = opponent.getHp();
        blackMageTest.thunder(opponent, new Random());
        assertEquals(opponent.getHp(), op_hp-blackMageTest.getEquippedWeapon().getMagicalDamage());
    }

    @RepeatedTest(25)
    void fire() {
        int op_hp = opponent.getHp();
        blackMageTest.fire(opponent, new Random());
        assertEquals(opponent.getHp(), op_hp-blackMageTest.getEquippedWeapon().getMagicalDamage());
    }

    @Test
    void testEquals() {
        ICharacterClass same_blackMage = new Black_Mage();
        same_blackMage.equip(staff);

        ICharacterClass sameClass_diffWeapon = new Black_Mage();
        IWeapon other_staff = new Staff("Vara", 10, 5, 5);
        sameClass_diffWeapon.equip(other_staff);

        ICharacterClass other_character_class = new White_Mage();
        other_character_class.equip(blackMageTest.getEquippedWeapon());

        checkEquals(blackMageTest, same_blackMage, sameClass_diffWeapon, other_character_class);
    }

    @Test
    void testHashCode() {
        ICharacterClass same_blackMage = new Black_Mage();
        same_blackMage.equip(staff);

        ICharacterClass sameClass_diffWeapon = new Black_Mage();
        IWeapon other_staff = new Staff("Vara", 10, 5, 5);
        sameClass_diffWeapon.equip(other_staff);

        ICharacterClass other_character_class = new White_Mage();
        other_character_class.equip(blackMageTest.getEquippedWeapon());

        checkHashCode(blackMageTest, same_blackMage, sameClass_diffWeapon, other_character_class);
    }
}