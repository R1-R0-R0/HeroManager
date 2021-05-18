package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.controller.LevelUpController;
import fr.univ_amu.heromanager.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.gui.CharacterModel;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.model.job.JobSkill;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.utils.gui.character_creator.JobSkillItem;
import fr.univ_amu.heromanager.utils.gui.character_creator.SpellItem;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Item Picker fr.univ_amu.heromanager.view.
 *
 * @see fr.univ_amu.heromanager.model.gui.LevelUpModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see LevelUpController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class LevelUpView implements View {

    private static LevelUpView instance;
    private Stage stage;

    /**
     * Constructor of this class, when called, init fr.univ_amu.heromanager.view and its components.
     * SHOULD NOT BE CALLED DIRECTLY, called by its corresponding fr.univ_amu.heromanager.model class
     *
     * @param owner caller of fr.univ_amu.heromanager.view
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
            new Dialog("An error occurred while opening Level Up fr.univ_amu.heromanager.view", e).showAndWait();
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
     * Allows to define skill list fr.univ_amu.heromanager.view elements and selects them if character already possesses them
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
     * Allows to define spells list fr.univ_amu.heromanager.view elements and selects them if character already possesses them
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
     * @return stage of fr.univ_amu.heromanager.view
     */
    public Stage getStage() {
        return stage;
    }
}
