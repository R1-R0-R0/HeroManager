package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.controller.LoadGameController;
import fr.univ_amu.heromanager.Main;
import fr.univ_amu.heromanager.exceptions.UnsupportedJobTypeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.gui.CharacterCreatorModel;
import fr.univ_amu.heromanager.model.job.Gender;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * View manager of Load Game fr.univ_amu.heromanager.view
 *
 * @see fr.univ_amu.heromanager.model.gui.LoadGameModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see fr.univ_amu.heromanager.controller.LoadGameController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class LoadGameView implements View {

    private static LoadGameView instance;
    private Stage stage;

    /**
     * Constructor of this class.
     * Should NEVER BE CALLED directly, always called by its fr.univ_amu.heromanager.model class.
     * When called, init fxml fr.univ_amu.heromanager.view
     */
    public LoadGameView() {
        try {
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("/fxml/load_game_menu.fxml"));
            stage.setTitle("HeroManager - Load Game");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MenuView.getInstance().getStage());
            stage.show();

            instance = this;
        } catch (IOException e) {
            new Dialog("An error occurred while opening Load Game fr.univ_amu.heromanager.view", e).showAndWait();
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
     *
     * @param jobs jobs to show
     */
    public void setGamesList(List<Job> jobs) {
        ObservableList<Job> jobObservableList = FXCollections.observableList(jobs);
        LoadGameController.getInstance().charactersList.setItems(jobObservableList);
    }

    /**
     * Allows to show given character class image
     *
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

        imgView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgPath))));
    }

    /**
     * Allows to show given character image
     *
     * @param character selected character
     */
    public void setJobImage(Job character) {
        ImageView imgView = LoadGameController.getInstance().imageJob;

        String classNameFile = character.getJobType().name().toLowerCase();
        String genderNameFile = (character.getGender() == Gender.MAN) ? "_m.jpg" : "_f.jpg";
        String imageJobPath = "/images/jobs/pictures/" + classNameFile + genderNameFile;

        imgView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageJobPath))));
    }

    /**
     * Allows to show information about selected character
     *
     * @param character selected character
     */
    public void setJobDescription(Job character) {
        TextFlow textView = LoadGameController.getInstance().textJobDesc;
        textView.getChildren().clear();

        Text name = new Text(character.getName() + "\n");
        name.setFont(new Font(42));

        Text lvlTypeAndClass = new Text("LVL " + character.getLevel() + " - " + character.getJobType() + " - " + character.getRaceType() + "\n");
        lvlTypeAndClass.setFont(new Font(28));

        Text statistics = new Text(
                "- Strength:\t" + character.getTotalStrength() + " (" + character.getStrength() + "+" + character.getStrengthBoost() + ")\n" +
                        "- Dexterity:\t" + character.getTotalDexterity() + " (" + character.getDexterity() + "+" + character.getDexterityBoost() + ")\n" +
                        "- Intelligence:\t" + character.getTotalIntelligence() + " (" + character.getIntelligence() + "+" + character.getIntelligenceBoost() + ")\n" +
                        "- Wisdom:\t" + character.getTotalWisdom() + " (" + character.getWisdom() + "+" + character.getWisdomBoost() + ")\n" +
                        "- Robustness:\t" + character.getTotalRobustness() + " (" + character.getRobustness() + "+" + character.getRobustnessBoost() + ")\n" +
                        "- Charisma:\t" + character.getTotalCharisma() + " (" + character.getCharisma() + "+" + character.getCharismaBoost() + ")\n" +
                        "- Armor:\t" + character.getTotalArmor() + " (" + character.getArmor() + "+" + character.getArmorBoost() + ")\n"
        );

        Text description = new Text("\nDescription:\n" + character.getDescription());

        textView.getChildren().addAll(name, lvlTypeAndClass, statistics, description);
    }

    /**
     * @return stage's fr.univ_amu.heromanager.view
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * To close fr.univ_amu.heromanager.view
     */
    public void close() {
        stage.close();
    }
}
