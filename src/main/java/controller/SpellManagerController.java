package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.gui.SpellManagerModel;
import model.job.JobType;
import model.spell.Component;
import model.spell.Spell;
import view.SpellManagerView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of spell manager view
 *
 * @see view.SpellManagerView associated class view (MVC pattern)
 * @see SpellManagerModel associated class model (MVC pattern)
 */
public class SpellManagerController implements Controller {

    private static SpellManagerController instance;

    @FXML
    public TextField spellNameLabel, spellSchoolLabel, spellCastingTimeLabel, spellDurationLabel;

    @FXML
    public TextArea spellDescLabel;

    @FXML
    public Spinner<Integer> spellLevelSpinner, spellRangeSpinner;

    @FXML
    public ChoiceBox<JobType> spellJobTypePicker;

    @FXML
    public ChoiceBox<Component> spellComponent1Picker, spellComponent2Picker, spellComponent3Picker;

    @FXML
    public ToggleButton spellDoDamagesButton;

    @FXML
    public ListView<Spell> spellList;

    @FXML
    public Button newSpellButton, updateSpellButton, deleteSpellButton;


    /**
     * @return instance of this class
     */
    public static SpellManagerController getInstance() {
        return instance;
    }

    /**
     * Entry point of controller.
     * When called, initializes all item's selectors
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        spellJobTypePicker.setItems(FXCollections.observableArrayList(JobType.JOBS_SPELLS_AUTHORIZED));

        spellComponent1Picker.setItems(FXCollections.observableArrayList(Component.values()));
        spellComponent2Picker.setItems(FXCollections.observableArrayList(Component.values()));
        spellComponent3Picker.setItems(FXCollections.observableArrayList(Component.values()));

        spellList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> SpellManagerModel.getInstance().spellPicked(newValue));
    }

    /**
     * Event called when new spell button is clicked
     */
    @FXML
    public void newSpellButtonOnClick() {
        SpellManagerModel.getInstance().createSpell();
    }

    /**
     * Event called when update spell button is clicked
     */
    @FXML
    public void updateSpellButtonOnClick() {
        SpellManagerModel.getInstance().updateSpell();
    }

    /**
     * Event called when delete spell button is clicked
     */
    @FXML
    public void deleteSpellButtonOnClick() {
        SpellManagerModel.getInstance().deleteSpell();
    }

    /**
     * Event called when doDamages button is clicked and update its text
     */
    @FXML
    public void doDamageButtonOnClick() {
        if (spellDoDamagesButton.isSelected())
            SpellManagerView.getInstance().setDoDamageButtonText("Yes");
        else
            SpellManagerView.getInstance().setDoDamageButtonText("No");
    }

    /**
     * Event triggered when user clicks on back button to return to menu
     */
    @FXML
    public void backButtonOnClick() {
        SpellManagerModel.getInstance().returnToMenu();
    }
}