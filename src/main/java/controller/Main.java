package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import view.CharacterView;
import view.MenuView;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // new CharacterModel(null);
        new MenuView();
    }
}
