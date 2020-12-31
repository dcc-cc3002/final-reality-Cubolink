package com.github.Cubolink.finalreality.controller;

import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * EnemyIA Handler. Listens the turn events and execute actions when the controller is in an enemy phase.
 */
public class EnemyIA implements PropertyChangeListener {
    private final IGameController gameController;

    /**
     * Default constructor for the EnemyIA. Instantiates the IA.
     * @param gameController to which the IA is associated.
     */
    public EnemyIA(IGameController gameController) {
        this.gameController = gameController;
    }

    /**
     * This method is called when the gameController has come out of a waiting phase and has started a new turn,
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        System.out.println("Enemy listens the New phase");
        if (gameController.inEnemyTurn()) {
            System.out.println("In Enemy phase => Enemy do stuff");
            gameController.next();
        } else {
            System.out.println("Not in enemy phase => Enemy doesn't do anything");
        }
    }

    /**
     * The EnemyIA performs an action
     */
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
