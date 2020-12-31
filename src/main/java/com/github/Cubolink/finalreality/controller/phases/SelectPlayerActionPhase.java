package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.IGameController;

/**
 * Select Player Action Phase. When the game is in this phase, the player has to choose what a player character will do.
 * The player can choose between attack, equip or wait as actions for the character to do.
 */
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
        void execute(IGameController gameController, IGamePhase previousPhase);
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
    public SelectPlayerActionPhase(IGameController gameController) {
        super(gameController, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        actions[getModuleOfIndexPointedByCursor(actions.length)].execute(gameController, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhaseInfo() {
        return "Select the action you want "+gameController.getCurrentCharacter().getName()+ " to do.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPhaseOptions() {
        String[] strings = phaseTransitionNames.clone();

        int selectedOption = getModuleOfIndexPointedByCursor(actions.length);
        strings[selectedOption] = "*" + strings[selectedOption];

        return strings;

    }

}
