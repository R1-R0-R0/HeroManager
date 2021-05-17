package view;

import controller.LevelUpController;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import model.job.Job;
import model.job.JobSkill;
import model.spell.Spell;
import utils.gui.Dialog;
import utils.gui.character_creator.JobSkillItem;
import utils.gui.character_creator.SpellItem;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Item Picker view.
 *
 * @see model.gui.LevelUpModel associated class model (MVC pattern)
 * @see LevelUpController associated class controller (MVC pattern)
 */
public class LevelUpView implements View {

    private static LevelUpView instance;
    private Stage stage;

    /**
     * Constructor of this class, when called, init view and its components.
     * SHOULD NOT BE CALLED DIRECTLY, called by its corresponding model class
     *
     * @param owner caller of view
     */
    public LevelUpView(Stage owner) {
        try {
            instance = this;

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/level_up.fxml"));
            stage.setTitle("HeroManager - Level up");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            new Dialog("An error occurred while opening Level Up view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static LevelUpView getInstance() {
        return instance;
    }

    /**
     * To enable/disable spell tab
     *
     * @param disabled TRUE to disable, FALSE otherwise
     */
    public void setSpellsTabDisable(boolean disabled) {
        LevelUpController.getInstance().spellsTab.setDisable(disabled);
    }

    /**
     * Allows to define skill list view elements and selects them if character already possesses them
     *
     * @param jobSkills skills to show
     */
    public void setJobSkillsListView(List<JobSkill> jobSkills) {
        Job character = CharacterModel.getInstance().getCharacter();
        ObservableList<JobSkillItem> items = FXCollections.observableArrayList();

        for (JobSkill jobSkill : jobSkills) {
            JobSkillItem skill = new JobSkillItem(jobSkill);
            if (character.getSkills().contains(jobSkill))
                skill.selectedProperty().setValue(true);

            items.add(skill);
        }

        LevelUpController.getInstance().skillsListView.setItems(items);
    }

    /**
     * Allows to define spells list view elements and selects them if character already possesses them
     *
     * @param spells spells to show
     */
    public void setSpellsListView(List<Spell> spells) {
        Job character = CharacterModel.getInstance().getCharacter();
        ObservableList<SpellItem> items = FXCollections.observableArrayList();

        for (Spell spell : spells) {
            SpellItem si = new SpellItem(spell);
            if (character.getSpellInventory().contains(spell))
                si.selectedProperty().setValue(true);

            items.add(si);
        }

        LevelUpController.getInstance().spellsListView.setItems(items);
    }

    /**
     * @return stage of view
     */
    public Stage getStage() {
        return stage;
    }
}
