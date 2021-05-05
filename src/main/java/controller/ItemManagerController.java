package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.WeaponType;
import utils.gui.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Item Manager view
 *
 * @see view.ItemManagerView associated class view (MVC pattern)
 * @see ItemManagerModel associated class model (MVC pattern)
 */
public class ItemManagerController implements Initializable {

    private static ItemManagerController instance;
    /**
     * Picker (ComboBox) of item type to choose
     */
    @FXML
    public ComboBox<ItemType> typePicker;
    /**
     * List view of selected items by picker
     */
    @FXML
    public ListView<Item> itemList;
    /**
     * Label of item name selected
     */
    @FXML
    public Label itemNameLabel;
    /**
     * Pane's view corresponding of each type of equipment.
     * Because Weapons, Equipement... don't have same attributes, view's changes according to selected item type
     */
    @FXML
    public GridPane weaponsPane, equipmentPane, consumablePane;
    /**
     * Name's field of selected item, to view or change it
     */
    @FXML
    public TextField weaponNameText, equipmentNameText, consumableNameText;
    /**
     * Description's field of selected item, to view or change it
     */
    @FXML
    public TextArea weaponDescriptionText, weaponPropertiesText, equipmentDescriptionText, consumableDescriptionText;
    /**
     * Picker (ChoiceBox) to choose what type of weapon selected weapon is
     */
    @FXML
    public ChoiceBox<WeaponType> weaponTypePicker;
    /**
     * Picker (ChoiceBox) to choose what type of damage selected weapon inflict
     */
    @FXML
    public ChoiceBox<DamageType> damageTypePicker;
    /**
     * Picker (ChoiceBox) to choose what kind of equipment is (head, body, ...)
     */
    @FXML
    public ChoiceBox<ItemManagerModel.EquipmentPart> equipmentPartPicker;
    /**
     * Picker(ChoiceBox) to select what kind of equipment is (weight)
     */
    @FXML
    public ChoiceBox<EquipmentType> equipmentTypePicker;
    /**
     * Picker (ChoiceBox) to select what kind of equipment effect to inflict
     */
    @FXML
    public ChoiceBox<?> equipmentEffectPicker;

    /**
     * @return instance of this class
     */
    public static ItemManagerController getInstance() {
        return instance;
    }

    /**
     * Entry point of controller.
     * When called, initializes all item's selectors
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        ObservableList<ItemType> itemTypeObservableList = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(itemTypeObservableList);

        ObservableList<WeaponType> weaponTypeObservableList = FXCollections.observableArrayList(WeaponType.values());
        weaponTypePicker.setItems(weaponTypeObservableList);
        ObservableList<DamageType> damageTypeObservableList = FXCollections.observableArrayList(DamageType.values());
        damageTypePicker.setItems(damageTypeObservableList);

        ObservableList<ItemManagerModel.EquipmentPart> equipmentPartObservableList = FXCollections.observableArrayList(ItemManagerModel.EquipmentPart.values());
        equipmentPartPicker.setItems(equipmentPartObservableList);
        ObservableList<EquipmentType> equipmentTypeObservableList = FXCollections.observableArrayList(EquipmentType.values());
        equipmentTypePicker.setItems(equipmentTypeObservableList);
    }

    /**
     * Event picker triggered when item type (weapon, equipment...) is selected
     */
    @FXML
    public void itemTypeSelectedEvent() {
        ItemType selectedType = typePicker.getValue();

        weaponsPane.setVisible(false);
        equipmentPane.setVisible(false);
        consumablePane.setVisible(false);

        switch (selectedType) {
            case WEAPONS -> weaponsPane.setVisible(true);
            case EQUIPMENTS -> equipmentPane.setVisible(true);
            case CONSUMABLES -> consumablePane.setVisible(true);
        }
    }

    /**
     * Event triggered when user click on button to create item.
     * If some required fields are empty, an error pop up will be throw
     */
    @FXML
    public void newItemButtonOnClick() {

        switch (typePicker.getValue()) {
            case WEAPONS -> {
                if (weaponNameText.getText().matches("^(\\s)*$")
                        || weaponDescriptionText.getText().matches("^(\\s)*$")
                        || weaponDescriptionText.getText().matches("^(\\s)*$")
                        || weaponTypePicker.getValue() == null
                        || damageTypePicker.getValue() == null) {
                    Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to create your new item");
                    errDialog.showAndWait();
                    return;
                }

                /*
                ItemManagerModel.getInstance().newItem(
                        weaponNameText.getText(),
                        weaponDescriptionText.getText(),
                        weaponPropertiesText.getText(),
                        weaponTypePicker.getValue(),
                        damageTypePicker.getValue()
                );

                 */
            }
        }
    }

    /**
     * Event triggered when user click on button to update an existant item.
     * If some required fields are empty, an error pop up will be throw
     */
    @FXML
    public void updateItemButtonOnClick() {
        switch (typePicker.getValue()) {
            case WEAPONS -> {
                if (weaponNameText.getText().matches("^(\\s)*$")
                        || weaponDescriptionText.getText().matches("^(\\s)*$")
                        || weaponDescriptionText.getText().matches("^(\\s)*$")
                        || weaponTypePicker.getValue() == null
                        || damageTypePicker.getValue() == null) {
                    Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to update your item");
                    errDialog.showAndWait();
                    return;
                }

                // TODO When database is created
                /*
                ItemManagerModel.getInstance().updateItem(
                        weaponNameText.getText(),
                        weaponDescriptionText.getText(),
                        weaponPropertiesText.getText(),
                        weaponTypePicker.getValue(),
                        damageTypePicker.getValue()
                );

                 */
            }
        }
    }

    /**
     * Event triggered when user clicks on button to delete item.
     * If name's field is empty, an error pop up will be throw
     */
    @FXML
    public void deleteItemButtonOnClick() {
        switch (typePicker.getValue()) {
            case WEAPONS -> {
                if (weaponNameText.getText().matches("^(\\s)*$")) {
                    Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Name's value empty", "Please enter at least name of item you want to delete");
                    errDialog.showAndWait();
                    return;
                }

                ItemManagerModel.getInstance().deleteItemEvent(weaponNameText.getText());
            }
        }
    }

    /**
     * Event triggered when user clicks on back button to return to menu
     */
    @FXML
    public void backButtonOnClick() {
        ItemManagerModel.getInstance().returnToMenu();
    }
}
