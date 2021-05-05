package model.gui;

import controller.NewGameController;
import view.NewGameView;

/**
 * @Deprecated User will just directly create his character. This class will be removed soon.
 */
public class NewGameModel {
    
    private static NewGameModel instance;

    /**
     * @Deprecated User will just directly create his character. This class will be removed soon.
     */
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

    public void createNewCharacter() {
        new CharacterCreatorModel(NewGameView.getInstance().getStage());
    }
}
