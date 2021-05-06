package view;

import controller.ItemManagerController;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;

import java.io.IOException;

/**
 * View manager of Item manager's view
 *
 * @see ItemManagerModel associated class model (MVC pattern)
 * @see controller.ItemManagerController associated class controller (MVC pattern)
 */
public class ItemManagerView {

    private static ItemManagerView instance;
    private Stage stage;

    /**
     * Should NOT BE CALLED directly, its corresponding model will call it automatically.
     * When called, load item manager's fxml
     */
    public ItemManagerView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_manager.fxml"));
            stage.setTitle("HeroManager - Item Manager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> ItemManagerModel.getInstance().returnToMenu());
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return instance of this class
     */
    public static ItemManagerView getInstance() {
        return instance;
    }

    /**
     * To update view when user selected an item type in picker (combobox)
     */
    public void itemTypeSelectedEvent() {
        ItemManagerController controller = ItemManagerController.getInstance();
        ItemType selectedType = controller.typePicker.getValue();

        controller.weaponsPane.setVisible(false);
        controller.equipmentPane.setVisible(false);
        controller.consumablePane.setVisible(false);

        switch (selectedType) {
            case WEAPONS -> {
                controller.weaponsPane.setVisible(true);
                controller.itemList.setItems(FXCollections.observableArrayList(ItemManagerModel.getInstance().getWeaponsList()));
            }
            case EQUIPMENTS -> {
                controller.equipmentPane.setVisible(true);
                controller.itemList.setItems(FXCollections.observableArrayList(ItemManagerModel.getInstance().getEquipmentsList()));
            }
            case CONSUMABLES -> {
                controller.consumablePane.setVisible(true);
                controller.itemList.setItems(FXCollections.observableArrayList(ItemManagerModel.getInstance().getConsumablesList()));
            }
        }

        controller.newItemButton.setDisable(false);
    }

    /**
     * @return instance of this class
     */
    public static ItemManagerView getInstance() {
        return instance;
    }

    /**
     * To show items in list view ui
     *
     * @param items items to show
     */
    public void setItemsListView(List<Item> items) {
        ItemManagerController.getInstance().itemList.setItems(FXCollections.observableArrayList(items));
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
