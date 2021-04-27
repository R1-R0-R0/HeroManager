package model.gui;

import view.CharacterView;
import view.MenuView;

public class MenuModel {

    private static MenuModel instance;

    public MenuModel() {
        instance = this;
    }

    public static MenuModel getInstance() {
        return instance;
    }

    public void resumeGame() {

        // Job expl = new Job("Hiraye", "A human", 100, 100, 1, 2, 3, 4, 5, )
        new CharacterView();
        MenuView.getInstance().close();
    }

    public void newGame() {
        // TODO : New window listing every characters created (Like Stellaris)
    }

    public void loadGame() {
        // TODO : New window listing of saved games
    }
}
