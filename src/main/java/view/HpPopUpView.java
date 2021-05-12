package view;

import controller.HpPopUpController;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View manager of Hp Pop Up window
 *
 * @see model.gui.HpPopUpModel associated class model (MVC pattern)
 * @see HpPopUpController associated class controller (MVC pattern)
 */
public class HpPopUpView implements View {

    private static HpPopUpView instance;

    /**
     * Constructor of this class.
     * Should NEVER BE CALLED directly, always called by model class HpPopUpModel.
     * Initialize pop up view and associated text
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
            e.printStackTrace();
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
