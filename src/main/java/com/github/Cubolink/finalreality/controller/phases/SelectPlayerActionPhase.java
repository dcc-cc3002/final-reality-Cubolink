package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;

public class SelectPlayerActionPhase extends AbstractPhase {
    /**
     * Private interface to provide phase transition execution methods.
     */
    private interface phaseTransition {
        /**
         * Executes a controller phase transition.
         * @param gameController to which the phase will be associated.
         * @param previousPhase the phase that was previous to the new phase
         */
        void execute(GameController gameController, IGamePhase previousPhase);
    }

    /**
     * Array of possible phase transitions
     */
    private static final phaseTransition[] actions = new phaseTransition[] {
            // Attack
            SelectAttackedTargetPhase::new,
            // Equip
            SelectWeaponPhase::new,
            // Wait
            (gameController, previousPhase) -> {
                gameController.waitCharacter();
                new WaitNextTurnPhase(gameController);
            }
    };

    /**
     * Array of the options (phase transitions names)
     */
    private static final String[] phaseTransitionNames  = new String[] {
            "Attack",
            "Equip",
            "Wait"
    };

    /**
     * Select Player Action Phase default constructor.
     * @param gameController which this gamePhase is associated to.
     */
    public SelectPlayerActionPhase(GameController gameController) {
        super(gameController, null);
    }

    @Override
    public void nextPhase() {
        actions[getModuleOfIndexPointedByCursor(actions.length)].execute(gameController, this);
        // IGamePhase nextGamePhase = possibleActions.get(getModuleOfIndexPointedByCursor(possibleActions.size()));
        // gameController.setCurrentGamePhase(nextGamePhase);
    }

    @Override
    public String getPhaseInfo() {
        return "Select the action you want "+gameController.getCurrentCharacter().getName()+ " to do.";
    }

    @Override
    public String[] getPhaseOptions() {
        String[] strings = phaseTransitionNames.clone();

        int selectedOption = getModuleOfIndexPointedByCursor(actions.length);
        strings[selectedOption] = "*" + strings[selectedOption];

        return strings;

    }

}
