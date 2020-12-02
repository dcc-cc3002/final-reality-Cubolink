package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.controller.factories.*;
import com.github.Cubolink.finalreality.controller.listeners.FallenCharacterHandler;
import com.github.Cubolink.finalreality.model.IItem;
import com.github.Cubolink.finalreality.model.character.Enemy;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController implements IGameController{
    private static final BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();
    private static final List<ICharacter> characters = new ArrayList<>();
    private static ICharacter currentCharacter;

    private static final IWeaponFactory weaponFactory = new WeaponFactory();
    private static final IEnemyFactory enemyFactory = new EnemyFactory(turnsQueue);
    private static final IPlayerCharacterFactory playerFactory = new PlayerCharacterFactory(turnsQueue);

    private static final Inventory playerInventory = new Inventory();
    private final FallenCharacterHandler fallenCharacterHandler = new FallenCharacterHandler(this);
    private static final int MAX_PLAYER_CHARACTER_NUM = 4;
    private static int current_number_of_player_characters = 0;
    public static final Random random = new Random();

    @Override
    public void checkEndGame(/*ICharacter character*/) {
        boolean playerCharacterAliveRemaining = false;
        boolean enemiesAliveRemaining = false;

        for (ICharacter character : characters) {
            if (character.isAlive()){
                if (character.isPlayable()) {
                    playerCharacterAliveRemaining = true;
                } else {
                    enemiesAliveRemaining = true;
                }
            }
        }

        if (playerCharacterAliveRemaining && enemiesAliveRemaining){
            return;
        }  // else: no player characters alive remain or no enemies alive remain
        System.out.println("The Game Ended.");

        if (playerCharacterAliveRemaining){
            System.out.println("The player wins.");
        } else {
            System.out.println("The player was defeated.");
        }
    }

    @Override
    public void nextCharacterInQueue() {
        currentCharacter = turnsQueue.poll();
        getCharacterInfo();
    }

    private void getCharacterInfo(IPlayerCharacter playerCharacter) {
        String s = playerCharacter.getName()
                + " (" + playerCharacter.getCharacterClass().getClassname() + "). "
                + "LIFE: " + playerCharacter.getHp() + "/" + playerCharacter.getMaxHp() + " "
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
    public void playerAttackCharacter(ICharacter objectiveCharacter) {
        currentCharacter.attack(objectiveCharacter);
    }

    @Override
    public void waitCharacter() {
        currentCharacter.waitTurn();
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

    @Override
    public void equipWeaponToCharacter(IWeapon weapon, IPlayerCharacter playerCharacter) {
        playerCharacter.equip(weapon);
    }


    private void characterCreationSetUp(ICharacter character) {
        character.addDefeatEventListener(fallenCharacterHandler);
        characters.add(character);
    }

    private void enemyCreationSetUp(Enemy enemy) {
        characterCreationSetUp(enemy);
    }
    private void playerCharacterCreationSetUp(IPlayerCharacter playerCharacter) {
        characterCreationSetUp(playerCharacter);
        current_number_of_player_characters++;
    }

    @Override
    public void createEnemy(){
        enemyCreationSetUp(enemyFactory.createEnemy(random));
    }


    private boolean canCreateCharacterPlayer(){
        return current_number_of_player_characters >= MAX_PLAYER_CHARACTER_NUM;
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
    public void createEngineer() {
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

    public void createAxe() {
        storeItem(new Axe("Hacha", 15, 10));
    }

    public void createBow() {
        storeItem(new Bow("Arco", 7, 5));
    }

    public void createKnife() {
        storeItem(new Knife("Cuchillo", 5, 2));
    }

    public void createStaff() {
        storeItem(new Staff("Báculo", 1, 10, 5));
    }

    public void createSword() {
        storeItem(new Sword("Espada", 10, 7));
    }
}
