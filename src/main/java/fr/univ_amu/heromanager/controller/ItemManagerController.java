package fr.univ_amu.heromanager.controller;

import fr.univ_amu.heromanager.exceptions.UnsupportedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import fr.univ_amu.heromanager.model.gui.ItemManagerModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import fr.univ_amu.heromanager.model.items.weapons.DamageType;
import fr.univ_amu.heromanager.model.items.weapons.WeaponType;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.ItemManagerView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Controller of Item Manager fr.univ_amu.heromanager.view
 *
 * @see fr.univ_amu.heromanager.view.ItemManagerView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see ItemManagerModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 */
public class ItemManagerController implements Controller {

    private static ItemManagerController instance;
    /**
     * Picker (ComboBox) of item type to choose
     */
    @FXML
    public ComboBox<ItemType> typePicker;
    /**
     * List fr.univ_amu.heromanager.view of selected items by picker
     */
    @FXML
    public ListView<Item> itemList;
    /**
     * Label of item name selected
     */
    @FXML
    public Label itemNameLabel;
    /**
     * Pane's fr.univ_amu.heromanager.view corresponding of each type of equipment.
     * Because Weapons, Equipement... don't have same attributes, fr.univ_amu.heromanager.view's changes according to selected item type
     */
    @FXML
    public GridPane weaponsPane, equipmentPane, consumablePane;
    /**
     * Name's field of selected item, to fr.univ_amu.heromanager.view or change it
     */
    @FXML
    public TextField weaponNameText, equipmentNameText, consumableNameText;
    /**
     * Description's field of selected item, to fr.univ_amu.heromanager.view or change it
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
    public ChoiceBox<EquipmentPart> equipmentPartPicker;
    /**
     * Picker(ChoiceBox) to select what kind of equipment is (weight)
     */
    @FXML
    public ChoiceBox<EquipmentType> equipmentTypePicker;
    /**
     * Buttons related to performable actions on items
     */
    @FXML
    public Button newItemButton, updateItemButton, deleteItemButton;

    /**
     * Spinner used to set armor bonus value to equipments
     */
    @FXML
    public Spinner<Integer> spinnerArmorBonus;

    /**
     * Spinners related to characteristics bonuses applied to equipments
     */
    @FXML
    public Spinner<Integer> spinnerStrength, spinnerDexterity, spinnerRobustness, spinnerIntelligence, spinnerWisdom, spinnerCharisma, spinnerSpeed;

    /**
     * @return instance of this class
     */
    public static ItemManagerController getInstance() {
        return instance;
    }

    /**
     * Entry point of fr.univ_amu.heromanager.controller.
     * When called, initializes all item's selectors
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        itemList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> itemSelectedEvent());

        ObservableList<ItemType> itemTypeObservableList = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(itemTypeObservableList);

        ObservableList<WeaponType> weaponTypeObservableList = FXCollections.observableArrayList(WeaponType.values());
        weaponTypePicker.setItems(weaponTypeObservableList);
        ObservableList<DamageType> damageTypeObservableList = FXCollections.observableArrayList(DamageType.values());
        damageTypePicker.setItems(damageTypeObservableList);

        ObservableList<EquipmentPart> equipmentPartObservableList = FXCollections.observableArrayList(EquipmentPart.values());
        equipmentPartPicker.setItems(equipmentPartObservableList);
        ObservableList<EquipmentType> equipmentTypeObservableList = FXCollections.observableArrayList(EquipmentType.values());
        equipmentTypePicker.setItems(equipmentTypeObservableList);
    }

    /**
     * Event picker triggered when item type (weapon, equipment...) is selected
     */
    @FXML
    public void itemTypeSelectedEvent() {
        ItemManagerView.getInstance().itemTypeSelectedEvent();
    }

    /**
     * Event triggered when an item has been selected in list fr.univ_amu.heromanager.view
     */
    public void itemSelectedEvent() {
        ItemManagerModel.getInstance().itemSelectedEvent();
    }

    /**
     * Event triggered when user click on button to create item
     */
    @FXML
    public void newItemButtonOnClick() {
        try {
            ItemManagerModel.getInstance().createItem();
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()));
            errDialog.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Event triggered when user click on button to update an existent item
     */
    @FXML
    public void updateItemButtonOnClick() {
        try {
            ItemManagerModel.getInstance().updateItem();
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()));
            errDialog.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Event triggered when user clicks on button to delete item
     */
    @FXML
    public void deleteItemButtonOnClick() {
        try {
            ItemManagerModel.getInstance().deleteItem();
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()));
            errDialog.showAndWait();
            System.exit(1);
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
