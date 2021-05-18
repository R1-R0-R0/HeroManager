package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.gui.HpPopUpModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of HP Pop Up.
 * Initialized when user want to update health points character
 *
 * @see view.HpPopUpView associated class view (MVC pattern)
 * @see HpPopUpModel associated class model (MVC pattern)
 */
public class HpPopUpController implements Controller {

    private static HpPopUpController instance;

    /**
     * Text who represents character's max health
     */
    @FXML
    public Text maxHPText;

    /**
     * Input to enter new value of health. Must be between 0 and character's max health
     */
    @FXML
    public TextField newHPInput;

    /**
     * @return instance of this class
     */
    public static HpPopUpController getInstance() {
        return instance;
    }

    /**
     * Entry point of controller
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    /**
     * Called when SET button is clicked
     */
    @FXML
    public void setButtonOnClick() {
        HpPopUpModel.getInstance().defineNewHP(newHPInput.getText());
    }

    /**
     * Called when CANCEL button is clicked
     */
    @FXML
    public void cancelButtonOnClick() {
        HpPopUpModel.getInstance().cancelNewHP();
    }
}
