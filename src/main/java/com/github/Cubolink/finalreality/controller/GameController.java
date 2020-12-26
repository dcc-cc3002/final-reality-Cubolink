package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.controller.listeners.CharacterReadyInQueueHandler;
import com.github.Cubolink.finalreality.controller.listeners.EndGameHandler;
import com.github.Cubolink.finalreality.controller.listeners.FallenCharacterHandler;
import com.github.Cubolink.finalreality.controller.listeners.NextTurnHandler;
import com.github.Cubolink.finalreality.controller.phases.IGamePhase;
import com.github.Cubolink.finalreality.controller.phases.WaitNextTurnPhase;
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
    private final CharacterReadyInQueueHandler characterReadyInQueueHandler = new CharacterReadyInQueueHandler(this);
    private final NextTurnHandler nextTurnHandler = new NextTurnHandler(this);
    // Flow attributes
    private static IGamePhase currentGamePhase;
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
    private short indexPointedByCursor = 0;

    public static class Mutex {
        private boolean isLocked = false;

        public synchronized void lock() {
            while (isLocked) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            isLocked = true;
        }

        public synchronized void unlock() {
            isLocked = false;
            notify();
        }
    }

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

    public int getMaxPlayerCharacterNum() {
        return MAX_PLAYER_CHARACTER_NUM;
    }
    public int getMaxEnemyCharacterNum() {
        return MAX_ENEMY_CHARACTER_NUM;
    }

    public void setUp() {
        createKnightPlayer();
        createThiefPlayer();
        createEngineerPlayer();
        createBlackMagePlayer();

        createBronzeSword();
        createBronzeKnife();
        createBronzeBow();
        createBronzeAxe();
        createNormalStaff();

        for (int i = 0; i < MAX_ENEMY_CHARACTER_NUM; i++) {
            createEnemy();
        }
        start();
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
        new WaitNextTurnPhase(this);
    }

    public void setCurrentGamePhase(IGamePhase newPhase) {

        currentGamePhase = newPhase;
        resetIndexPointedByCursor();
    }
    public boolean inWaitingPhase() {
        return currentGamePhase.isWaitingPhase();
    }
    public String getPhaseInfo() {
        return currentGamePhase.getPhaseInfo();
    }
    public String[] getPhaseOptions() {
        return currentGamePhase.getPhaseOptions();
    }
    public String[] getPlayerCharactersInfo() {
        String[] charactersInfo = new String[current_number_of_player_characters];
        for (int i = 0; i < current_number_of_player_characters; i++) {
            charactersInfo[i] = getCharacterInfo(playerCharactersList.get(i));
        }
        return charactersInfo;
    }
    public String[] getEnemyCharactersInfo() {
        String[] charactersInfo = new String[current_number_of_enemy_characters];
        for (int i = 0; i < current_number_of_enemy_characters; i++) {
            charactersInfo[i] = getCharacterInfo(enemiesList.get(i));
        }
        return charactersInfo;
    }

    public short getIndexPointedByCursor() {
        return indexPointedByCursor;
    }

    public void resetIndexPointedByCursor() {
        indexPointedByCursor = 0;
    }

    public void moveCursorRight() {
        indexPointedByCursor += 1;
    }
    public void moveCursorLeft() {
        indexPointedByCursor -= 1;
    }

    @Override
    public void aCharacterIsWaiting() {
        if (currentGamePhase.isWaitingPhase() && thereAreCharactersWaiting()) {
            currentGamePhase.nextPhase();
        }
    }
    public boolean thereAreCharactersWaiting() {
        return !turnsQueue.isEmpty();
    }

    public void next() {
        currentGamePhase.nextPhase();
    }
    public void prev() {
        currentGamePhase.prevPhase();
    }

    @Override
    public void end() {
        System.out.println("Game Over");
        if (current_number_of_player_characters > 0) {
            System.out.println("The Player has won");
        } else {
            System.out.println("The Enemy has beaten the player");
        }
    }

    @Override
    public boolean isTheGameFinished() {
        int alivePlayerCharacterNumber = 0;
        int aliveEnemyNumber = 0;

        for (ICharacter character : characters) {
            if (character.isAlive()){
                if (character.isPlayable()) {
                    alivePlayerCharacterNumber++;
                } else {
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
    public ICharacter getCurrentCharacter() {
        return currentCharacter;
    }

    @Override
    public void nextCharacterInQueue() {
        currentCharacter = turnsQueue.poll();
    }

    public List<ICharacter> getCharacterList() {
        return characters;
    }

    @Override
    public List<IPlayerCharacter> getCharacterPlayerList() {
        return playerCharactersList;
    }

    @Override
    public List<Enemy> getEnemyList() {
        return enemiesList;
    }

    private String getCharacterInfo(IPlayerCharacter playerCharacter) {
        return playerCharacter.getName()
                + " (" + playerCharacter.getCharacterClass().getClassname() + ").\n"
                + "LIFE: " + playerCharacter.getHp() + "/" + playerCharacter.getMaxHp() + "\n"
                + "MANA: " + playerCharacter.getCharacterClass().getMana() + "\n"
                + "WEAPON: " + playerCharacter.getEquippedWeapon();
    }

    private String getCharacterInfo(Enemy enemy) {
        return enemy.getName()
                + " (Enemy).\n"
                + "LIFE: " + enemy.getHp() + "/" + enemy.getMaxHp();
    }

    @Override
    public String getCharacterInfo() {
        if (currentCharacter.isPlayable()) {
            return getCharacterInfo((IPlayerCharacter) currentCharacter);
        } else {
            return getCharacterInfo((Enemy) currentCharacter);
        }
    }

    @Override
    public void waitCharacter() {
        currentCharacter.waitTurn();
        currentCharacter = null;
    }

    @Override
    public void attackCharacter(ICharacter objectiveCharacter) {
        currentCharacter.attack(objectiveCharacter);
        waitCharacter();
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

    public List<IWeapon> getWeaponList() {
        return playerInventory.getWeaponList();
    }

    private void characterCreationSetUp(ICharacter character) {
        character.addDefeatEventListener(fallenCharacterHandler);
        character.addReadyInQueueEventListener(characterReadyInQueueHandler);
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
