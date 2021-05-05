package view;

import controller.CharacterCreatorController;
import controller.LoadGameController;
import controller.Main;
import exceptions.UnsupportedJobTypeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.gui.CharacterCreatorModel;
import model.job.Gender;
import model.job.Job;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Load Game view
 *
 * @see model.gui.LoadGameModel associated class model (MVC pattern)
 * @see controller.LoadGameController associated class controller (MVC pattern)
 */
public class LoadGameView {

    private static LoadGameView instance;
    private Stage stage;

    /**
     * Constructor of this class.
     * Should NEVER BE CALLED directly, always called by its model class.
     * When called, init fxml view
     */
    public LoadGameView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/load_game_menu.fxml"));
            stage.setTitle("HeroManager - Load Game");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MenuView.getInstance().getStage());
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return instance of this class
     */
    public static LoadGameView getInstance() {
        return instance;
    }

    /**
     * Allows to fill up all saved games in list
     * @param jobs
     */
    public void setGamesList(List<Job> jobs) {
        ObservableList<Job> jobObservableList = FXCollections.observableList(jobs);
        LoadGameController.getInstance().charactersList.setItems(jobObservableList);
    }

    /**
     * Allows to show given character class image
     * @param character character class image to show
     * @throws UnsupportedJobTypeException if character class is not handled by method
     */
    public void setJobImageClass(Job character) throws UnsupportedJobTypeException {
        ImageView imgView = LoadGameController.getInstance().imageJobClass;

        String imgPath;

        switch (character.getJobType()) {
            case BARBARIAN -> imgPath = CharacterCreatorModel.BARBARIAN_LOGO_PATH;
            case BARD -> imgPath = CharacterCreatorModel.BARD_LOGO_PATH;
            case CLERIC -> imgPath = CharacterCreatorModel.CLERIC_LOGO_PATH;
            case DRUID -> imgPath = CharacterCreatorModel.DRUID_LOGO_PATH;
            case FIGHTER -> imgPath = CharacterCreatorModel.FIGHTER_LOGO_PATH;
            case MONK -> imgPath = CharacterCreatorModel.MONK_LOGO_PATH;
            case PALADIN -> imgPath = CharacterCreatorModel.PALADIN_LOGO_PATH;
            case RANGER -> imgPath = CharacterCreatorModel.RANGER_LOGO_PATH;
            case ROGUE -> imgPath = CharacterCreatorModel.ROGUE_LOGO_PATH;
            case SORCERER -> imgPath = CharacterCreatorModel.SORCERER_LOGO_PATH;
            case WARLOCK -> imgPath = CharacterCreatorModel.WARLOCK_LOGO_PATH;
            case WIZARD -> imgPath = CharacterCreatorModel.WIZARD_LOGO_PATH;
            default -> throw new UnsupportedJobTypeException(character.getJobType().name() + " not handled");
        }

        imgView.setImage(new Image(getClass().getResourceAsStream(imgPath)));
    }

    /**
     * Allows to show given character image
     * @param character selected character to show its image
     */
    public void setJobImage(Job character) {
        ImageView imgView = LoadGameController.getInstance().imageJob;

        String classNameFile = character.getJobType().name().toLowerCase();
        String genderNameFile = (character.getGender() == Gender.MAN) ? "_m.jpg" : "_f.jpg";
        String imageJobPath = "/images/jobs/pictures/" + classNameFile + genderNameFile;

        imgView.setImage(new Image(getClass().getResourceAsStream(imageJobPath)));
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
