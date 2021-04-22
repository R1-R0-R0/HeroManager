package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.IndexModel;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    private static IndexController instance;
    public static IndexModel model;

    @FXML public Button simpleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new IndexModel();

        instance = this;
    }

    @FXML
    public void simpleButtonClick() {
        model.simpleButtonTrigger();
    }

    public static IndexController getInstance() {
        return instance;
    }
}
