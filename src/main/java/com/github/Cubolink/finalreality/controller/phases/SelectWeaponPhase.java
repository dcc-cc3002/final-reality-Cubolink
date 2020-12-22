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
}
