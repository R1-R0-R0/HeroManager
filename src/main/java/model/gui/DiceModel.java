package model.gui;

import javafx.scene.control.Alert;
import model.Dice;
import utils.gui.Dialog;
import view.DiceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of Dice Thrower view.
 * Implements all logic and reactions of view
 *
 * @see DiceView associated class view (MVC pattern)
 * @see controller.DiceController associated class controller (MVC pattern)
 */
public class DiceModel implements Model {

    private static DiceModel instance;

    /**
     * Constructor of this class.
     * When called, shows view.
     */
    public DiceModel() {
        instance = this;

        new DiceView();
    }

    /**
     * @return instance of this class
     */
    public static DiceModel getInstance() {
        return instance;
    }

    /**
     * With given formula, allows to rolls asked dices and gives result to the view, and details of result.
     * If formula is incorrect, error dialog will show up.
     * Example of formula : 3d4 + 1d20 (3 dices with range of 4, and 1 dice with range of 20)
     *
     * @param sDices formula to process
     * @see Dice implementation of a dice
     */
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

    /**
     * Allows to convert a string from a list of wanted dices
     *
     * @param dices string to parse
     * @return list of parsed dices
     */
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

    /**
     * When called, shows up dialog to explain how dice thrower works
     */
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
}
