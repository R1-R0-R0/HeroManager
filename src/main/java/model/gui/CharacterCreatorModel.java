package model.gui;

import controller.CharacterCreatorController;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Characteristics;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
import utils.gui.Dialog;
import utils.gui.character_creator.JobSkillItem;
import utils.gui.character_creator.SpellItem;
import view.CharacterCreatorView;
import view.MenuView;

/**
 * Model of Character creator view.
 * Called to show Character creator view and handle all view logic.
 *
 * @see CharacterCreatorView associated class view (MVC pattern)
 * @see CharacterCreatorController associated class controller (MVC pattern)
 */
public class CharacterCreatorModel implements Model {

    /**
     * Paths to all classes images
     */
    public final static String
            BARBARIAN_LOGO_PATH = "/images/jobs/logo/barbarian.jpg",
            BARD_LOGO_PATH = "/images/jobs/logo/bard.jpg",
            CLERIC_LOGO_PATH = "/images/jobs/logo/cleric.jpg",
            DRUID_LOGO_PATH = "/images/jobs/logo/druid.jpg",
            FIGHTER_LOGO_PATH = "/images/jobs/logo/fighter.jpg",
            MONK_LOGO_PATH = "/images/jobs/logo/monk.jpg",
            PALADIN_LOGO_PATH = "/images/jobs/logo/paladin.jpg",
            RANGER_LOGO_PATH = "/images/jobs/logo/ranger.jpg",
            ROGUE_LOGO_PATH = "/images/jobs/logo/rogue.jpg",
            SORCERER_LOGO_PATH = "/images/jobs/logo/sorcerer.jpg",
            WARLOCK_LOGO_PATH = "/images/jobs/logo/warlock.jpg",
            WIZARD_LOGO_PATH = "/images/jobs/logo/wizard.jpg";

    /**
     * Value to set how much user can assign extra points in statistics while creating his character
     */
    public final static int MAX_AVAILABLE_STATS_POINTS = 12;

    private static CharacterCreatorModel instance;

    /**
     * Created job in 1st creation step
     */
    private Job createdJob;
    /**
     * Number of available statistics points remaining on statistics points distribution on 2nd part of character creation
     */
    private int availablePoints;
    /**
     * This value is used in updateStatistics method to ignore Spinner value changed event when value is modified by method itself, and do not affect anything related on statistics.
     * (Value is modified by the method when user tries to increment stats but he don't have any stats points left to do so)
     */
    private boolean updatedValue = false;

    /**
     * Constructor of this class, called to show view.
     *
     * @param owner window's caller of this view (theorically Menu view)
     */
    public CharacterCreatorModel(Stage owner) {
        instance = this;
        availablePoints = MAX_AVAILABLE_STATS_POINTS;
        CharacterCreatorView view = new CharacterCreatorView(owner);

        /*
        view.setJobSkillsListView(all skills);
        view.setSpellsListView(all spells);
        TODO: Define when all skills and spells are available
         */
    }

    /**
     * @return instance of this class
     */
    public static CharacterCreatorModel getInstance() {
        return instance;
    }

    /**
     * Called when a character class is selected by user, to update images.
     * Call genderTypeOnPick() if gender has been selected to change 2nd image if needed
     *
     * @param jobType selected class type
     * @see CharacterCreatorController#genderTypeOnPick() called method if gender has been selected before, to update image if needed
     */
    public void selectedJobType(JobType jobType) {
        CharacterCreatorView.getInstance().enableInputs();

        CharacterCreatorView view = CharacterCreatorView.getInstance();
        switch (jobType) {
            case BARBARIAN -> view.changeJobTypeImage(BARBARIAN_LOGO_PATH);
            case BARD -> view.changeJobTypeImage(BARD_LOGO_PATH);
            case CLERIC -> view.changeJobTypeImage(CLERIC_LOGO_PATH);
            case DRUID -> view.changeJobTypeImage(DRUID_LOGO_PATH);
            case FIGHTER -> view.changeJobTypeImage(FIGHTER_LOGO_PATH);
            case MONK -> view.changeJobTypeImage(MONK_LOGO_PATH);
            case PALADIN -> view.changeJobTypeImage(PALADIN_LOGO_PATH);
            case RANGER -> view.changeJobTypeImage(RANGER_LOGO_PATH);
            case ROGUE -> view.changeJobTypeImage(ROGUE_LOGO_PATH);
            case SORCERER -> view.changeJobTypeImage(SORCERER_LOGO_PATH);
            case WARLOCK -> view.changeJobTypeImage(WARLOCK_LOGO_PATH);
            case WIZARD -> view.changeJobTypeImage(WIZARD_LOGO_PATH);
        }

        CharacterCreatorController controller = CharacterCreatorController.getInstance();
        if (controller.genderPicker.getValue() != null)
            controller.genderTypeOnPick();
    }

