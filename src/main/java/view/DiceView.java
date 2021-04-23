package view;

import controller.DiceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DiceView {

    private static DiceView instance;

    public DiceView() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/dice.fxml"));
            stage.setTitle("HeroManager - Dices");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDiceResult(int result) {
        DiceController.getInstance().diceResultText.setText(String.valueOf(result));
    }

    public static DiceView getInstance() {
        return instance;
    }
}
