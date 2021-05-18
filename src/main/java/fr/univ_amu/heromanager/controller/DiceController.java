package fr.univ_amu.heromanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import fr.univ_amu.heromanager.model.gui.DiceModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Dice Thrower fr.univ_amu.heromanager.view.
 * Implements all listeners of buttons
 *
 * @see fr.univ_amu.heromanager.view.DiceView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see DiceModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 */
public class DiceController implements Controller {

    private static DiceController instance;

    /**
     * Text field who user enter formula
     */
    @FXML
    public TextField dicesText;

    /**
     * Text of dice roll result
     */
    @FXML
    public Text diceResultText;

    /**
     * Text of detailed dice roll result
     */
    @FXML
    public Text diceResultDetailsText;

    /**
     * @return instance of this class
     */
    public static DiceController getInstance() {
        return instance;
    }

    /**
     * Entry of fr.univ_amu.heromanager.controller
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    /**
     * Method called when roll button is clicked
     */
    @FXML
    public void rollDicesButtonOnClick() {
        DiceModel.getInstance().rollDices(dicesText.getText());
    }

    /**
     * Method called when how it works button is clicked
     */
    @FXML
    public void howItWorksButtonOnClick() {
        DiceModel.getInstance().howItWorksEvent();
    }
}
