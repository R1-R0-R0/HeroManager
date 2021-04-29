package controller;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import model.gui.MenuModel;
import view.CharacterView;
import view.MenuView;

public class Main extends Application {

    public final static Image APP_LOGO = new Image(Main.class.getResourceAsStream("/images/icon/logo.png"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MenuModel();
    }
}
