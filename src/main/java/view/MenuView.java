package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gui.MenuModel;

import java.io.IOException;

public class MenuView {

    private static MenuView instance;
    private Stage stage;

    public MenuView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage.setTitle("HeroManager");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            new MenuModel();
            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }

    public static MenuView getInstance() {
        return instance;
    }
}
