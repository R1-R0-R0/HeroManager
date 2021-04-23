package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HpPopUpController implements Initializable {

    private static HpPopUpController instance;

    @FXML
    public Text maxHPText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    public static HpPopUpController getInstance() {
        return instance;
    }
}
