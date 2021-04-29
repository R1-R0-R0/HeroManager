package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.gui.CharacterCreatorModel;
import model.job.JobType;
import model.race.Alignment;
import model.race.RaceType;
import view.CharacterCreatorView;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterCreatorController implements Initializable {

    private static CharacterCreatorController instance;
    @FXML
    public ImageView jobImage, jobTypeImage;
    @FXML
    public ComboBox<JobType> jobTypePicker;
    @FXML
    public TextField jobNameText;
    @FXML
    public TextArea jobDescriptionText;
    @FXML
    public ComboBox<?> genderPicker;
    @FXML
    public ComboBox<Alignment> alignmentPicker;
    @FXML
    public ComboBox<RaceType> racePicker;

    public static CharacterCreatorController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        jobImage.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        jobTypeImage.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        jobTypeImage.setEffect(new DropShadow(20, Color.BLACK));

        ObservableList<JobType> jobTypeObservableList = FXCollections.observableArrayList(JobType.values());
        jobTypePicker.setItems(jobTypeObservableList);

        ObservableList<Alignment> alignmentObservableList = FXCollections.observableArrayList(Alignment.values());
        alignmentPicker.setItems(alignmentObservableList);

        ObservableList<RaceType> raceTypeObservableList = FXCollections.observableArrayList(RaceType.values());
        racePicker.setItems(raceTypeObservableList);
    }

    @FXML
    public void backButtonOnClick() {
        CharacterCreatorModel.getInstance().close();
    }

    @FXML
    public void jobTypeOnPick() {
        CharacterCreatorModel.getInstance().selectedJobType(jobTypePicker.getValue());
    }
}
