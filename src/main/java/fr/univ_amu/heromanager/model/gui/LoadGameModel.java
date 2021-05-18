package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.LoadGameController;
import fr.univ_amu.heromanager.exceptions.UnsupportedJobTypeException;
import javafx.scene.control.Alert;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.LoadGameView;
import fr.univ_amu.heromanager.view.MenuView;

import java.util.Arrays;

/**
 * Model of Load Game fr.univ_amu.heromanager.view
 * Called to show fr.univ_amu.heromanager.view and its components
 *
 * @see LoadGameView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see fr.univ_amu.heromanager.controller.LoadGameController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class LoadGameModel implements Model {

    private static LoadGameModel instance;
    private Job selectedCharacter;

    /**
     * When called, init fr.univ_amu.heromanager.view and its components
     */
    public LoadGameModel() {
        instance = this;
        LoadGameView view = new LoadGameView();

        view.setGamesList(HeroManagerDB.getJobs());
    }

    /**
     * @return instance of this class
     */
    public static LoadGameModel getInstance() {
        return instance;
    }

    /**
     * Event triggered when user selected a job in list, and allows to show its information
     *
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
        HeroManagerDB.modifyJob(selectedCharacter);
    }

    /**
     * To close fr.univ_amu.heromanager.view
     */
    public void close() {
        LoadGameView.getInstance().close();
    }
}
