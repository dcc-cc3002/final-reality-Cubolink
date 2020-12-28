package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class EnemyIA implements PropertyChangeListener {
    private final GameController gameController;

    public EnemyIA(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        System.out.println("Enemy listens the New phase");
        if (gameController.inEnemyPhase()) {
            System.out.println("In Enemy phase => Enemy do stuff");
            gameController.next();// action();
        } else {
            System.out.println("Not in enemy phase => Enemy doesn't do anything");
        }
    }

    public void action(){
        assert !(gameController.getCurrentCharacter().isPlayable());

        System.out.println("enemy attacking-------------------------------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = Math.abs(GameController.random.nextInt());
        List<IPlayerCharacter> playerCharacters = gameController.getCharacterPlayerList();
        IPlayerCharacter target = playerCharacters.get(index % playerCharacters.size());

        gameController.attackCharacter(target);
        System.out.println("---------------------------enemy attacked");
    }
}
