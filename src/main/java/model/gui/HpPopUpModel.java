package model.gui;

import view.HpPopUpView;

public class HpPopUpModel {

    public void defineNewHP(String newHP) {
        int hp = Integer.parseInt(newHP);

        if (hp < 0 || hp > 100) {
            return; // TODO : Do error pop-up
        }

        CharacterModel.getInstance().hpBarOnClickEvent();
    }

    public void cancelNewHP() {
        HpPopUpView.getInstance().closeStage();
        CharacterModel.getInstance().hpBarOnClickEventCancel();
    }
}
