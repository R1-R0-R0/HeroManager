package model.gui;

import model.Dice;
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
            System.err.println("Regex is wrong");
            return;
        }

        List<Dice> dices = dicesParser(sDices);

        int total = 0;

        for (Dice dice : dices) {
            total += dice.roll();
        }

        DiceView.getInstance().setDiceResult(total);
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
