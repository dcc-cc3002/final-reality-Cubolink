package com.github.Cubolink.finalreality.controller.phases;

import com.github.Cubolink.finalreality.controller.GameController;
import com.github.Cubolink.finalreality.model.character.player.IPlayerCharacter;
import com.github.Cubolink.finalreality.model.items.weapon.concreteweapon.IWeapon;

import java.util.List;

public class SelectWeaponPhase extends AbstractPhase {
    public SelectWeaponPhase(GameController gameController, IGamePhase previousPhase) {
        super(gameController, previousPhase);
    }

    @Override
    public void nextPhase() {
        List<IWeapon> weapons = gameController.getWeaponList();
        IWeapon selectedWeapon = weapons.get(gameController.getIndexPointedByCursor());
        gameController.equipWeaponToCharacter(selectedWeapon, (IPlayerCharacter) gameController.getCurrentCharacter());

        prevPhase();
    }

    @Override
    public String getPhaseInfo() {
        return "Select the weapon you want the character to equip.";
    }

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
