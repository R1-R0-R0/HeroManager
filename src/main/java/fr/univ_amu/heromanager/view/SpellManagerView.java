package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.Main;
import fr.univ_amu.heromanager.controller.SpellManagerController;
import fr.univ_amu.heromanager.model.gui.SpellManagerModel;
import fr.univ_amu.heromanager.model.spell.Component;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * View manager of spell manager fr.univ_amu.heromanager.view
 *
 * @see SpellManagerModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see SpellManagerController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class SpellManagerView implements View {

    private static SpellManagerView instance;
    private Stage stage;

    /**
     * Should NOT BE CALLED directly, its corresponding fr.univ_amu.heromanager.model will call it automatically.
     * When called, load item manager's fxml
     */
    public SpellManagerView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/spell_manager.fxml"));
            stage.setTitle("HeroManager - Item Manager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> SpellManagerModel.getInstance().returnToMenu());
            stage.show();

            instance = this;
        } catch (IOException e) {
            new Dialog("An error occurred while opening Spell Manager fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * 2nd constructor used when another view apart from menu is opening spell manager
     *
     * @param owner view caller
     */
    public SpellManagerView(Stage owner) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/spell_manager.fxml"));
            stage.setTitle("HeroManager - Item Manager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> SpellManagerModel.getInstance().returnToMenu());
            stage.initOwner(owner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            instance = this;
        } catch (IOException e) {
            new Dialog("An error occurred while opening Spell Manager fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static SpellManagerView getInstance() {
        return instance;
    }

    /**
     * Allows to show all given spells in list fr.univ_amu.heromanager.view
     *
     * @param spells spells to show
     */
    public void setSpellListView(List<Spell> spells) {
        ListView<Spell> spellListView = SpellManagerController.getInstance().spellList;
        spellListView.setItems(FXCollections.observableArrayList(spells));
    }

    /**
     * To set damage toggle button text
     *
     * @param value text to show
     */
    public void setDoDamageButtonText(String value) {
        SpellManagerController.getInstance().spellDoDamagesButton.setText(value);
    }

    /**
     * To enable/disable new spell button
     *
     * @param disable TRUE to disable, FALSE otherwise
     */
    public void setNewButtonDisable(boolean disable) {
        SpellManagerController.getInstance().newSpellButton.setDisable(disable);
    }

    /**
     * To enable/disable update spell button
     *
     * @param disable TRUE to disable, FALSE otherwise
     */
    public void setUpdateButtonDisable(boolean disable) {
        SpellManagerController.getInstance().updateSpellButton.setDisable(disable);
    }

    /**
     * To enable/disable delete spell button
     *
     * @param disable TRUE to disable, FALSE otherwise
     */
    public void setDeleteButtonDisable(boolean disable) {
        SpellManagerController.getInstance().deleteSpellButton.setDisable(disable);
    }

    /**
     * To set all fields by given spell attributes
     *
     * @param spell spell to set values
     */
    public void setInputs(Spell spell) {
        clearAllInputs();

        SpellManagerController controller = SpellManagerController.getInstance();

        controller.spellNameLabel.setText(spell.getName());
        controller.spellDescLabel.setText(spell.getDescription());
        controller.spellSchoolLabel.setText(spell.getSchool());
        controller.spellCastingTimeLabel.setText(spell.getCastingTime());
        controller.spellDurationLabel.setText(spell.getDuration());
        controller.spellLevelSpinner.getValueFactory().setValue(spell.getLevel());
        controller.spellRangeSpinner.getValueFactory().setValue(spell.getRange());
        controller.spellJobTypePicker.setValue(spell.getJobType());
        controller.spellDoDamagesButton.setSelected(spell.isDoDamages());

        List<Component> components = spell.getComponents();

        switch (components.size()) {
            case 3:
                controller.spellComponent3Picker.setValue(components.get(2));
            case 2:
                controller.spellComponent2Picker.setValue(components.get(1));
            case 1:
                controller.spellComponent1Picker.setValue(components.get(0));
        }
    }

    /**
     * Clearing all inputs in view
     */
    public void clearAllInputs() {
        SpellManagerController controller = SpellManagerController.getInstance();

        controller.spellNameLabel.clear();
        controller.spellDescLabel.clear();
        controller.spellSchoolLabel.clear();
        controller.spellCastingTimeLabel.clear();
        controller.spellDurationLabel.clear();
        controller.spellLevelSpinner.getValueFactory().setValue(1);
        controller.spellRangeSpinner.getValueFactory().setValue(0);
        controller.spellJobTypePicker.setValue(null);
        controller.spellDoDamagesButton.setSelected(true);
        controller.spellComponent1Picker.setValue(null);
        controller.spellComponent2Picker.setValue(null);
        controller.spellComponent3Picker.setValue(null);

        setUpdateButtonDisable(true);
        setDeleteButtonDisable(true);
    }

    /**
     * Stage getter of Spell manager view
     *
     * @return stage of view
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
