package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.model.character.ICharacter;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;

import java.util.List;

public class WaitNextTurnPhase extends AbstractPhase{


    public WaitNextTurnPhase(GameController gameController) {
        super(gameController, null);
    }

    @Override
    public void nextPhase() {
        if (gameController.getCurrentCharacter().isPlayable()) {
            gameController.setCurrentGamePhase(new SelectPlayerActionPhase(gameController));

        } else {
            int index = GameController.random.nextInt();
            List<IPlayerCharacter> playerCharacters = gameController.getCharacterPlayerList();
            IPlayerCharacter target = playerCharacters.get(index % playerCharacters.size());

            gameController.attackCharacter(target);

            gameController.setCurrentGamePhase(new WaitNextTurnPhase(gameController));
        }
    }

    @Override
    public boolean isWaitingPhase() {
        return true;
    }
}
