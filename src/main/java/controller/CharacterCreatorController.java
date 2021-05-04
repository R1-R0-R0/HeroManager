package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Characteristics;
import model.gui.CharacterCreatorModel;
import model.job.Gender;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
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
    public ComboBox<Gender> genderPicker;
    @FXML
    public ComboBox<Alignment> alignmentPicker;
    @FXML
    public ComboBox<Race> racePicker;
    @FXML
    public Button createCharacterButton;
    @FXML
    public GridPane paneCreation1;
    @FXML
    public VBox paneCreation2;
    @FXML
    public Spinner<Integer> spinnerStrength, spinnerDexterity, spinnerIntelligence, spinnerWisdom, spinnerRobustness, spinnerCharisma;
    @FXML
    public Label labelRemainingPoints;

    public static CharacterCreatorController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        jobImage.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        jobTypeImage.setImage(new Image(getClass().getResourceAsStream("/images/ui/question_mark.png")));
        jobImage.setEffect(new DropShadow(20, Color.BLACK));
        jobTypeImage.setEffect(new DropShadow(20, Color.BLACK));

        ObservableList<JobType> jobTypeObservableList = FXCollections.observableArrayList(JobType.values());
        jobTypePicker.setItems(jobTypeObservableList);

        ObservableList<Gender> genderObservableList = FXCollections.observableArrayList(Gender.values());
        genderPicker.setItems(genderObservableList);

        ObservableList<Alignment> alignmentObservableList = FXCollections.observableArrayList(Alignment.values());
        alignmentPicker.setItems(alignmentObservableList);

        ObservableList<Race> raceObservableList = FXCollections.observableArrayList(Race.values());
        racePicker.setItems(raceObservableList);

        spinnerStrength.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerDexterity.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerIntelligence.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerWisdom.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerRobustness.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerCharisma.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        spinnerStrength.valueProperty().addListener((observable, oldValue, newValue) -> CharacterCreatorModel.getInstance().updateStatistics(Characteristics.STRENGTH, oldValue, newValue));
        spinnerDexterity.valueProperty().addListener(((observable, oldValue, newValue) -> CharacterCreatorModel.getInstance().updateStatistics(Characteristics.DEXTERITY, oldValue, newValue)));
        spinnerIntelligence.valueProperty().addListener(((observable, oldValue, newValue) -> CharacterCreatorModel.getInstance().updateStatistics(Characteristics.INTELLIGENCE, oldValue, newValue)));
        spinnerWisdom.valueProperty().addListener(((observable, oldValue, newValue) -> CharacterCreatorModel.getInstance().updateStatistics(Characteristics.WISDOM, oldValue, newValue)));
        spinnerRobustness.valueProperty().addListener((observable, oldValue, newValue) -> CharacterCreatorModel.getInstance().updateStatistics(Characteristics.ROBUSTNESS, oldValue, newValue));
        spinnerCharisma.valueProperty().addListener(((observable, oldValue, newValue) -> CharacterCreatorModel.getInstance().updateStatistics(Characteristics.CHARISMA, oldValue, newValue)));
    }

    @FXML
    public void backButtonOnClick() {
        CharacterCreatorModel.getInstance().close();
    }

    @FXML
    public void jobTypeOnPick() {
        CharacterCreatorModel.getInstance().selectedJobType(jobTypePicker.getValue());
    }

    @FXML
    public void genderTypeOnPick() {
        CharacterCreatorView.getInstance().selectedGender(genderPicker.getValue());
    }

    @FXML
    public void createCharacterButtonOnClick() {
        CharacterCreatorModel.getInstance().createCharacter();
    }
}
