package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.enemy.Enemy;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.character.player.PlayerCharacter;


import java.util.List;

public class SelectAttackedTargetPhase extends AbstractPhase {
    public SelectAttackedTargetPhase(GameController gameController, IGamePhase previousPhase) {
        super(gameController, previousPhase);
    }

    @Override
    public void nextPhase() {
        List<ICharacter> characters = gameController.getCharacterList();
        ICharacter target = characters.get(gameController.getIndexPointedByCursor() % characters.size());
        if (target.isAlive()) {
            System.out.println(gameController.getCurrentCharacter().getName() + " tries to attack "+target.getName());
            gameController.attackCharacter(target);
            IGamePhase newPhase = new WaitNextTurnPhase(gameController);
            gameController.setCurrentGamePhase(newPhase);
            System.out.println("Attack performed.\n");
        } else {
            prevPhase();
        }

    }

    @Override
    public String getPhaseInfo() {
        return "Select the target you want "+ gameController.getCurrentCharacter().getName()+ " to Attack";
    }

    @Override
    public String[] getPhaseOptions() {
        List<ICharacter> characters = gameController.getCharacterList();
        String[] strings = new String[characters.size()];

        for (int i = 0; i < characters.size(); i++) {
            if (i == getModuleOfIndexPointedByCursor(characters.size())) {
                strings[i] = "*"+characters.get(i).getName();
            } else {
                strings[i] = characters.get(i).getName();
            }
        }

        return strings;
    }
}
