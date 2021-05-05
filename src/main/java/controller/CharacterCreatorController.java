package controller;

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

/**
 * Controller of Character's creator view
 *
 * @see CharacterCreatorView associated class view (MVC pattern)
 * @see CharacterCreatorModel associated class model (MVC pattern)
 */
public class CharacterCreatorController implements Initializable {

    /**
     * Path of question mark image, used to show undefined selection
     */
    public final static String QUESTION_MARK_PATH = "/images/ui/question_mark.png";

    private static CharacterCreatorController instance;

    /**
     * ImageViews to show character's image and character's image class
     */
    @FXML
    public ImageView jobImage, jobTypeImage;

    /**
     * Picker (ComboBox) of character's class
     */
    @FXML
    public ComboBox<JobType> jobTypePicker;

    /**
     * Text field to define name's character
     */
    @FXML
    public TextField jobNameText;

    /**
     * Text area to define description's character
     */
    @FXML
    public TextArea jobDescriptionText;

    /**
     * Picker (ComboBox) to define character's gender
     */
    @FXML
    public ComboBox<Gender> genderPicker;

    /**
     * Picker (ComboBox) to define character's alignment
     */
    @FXML
    public ComboBox<Alignment> alignmentPicker;

    /**
     * Picker (ComboBox) to define character's race
     */
    @FXML
    public ComboBox<Race> racePicker;

    /**
     * Button to create character
     */
    @FXML
    public Button createCharacterButton;

    /**
     * Pane containing all nodes to first step of character's creation (name, description, gender, alignment, race)
     */
    @FXML
    public GridPane paneCreation1;

    /**
     * Pane containing all nodes to second step of character's creation (stats points distribution, skills, spells)
     */
    @FXML
    public VBox paneCreation2;

    /**
     * Spinners to define statistics points (between 8 and 15)
     */
    @FXML
    public Spinner<Integer> spinnerStrength, spinnerDexterity, spinnerIntelligence, spinnerWisdom, spinnerRobustness, spinnerCharisma;

    /**
     * Label to show how much statistics points left on distribution
     */
    @FXML
    public Label labelRemainingPoints;

    /**
     * Tabs to set up character in the second step of creation
     */
    @FXML
    public Tab statsTab, skillsTab, spellsTab;

    /**
     * @return instance of this class
     */
    public static CharacterCreatorController getInstance() {
        return instance;
    }

    /**
     * Entry of controller, which loads all pictures and pickers & spinners event handlers
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        jobImage.setImage(new Image(getClass().getResourceAsStream(QUESTION_MARK_PATH)));
        jobTypeImage.setImage(new Image(getClass().getResourceAsStream(QUESTION_MARK_PATH)));
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

    /**
     * Event handler when back button is clicked
     */
    @FXML
    public void backButtonOnClick() {
        CharacterCreatorModel.getInstance().close();
    }

    /**
     * Event handler when character's class is selected
     */
    @FXML
    public void jobTypeOnPick() {
        CharacterCreatorModel.getInstance().selectedJobType(jobTypePicker.getValue());
    }

    /**
     * Event handler when character's gender is selected
     */
    @FXML
    public void genderTypeOnPick() {
        CharacterCreatorView.getInstance().selectedGender(genderPicker.getValue());
    }

    /**
     * Event handler when create character button is clicked
     */
    @FXML
    public void createCharacterButtonOnClick() {
        CharacterCreatorModel.getInstance().createCharacter();
    }
}
