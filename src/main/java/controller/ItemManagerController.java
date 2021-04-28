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

    }

    @FXML
    public void updateItemButtonOnClick() {

    }

    @FXML
    public void deleteItemButtonOnClick() {

    }

    @FXML
    public void backButtonOnClick() {
        ItemManagerModel.getInstance().returnToMenu();
    }
}
