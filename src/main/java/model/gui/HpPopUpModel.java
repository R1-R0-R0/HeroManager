package model.gui;

import javafx.scene.control.Alert;
import utils.gui.Dialog;
import view.HpPopUpView;

public class HpPopUpModel {

    public void defineNewHP(String newHP) {
        try {
            int hp = Integer.parseInt(newHP);

            System.out.println("hp = " + hp);

            if (hp < 0)
                new Dialog(Alert.AlertType.ERROR, "Invalid amount", "New HP value can't be lower than 0").showAndWait();
            else if (hp > 100)// TODO : Set MAX HP later
                new Dialog(Alert.AlertType.ERROR, "Invalid amount", "New HP value can't be higher than 100").showAndWait();
            else {
                HpPopUpView.getInstance().closeStage();
                CharacterModel.getInstance().hpBarOnClickEventDone(hp);
            }
        } catch (NumberFormatException e) {
            Dialog dialog = new Dialog(Alert.AlertType.ERROR, "Invalid entry", "Please type a number.");
            dialog.showAndWait();
        }
    }

    public void cancelNewHP() {
        HpPopUpView.getInstance().closeStage();
        CharacterModel.getInstance().hpBarOnClickEventCancel();
    }
}
