package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.gui.HpPopUpModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HpPopUpController implements Initializable {

    private static HpPopUpController instance;
    private static HpPopUpModel model;

    @FXML
    public Text maxHPText;
    @FXML
    public TextField newHPInput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        model = new HpPopUpModel();
    }

    public static HpPopUpController getInstance() {
        return instance;
    }

    public static HpPopUpModel getModel() {
        return model;
    }

    @FXML
    public void setButtonOnClick() {
        model.defineNewHP(newHPInput.getText());
    }

    @FXML
    public void cancelButtonOnClick() {
        model.cancelNewHP();
    }
}
