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

/**
 * Controller of Item Picker view
 * It implements all listeners and init all required items
 *
 * @see view.ItemPickerView associated class view (MVC pattern)
 * @see model.gui.ItemPickerModel associated class model (MVC pattern)
 */
public class ItemPickerController implements Initializable {

    private static ItemPickerController instance;
    /**
     * Picker (ComboBox) of item type to choose
     */
    @FXML
    public ComboBox<ItemType> typePicker;
    /**
     * List of selected items type
     */
    @FXML
    public ListView<Item> itemList;
    /**
     * Label view to show selected item name
     */
    @FXML
    public Label itemNameLabel;
    /**
     * Selected item by user
     */
    public Item selectedItem;

    /**
     * @return instance of this class
     */
    public static ItemPickerController getInstance() {
        return instance;
    }

    /**
     * Entry point of controller.
     * When called, init picker of item types according to enum
     *
     * @param location
     * @param resources
     * @see ItemType enum of existing types of items
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        ObservableList<ItemType> list = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(list);
    }

    /**
     * Event triggered when user select a type of item
     */
    @FXML
    public void itemTypeSelectedEvent() {
        ItemType selectedType = typePicker.getValue();

        switch (selectedType) {
            case WEAPONS:
            case EQUIPMENTS:
            case CONSUMABLES:
        }
    }

    /**
     * Event triggered when user confirm item choice
     */
    @FXML
    public void selectItemButtonOnClick() {
        if (selectedItem == null) return;
    }

    /**
     * Event triggered when user wants to cancel item picker view and go back to previous view
     */
    @FXML
    public void backButtonOnClick() {

    }
}
