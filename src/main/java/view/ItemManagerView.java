package view;

import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;

import java.io.IOException;

public class ItemManagerView {

    private Stage stage;
    private static ItemManagerView instance;

    public ItemManagerView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_manager.fxml"));
            stage.setTitle("HeroManager - Item Manager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> ItemManagerModel.getInstance().returnToMenu());
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }

    public static ItemManagerView getInstance() {
        return instance;
    }
}
