package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.gui.DiceModel;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DiceController implements Initializable {

    private static DiceController instance;

    @FXML
    public TextField dicesText;
    @FXML
    public Label diceResultText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    public static DiceController getInstance() {
        return instance;
    }

    @FXML
    public void rollDicesButtonOnClick() {
        DiceModel.getInstance().rollDices(dicesText.getText());
    }

    @FXML
    public void howItWorksButtonOnClick() {

    }
}
