package controller;

import exceptions.UnsupportedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.gui.ItemPickerModel;
import model.items.Item;
import model.items.ItemType;
import utils.gui.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Item Picker view
 * It implements all listeners and init all required items
 *
 * @see view.ItemPickerView associated class view (MVC pattern)
 * @see model.gui.ItemPickerModel associated class model (MVC pattern)
 */
public class ItemPickerController implements Controller {

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
     * Pane to show all information about selected item
     */
    @FXML
    public AnchorPane itemInfoPane;

    /**
     * Button to confirm user selection and close dialog
     */
    @FXML
    public Button selectItemButton;

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

        itemList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ItemPickerModel.getInstance().setSelectedItem(newValue));
    }

    /**
     * Event triggered when user select a type of item
     */
    @FXML
    public void itemTypeSelectedEvent() {
        try {
            ItemPickerModel.getInstance().itemTypeSelectedEvent();
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage()).showAndWait();
            System.exit(1);
        }
    }

    /**
     * Event triggered when user confirm item choice
     */
    @FXML
    public void selectItemButtonOnClick() {
        ItemPickerModel.getInstance().confirmItemSelection();
    }

    /**
     * Event triggered when user wants to cancel item picker view and go back to previous view
     */
    @FXML
    public void backButtonOnClick() {
        ItemPickerModel.getInstance().back();
    }
}
