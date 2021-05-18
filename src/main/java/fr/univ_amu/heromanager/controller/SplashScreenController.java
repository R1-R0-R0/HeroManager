package fr.univ_amu.heromanager.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Splash Screen
 *
 * @see fr.univ_amu.heromanager.view.SplashScreenView associated class fr.univ_amu.heromanager.view (MVC pattern)
 */
public class SplashScreenController implements Initializable {

    private static SplashScreenController instance;

    /**
     * Progress bar
     */
    @FXML
    public ProgressBar loadingProgress;
    public GridPane window;
    public Pane img;

    /**
     * @return instance of this class
     */
    public static SplashScreenController getInstance() {
        return instance;
    }

    /**
     * Entry of fr.univ_amu.heromanager.controller. When called, loads all necessary attributes for the software to work properly
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }
}
