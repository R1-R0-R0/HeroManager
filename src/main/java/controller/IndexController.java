package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.IndexModel;
import view.IndexView;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    public static IndexController instance;
    public static IndexView view;
    public static IndexModel model;

    @FXML public Button simpleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        view = new IndexView();
        model = new IndexModel();
    }

    @FXML
    public void simpleButtonClick() {
        model.simpleButtonTrigger();
    }
}
