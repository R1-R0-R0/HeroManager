package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.gui.LoadGameModel;
import model.job.Job;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Load Game view.
 * Implements all listeners and images.
 *
 * @see view.LoadGameView associated class view (MVC pattern)
 * @see LoadGameModel associated class model (MVC pattern)
 */
public class LoadGameController implements Initializable {

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
     * TextFlow view filled with information of selected character
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
     * Entry point of controller.
     * When called, init images and text required to help user
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        imageJob.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        imageJobClass.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        textJobDesc.getChildren().add(new Text("<- Select a character on the left."));

        charactersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> characterSelected(newValue));
    }

    /**
     * Event triggered when user selected character in list
     */
    @FXML
    public void characterSelected(Job selectedItem) {
        LoadGameModel.getInstance().selectedCharacter(selectedItem);
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
