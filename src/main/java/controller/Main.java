package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/index.fxml"));
            primaryStage.setTitle("HeroManager");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
