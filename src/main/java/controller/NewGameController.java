package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class NewGameController implements Initializable {

    @FXML
    public ListView charactersList;
    @FXML
    public ImageView imageJob;
    @FXML
    public ImageView imageJobClass;
    @FXML
    public TextField textJobDesc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageJob.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        imageJobClass.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
    }
}
