package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.gui.LoadGameModel;

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

    /**
     * List of all saved characters/games
     */
    @FXML
    public ListView charactersList;

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
     * Entry point of controller.
     * When called, init images and text required to help user
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageJob.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        imageJobClass.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        textJobDesc.getChildren().add(new Text("<- Select a character on the left."));
    }

    /**
     * Event triggered when user clicks on load button
     */
    @FXML
    public void loadButtonOnClick() {
        // TODO
    }

    /**
     * Event triggered when user clicks on back button to return to menu
     */
    @FXML
    public void backButtonOnClick() {
        LoadGameModel.getInstance().close();
    }
}
