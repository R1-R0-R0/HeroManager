package view;

import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View manager of Load Game view
 *
 * @see model.gui.LoadGameModel associated class model (MVC pattern)
 * @see controller.LoadGameController associated class controller (MVC pattern)
 */
public class LoadGameView {

    private static LoadGameView instance;
    private Stage stage;

    /**
     * Constructor of this class.
     * Should NEVER BE CALLED directly, always called by its model class.
     * When called, init fxml view
     */
    public LoadGameView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/load_game_menu.fxml"));
            stage.setTitle("HeroManager - Load Game");
            stage.getIcons().add(Main.APP_LOGO);
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

    /**
     * @return instance of this class
     */
    public static LoadGameView getInstance() {
        return instance;
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
