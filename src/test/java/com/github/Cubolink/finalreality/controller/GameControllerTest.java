package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.character.enemy.EnemyFactory;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacterFactory;
import com.github.Cubolink.finalreality.model.items.IItem;
import com.github.Cubolink.finalreality.model.items.weapon.WeaponFactory;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Knife;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.Sword;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    GameController controller;
    WeaponFactory weaponFactory;
    PlayerCharacterFactory playerCharacterFactory;
    EnemyFactory enemyFactory;


    void muteOutput() {
        System.setOut(new PrintStream(outputStream));
    }
    void unmuteOutput() {
        System.setOut(originalOut);
    }

    @BeforeEach
    void setUp() {
        muteOutput();
        controller = new GameController();
        weaponFactory = new WeaponFactory();
        playerCharacterFactory = new PlayerCharacterFactory(controller.getTurnsQueue());
        enemyFactory = new EnemyFactory(controller.getTurnsQueue());

    }

    @AfterEach
    void tearDown() {
        unmuteOutput();
    }

    @Test
    void testControllerSetUp() throws InterruptedException {
        controller.setUp();
        Thread.sleep(6000);
        assertTrue(controller.thereAreCharactersWaiting());
        System.out.println(controller.getPhaseInfo());
    }

    @Test
    void testStart() throws InterruptedException {
        // Check the game doesn't start when we don't have neither enemies nor player characters
        controller.start();
        assertFalse(controller.thereAreCharactersWaiting());

        // Check the game doesn't start when we don't have player characters
        controller.createEnemy();
        controller.start();
        assertFalse(controller.thereAreCharactersWaiting());

        // Check the game does start when our player characters and enemies are ready
        controller.createKnightPlayer();
        controller.createIronSword();
        controller.equipWeaponToCharacter(weaponFactory.createIronSword(), controller.getCharacterPlayerList().get(0));
        controller.start();
        Thread.sleep(6000);
        assertTrue(controller.thereAreCharactersWaiting());

    }

    @Test
    void testCheckEndGame() throws InterruptedException {
        GameController.random = new Random(0);
        controller.createSilverSword();
        controller.createKnightPlayer();

        controller.createEnemy();
        controller.createEnemy();

        controller.start();
        Thread.sleep(10);
        ICharacter character = controller.getCurrentCharacter();
        if (!character.isPlayable()) {
            Thread.sleep(100);
            controller.waitCharacter();
            controller.nextCharacterInQueue();
            character = controller.getCurrentCharacter();
        }
        assertTrue(character.isPlayable());

        controller.equipWeaponToCharacter(weaponFactory.createSilverSword(), (IPlayerCharacter) character);
        controller.getEnemyList().get(0).receiveDamage(99999);  // Defeat one enemy
        controller.getEnemyList().get(1).receiveDamage(99999);  // Defeat all enemies

    }

    @Test
    void testEnd() {
        GameController.random = new Random(0);
        controller.createKnightPlayer();
        controller.createEnemy();
        controller.start();
        controller.getEnemyList().get(0).receiveDamage(99999);  // Defeat all enemies

        controller = new GameController();
        GameController.random = new Random(0);
        controller.createKnightPlayer();
        controller.createEnemy();
        controller.start();
        controller.getCharacterPlayerList().get(0).receiveDamage(99999);  // Defeat all player characters
    }

    @Test
    void testPlayerAttackCharacter() throws InterruptedException {
        GameController.random = new Random(0);

        controller.createKnightPlayer();
        controller.createEnemy();
        controller.start();

        ICharacter character;

        Thread.sleep(1000);
        controller.nextCharacterInQueue();
        character = controller.getCurrentCharacter();
        if (!character.isPlayable()) {
            Thread.sleep(100);
            controller.waitCharacter();
            Thread.sleep(10);
            character = controller.getCurrentCharacter();
        }

        assertTrue(character.isPlayable());
        controller.createSilverSword();
        controller.equipWeaponToCharacter(weaponFactory.createSilverSword(), (IPlayerCharacter) character);
        assertEquals(controller.getEnemyList().get(0).getHp(), controller.getEnemyList().get(0).getMaxHp());
        controller.attackCharacter(controller.getEnemyList().get(0));
        assertTrue(controller.getEnemyList().get(0).getHp() < controller.getEnemyList().get(0).getMaxHp());
    }

    @Test
    void testWaitCharacter() throws InterruptedException {
        controller.createKnightPlayer();
        controller.createEnemy();
        ICharacter currentCharacter;

        // We make sure that the knight has less weight, so he should wait less than the enemy.
        assertTrue(controller.getCharacterPlayerList().get(0).getWeight() < controller.getEnemyList().get(0).getWeight());

        // Start, get the current character, assert that is a player character because we created it first
        controller.start();
        Thread.sleep(100);
        currentCharacter = controller.getCurrentCharacter();
        assertTrue(currentCharacter.isPlayable());

        // Make sure when we wait, the current character is now null
        controller.waitCharacter();
        assertNull(controller.getCurrentCharacter());
        // Make sure after a while, the current character is not null
        Thread.sleep(100);
        assertNotNull(controller.getCurrentCharacter());

        // Make sure this player without weapon is ready before the enemy

        currentCharacter = controller.getCurrentCharacter();
        assertTrue(currentCharacter.isPlayable());

        // Make sure that if we stay some time and then wait, the enemy will be ready before.
        Thread.sleep(2000);
        controller.waitCharacter();
        Thread.sleep(1000);
        assertNull(controller.getCurrentCharacter());  // We have to take the character manually
        controller.nextCharacterInQueue();
        currentCharacter = controller.getCurrentCharacter();  //
        assertFalse(currentCharacter.isPlayable());
    }

    @Test
    void equipWeaponToCharacterTest() {
        controller.createKnightPlayer();
        IPlayerCharacter playerCharacter = controller.getCharacterPlayerList().get(0);

        // Trying to equip a weapon that isn't in the inventory
        controller.equipWeaponToCharacter(weaponFactory.createIronSword(), playerCharacter);
        assertNull(controller.getCharacterPlayerList().get(0).getEquippedWeapon());

        // Trying to equip a weapon that is in the inventory, but the character can't equip
        controller.createNormalStaff();
        assertTrue(controller.getItemSet().contains(weaponFactory.createNormalStaff()));
        controller.equipWeaponToCharacter(weaponFactory.createNormalStaff(), playerCharacter);
        assertTrue(controller.getItemSet().contains(weaponFactory.createNormalStaff()));

        // Trying to equip a weapon that is in the inventory, and the character can equip
        controller.createIronSword();
        assertTrue(controller.getItemSet().contains(weaponFactory.createIronSword()));
        controller.equipWeaponToCharacter(weaponFactory.createIronSword(), playerCharacter);
        assertFalse(controller.getItemSet().contains(weaponFactory.createIronSword()));
        assertEquals(weaponFactory.createIronSword(), controller.getCharacterPlayerList().get(0).getEquippedWeapon());

        // Trying to equip a weapon that is in the inventory, the character can equip,
        // and it's different to the one he was equipping
        controller.createSilverSword();
        assertTrue(controller.getItemSet().contains(weaponFactory.createSilverSword()));
        assertFalse(controller.getItemSet().contains(weaponFactory.createIronSword()));
        controller.equipWeaponToCharacter(weaponFactory.createSilverSword(), playerCharacter);
        assertFalse(controller.getItemSet().contains(weaponFactory.createSilverSword()));
        assertTrue(controller.getItemSet().contains(weaponFactory.createIronSword()));
        assertEquals(weaponFactory.createSilverSword(), controller.getCharacterPlayerList().get(0).getEquippedWeapon());

        // Trying to equip a weapon that is in the inventory, the character can equip,
        // and that he was actually already equipping an equal weapon
        controller.createSilverSword();
        assertTrue(controller.getItemSet().contains(weaponFactory.createSilverSword()));
        controller.equipWeaponToCharacter(weaponFactory.createSilverSword(), playerCharacter);
        assertTrue(controller.getItemSet().contains(weaponFactory.createSilverSword()));
        assertEquals(weaponFactory.createSilverSword(), controller.getCharacterPlayerList().get(0).getEquippedWeapon());
    }

    @Test
    void testStoreItem() {
        IItem testItem1 = new Sword("Masamune", 20, 10);
        IItem testItem2 = new Knife("Daga", 10, 2);
        IItem testItem3 = new Knife("Bisturi", 15, 1);
        assertTrue(controller.getItemSet().isEmpty());

        // Test when we store an item the item set contains it and only it.
        controller.storeItem(testItem1);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertFalse(controller.getItemSet().contains(testItem2));
        assertFalse(controller.getItemSet().contains(testItem3));

        controller.storeItem(testItem2);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertFalse(controller.getItemSet().contains(testItem3));

        // Test when we can store an item multiple times without problem
        controller.storeItem(testItem3);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        controller.storeItem(testItem3);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        controller.storeItem(testItem3);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));
    }

    @Test
    void testGetItemSet() {
        IItem testItem1 = new Sword("Masamune", 20, 10);
        IItem testItem2 = new Knife("Daga", 10, 2);
        IItem testItem3 = new Knife("Bisturi", 15, 1);
        assertTrue(controller.getItemSet().isEmpty());

        // Test the item set has all we've stored
        controller.storeItem(testItem1);
        controller.storeItem(testItem2);
        controller.storeItem(testItem3);
        controller.storeItem(testItem3);
        controller.storeItem(testItem3);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        // Test the item set has all we've stored and not taken
        controller.takeItem(testItem1);
        assertFalse(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        controller.dropItem(testItem2);
        assertFalse(controller.getItemSet().contains(testItem1));
        assertFalse(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        // Test the item set can survive when we have an item multiple times
        controller.takeItem(testItem3);
        assertFalse(controller.getItemSet().contains(testItem1));
        assertFalse(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));
        controller.dropItem(testItem3);
        assertFalse(controller.getItemSet().contains(testItem1));
        assertFalse(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));
        controller.takeItem(testItem3);
        assertFalse(controller.getItemSet().contains(testItem1));
        assertFalse(controller.getItemSet().contains(testItem2));
        assertFalse(controller.getItemSet().contains(testItem3));
        assertTrue(controller.getItemSet().isEmpty());
    }

    @Test
    void testTakeItem() {
        IItem testItem1 = new Sword("Masamune", 20, 10);
        IItem testItem2 = new Knife("Daga", 10, 2);
        IItem testItem3 = new Knife("Bisturi", 15, 1);
        assertTrue(controller.getItemSet().isEmpty());

        controller.storeItem(testItem1);
        controller.storeItem(testItem2);
        controller.storeItem(testItem3);
        controller.storeItem(testItem3);
        controller.storeItem(testItem3);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        // Test we can take once an item that we stored once
        assertEquals(testItem1, controller.takeItem(testItem1));
        assertNull(controller.takeItem(testItem1));

        assertEquals(testItem2, controller.takeItem(testItem2));
        assertNull(controller.takeItem(testItem2));

        // Test we can take three times an item that we stored three times
        assertEquals(testItem3, controller.takeItem(testItem3));
        assertNotNull(controller.takeItem(testItem3));
        assertEquals(testItem3, controller.takeItem(testItem3));
        assertNull(controller.takeItem(testItem3));

    }

    @Test
    void testDropItem() {
        IItem testItem1 = new Sword("Masamune", 20, 10);
        IItem testItem2 = new Knife("Daga", 10, 2);
        IItem testItem3 = new Knife("Bisturi", 15, 1);
        assertTrue(controller.getItemSet().isEmpty());

        controller.storeItem(testItem1);
        controller.storeItem(testItem2);
        controller.storeItem(testItem3);
        controller.storeItem(testItem3);
        controller.storeItem(testItem3);
        assertFalse(controller.getItemSet().isEmpty());
        assertTrue(controller.getItemSet().contains(testItem1));
        assertTrue(controller.getItemSet().contains(testItem2));
        assertTrue(controller.getItemSet().contains(testItem3));

        // Test we can drop once an item that we stored once
        controller.dropItem(testItem1);
        assertFalse(controller.getItemSet().contains(testItem1));

        controller.dropItem(testItem2);
        assertFalse(controller.getItemSet().contains(testItem2));

        // Test we can drop three times an item that we stored three times
        controller.dropItem(testItem3);
        assertTrue(controller.getItemSet().contains(testItem3));
        controller.dropItem(testItem3);
        assertTrue(controller.getItemSet().contains(testItem3));
        controller.dropItem(testItem3);
        assertFalse(controller.getItemSet().contains(testItem3));
    }

    @Test
    void createEnemyWhenFullEnemyParty() {
        assertTrue(controller.getEnemyList().isEmpty());

        for (int i = 0; i < 10; i++) {
            controller.createEnemy();
        }
    }

    @RepeatedTest(300)
    void createEnemy() {

        Random randomSeedGenerator = new Random();
        long seed = randomSeedGenerator.nextLong();

        Enemy expectedEnemy = enemyFactory.createEnemy(new Random(seed));
        assertFalse(controller.getEnemyList().contains(expectedEnemy));

        GameController.random = new Random(seed);
        controller.createEnemy();
        assertTrue(controller.getEnemyList().contains(expectedEnemy));


    }

    void checkEmptyParty() {
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));
    }

    @Test
    void createPlayerWhenFullParty() {
        checkEmptyParty();

        // Check cannot put thief player when party is full
        controller.createWhiteMagePlayer();
        controller.createBlackMagePlayer();
        controller.createEngineerPlayer();
        controller.createKnightPlayer();
        controller.createThiefPlayer();

        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));


        setUp();
        checkEmptyParty();

        // Check cannot put knight when party is full
        controller.createWhiteMagePlayer();
        controller.createBlackMagePlayer();
        controller.createEngineerPlayer();
        controller.createThiefPlayer();
        controller.createKnightPlayer();

        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));

        setUp();
        checkEmptyParty();

        // Check cannot put engineer when party is full
        controller.createWhiteMagePlayer();
        controller.createBlackMagePlayer();
        controller.createKnightPlayer();
        controller.createThiefPlayer();
        controller.createEngineerPlayer();

        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));

        setUp();
        checkEmptyParty();

        // Check cannot put black mage when party is full
        controller.createWhiteMagePlayer();
        controller.createEngineerPlayer();
        controller.createKnightPlayer();
        controller.createThiefPlayer();
        controller.createBlackMagePlayer();

        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));

        setUp();
        checkEmptyParty();

        // Check cannot put white mage when party is full
        controller.createBlackMagePlayer();
        controller.createEngineerPlayer();
        controller.createKnightPlayer();
        controller.createThiefPlayer();
        controller.createWhiteMagePlayer();

        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));
    }

    @Test
    void createWhiteMagePlayer() {
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));
        controller.createWhiteMagePlayer();
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createWhiteMageCharacter("Rhys")));

    }

    @Test
    void createBlackMagePlayer() {
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
        controller.createBlackMagePlayer();
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createBlackMageCharacter("Soren")));
    }

    @Test
    void createEngineerPlayer() {
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
        controller.createEngineerPlayer();
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createEngineerCharacter("Jill")));
    }

    @Test
    void createKnightPlayer() {
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
        controller.createKnightPlayer();
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createKnightCharacter("Titania")));
    }

    @Test
    void createThiefPlayer() {
        assertFalse(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));
        controller.createThiefPlayer();
        assertTrue(controller.getCharacterPlayerList().contains(playerCharacterFactory.createThiefCharacter("Sothe")));
    }

    @Test
    void createBronzeAxe() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createBronzeAxe()));

        controller.createBronzeAxe();
        assertTrue(items.contains(weaponFactory.createBronzeAxe()));
    }

    @Test
    void createIronAxe() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createIronAxe()));

        controller.createIronAxe();
        assertTrue(items.contains(weaponFactory.createIronAxe()));
    }

    @Test
    void createSteelAxe() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSteelAxe()));

        controller.createSteelAxe();
        assertTrue(items.contains(weaponFactory.createSteelAxe()));
    }

    @Test
    void createSilverAxe() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSilverAxe()));

        controller.createSilverAxe();
        assertTrue(items.contains(weaponFactory.createSilverAxe()));
    }

    @Test
    void createBronzeBow() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createBronzeBow()));

        controller.createBronzeBow();
        assertTrue(items.contains(weaponFactory.createBronzeBow()));
    }

    @Test
    void createIronBow() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createIronBow()));

        controller.createIronBow();
        assertTrue(items.contains(weaponFactory.createIronBow()));
    }

    @Test
    void createSteelBow() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSteelBow()));

        controller.createSteelBow();
        assertTrue(items.contains(weaponFactory.createSteelBow()));
    }

    @Test
    void createSilverBow() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSilverBow()));

        controller.createSilverBow();
        assertTrue(items.contains(weaponFactory.createSilverBow()));
    }

    @Test
    void createBronzeKnife() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createBronzeKnife()));

        controller.createBronzeKnife();
        assertTrue(items.contains(weaponFactory.createBronzeKnife()));
    }

    @Test
    void createIronKnife() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createIronKnife()));

        controller.createIronKnife();
        assertTrue(items.contains(weaponFactory.createIronKnife()));
    }

    @Test
    void createSteelKnife() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSteelKnife()));

        controller.createSteelKnife();
        assertTrue(items.contains(weaponFactory.createSteelKnife()));
    }

    @Test
    void createSilverKnife() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSilverKnife()));

        controller.createSilverKnife();
        assertTrue(items.contains(weaponFactory.createSilverKnife()));
    }

    @Test
    void createBronzeSword() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createBronzeSword()));

        controller.createBronzeSword();
        assertTrue(items.contains(weaponFactory.createBronzeSword()));
    }

    @Test
    void createIronSword() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createIronSword()));

        controller.createIronSword();
        assertTrue(items.contains(weaponFactory.createIronSword()));
    }

    @Test
    void createSteelSword() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSteelSword()));

        controller.createSteelSword();
        assertTrue(items.contains(weaponFactory.createSteelSword()));
    }

    @Test
    void createSilverSword() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createSilverSword()));

        controller.createSilverSword();
        assertTrue(items.contains(weaponFactory.createSilverSword()));
    }

    @Test
    void createNormalStaff() {
        Set<IItem> items = controller.getItemSet();
        assertFalse(items.contains(weaponFactory.createNormalStaff()));

        controller.createNormalStaff();
        assertTrue(items.contains(weaponFactory.createNormalStaff()));
    }
}