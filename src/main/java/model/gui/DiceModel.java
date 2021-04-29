package model.gui;

import javafx.scene.control.Alert;
import model.Dice;
import utils.gui.Dialog;
import view.DiceView;

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
            Dialog dialog = new Dialog(
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
        Dialog dialog = new Dialog(
                Alert.AlertType.INFORMATION,
                "How dice thorwer works ?",
                "Type in given text field the formula of what dices you want.\n" +
                        "For an example, if you want 3 dices of 20, and 2 dices of 10:\n" +
                        "3d10 + 2d10.\n" +
                        "Then you just have to click on roll button!\n" +
                        "If your entered formula don't respect syntax, an error will pop up to warn you.");

        dialog.show();
    }

    public static DiceModel getInstance() {
        return instance;
    }
}
