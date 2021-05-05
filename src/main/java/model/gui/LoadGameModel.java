package model.gui;

import view.LoadGameView;

/**
 * Model of Load Game view
 * Called to show view and its components
 *
 * @see LoadGameView associated class view (MVC pattern)
 * @see controller.LoadGameController associated class controller (MVC pattern)
 */
public class LoadGameModel {

    private static LoadGameModel instance;

    /**
     * When called, init view and its components
     */
    public LoadGameModel() {
        instance = this;
        new LoadGameView();
    }

    /**
     * @return instance of this class
     */
    public static LoadGameModel getInstance() {
        return instance;
    }

    /**
     * To close view
     */
    public void close() {
        LoadGameView.getInstance().close();
    }
}
