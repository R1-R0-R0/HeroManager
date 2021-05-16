package view;

import controller.CharacterCreatorController;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Characteristics;
import model.gui.CharacterCreatorModel;
import model.job.Gender;
import model.job.Job;
import model.job.JobSkill;
import model.job.JobType;
import model.spell.Spell;
import utils.gui.character_creator.JobSkillItem;
import utils.gui.character_creator.SpellItem;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Character cration's view.
 *
 * @see CharacterCreatorModel associated class model (MVC pattern)
 * @see CharacterCreatorController associated class controller (MVC pattern)
 */
public class CharacterCreatorView implements View {

    private static CharacterCreatorView instance;
    private Stage stage;

    /**
     * Constructor of this class.
     * Should NOT BE CALLED directly, CharacterCreatorModel automatically calls it.
     * When called, fxml view, its stage is set up
     *
     * @param owner window's caller of this view (theorically Menu view)
     */
    public CharacterCreatorView(Stage owner) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/character_creator.fxml"));
            stage.setTitle("HeroManager - Character Creator");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return instance of this class
     */
    public static CharacterCreatorView getInstance() {
        return instance;
    }

    /**
     * Used to update image view who represents character class
     *
     * @param path path to image
     */
    public void changeJobTypeImage(String path) {
        CharacterCreatorController.getInstance().jobTypeImage.setImage(new Image(getClass().getResourceAsStream(path)));
    }

    /**
     * Used to update image view who represents character
     *
     * @param path path to image
     */
    public void changeJobImage(String path) {
        CharacterCreatorController.getInstance().jobImage.setImage(new Image(getClass().getResourceAsStream(path)));
    }

    /**
     * Called when user selected his character's gender, to change character's image representation
     *
     * @param gender selected gender
     */
    public void selectedGender(Gender gender) {
        CharacterCreatorView view = CharacterCreatorView.getInstance();

        String classNameFile = CharacterCreatorController.getInstance().jobTypePicker.getValue().name().toLowerCase();
        String genderNameFile = (gender == Gender.MAN) ? "_m.jpg" : "_f.jpg";
        String imageJobPath = "/images/jobs/pictures/" + classNameFile + genderNameFile;

        view.changeJobImage(imageJobPath);
    }

    /**
     * To close window
     */
    public void close() {
        stage.close();
    }

    /**
     * Enable all inputs of first step of creation
     */
    public void enableInputs() {
        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        controller.jobNameText.setDisable(false);
        controller.jobDescriptionText.setDisable(false);
        controller.genderPicker.setDisable(false);
        controller.alignmentPicker.setDisable(false);
        controller.racePicker.setDisable(false);

        controller.createCharacterButton.setDisable(false);
    }

    /**
     * Disable all inputs of first step of creation
     */
    public void disableInputs() {
        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        controller.jobNameText.setDisable(true);
        controller.jobDescriptionText.setDisable(true);
        controller.genderPicker.setDisable(true);
        controller.alignmentPicker.setDisable(true);
        controller.racePicker.setDisable(true);

        controller.createCharacterButton.setDisable(true);
    }

    /**
     * When called, switch first step of creation to the second step, to assign character's stats points, skills and spells.
     * Spell tab is activated only if character's class allows it
     */
    public void setUpNewCharacter() {
        CharacterCreatorController.getInstance().paneCreation1.setVisible(false);
        CharacterCreatorController.getInstance().paneCreation2.setVisible(true);

        Job createdJob = CharacterCreatorModel.getInstance().getCreatedJob();
        for (JobType jobType : JobType.JOBS_SPELLS_AUTHORIZED) {
            if (createdJob.getJobType().equals(jobType)) {
                CharacterCreatorController.getInstance().spellsTab.setDisable(false);
                return;
            }
        }
    }

    /**
     * When called, update available points label to statistics distribution
     *
     * @param availablePoints number of available points
     */
    public void updateStatisticsAvailablePoints(int availablePoints) {
        CharacterCreatorController.getInstance().labelRemainingPoints.setText(Integer.toString(availablePoints));
    }

    /**
     * Method used to force specific spinner statistic value
     *
     * @param characteristic statistic to update
     * @param value          value to assign to spinner
     */
    public void setSpinnerStatisticValue(Characteristics characteristic, int value) {
        assert (value >= 8 && value <= 15);
        switch (characteristic) {
            case STRENGTH -> CharacterCreatorController.getInstance().spinnerStrength.getValueFactory().setValue(value);
            case DEXTERITY -> CharacterCreatorController.getInstance().spinnerDexterity.getValueFactory().setValue(value);
            case INTELLIGENCE -> CharacterCreatorController.getInstance().spinnerIntelligence.getValueFactory().setValue(value);
            case WISDOM -> CharacterCreatorController.getInstance().spinnerWisdom.getValueFactory().setValue(value);
            case ROBUSTNESS -> CharacterCreatorController.getInstance().spinnerRobustness.getValueFactory().setValue(value);
            case CHARISMA -> CharacterCreatorController.getInstance().spinnerCharisma.getValueFactory().setValue(value);
        }
    }

    /**
     * Allows to define skill list view elements
     *
     * @param jobSkills skills to show
     */
    public void setJobSkillsListView(List<JobSkill> jobSkills) {
        ObservableList<JobSkillItem> items = FXCollections.observableArrayList();

        for (JobSkill jobSkill : jobSkills)
            items.add(new JobSkillItem(jobSkill));

        CharacterCreatorController.getInstance().skillsListView.setItems(items);
    }

    /**
     * Allows to define spells list view elements
     *
     * @param spells spells to show
     */
    public void setSpellsListView(List<Spell> spells) {
        ObservableList<SpellItem> items = FXCollections.observableArrayList();

        for (Spell spell : spells)
            items.add(new SpellItem(spell));

        CharacterCreatorController.getInstance().spellsListView.setItems(items);
    }

    /**
     * @return stage of this view
     */
    public Stage getStage() {
        return stage;
    }
}
