package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.CheckBoxListCell;
import model.Characteristics;
import model.gui.CharacterCreatorModel;
import model.gui.CharacterModel;
import model.gui.LevelUpModel;
import model.job.Job;
import model.job.JobType;
import utils.gui.character_creator.JobSkillItem;
import utils.gui.character_creator.SpellItem;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelUpController implements Controller {

    private static LevelUpController instance;

    /**
     * Spinners to define statistics points (defined by game master)
     */
    @FXML
    public Spinner<Integer> spinnerStrength, spinnerDexterity, spinnerIntelligence, spinnerWisdom, spinnerRobustness, spinnerCharisma;

    /**
     * List view of job skills
     */
    @FXML
    public ListView<JobSkillItem> skillsListView;

    /**
     * List view of spells
     */
    @FXML
    public ListView<SpellItem> spellsListView;

    @FXML
    public Tab statsTab, skillsTab, spellsTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        Job character = CharacterModel.getInstance().getCharacter();

        spinnerStrength.getValueFactory().setValue(character.getStrength());
        spinnerDexterity.getValueFactory().setValue(character.getDexterity());
        spinnerIntelligence.getValueFactory().setValue(character.getIntelligence());
        spinnerWisdom.getValueFactory().setValue(character.getWisdom());
        spinnerRobustness.getValueFactory().setValue(character.getRobustness());
        spinnerCharisma.getValueFactory().setValue(character.getCharisma());

        spinnerStrength.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerDexterity.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerIntelligence.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerWisdom.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerRobustness.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerCharisma.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        spinnerStrength.valueProperty().addListener((observable, oldValue, newValue) -> LevelUpModel.getInstance().updateStatistics(Characteristics.STRENGTH, oldValue, newValue));
        spinnerDexterity.valueProperty().addListener(((observable, oldValue, newValue) -> LevelUpModel.getInstance().updateStatistics(Characteristics.DEXTERITY, oldValue, newValue)));
        spinnerIntelligence.valueProperty().addListener(((observable, oldValue, newValue) -> LevelUpModel.getInstance().updateStatistics(Characteristics.INTELLIGENCE, oldValue, newValue)));
        spinnerWisdom.valueProperty().addListener(((observable, oldValue, newValue) -> LevelUpModel.getInstance().updateStatistics(Characteristics.WISDOM, oldValue, newValue)));
        spinnerRobustness.valueProperty().addListener((observable, oldValue, newValue) -> LevelUpModel.getInstance().updateStatistics(Characteristics.ROBUSTNESS, oldValue, newValue));
        spinnerCharisma.valueProperty().addListener(((observable, oldValue, newValue) -> LevelUpModel.getInstance().updateStatistics(Characteristics.CHARISMA, oldValue, newValue)));

        skillsListView.setCellFactory(CheckBoxListCell.forListView(JobSkillItem::selectedProperty));
        spellsListView.setCellFactory(CheckBoxListCell.forListView(SpellItem::selectedProperty));

        Job createdJob = CharacterCreatorModel.getInstance().getCreatedJob();
        for (JobType jobType : CharacterCreatorModel.JOBS_SPELLS_AUTHORIZED) {
            if (createdJob.getJobType().equals(jobType)) {
                spellsTab.setDisable(false);
                return;
            }
        }
    }

    @FXML
    public void confirmLevelUpButtonOnClick() {
        LevelUpModel.getInstance().updateCharacterAndContinue();
    }

    public static LevelUpController getInstance() {
        return instance;
    }
}
