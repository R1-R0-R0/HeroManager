package controller;

import javafx.fxml.FXML;
import model.gui.MenuModel;
import view.MenuView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of this views.
 * Handle all events of Menu view
 *
 * @see MenuView associated class view (MVC pattern)
 * @see MenuModel associated class model (MVC pattern)
 */
public class MenuController implements Controller {

    private static MenuController instance;

    /**
     * @return instance of this class
     */
    public static MenuController getInstance() {
        return instance;
    }

    /**
     * Entry of controller. When called, loads all necessary attributes for the software to work properly
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    /**
     * Event triggered when resume button is clicked
     */
    @FXML
    public void resumeButtonOnClick() {
        MenuModel.getInstance().resumeGame();
    }

    /**
     * Event triggered when new game button is clicked
     */
    @FXML
    public void newGameButtonOnClick() {
        MenuModel.getInstance().newGame();
    }

    /**
     * Event triggered when load game button is clicked
     */
    @FXML
    public void loadGameButtonOnClick() {
        MenuModel.getInstance().loadGame();
    }

    /**
     * Event triggered when manager item button is clicked
     */
    @FXML
    public void manageItemsButtonOnClick() {
        MenuModel.getInstance().openItemManager();
    }

    /**
     * Open spell manager view when user clicks on spell manager button
     */
    @FXML
    public void manageSpellsButtonOnClick() {
        MenuModel.getInstance().openSpellManager();
    }

    /**
     * Event triggered when settings button is clicked
     */
    @FXML
    public void settingsButtonOnClick() {

    }

    /**
     * Event triggered when settings button is clicked
     */
    @FXML
    public void quitButtonOnClick() {
        MenuModel.getInstance().quitProgram();
    }
}
