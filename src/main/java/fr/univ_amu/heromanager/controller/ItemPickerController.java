package fr.univ_amu.heromanager.controller;

import fr.univ_amu.heromanager.exceptions.UnsupportedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import fr.univ_amu.heromanager.model.gui.ItemPickerModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Controller of Item Picker fr.univ_amu.heromanager.view
 * It implements all listeners and init all required items
 *
 * @see fr.univ_amu.heromanager.view.ItemPickerView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see fr.univ_amu.heromanager.model.gui.ItemPickerModel associated class fr.univ_amu.heromanager.model (MVC pattern)
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
     * Label fr.univ_amu.heromanager.view to show selected item name
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
     * Entry point of fr.univ_amu.heromanager.controller.
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
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace())).showAndWait();
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
     * Event triggered when user wants to cancel item picker fr.univ_amu.heromanager.view and go back to previous fr.univ_amu.heromanager.view
     */
    @FXML
    public void backButtonOnClick() {
        ItemPickerModel.getInstance().back();
    }
}
