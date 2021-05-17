package model.gui;

import controller.LoadGameController;
import exceptions.UnsupportedJobTypeException;
import javafx.scene.control.Alert;
import model.files.HeroManagerDB;
import model.job.Job;
import utils.gui.Dialog;
import view.LoadGameView;
import view.MenuView;

import java.util.Arrays;

/**
 * Model of Load Game view
 * Called to show view and its components
 *
 * @see LoadGameView associated class view (MVC pattern)
 * @see controller.LoadGameController associated class controller (MVC pattern)
 */
public class LoadGameModel implements Model {

    private static LoadGameModel instance;
    private Job selectedCharacter;

    /**
     * When called, init view and its components
     */
    public LoadGameModel() {
        instance = this;
        LoadGameView view = new LoadGameView();

        view.setGamesList(HeroManagerDB.getJobs());
    }

    /**
     * Event triggered when user selected a job in list, and allows to show its information
     * @param selectedCharacter selected character
     */
    public void selectedCharacter(Job selectedCharacter) {
        try {
            this.selectedCharacter = selectedCharacter;

            LoadGameView.getInstance().setJobImageClass(selectedCharacter);
            LoadGameView.getInstance().setJobImage(selectedCharacter);
            LoadGameView.getInstance().setJobDescription(selectedCharacter);

            LoadGameController.getInstance().loadButton.setDisable(false);
        } catch (UnsupportedJobTypeException e) {
            e.printStackTrace();
            Dialog err = new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()));
            err.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Called when user click on load button, and load selected character
     */
    public void loadGame() {
        new CharacterModel(selectedCharacter);
        close();
        MenuView.getInstance().close();
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
