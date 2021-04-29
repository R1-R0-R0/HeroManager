package model.gui;

import controller.CharacterCreatorController;
import javafx.stage.Stage;
import model.job.JobType;
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
}
