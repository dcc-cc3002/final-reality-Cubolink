package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.controller.listeners.EndGameHandler;
import com.github.Cubolink.finalreality.controller.listeners.FallenCharacterHandler;
import com.github.Cubolink.finalreality.model.character.enemy.EnemyFactory;
import com.github.Cubolink.finalreality.model.character.enemy.IEnemyFactory;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacterFactory;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacterFactory;
import com.github.Cubolink.finalreality.model.items.IItem;
import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.items.weapon.IWeaponFactory;
import com.github.Cubolink.finalreality.model.items.weapon.WeaponFactory;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController implements IGameController{
    // Event Handlers
    private final EndGameHandler endGameHandler = new EndGameHandler(this);
    private final FallenCharacterHandler fallenCharacterHandler = new FallenCharacterHandler(this, endGameHandler);
    // Flow attributes
    public static Random random;
    private static Inventory playerInventory;
    private static BlockingQueue<ICharacter> turnsQueue;
    private static List<ICharacter> characters;
    private static List<IPlayerCharacter> playerCharactersList;
    private static List<Enemy> enemiesList;
    // Creation tools
    private static IWeaponFactory weaponFactory;
    private static IEnemyFactory enemyFactory;
    private static IPlayerCharacterFactory playerFactory;
    // Creation rules
    private static final int MAX_PLAYER_CHARACTER_NUM = 4;
    private static final int MAX_ENEMY_CHARACTER_NUM = 6;
    // State variables
    private int current_number_of_player_characters;
    private int current_number_of_enemy_characters;
    private ICharacter currentCharacter;

    public GameController() {
        random = new Random();
        playerInventory = new Inventory();
        turnsQueue = new LinkedBlockingQueue<>();
        characters = new ArrayList<>();
        playerCharactersList = new ArrayList<>();
        enemiesList = new ArrayList<>();

        weaponFactory = new WeaponFactory();
        enemyFactory = new EnemyFactory(turnsQueue);
        playerFactory = new PlayerCharacterFactory(turnsQueue);

        current_number_of_player_characters = 0;
        current_number_of_enemy_characters = 0;
    }

    @Override
    public void start() {
        // Check both enemy and player parties have at least one member each
        if (current_number_of_enemy_characters == 0 || current_number_of_player_characters == 0) {
            return;
        }

        // Now we can put all characters to join to the turns queue
        for (ICharacter character: characters) {
            character.waitTurn();
        }
    }

    @Override
    public void end() {

    }

    @Override
    public boolean isTheGameFinished() {
        int alivePlayerCharacterNumber = 0;
        int aliveEnemyNumber = 0;

        for (ICharacter character : characters) {
            if (character.isAlive()){
                if (playerCharactersList.contains(character)) {
                    alivePlayerCharacterNumber++;
                } else if (enemiesList.contains(character)) {
                    aliveEnemyNumber++;
                }
            }
        }

        current_number_of_player_characters = alivePlayerCharacterNumber;
        current_number_of_enemy_characters = aliveEnemyNumber;

        // else: no player characters alive remain or no enemies alive remain
        return alivePlayerCharacterNumber == 0 || aliveEnemyNumber == 0;
    }

    @Override
    public BlockingQueue<ICharacter> getTurnsQueue() {
        return turnsQueue;
    }

    @Override
    public void nextCharacterInQueue() {
        currentCharacter = turnsQueue.poll();
    }

    @Override
    public List<IPlayerCharacter> getCharacterPlayerList() {
        return playerCharactersList;
    }

    @Override
    public List<Enemy> getEnemyList() {
        return enemiesList;
    }

    private void getCharacterInfo(IPlayerCharacter playerCharacter) {
        String s = playerCharacter.getName()
                + " (" + playerCharacter.getCharacterClass().getClassname() + ").\n"
                + "LIFE: " + playerCharacter.getHp() + "/" + playerCharacter.getMaxHp() + "\n"
                + "MANA: " + playerCharacter.getCharacterClass().getMana() + "\n"
                + "WEAPON: " + playerCharacter.getEquippedWeapon();
        System.out.println(s);
    }

    private void getCharacterInfo(Enemy enemy) {
        String s = enemy.getName()
                + " (Enemy). "
                + "LIFE: " + enemy.getHp() + "/" + enemy.getMaxHp();
        System.out.println(s);
    }

    @Override
    public void getCharacterInfo() {
        if (currentCharacter.isPlayable()) {
            getCharacterInfo((IPlayerCharacter) currentCharacter);
        } else {
            getCharacterInfo((Enemy) currentCharacter);
        }
    }

    @Override
    public void waitCharacter() {
        currentCharacter.waitTurn();
    }

    @Override
    public void playerAttackCharacter(ICharacter objectiveCharacter) {
        currentCharacter.attack(objectiveCharacter);
        currentCharacter.waitTurn();
    }

    @Override
    public void equipWeaponToCharacter(IWeapon weapon, IPlayerCharacter playerCharacter) {
        IWeapon previousEquippedWeapon = playerCharacter.getEquippedWeapon();

        // We check if we're trying to equip the same weapon that he is already equipping.
        if (weapon.equals(previousEquippedWeapon)) {
            return;
        }

        // We check if the weapon we're trying to equip is on the inventory
        IWeapon weaponToEquip = (IWeapon) takeItem(weapon);
        if (weaponToEquip == null) {
            return;
        }

        // We try to equip the weapon
        playerCharacter.equip(weapon);
        // Then we check if the character managed to equip the weapon
        if (weapon.equals(playerCharacter.getEquippedWeapon())) {
            // The equip action ended successfully

            // Now we can store the previous weapon the character was equipping, if it was
            if (previousEquippedWeapon == null) {
                return;
            }
            storeItem(previousEquippedWeapon);
        } else {
            // The character could not equip the weapon, so we store it back to the inventory
            storeItem(weapon);
        }

    }

    @Override
    public void storeItem(IItem item) {
        playerInventory.storeItem(item);
    }

    @Override
    public Set<IItem> getItemSet() {
        return playerInventory.getItemSet();
    }

    @Override
    public IItem takeItem(IItem item) {
        return playerInventory.takeItem(item);
    }

    @Override
    public void dropItem(IItem item) {
        playerInventory.dropItem(item);
    }


    private void characterCreationSetUp(ICharacter character) {
        character.addDefeatEventListener(fallenCharacterHandler);
        characters.add(character);
    }

    private void enemyCreationSetUp(Enemy enemy) {
        characterCreationSetUp(enemy);
        current_number_of_enemy_characters++;
        enemiesList.add(enemy);
    }
    private void playerCharacterCreationSetUp(IPlayerCharacter playerCharacter) {
        characterCreationSetUp(playerCharacter);
        current_number_of_player_characters++;
        playerCharactersList.add(playerCharacter);
    }

    private boolean canCreateEnemyPlayer() {
        return current_number_of_enemy_characters < MAX_ENEMY_CHARACTER_NUM;
    }
    private boolean canCreateCharacterPlayer() {
        return current_number_of_player_characters < MAX_PLAYER_CHARACTER_NUM;
    }

    @Override
    public void createEnemy() {
        if (canCreateEnemyPlayer()) {
            enemyCreationSetUp(enemyFactory.createEnemy(random));
        }
    }

    @Override
    public void createWhiteMagePlayer() {
        if (canCreateCharacterPlayer()) {
            playerCharacterCreationSetUp(playerFactory.createWhiteMageCharacter("Rhys"));
        }

    }

    @Override
    public void createBlackMagePlayer() {
        if (canCreateCharacterPlayer()) {
            playerCharacterCreationSetUp(playerFactory.createBlackMageCharacter("Soren"));
        }
    }

    @Override
    public void createEngineerPlayer() {
        if (canCreateCharacterPlayer()) {
            playerCharacterCreationSetUp(playerFactory.createEngineerCharacter("Jill"));
        }

    }

    @Override
    public void createKnightPlayer() {
        if (canCreateCharacterPlayer()) {
            playerCharacterCreationSetUp(playerFactory.createKnightCharacter("Titania"));
        }
    }

    @Override
    public void createThiefPlayer() {
        if (canCreateCharacterPlayer()) {
            playerCharacterCreationSetUp(playerFactory.createThiefCharacter("Sothe"));
        }
    }

    @Override
    public void createBronzeAxe() {
        storeItem(weaponFactory.createBronzeAxe());
    }

    @Override
    public void createIronAxe() {
        storeItem(weaponFactory.createIronAxe());
    }

    @Override
    public void createSteelAxe() {
        storeItem(weaponFactory.createSteelAxe());
    }

    @Override
    public void createSilverAxe() {
        storeItem(weaponFactory.createSilverAxe());
    }

    @Override
    public void createBronzeBow() {
        storeItem(weaponFactory.createBronzeBow());
    }

    @Override
    public void createIronBow() {
        storeItem(weaponFactory.createIronBow());
    }

    @Override
    public void createSteelBow() {
        storeItem(weaponFactory.createSteelBow());
    }

    @Override
    public void createSilverBow() {
        storeItem(weaponFactory.createSilverBow());
    }

    @Override
    public void createBronzeKnife() {
        storeItem(weaponFactory.createBronzeKnife());
    }

    @Override
    public void createIronKnife() {
        storeItem(weaponFactory.createIronKnife());
    }

    @Override
    public void createSteelKnife() {
        storeItem(weaponFactory.createSteelKnife());
    }

    @Override
    public void createSilverKnife() {
        storeItem(weaponFactory.createSilverKnife());
    }

    @Override
    public void createBronzeSword() {
        storeItem(weaponFactory.createBronzeSword());
    }

    @Override
    public void createIronSword() {
        storeItem(weaponFactory.createIronSword());
    }

    @Override
    public void createSteelSword() {
        storeItem(weaponFactory.createSteelSword());
    }

    @Override
    public void createSilverSword() {
        storeItem(weaponFactory.createSilverSword());
    }

    @Override
    public void createNormalStaff() {
        storeItem(weaponFactory.createNormalStaff());
    }
}
