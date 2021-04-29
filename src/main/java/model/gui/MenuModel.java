package model.gui;

import view.CharacterView;
import view.MenuView;

public class MenuModel {

    private static MenuModel instance;

    public MenuModel() {
        instance = this;
        new MenuView();
    }

    public static MenuModel getInstance() {
        return instance;
    }

    public void resumeGame() {

        // Job expl = new Job("Hiraye", "A human", 100, 100, 1, 2, 3, 4, 5, )
        new CharacterModel(null);
        MenuView.getInstance().close();
    }

    public void newGame() {
        new NewGameModel();
    }

    public void loadGame() {
        new LoadGameModel();
    }

    public void openItemManager() {
        MenuView.getInstance().close();
        new ItemManagerModel();
    }

    public void quitProgram() {
        System.exit(0);
    }
}
