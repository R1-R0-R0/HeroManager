package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.gui.NewGameModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Deprecated User will just directly create his character. This class will be removed soon.
 */
public class NewGameController implements Controller {

    @FXML
    public ListView charactersList;
    @FXML
    public ImageView imageJob;
    @FXML
    public ImageView imageJobClass;
    @FXML
    public TextFlow textJobDesc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageJob.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        imageJobClass.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        textJobDesc.getChildren().add(new Text("<- Select a character on the left."));
    }

    @FXML
    public void createButtonOnClick() {
        NewGameModel.getInstance().createNewCharacter();
    }

    @FXML
    public void createGameButtonOnClick() {
        // TODO
    }

    @FXML
    public void backButtonOnClick() {
        NewGameModel.getInstance().close();
    }
}
