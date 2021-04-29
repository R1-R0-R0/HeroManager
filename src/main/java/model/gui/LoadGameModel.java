package model.gui;

import view.LoadGameView;
import view.NewGameView;

public class LoadGameModel {

    private static LoadGameModel instance;

    public LoadGameModel() {
        instance = this;
        new LoadGameView();
    }

    public void close() {
        LoadGameView.getInstance().close();
    }

    public static LoadGameModel getInstance() {
        return instance;
    }
}
