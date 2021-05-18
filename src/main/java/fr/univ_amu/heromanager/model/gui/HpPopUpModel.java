package fr.univ_amu.heromanager.model.gui;

import javafx.scene.control.Alert;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.HpPopUpView;

/**
 * Model of HP Pop Up View.
 * Used when user clicks on hp bar to update character's health
 *
 * @see HpPopUpView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see fr.univ_amu.heromanager.controller.HpPopUpController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class HpPopUpModel implements Model {

    private static HpPopUpModel instance;

    private int maxHP;

    /**
     * Constructor of this class, called to show up hp pop up
     *
     * @param maxHP character's max health
     */
    public HpPopUpModel(int maxHP) {
        instance = this;

        this.maxHP = maxHP;
        new HpPopUpView(maxHP);
    }

    /**
     * @return instance of this class
     */
    public static HpPopUpModel getInstance() {
        return instance;
    }

    /**
     * When used, check if entered value is valid (value > 0 AND value < maxHP),
     * if yes, value is returned to character fr.univ_amu.heromanager.view to update
     *
     * @param newHP new amount of hp to define
     */
    public void defineNewHP(String newHP) {
        try {
            int hp = Integer.parseInt(newHP);

            if (hp < 0)
                new Dialog(Alert.AlertType.ERROR, "Invalid amount", "New HP value can't be lower than 0").showAndWait();
            else if (hp > maxHP)
                new Dialog(Alert.AlertType.ERROR, "Invalid amount", "New HP value can't be higher than " + maxHP).showAndWait();
            else {
                HpPopUpView.getInstance().closeStage();
                CharacterModel.getInstance().hpBarOnClickEventDone(hp);
            }
        } catch (NumberFormatException e) {
            Dialog dialog = new Dialog(Alert.AlertType.ERROR, "Invalid entry", "Please type a number.");
            dialog.showAndWait();
        }
    }

    /**
     * Called when cancel button is pressed, allows to cancel health modification and go back to character's fr.univ_amu.heromanager.view
     */
    public void cancelNewHP() {
        HpPopUpView.getInstance().closeStage();
        CharacterModel.getInstance().hpBarOnClickEventCancel();
    }
}
