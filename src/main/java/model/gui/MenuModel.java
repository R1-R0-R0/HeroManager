package model.gui;

import model.files.HeroManagerDB;
import model.job.Job;
import view.MenuView;

/**
 * Model of Menu view.
 * Main view of program, this class is the first view called by the software at startup
 *
 * @see MenuView associated class view (MVC pattern)
 * @see controller.MenuController associated class controller (MVC pattern)
 */
public class MenuModel implements Model {

    private static MenuModel instance;
    private Job lastGame;

    /**
     * Constructor. When called, show Menu view
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
     * It allows to open Character view with last game saved
     */
    public void resumeGame() {
        new CharacterModel(lastGame);
        MenuView.getInstance().close();
    }

    /**
     * Event triggered when user clicks on new game button.
     * When called, shows up character's creation view
     */
    public void newGame() {
        new CharacterCreatorModel(MenuView.getInstance().getStage());
    }

    /**
     * Event tiggered when user clicks on load game button.
     * When called, shows up view to load a saved character
     */
    public void loadGame() {
        new LoadGameModel();
    }

    /**
     * Event triggered when user clicks on item manager's button.
     * When called, open view to manage items
     */
    public void openItemManager() {
        MenuView.getInstance().close();
        new ItemManagerModel();
    }

    /**
     * Open spell manager view, and close menu view
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
