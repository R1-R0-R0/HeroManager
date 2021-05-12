package view;

import controller.CharacterCreatorController;
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
import utils.gui.character_creator.JobSkillItem;
import utils.gui.character_creator.SpellItem;

import java.io.IOException;
import java.util.List;

public class LevelUpView implements View {

    private static LevelUpView instance;
    private Stage stage;

    public LevelUpView(Stage owner) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/level_up.fxml"));
            stage.setTitle("HeroManager - Level up");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LevelUpView getInstance() {
        return instance;
    }

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

        CharacterCreatorController.getInstance().skillsListView.setItems(items);
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

        CharacterCreatorController.getInstance().spellsListView.setItems(items);
    }

    public Stage getStage() {
        return stage;
    }
}
