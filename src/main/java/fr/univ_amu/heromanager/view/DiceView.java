package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.controller.DiceController;
import fr.univ_amu.heromanager.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Dice fr.univ_amu.heromanager.view
 *
 * @see fr.univ_amu.heromanager.model.gui.DiceModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see DiceController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class DiceView implements View {

    private static DiceView instance;

    /**
     * Constructor of this class.
     * Should NOT BE CALLED directly, its fr.univ_amu.heromanager.model calls it automatically.
     * When called, init stage and its fxml
     */
    public DiceView() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/dice.fxml"));
            stage.setTitle("HeroManager - Dice Thrower");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            // stage.setResizable(false);
            stage.setMinWidth(400);
            stage.setMinHeight(263);
            stage.setMaxHeight(263);
            stage.show();

            instance = this;
        } catch (IOException e) {
            new Dialog("An error occurred while opening Dice fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static DiceView getInstance() {
        return instance;
    }

    /**
     * Updates dice roll result display by given value
     *
     * @param result value to display
     */
    public void setDiceResult(int result) {
        DiceController.getInstance().diceResultText.setText(String.valueOf(result));
    }

    /**
     * Updates dice roll details result by given list of values
     *
     * @param results list of each dices
     */
    public void setDiceDetails(List<Integer> results) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        sb.append("(");

        for (int result : results) {
            if (first) first = false;
            else sb.append(", ");

            sb.append(result);
        }

        sb.append(")");

        DiceController.getInstance().diceResultDetailsText.setText(sb.toString());
    }
}
