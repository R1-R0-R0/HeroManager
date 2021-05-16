package model.gui;

import controller.SpellManagerController;
import javafx.scene.control.Alert;
import model.spell.Component;
import model.spell.Spell;
import utils.ListenableArrayList;
import utils.gui.Dialog;
import view.SpellManagerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of Spell Manager view.
 * Called to show spell manager and handle its components
 *
 * @see SpellManagerView associated class view (MVC pattern)
 * @see SpellManagerController associated class controller (MVC pattern)
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

        /*
            // TODO From database
            this.setSpells
         */
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
     * Retrieve elements from view, create and save a spell
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

        spells.add(spell);
        // TODO ADD DB

        selectedSpell = null;
        SpellManagerView.getInstance().clearAllInputs();
    }

    /**
     * Retrieve elements from view, update and save a spell
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

        if (selectedSpell.getName().equals(newSpell.getName())) {
            // TODO Update item in database
        } else {
            // TODO Delete and recreate item in database
        }

        selectedSpell = newSpell;
    }

    /**
     * Retrieve elements from view, delete and save a spell
     */
    public void deleteSpell() {
        if (true /* TODO IF OBJECT COULD BE DELETED FROM DB */ && spells.remove(selectedSpell)) {
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
     * Allows to close view and return to menu
     */
    public void returnToMenu() {
        SpellManagerView.getInstance().close();
        new MenuModel();
    }
}