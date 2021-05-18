package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Splash Screen
 *
 * @see view.SplashScreenView associated class view (MVC pattern)
 */
public class SplashScreenController implements Initializable {

    private static SplashScreenController instance;

    /**
     * Progress bar
     */
    @FXML
    public ProgressBar loadingProgress;

    /**
     * @return instance of this class
     */
    public static SplashScreenController getInstance() {
        return instance;
    }

    /**
     * Entry of controller. When called, loads all necessary attributes for the software to work properly
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }
}