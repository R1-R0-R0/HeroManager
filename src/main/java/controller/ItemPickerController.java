package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.items.Item;
import model.items.ItemType;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemPickerController implements Initializable {

    @FXML
    public ComboBox<ItemType> typePicker;
    @FXML
    public ListView<Item> itemList;
    @FXML
    public Label itemNameLabel;

    public Item selectedItem;

    private static ItemPickerController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        ObservableList<ItemType> list = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(list);
    }

    public static ItemPickerController getInstance() {
        return instance;
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
    public void selectItemButtonOnClick() {
        if (selectedItem == null) return;
    }

    @FXML
    public void backButtonOnClick() {

    }
}
