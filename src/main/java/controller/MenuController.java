package controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private static MenuController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    public static MenuController getInstance() {
        return instance;
    }
}
