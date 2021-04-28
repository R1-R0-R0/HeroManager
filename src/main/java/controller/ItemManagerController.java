package controller;

import com.sun.javafx.scene.control.LabeledText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.weapons.DamageType;
import model.items.weapons.WeaponType;
import utils.gui.Dialog;

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
    public TextField nameText;
    @FXML
    public TextArea descriptionText;
    @FXML
    public TextArea propertiesText;
    @FXML
    public ChoiceBox<WeaponType> weaponTypePicker;
    @FXML
    public ChoiceBox<DamageType> damageTypePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ItemType> itemTypeObservableList = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(itemTypeObservableList);

        ObservableList<WeaponType> weaponTypeObservableList = FXCollections.observableArrayList(WeaponType.values());
        weaponTypePicker.setItems(weaponTypeObservableList);

        ObservableList<DamageType> damageTypeObservableList = FXCollections.observableArrayList(DamageType.values());
        damageTypePicker.setItems(damageTypeObservableList);
    }

    @FXML
    public void itemTypeSelectedEvent() {
        ItemType selectedType = typePicker.getValue();

        switch (selectedType) {
            case WEAPONS:
            case EQUIPMENTS:
            case CONSUMABLES:
        }
    }

    @FXML
    public void newItemButtonOnClick() {
        if (nameText.getText().matches("^(\\s)*$")
                || descriptionText.getText().matches("^(\\s)*$")
                || propertiesText.getText().matches("^(\\s)*$")
                || weaponTypePicker.getValue() == null
                || damageTypePicker.getValue() == null) {
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to create your new item");
            errDialog.showAndWait();
            return;
        }

        ItemManagerModel.getInstance().newItem(
                nameText.getText(),
                descriptionText.getText(),
                propertiesText.getText(),
                weaponTypePicker.getValue(),
                damageTypePicker.getValue()
        );
    }

    @FXML
    public void updateItemButtonOnClick() {
        if (nameText.getText().matches("^(\\s)*$")
                || descriptionText.getText().matches("^(\\s)*$")
                || propertiesText.getText().matches("^(\\s)*$")
                || weaponTypePicker.getValue() == null
                || damageTypePicker.getValue() == null) {
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to update the item");
            errDialog.showAndWait();
            return;
        }

        ItemManagerModel.getInstance().updateItem(
                nameText.getText(),
                descriptionText.getText(),
                propertiesText.getText(),
                weaponTypePicker.getValue(),
                damageTypePicker.getValue()
        );
    }

    @FXML
    public void deleteItemButtonOnClick() {
        if (nameText.getText().matches("^(\\s)*$")) {
            Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Name's value empty", "Please enter at least name of item you want to delete");
            errDialog.showAndWait();
            return;
        }

        ItemManagerModel.getInstance().deleteItemEvent(nameText.getText());
    }

    @FXML
    public void backButtonOnClick() {
        ItemManagerModel.getInstance().returnToMenu();
    }
}
