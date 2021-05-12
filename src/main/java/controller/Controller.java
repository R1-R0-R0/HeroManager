package controller;

import javafx.fxml.Initializable;

/**
 * Interface to define a controller in gui mvc pattern
 */
public interface Controller extends Initializable {

    static <T> T getInstance() {
        return null;
    }
}
