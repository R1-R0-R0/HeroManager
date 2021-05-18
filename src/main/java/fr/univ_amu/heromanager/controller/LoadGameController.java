package fr.univ_amu.heromanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import fr.univ_amu.heromanager.model.gui.LoadGameModel;
import fr.univ_amu.heromanager.model.job.Job;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller of Load Game fr.univ_amu.heromanager.view.
 * Implements all listeners and images.
 *
 * @see fr.univ_amu.heromanager.view.LoadGameView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see LoadGameModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 */
public class LoadGameController implements Controller {

    private static LoadGameController instance;
    /**
     * List of all saved characters/games
     */
    @FXML
    public ListView<Job> charactersList;
    /**
     * ImageView of selected character
     */
    @FXML
    public ImageView imageJob;
    /**
     * ImageView of class of selected character
     */
    @FXML
    public ImageView imageJobClass;
    /**
     * TextFlow fr.univ_amu.heromanager.view filled with information of selected character
     */
    @FXML
    public TextFlow textJobDesc;

    /**
     * Load button, used to load game when character is selected
     */
    @FXML
    public Button loadButton;


    /**
     * @return instance of this class
     */
    public static LoadGameController getInstance() {
        return instance;
    }

    /**
     * Entry point of fr.univ_amu.heromanager.controller.
     * When called, init images and text required to help user
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        imageJob.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ui/question_mark.png"))));
        imageJobClass.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ui/question_mark.png"))));
        textJobDesc.getChildren().add(new Text("<- Select a character on the left."));

        charactersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> characterSelected(newValue));
    }

    /**
     * Event triggered when user selected character in list
     *
     * @param selectedCharacter selected character by user
     */
    @FXML
    public void characterSelected(Job selectedCharacter) {
        LoadGameModel.getInstance().selectedCharacter(selectedCharacter);
    }

    /**
     * Event triggered when user clicks on load button
     */
    @FXML
    public void loadButtonOnClick() {
        LoadGameModel.getInstance().loadGame();
    }

    /**
     * Event triggered when user clicks on back button to return to menu
     */
    @FXML
    public void backButtonOnClick() {
        LoadGameModel.getInstance().close();
    }
}
