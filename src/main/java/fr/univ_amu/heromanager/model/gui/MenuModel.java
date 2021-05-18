package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.view.MenuView;

/**
 * Model of Menu fr.univ_amu.heromanager.view.
 * Main fr.univ_amu.heromanager.view of program, this class is the first fr.univ_amu.heromanager.view called by the software at startup
 *
 * @see MenuView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see fr.univ_amu.heromanager.controller.MenuController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class MenuModel implements Model {

    private static MenuModel instance;
    private Job lastGame;

    /**
     * Constructor. When called, show Menu fr.univ_amu.heromanager.view
     */
    public MenuModel() {
        instance = this;
        MenuView view = new MenuView();

        lastGame = HeroManagerDB.lastPlayedParty();
        view.setLastGame(lastGame);
    }

    /**
     * @return instance of this class
     */
    public static MenuModel getInstance() {
        return instance;
    }

    /**
     * Event triggered when user clicks on resume button.
     * It allows to open Character fr.univ_amu.heromanager.view with last game saved
     */
    public void resumeGame() {
        new CharacterModel(lastGame);
        MenuView.getInstance().close();
    }

    /**
     * Event triggered when user clicks on new game button.
     * When called, shows up character's creation fr.univ_amu.heromanager.view
     */
    public void newGame() {
        new CharacterCreatorModel(MenuView.getInstance().getStage());
    }

    /**
     * Event tiggered when user clicks on load game button.
     * When called, shows up fr.univ_amu.heromanager.view to load a saved character
     */
    public void loadGame() {
        new LoadGameModel();
    }

    /**
     * Event triggered when user clicks on item manager's button.
     * When called, open fr.univ_amu.heromanager.view to manage items
     */
    public void openItemManager() {
        MenuView.getInstance().close();
        new ItemManagerModel();
    }

    /**
     * Open spell manager fr.univ_amu.heromanager.view, and close menu fr.univ_amu.heromanager.view
     */
    public void openSpellManager() {
        MenuView.getInstance().close();
        new SpellManagerModel();
    }

    /**
     * Event triggered when user clicks on quit button.
     * When called, just close the whole program
     */
    public void quitProgram() {
        MenuView.getInstance().close();
        System.exit(0);
    }
}
