package view;

import controller.ItemPickerController;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.items.Item;
import model.items.ItemType;

import java.io.IOException;

/**
 * View manager of Item Picker view.
 *
 * @see model.gui.ItemPickerModel associated class model (MVC pattern)
 * @see ItemPickerController associated class controller (MVC pattern)
 */
public class ItemPickerView {

    private static ItemPickerView instance;
    private Stage stage;

    /**
     * 1st constructor of this class, with another view as owner.
     * Should NOT BE CALLED directly, ItemPickerModel automatically calls it.
     * When called, init view and its fxml.
     *
     * @param owner caller of view
     */
    public ItemPickerView(Stage owner) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_picker.fxml"));
            stage.setTitle("HeroManager - Item Picker");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2nd constructor of this class, without another view as owner.
     * Should NOT BE CALLED directly, ItemPickerModel automatically calls it.
     * When called, init view and its fxml.
     */
    public ItemPickerView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_picker.fxml"));
            stage.setTitle("HeroManager - Item Picker");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return instance of this class
     */
    public static ItemPickerView getInstance() {
        return instance;
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }

    /**
     * Called when user selected an item type, and enables all another fields.
     *
     * @param itemType selected item type
     */
    public void setItemType(ItemType itemType) {
        setTypePickerDisabled(true);
        ItemPickerController.getInstance().typePicker.setValue(itemType);
    }

    /**
     * Allows to block item type selection
     * @param disabled TRUE to disable, FALSE to enable
     */
    public void setTypePickerDisabled(boolean disabled) {
        ItemPickerController.getInstance().typePicker.setDisable(disabled);
    }

    /**
     * To update item list view
     */
    public void setListView(List<? extends Item> items) {
        ItemPickerController.getInstance().itemList.setItems(FXCollections.observableArrayList(items));
    }
}
