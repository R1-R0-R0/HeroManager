package view;

import controller.HpPopUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HpPopUpView {

    private static HpPopUpView instance;

    public HpPopUpView(int maxHP) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/hp_pop_up.fxml"));
            stage.setTitle("HeroManager - New HP");
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

    public static HpPopUpView getInstance() {
        return instance;
    }

    public void closeStage() {
        Stage stage = ((Stage) HpPopUpController.getInstance().maxHPText.getScene().getWindow());
        stage.close();
    }
}
