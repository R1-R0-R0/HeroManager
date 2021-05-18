package fr.univ_amu.heromanager.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import fr.univ_amu.heromanager.model.gui.SpellManagerModel;
import fr.univ_amu.heromanager.model.job.JobType;
import fr.univ_amu.heromanager.model.spell.Component;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.view.SpellManagerView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of spell manager fr.univ_amu.heromanager.view
 *
 * @see fr.univ_amu.heromanager.view.SpellManagerView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see SpellManagerModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 */
public class SpellManagerController implements Controller {

    private static SpellManagerController instance;

    /**
     * Fields corresponding to spell attributes
     */
    @FXML
    public TextField spellNameLabel, spellSchoolLabel, spellCastingTimeLabel, spellDurationLabel;

    /**
     * Field corresponding to spell description
     */
    @FXML
    public TextArea spellDescLabel;

    /**
     * Fields corresponding to level and range pickers
     */
    @FXML
    public Spinner<Integer> spellLevelSpinner, spellRangeSpinner;

    /**
     * Field corresponding to job type picker
     */
    @FXML
    public ChoiceBox<JobType> spellJobTypePicker;

    /**
     * Fields corresponding to spell components pickers
     */
    @FXML
    public ChoiceBox<Component> spellComponent1Picker, spellComponent2Picker, spellComponent3Picker;

    /**
     * Field corresponding to toggle button
     */
    @FXML
    public ToggleButton spellDoDamagesButton;

    /**
     * Field corresponding to list fr.univ_amu.heromanager.view
     */
    @FXML
    public ListView<Spell> spellList;

    /**
     * Fields corresponding to buttons
     */
    @FXML
    public Button newSpellButton, updateSpellButton, deleteSpellButton;


    /**
     * @return instance of this class
     */
    public static SpellManagerController getInstance() {
        return instance;
    }

    /**
     * Entry point of fr.univ_amu.heromanager.controller.
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
