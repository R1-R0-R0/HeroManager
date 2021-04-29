package controller;

import com.sun.javafx.scene.control.LabeledText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.WeaponType;
import utils.gui.Dialog;

import javax.security.auth.callback.ChoiceCallback;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagerController implements Initializable {

    @FXML
    public ComboBox<ItemType> typePicker;
    @FXML
    public ListView<Item> itemList;
    @FXML
    public Label itemNameLabel;
    @FXML
    public GridPane weaponsPane, equipmentPane, consumablePane;
    @FXML
    public TextField weaponNameText, equipmentNameText, consumableNameText;
    @FXML
    public TextArea weaponDescriptionText, weaponPropertiesText, equipmentDescriptionText, consumableDescriptionText;
    @FXML
    public ChoiceBox<WeaponType> weaponTypePicker;
    @FXML
    public ChoiceBox<DamageType> damageTypePicker;
    @FXML
    public ChoiceBox<ItemManagerModel.EquipmentPart> equipmentPartPicker;
    @FXML
    public ChoiceBox<EquipmentType> equipmentTypePicker;
    @FXML
    public ChoiceBox<?> equipmentEffectPicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    @FXML
    public void itemTypeSelectedEvent() {
        ItemType selectedType = typePicker.getValue();

        weaponsPane.setVisible(false);
        equipmentPane.setVisible(false);
        consumablePane.setVisible(false);

        switch (selectedType) {
            case WEAPONS -> weaponsPane.setVisible(true);
            case EQUIPMENTS ->  equipmentPane.setVisible(true);
            case CONSUMABLES -> consumablePane.setVisible(true);
        }
    }

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

    @FXML
    public void backButtonOnClick() {
        ItemManagerModel.getInstance().returnToMenu();
    }
}
