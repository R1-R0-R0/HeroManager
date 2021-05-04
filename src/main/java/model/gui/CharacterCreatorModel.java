package model.gui;

import controller.CharacterCreatorController;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
import utils.gui.Dialog;
import view.CharacterCreatorView;

public class CharacterCreatorModel {

    public final static String
            BARBARIAN_IMAGE_PATH = "/images/jobs/logo/barbarian.jpg",
            BARD_IMAGE_PATH = "/images/jobs/logo/bard.jpg",
            CLERIC_IMAGE_PATH = "/images/jobs/logo/cleric.jpg",
            DRUID_IMAGE_PATH = "/images/jobs/logo/druid.jpg",
            FIGHTER_IMAGE_PATH = "/images/jobs/logo/fighter.jpg",
            MONK_IMAGE_PATH = "/images/jobs/logo/monk.jpg",
            PALADIN_IMAGE_PATH = "/images/jobs/logo/paladin.jpg",
            RANGER_IMAGE_PATH = "/images/jobs/logo/ranger.jpg",
            ROGUE_IMAGE_PATH = "/images/jobs/logo/rogue.jpg",
            SORCERER_IMAGE_PATH = "/images/jobs/logo/sorcerer.jpg",
            WARLOCK_IMAGE_PATH = "/images/jobs/logo/warlock.jpg",
            WIZARD_IMAGE_PATH = "/images/jobs/logo/wizard.jpg";

    private static CharacterCreatorModel instance;

    public CharacterCreatorModel(Stage owner) {
        instance = this;
        new CharacterCreatorView(owner);
    }

    public static CharacterCreatorModel getInstance() {
        return instance;
    }

    public void selectedJobType(JobType jobType) {
        CharacterCreatorView.getInstance().enableInputs();

        CharacterCreatorView view = CharacterCreatorView.getInstance();
        switch (jobType) {
            case BARBARIAN -> view.changeJobTypeImage(BARBARIAN_IMAGE_PATH);
            case BARD -> view.changeJobTypeImage(BARD_IMAGE_PATH);
            case CLERIC -> view.changeJobTypeImage(CLERIC_IMAGE_PATH);
            case DRUID -> view.changeJobTypeImage(DRUID_IMAGE_PATH);
            case FIGHTER -> view.changeJobTypeImage(FIGHTER_IMAGE_PATH);
            case MONK -> view.changeJobTypeImage(MONK_IMAGE_PATH);
            case PALADIN -> view.changeJobTypeImage(PALADIN_IMAGE_PATH);
            case RANGER -> view.changeJobTypeImage(RANGER_IMAGE_PATH);
            case ROGUE -> view.changeJobTypeImage(ROGUE_IMAGE_PATH);
            case SORCERER -> view.changeJobTypeImage(SORCERER_IMAGE_PATH);
            case WARLOCK -> view.changeJobTypeImage(WARLOCK_IMAGE_PATH);
            case WIZARD -> view.changeJobTypeImage(WIZARD_IMAGE_PATH);
        }

        CharacterCreatorController controller = CharacterCreatorController.getInstance();
        if (controller.genderPicker.getValue() != null)
            controller.genderTypeOnPick();
    }

    public void createCharacter() {
        CharacterCreatorView.getInstance().disableInputs();

        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        String name = controller.jobNameText.getText();
        String description = controller.jobDescriptionText.getText();
        Gender gender = controller.genderPicker.getValue();
        Alignment alignment = controller.alignmentPicker.getValue();
        Race race = controller.racePicker.getValue();
        JobType jobType = controller.jobTypePicker.getValue();

        if (name.matches("^(\\s)*$") || gender == null || alignment == null || race == null){
            new Dialog(Alert.AlertType.ERROR, "Required fields not filled", "ALL fields except description are required, please fill them.").showAndWait();
            CharacterCreatorView.getInstance().enableInputs();
            return;
        }

        Job createdJob = new Job(name, description, gender, alignment, race, jobType);
        CharacterCreatorView.getInstance().setUpNewCharacter();
    }

    public void close() {
        CharacterCreatorView.getInstance().close();
    }
}
