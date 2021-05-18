package view;

import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.gui.MenuModel;
import utils.gui.Dialog;

import java.io.IOException;

/**
 * @Deprecated User will just directly create his character. This class will be removed soon.
 */
public class NewGameView implements View {

    private Stage stage;
    private static NewGameView instance;

    /**
     * @Deprecated User will just directly create his character. This class will be removed soon.
     */
    public NewGameView() {
        try {
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("/fxml/new_game_menu.fxml"));
            stage.setTitle("HeroManager - New Game");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MenuView.getInstance().getStage());
            stage.show();

            instance = this;
        } catch (IOException e) {
            new Dialog("An error occurred while opening New Game view", e).showAndWait();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public static NewGameView getInstance() {
        return instance;
    }

    public void close() {
        stage.close();
    }
}
