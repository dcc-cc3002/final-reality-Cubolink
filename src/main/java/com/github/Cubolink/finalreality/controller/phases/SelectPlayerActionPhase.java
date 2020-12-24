package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectPlayerActionPhase extends AbstractPhase {
    private final ArrayList<IGamePhase> possibleActions =
            new ArrayList<>(Arrays.asList(
                    new SelectAttackedTargetPhase(gameController, this),
                    new SelectWeaponPhase(gameController, this),
                    new WaitNextTurnPhase(gameController)));

    public SelectPlayerActionPhase(GameController gameController) {
        super(gameController, null);
    }

    @Override
    public void nextPhase() {
        IGamePhase nextGamePhase = possibleActions.get(getModuleOfIndexPointedByCursor(possibleActions.size()));
        gameController.setCurrentGamePhase(nextGamePhase);
    }

    @Override
    public String getPhaseInfo() {
        return "Select the action you want "+gameController.getCurrentCharacter().getName()+ " to do.";
    }

    @Override
    public String[] getPhaseOptions() {
        String[] strings = new String[3];
        strings[0] = "Attack";
        strings[1] = "Equip";
        strings[2] = "Wait";

        int selectedOption = getModuleOfIndexPointedByCursor(possibleActions.size());
        strings[selectedOption] = "*" + strings[selectedOption];

        return strings;

    }
}
