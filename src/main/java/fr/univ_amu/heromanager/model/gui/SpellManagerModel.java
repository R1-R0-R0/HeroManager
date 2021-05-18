package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.SpellManagerController;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.model.spell.Component;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.utils.ListenableArrayList;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.MenuView;
import fr.univ_amu.heromanager.view.SpellManagerView;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of Spell Manager fr.univ_amu.heromanager.view.
 * Called to show spell manager and handle its components
 *
 * @see SpellManagerView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see SpellManagerController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class SpellManagerModel implements Model {

    private static SpellManagerModel instance;

    private final ListenableArrayList<Spell> spells;
    private Spell selectedSpell;

    /**
     * Constructor of this class.
     * When called, calls method to init view
     */
    public SpellManagerModel() {
        instance = this;

        new SpellManagerView();

        spells = new ListenableArrayList<>();
        spells.addListenerForAllActions(() -> SpellManagerView.getInstance().setSpellListView(spells));
        spells.addAll(HeroManagerDB.getSpells());
    }

    /**
     * 2nd constructor used when another view apart from menu is opening spell manager
     *
     * @param owner view caller
     */
    public SpellManagerModel(Stage owner) {
        instance = this;

        new SpellManagerView(owner);

        spells = new ListenableArrayList<>();
        spells.addListenerForAllActions(() -> SpellManagerView.getInstance().setSpellListView(spells));
        spells.addAll(HeroManagerDB.getSpells());
    }

    /**
     * @return instance of this class
     */
    public static SpellManagerModel getInstance() {
        return instance;
    }

    /**
     * To get current spell list shown
     *
     * @return list of spells
     */
    public ListenableArrayList<Spell> getSpells() {
        return spells;
    }

    /**
     * To set list of spells to manage
     *
     * @param spells list of spell
     */
    public void setSpells(List<Spell> spells) {
        this.spells.clear();
        this.spells.addAll(spells);
    }

    /**
     * To check if all required fields are filled
     *
     * @return TRUE if all fields are filled, FALSE otherwise
     */
    private boolean isAllRequiredFieldsFilled() {
        SpellManagerController controller = SpellManagerController.getInstance();

        return !(
                controller.spellNameLabel.getText().matches("^(\\s)*$")
                        || controller.spellSchoolLabel.getText().matches("^(\\s)*$")
                        || controller.spellCastingTimeLabel.getText().matches("^(\\s)*$")
                        || controller.spellDurationLabel.getText().matches("^(\\s)*$")
                        || controller.spellLevelSpinner.getValue() == null
                        || controller.spellRangeSpinner.getValue() == null
                        || controller.spellJobTypePicker.getValue() == null
                        || (controller.spellComponent1Picker.getValue() == null
                        && controller.spellComponent2Picker.getValue() == null
                        && controller.spellComponent3Picker.getValue() == null)
        );
    }

    /**
     * Retrieve elements from fr.univ_amu.heromanager.view, create and save a spell
     */
    public void createSpell() {
        if (!isAllRequiredFieldsFilled()) {
            new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to update your item").show();
            return;
        }

        SpellManagerController controller = SpellManagerController.getInstance();

        if (isSpellExist(controller.spellNameLabel.getText())) {
            new Dialog(
                    Alert.AlertType.ERROR,
                    "Spell " + controller.spellNameLabel.getText() + " already exist",
                    "Spell you want to create already exist, please consider another name"
            ).showAndWait();
            return;
        }

        List<Component> spellComponents = new ArrayList<>();

        if (controller.spellComponent1Picker.getValue() != null)
            spellComponents.add(controller.spellComponent1Picker.getValue());
        if (controller.spellComponent2Picker.getValue() != null)
            spellComponents.add(controller.spellComponent2Picker.getValue());
        if (controller.spellComponent3Picker.getValue() != null)
            spellComponents.add(controller.spellComponent3Picker.getValue());

        Spell spell = new Spell(
                controller.spellNameLabel.getText(),
                controller.spellDescLabel.getText(),
                controller.spellSchoolLabel.getText(),
                controller.spellCastingTimeLabel.getText(),
                controller.spellDurationLabel.getText(),
                controller.spellLevelSpinner.getValue(),
                controller.spellRangeSpinner.getValue(),
                controller.spellJobTypePicker.getValue(),
                controller.spellDoDamagesButton.isSelected(),
                spellComponents
        );

        if (HeroManagerDB.addSpell(spell))
            spells.add(spell);
        else
            new Dialog(Alert.AlertType.ERROR, "Item already exist", "This item already exist, or an error occurred during item creation").showAndWait();

        selectedSpell = null;
        SpellManagerView.getInstance().clearAllInputs();
    }

    /**
     * Retrieve elements from fr.univ_amu.heromanager.view, update and save a spell
     */
    public void updateSpell() {
        if (!isAllRequiredFieldsFilled()) {
            new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to update your item").show();
            return;
        }

        SpellManagerController controller = SpellManagerController.getInstance();

        List<Component> spellComponents = new ArrayList<>();
        if (controller.spellComponent1Picker.getValue() != null)
            spellComponents.add(controller.spellComponent1Picker.getValue());
        if (controller.spellComponent2Picker.getValue() != null)
            spellComponents.add(controller.spellComponent2Picker.getValue());
        if (controller.spellComponent3Picker.getValue() != null)
            spellComponents.add(controller.spellComponent3Picker.getValue());

        Spell newSpell = new Spell(
                controller.spellNameLabel.getText(),
                controller.spellDescLabel.getText(),
                controller.spellSchoolLabel.getText(),
                controller.spellCastingTimeLabel.getText(),
                controller.spellDurationLabel.getText(),
                controller.spellLevelSpinner.getValue(),
                controller.spellRangeSpinner.getValue(),
                controller.spellJobTypePicker.getValue(),
                controller.spellDoDamagesButton.isSelected(),
                spellComponents
        );

        for (int index = 0; index < spells.size(); index++) {
            if (spells.get(index) == selectedSpell) {
                spells.set(index, newSpell);
                break;
            }
        }

        if (selectedSpell.getName().equals(newSpell.getName()))
            HeroManagerDB.modifySpell(newSpell);
        else {
            HeroManagerDB.removeSpell(selectedSpell.getName());
            HeroManagerDB.addSpell(newSpell);
        }

        selectedSpell = newSpell;
    }

    /**
     * Retrieve elements from fr.univ_amu.heromanager.view, delete and save a spell
     */
    public void deleteSpell() {
        if (HeroManagerDB.removeSpell(selectedSpell.getName())) {
            spells.remove(selectedSpell);
            selectedSpell = null;
            SpellManagerView.getInstance().clearAllInputs();
            return;
        }

        new Dialog(
                Alert.AlertType.ERROR,
                "Critical error occured",
                "An error occured while spell deletion, please try to restart HeroManager."
        ).showAndWait();
    }

    /**
     * To set selected spell by user, to set inputs by selected spell properties
     *
     * @param spell selected spell
     */
    public void spellPicked(Spell spell) {
        if (spell == null) return;

        selectedSpell = spell;
        SpellManagerView view = SpellManagerView.getInstance();

        view.setInputs(spell);
        view.setUpdateButtonDisable(false);
        view.setDeleteButtonDisable(false);
    }

    /**
     * Check if spell's already exist
     *
     * @param name name of spell to check
     * @return TRUE if exist, FALSE otherwise
     */
    private boolean isSpellExist(String name) {
        for (Spell spell : spells) {
            if (spell.getName().equals(name))
                return true;
        }

        return false;
    }

    /**
     * Allows to save modifications, close fr.univ_amu.heromanager.view and return to menu
     */
    public void returnToMenu() {
        Dialog busyDialog = new Dialog("Saving modifications, please wait...");
        busyDialog.show();
        SpellManagerView.getInstance().close();

        new Thread(() -> {
            HeroManagerDB.initJobs();
            Platform.runLater(() -> {
                busyDialog.close();
                if (SpellManagerView.getInstance().getStage().getOwner() == null)
                    new MenuView();
            });
        }).start();
    }
}
