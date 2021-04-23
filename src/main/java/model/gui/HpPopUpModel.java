package model.gui;

import view.HpPopUpView;

public class HpPopUpModel {

    public void defineNewHP(String newHP) {
        try {
            int hp = Integer.parseInt(newHP);

            if (hp < 0 || hp > 100) {
                return; // TODO : Do error pop-up
            }

            HpPopUpView.getInstance().closeStage();
            CharacterModel.getInstance().hpBarOnClickEventDone(hp);
        } catch (NumberFormatException e) {
            // TODO : Error pop-up enter valid number
        }
    }

    public void cancelNewHP() {
        HpPopUpView.getInstance().closeStage();
        CharacterModel.getInstance().hpBarOnClickEventCancel();
    }
}
