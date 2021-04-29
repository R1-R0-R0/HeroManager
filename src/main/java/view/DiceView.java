package view;

import controller.DiceController;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DiceView {

    private static DiceView instance;

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
            e.printStackTrace();
        }
    }

    public void setDiceResult(int result) {
        DiceController.getInstance().diceResultText.setText(String.valueOf(result));
    }

    public void setDiceDetails(List<Integer> results) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        sb.append("(");

        for (int result : results) {
            if (first)  first = false;
            else        sb.append(", ");

            sb.append(result);
        }

        sb.append(")");

        DiceController.getInstance().diceResultDetailsText.setText(sb.toString());
    }

    public static DiceView getInstance() {
        return instance;
    }
}
