package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.controller.HpPopUpController;
import fr.univ_amu.heromanager.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.io.IOException;

/**
 * View manager of Hp Pop Up window
 *
 * @see fr.univ_amu.heromanager.model.gui.HpPopUpModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see HpPopUpController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class HpPopUpView implements View {

    private static HpPopUpView instance;

    /**
     * Constructor of this class.
     * Should NEVER BE CALLED directly, always called by fr.univ_amu.heromanager.model class HpPopUpModel.
     * Initialize pop up fr.univ_amu.heromanager.view and associated text
     *
     * @param maxHP character's max health points
     */
    public HpPopUpView(int maxHP) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/hp_pop_up.fxml"));
            stage.setTitle("HeroManager - New HP");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(CharacterView.getInstance().getStage());
            stage.show();

            instance = this;

            HpPopUpController.getInstance().maxHPText.setText("/ " + maxHP);
        } catch (IOException e) {
            new Dialog("An error occurred while opening HP View fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static HpPopUpView getInstance() {
        return instance;
    }

    /**
     * To close pop up
     */
    public void closeStage() {
        Stage stage = ((Stage) HpPopUpController.getInstance().maxHPText.getScene().getWindow());
        stage.close();
    }
}
