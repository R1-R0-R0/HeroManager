package model.gui;

import view.NewGameView;

public class NewGameModel {
    
    private static NewGameModel instance;

    public NewGameModel() {
        instance = this;
        new NewGameView();
    }

    public void close() {
        NewGameView.getInstance().close();
    }

    public static NewGameModel getInstance() {
        return instance;
    }
}