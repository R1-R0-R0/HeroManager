package model.gui;

import controller.CharacterCreatorController;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.RaceType;
import utils.gui.Dialog;
import view.CharacterCreatorView;

public class CharacterCreatorModel {

    public final static String
            BARBARIAN_IMAGE_PATH = "/images/jobs/barbarian.jpg",
            BARD_IMAGE_PATH = "/images/jobs/bard.jpg",
            CLERIC_IMAGE_PATH = "/images/jobs/cleric.jpg",
            DRUID_IMAGE_PATH = "/images/jobs/druid.jpg",
            FIGHTER_IMAGE_PATH = "/images/jobs/fighter.jpg",
            MONK_IMAGE_PATH = "/images/jobs/monk.jpg",
            PALADIN_IMAGE_PATH = "/images/jobs/paladin.jpg",
            RANGER_IMAGE_PATH = "/images/jobs/ranger.jpg",
            ROGUE_IMAGE_PATH = "/images/jobs/rogue.jpg",
            SORCERER_IMAGE_PATH = "/images/jobs/sorcerer.jpg",
            WARLOCK_IMAGE_PATH = "/images/jobs/warlock.jpg",
            WIZARD_IMAGE_PATH = "/images/jobs/wizard.jpg";

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
    }

    public void createCharacter() {
        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        String name = controller.jobNameText.getText();
        String description = controller.jobDescriptionText.getText();
        Gender gender = controller.genderPicker.getValue();
        Alignment alignment = controller.alignmentPicker.getValue();
        RaceType race = controller.racePicker.getValue();

        if (name.matches("^(\\s)*$") || gender == null || alignment == null || race == null){
            new Dialog(Alert.AlertType.ERROR, "Required fields not filled", "ALL fields except description are required, please fill them.").showAndWait();
            return;
        }

        // TODO : Job job = new Job();
    }

    public void close() {
        CharacterCreatorView.getInstance().close();
    }
}