    /**
     * Method called when 1st step of character creation is done.
     * It creates character in model and show 2nd part of creation, to set up statistics, skills and spells of character
     *
     * @see Job representation of character
     */
    public void createCharacter() {
        CharacterCreatorView.getInstance().disableInputs();

        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        String name = controller.jobNameText.getText();
        String description = controller.jobDescriptionText.getText();
        Gender gender = controller.genderPicker.getValue();
        Alignment alignment = controller.alignmentPicker.getValue();
        Race race = controller.racePicker.getValue();
        JobType jobType = controller.jobTypePicker.getValue();

        if (name.matches("^(\\s)*$") || gender == null || alignment == null || race == null) {
            new Dialog(Alert.AlertType.ERROR, "Required fields not filled", "ALL fields except description are required, please fill them.").showAndWait();
            CharacterCreatorView.getInstance().enableInputs();
            return;
        }

        createdJob = new Job(name, description, gender, alignment, race, jobType);
        CharacterCreatorView.getInstance().setUpNewCharacter();
        CharacterCreatorView.getInstance().updateStatisticsAvailablePoints(availablePoints);
    }

    /**
     * Called when one of statistics spinner is updated, to updates character in model, or not if all available points has been consumed
     *
     * @param characteristic affected statistic
     * @param oldValue       value before event
     * @param newValue       value after event
     */
    public void updateStatistics(Characteristics characteristic, int oldValue, int newValue) {
        if (updatedValue) {
            updatedValue = false;
            return;
        }

        int value = newValue - oldValue;

        if (value > 0 && availablePoints == 0) {
            updatedValue = true;
            CharacterCreatorView.getInstance().setSpinnerStatisticValue(characteristic, oldValue);
            return;
        }

        if (value < 0) {
            switch (characteristic) {
                case STRENGTH -> createdJob.decreaseStrength();
                case DEXTERITY -> createdJob.decreaseDexterity();
                case INTELLIGENCE -> createdJob.decreaseIntelligence();
                case WISDOM -> createdJob.decreaseWisdom();
                case ROBUSTNESS -> createdJob.decreaseRobustness();
                case CHARISMA -> createdJob.decreaseCharisma();
            }
        } else if (value > 0) {
            switch (characteristic) {
                case STRENGTH -> createdJob.increaseStrength();
                case DEXTERITY -> createdJob.increaseDexterity();
                case INTELLIGENCE -> createdJob.increaseIntelligence();
                case WISDOM -> createdJob.increaseWisdom();
                case ROBUSTNESS -> createdJob.increaseRobustness();
                case CHARISMA -> createdJob.increaseCharisma();
            }
        }

        availablePoints -= value;
        CharacterCreatorView.getInstance().updateStatisticsAvailablePoints(availablePoints);
    }

    /**
     * Called when user finished to create his character and wants to launch the game.
     * Assign selected skills and spells, and open character view
     */
    public void launchGame() {
        ListView<JobSkillItem> jobSkillItemListView = CharacterCreatorController.getInstance().skillsListView;
        ListView<SpellItem> spellItemListView = CharacterCreatorController.getInstance().spellsListView;

        ObservableList<JobSkillItem> jobSkillItems = jobSkillItemListView.getItems();
        for (JobSkillItem jobSkillItem : jobSkillItems) {
            if (jobSkillItem.isSelected())
                createdJob.addJobSkills(jobSkillItem.getSkill());
        }

        ObservableList<SpellItem> spellItems = spellItemListView.getItems();
        for (SpellItem spellItem : spellItems) {
            if (spellItem.isSelected())
                createdJob.addSpell(spellItem.getSpell());
        }

        new CharacterModel(createdJob);

        close();
        MenuView.getInstance().close();
    }

    /**
     * @return created job in 1st creation phase
     */
    public Job getCreatedJob() {
        return createdJob;
    }

    /**
     * To close view
     */
    public void close() {
        CharacterCreatorView.getInstance().close();
    }
}
