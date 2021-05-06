package controller;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.gui.MenuModel;

/**
 * Entry of HeroManager app
 */
public class Main extends Application {

    /**
     * Var used to define app logo, and usable everywhere in program
     */
    public final static Image APP_LOGO = new Image(Main.class.getResourceAsStream("/images/icon/logo.png"));

    /**
     * Main entry of program, used to launch JavaFX
     * @param args entry arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Main entry of JavaFX, called by main() when program starts up
     * @param primaryStage Unused parameter, first stage of program
     */
    @Override
    public void start(Stage primaryStage) {
        new MenuModel();
    }
}
