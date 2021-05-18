package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.view.NewGameView;

/**
 * @Deprecated User will just directly create his character. This class will be removed soon.
 */
public class NewGameModel implements Model {

    private static NewGameModel instance;

    /**
     * @Deprecated User will just directly create his character. This class will be removed soon.
     */
    public NewGameModel() {
        instance = this;
        new NewGameView();
    }

    public static NewGameModel getInstance() {
        return instance;
    }

    public void close() {
        NewGameView.getInstance().close();
    }

    public void createNewCharacter() {
        new CharacterCreatorModel(NewGameView.getInstance().getStage());
    }
}
