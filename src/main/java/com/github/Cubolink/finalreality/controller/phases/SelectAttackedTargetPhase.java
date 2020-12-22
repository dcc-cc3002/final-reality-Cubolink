package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.model.character.ICharacter;


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
            gameController.attackCharacter(target);
        } else {
            prevPhase();
        }

    }

}
