package model.gui;

import exceptions.UnsupportedJobTypeException;
import javafx.scene.control.Alert;
import model.job.Job;
import utils.gui.Dialog;
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
    private Job selectedCharacter;

    /**
     * When called, init view and its components
     */
    public LoadGameModel() {
        instance = this;
        new LoadGameView();

        // TODO : set up saves when saving methods are implemented
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
        } catch (UnsupportedJobTypeException e) {
            e.printStackTrace();
            Dialog err = new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
            err.showAndWait();
            System.exit(1);
        }
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
