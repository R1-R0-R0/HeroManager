package model.gui;

import javafx.scene.control.Alert;
import model.Dice;
import utils.gui.PopUpDialog;
import view.DiceView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DiceModel {

    private static DiceModel instance;

    public DiceModel() {
        instance = this;

        new DiceView();
    }

    public void rollDices(String sDices) {
        sDices = sDices.replaceAll(" ", "");
        sDices = sDices.toLowerCase();

        if (!sDices.matches("^(([0-9]+d[0-9]+)|([0-9]+d[0-9]+\\+)+([0-9]+d[0-9]+))$")) {
            PopUpDialog dialog = new PopUpDialog(
                    Alert.AlertType.ERROR,
                    "Wrong dice syntax",
                    "Entered syntax is incorrect.\nYour formula need to be like: \"2d3 + 3d4\" or \"1d2+3D3\" ");
            dialog.showAndWait();
            return;
        }

        List<Dice> dices = dicesParser(sDices);

        int total = 0;
        List<Integer> detailsResult = new ArrayList<>();

        for (Dice dice : dices) {
            int res = dice.roll();
            total += res;
            detailsResult.add(res);
        }

        DiceView.getInstance().setDiceResult(total);
        DiceView.getInstance().setDiceDetails(detailsResult);
    }

    private List<Dice> dicesParser(String dices) {
        List<Dice> diceList = new ArrayList<>();

        String[] dice = dices.split("\\+");

        for (String d : dice) {
            String[] diceParams = d.split("d");
            int nbOfDices = Integer.parseInt(diceParams[0]);
            int diceRange = Integer.parseInt(diceParams[1]);

            for (int i = 0; i < nbOfDices; i++) {
                diceList.add(new Dice(diceRange));
            }
        }

        return diceList;
    }

    public void howItWorksEvent() {

    }

    public static DiceModel getInstance() {
        return instance;
    }
}
