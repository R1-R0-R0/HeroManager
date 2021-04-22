package view;

import controller.IndexController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexView {

    private static IndexView instance;

    public IndexView() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/index.fxml"));
            stage.setTitle("HeroManager");
            stage.setScene(new Scene(root));
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateButtonText(String text) {
        IndexController.getInstance().simpleButton.setText(text);
    }

    public static IndexView getInstance() {
        return instance;
    }
}
