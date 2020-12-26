package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class EnemyIA /*implements PropertyChangeListener */{
    private final GameController gameController;

    public EnemyIA(GameController gameController) {
        this.gameController = gameController;
    }

//    @Override
    public void propertyChange(PropertyChangeEvent evt){
        action();
    }

    public void action(){
        assert !(gameController.getCurrentCharacter().isPlayable());

        System.out.println("enemy attacking");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = Math.abs(GameController.random.nextInt());
        List<IPlayerCharacter> playerCharacters = gameController.getCharacterPlayerList();
        IPlayerCharacter target = playerCharacters.get(index % playerCharacters.size());

        gameController.attackCharacter(target);
    }
}
