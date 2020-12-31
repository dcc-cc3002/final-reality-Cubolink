package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.IGameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.List;

/**
 * Select Weapon Phase. When the game is in this phase, the player has to choose a weapon to equip.
 */
public class SelectWeaponPhase extends AbstractPhase {

    /**
     * Instantiates a SelectWeaponPhase.
     * @param gameController which will be in this phase.
     * @param previousPhase the phase the game was in before changing to this.
     */
    public SelectWeaponPhase(IGameController gameController, IGamePhase previousPhase) {
        super(gameController, previousPhase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        List<IWeapon> weapons = gameController.getWeaponList();
        if (weapons.size() == 0) {
            return;
        }
        IWeapon selectedWeapon = weapons.get(getModuleOfIndexPointedByCursor(weapons.size()));
        gameController.equipWeaponToCharacter(selectedWeapon, (IPlayerCharacter) gameController.getCurrentCharacter());

        prevPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhaseInfo() {
        return "Select the weapon you want " + gameController.getCurrentCharacter().getName() + " to equip.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getPhaseOptions() {
        List<IWeapon> weapons = gameController.getWeaponList();
        int indexPointed = getModuleOfIndexPointedByCursor(weapons.size());

        String[] strings = new String[gameController.getWeaponList().size()];

        for (int i = 0; i < weapons.size(); i++) {
            if (i == indexPointed) {
                strings[i] = "*" + weapons.get(i).getName();
            } else {
                strings[i] = weapons.get(i).getName();
            }
        }
        return strings;
    }
}
