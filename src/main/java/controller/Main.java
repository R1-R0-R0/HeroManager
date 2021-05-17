package controller;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.gui.MenuModel;
import utils.files.HeroManagerDB;
import utils.gui.Dialog;

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
        System.out.println("BONJOUR");
        try {
            System.out.println("INIT DATABASE");
            long time = System.currentTimeMillis();
            HeroManagerDB.init();
            System.out.println("DATABASE LOADED IN " + (System.currentTimeMillis() - time) + " MS");

            System.out.println("LAUNCHING VIEW");
            new MenuModel();
        } catch (Exception e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage()).showAndWait();
            System.exit(1);
        }
    }
}
