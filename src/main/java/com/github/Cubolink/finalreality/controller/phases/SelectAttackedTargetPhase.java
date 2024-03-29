package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.gui.spritegroups.CursorSprite;
import com.github.Cubolink.finalreality.model.character.ICharacter;

import java.util.List;

/**
 * Select Attacked Target Phase. When the game is in this phase, the player has to choose a target to attack.
 *
 */
public class SelectAttackedTargetPhase extends AbstractPhase {
    /**
     * Instantiates a SelectAttackedTargetPhase.
     * @param gameController which will be in this phase.
     * @param previousPhase the phase the game was in before changing to this.
     */
    public SelectAttackedTargetPhase(IGameController gameController, IGamePhase previousPhase) {
        super(gameController, previousPhase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        List<ICharacter> aliveCharacters = gameController.getAliveCharactersList();

        ICharacter target = aliveCharacters.get(getModuleOfIndexPointedByCursor(aliveCharacters.size()));

        if (target.isAlive()) {
            System.out.println(gameController.getCurrentCharacter().getName() + " tries to attack " + target.getName());
            gameController.attackCharacter(target);
            IGamePhase newPhase = new WaitNextTurnPhase(gameController);
            gameController.setCurrentGamePhase(newPhase);
            System.out.println("Attack performed.\n");
        } else {
            prevPhase();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhaseInfo() {
        return "Select the target you want "+ gameController.getCurrentCharacter().getName()+ " to Attack";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPhaseOptions() {
        List<ICharacter> characters = gameController.getAliveCharactersList();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCursorSpritePosition(CursorSprite cursorSprite) {
        //int cursor = getModuleOfIndexPointedByCursor(gameController.getTotalNumberOfCharacters());
        int cursor = getModuleOfIndexPointedByCursor(gameController.getAliveCharactersList().size());

        cursorSprite.setCx(gameController.getSpriteCharacterCx(cursor));
        cursorSprite.setCy(gameController.getSpriteCharacterCy(cursor));
    }
}
