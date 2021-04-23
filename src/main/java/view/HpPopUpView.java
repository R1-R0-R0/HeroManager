package view;

import controller.HpPopUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HpPopUpView {

    private HpPopUpView instance;

    public HpPopUpView(int maxHP) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/hp_pop_up.fxml"));
            stage.setTitle("HeroManager - New HP");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            instance = this;

            HpPopUpController.getInstance().maxHPText.setText("/ " + maxHP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HpPopUpView getInstance() {
        return instance;
    }
}
