package controller;

import exceptions.UnsupportedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.EquipmentParts;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.WeaponType;
import utils.gui.Dialog;
import view.ItemManagerView;

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
    public ChoiceBox<EquipmentParts> equipmentPartPicker;
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
     * Entry point of controller.
     * When called, initializes all item's selectors
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        itemList.setOnMouseClicked(event -> itemSelectedEvent());

        ObservableList<ItemType> itemTypeObservableList = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(itemTypeObservableList);

        ObservableList<WeaponType> weaponTypeObservableList = FXCollections.observableArrayList(WeaponType.values());
        weaponTypePicker.setItems(weaponTypeObservableList);
        ObservableList<DamageType> damageTypeObservableList = FXCollections.observableArrayList(DamageType.values());
        damageTypePicker.setItems(damageTypeObservableList);

        ObservableList<EquipmentParts> equipmentPartObservableList = FXCollections.observableArrayList(EquipmentParts.values());
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
     * Event triggered when an item has been selected in list view
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
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
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
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
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
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
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
