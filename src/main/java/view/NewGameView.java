package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.gui.MenuModel;

import java.io.IOException;

public class NewGameView {

    private Stage stage;
    private static NewGameView instance;

    public NewGameView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/new_game_menu.fxml"));
            stage.setTitle("HeroManager - New Game");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MenuView.getInstance().getStage());
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NewGameView getInstance() {
        return instance;
    }

    public void close() {
        stage.close();
    }
}
