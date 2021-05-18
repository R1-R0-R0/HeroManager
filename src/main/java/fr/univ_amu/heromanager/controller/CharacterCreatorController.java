package fr.univ_amu.heromanager.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import fr.univ_amu.heromanager.model.Characteristics;
import fr.univ_amu.heromanager.model.gui.CharacterCreatorModel;
import fr.univ_amu.heromanager.model.job.Gender;
import fr.univ_amu.heromanager.model.job.JobType;
import fr.univ_amu.heromanager.model.race.Alignment;
import fr.univ_amu.heromanager.model.race.Race;
import fr.univ_amu.heromanager.utils.gui.character_creator.JobSkillItem;
import fr.univ_amu.heromanager.utils.gui.character_creator.SpellItem;
import fr.univ_amu.heromanager.view.CharacterCreatorView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller of Character's creator fr.univ_amu.heromanager.view
 *
 * @see CharacterCreatorView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see CharacterCreatorModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 */
public class CharacterCreatorController implements Controller {

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
     * List fr.univ_amu.heromanager.view of job skills
     */
    @FXML
    public ListView<JobSkillItem> skillsListView;

    /**
     * List fr.univ_amu.heromanager.view of spells
     */
    @FXML
    public ListView<SpellItem> spellsListView;

    /**
     * @return instance of this class
     */
    public static CharacterCreatorController getInstance() {
        return instance;
    }

    /**
     * Entry of fr.univ_amu.heromanager.controller, which loads all pictures, pickers and spinners event handlers
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        jobImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(QUESTION_MARK_PATH))));
        jobTypeImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(QUESTION_MARK_PATH))));
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

        skillsListView.setCellFactory(CheckBoxListCell.forListView(JobSkillItem::selectedProperty));
        spellsListView.setCellFactory(CheckBoxListCell.forListView(SpellItem::selectedProperty));
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

    /**
     * Event handler when user finished character creation and launch game
     */
    @FXML
    public void launchGameButtonOnClick() {
        CharacterCreatorModel.getInstance().launchGame();
    }
}
