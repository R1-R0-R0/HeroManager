package controller;

import javafx.fxml.Initializable;

/**
 * Interface to define a controller in gui mvc pattern
 */
public interface Controller extends Initializable {

    /**
     * @param <T> class implementing its interface
     * @return instance of implemented class
     */
    static <T> T getInstance() {
        return null;
    }
}
