package fr.univ_amu.heromanager;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.SplashScreenView;

import java.util.Objects;

/**
 * Entry of HeroManager app
 */
public class Main extends Application {

    /**
     * Var used to define app logo, and usable everywhere in program
     */
    public final static Image APP_LOGO = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/icon/logo.png")));

    /**
     * Main entry of program, used to launch JavaFX
     *
     * @param args entry arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Main entry of JavaFX, called by main() when program starts up
     *
     * @param primaryStage Unused parameter, first stage of program
     */
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("Hello! Java version: " + javaVersion + ", JavaFX version: " + javafxVersion);

        SplashScreenView splashScreen = new SplashScreenView();

        try {
            System.out.println("INIT DATABASE");
            long time = System.currentTimeMillis();
            HeroManagerDB.init();
            System.out.println("DATABASE LOADED IN " + (System.currentTimeMillis() - time) + " MS");

            Runtime.getRuntime().addShutdownHook(new Thread(HeroManagerDB::save));

            System.out.println("LAUNCHING VIEW");
            splashScreen.openMenu();
        } catch (Exception e) {
            new Dialog("A critical error occurred", e).showAndWait();
        }
    }
}
