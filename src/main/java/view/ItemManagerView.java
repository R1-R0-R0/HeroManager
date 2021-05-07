package view;

import controller.ItemManagerController;
import controller.Main;
import exceptions.UnsupportedItemException;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;

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

        clearItemInformation();
    }

    /**
     * To toggle if update and delete buttons are disabled or not
     *
     * @param disabled TRUE to disable them, FALSE otherwise
     */
    public void setDisableUpdateAndDeleteButtons(boolean disabled) {
        ItemManagerController controller = ItemManagerController.getInstance();
        controller.updateItemButton.setDisable(disabled);
        controller.deleteItemButton.setDisable(disabled);
    }

    /**
     * To clear all information in item edition view
     */
    public void clearItemInformation() {
        ItemManagerController controller = ItemManagerController.getInstance();

        controller.weaponNameText.clear();
        controller.weaponDescriptionText.clear();
        controller.weaponPropertiesText.clear();
        controller.weaponTypePicker.setValue(null);
        controller.damageTypePicker.setValue(null);

        controller.equipmentNameText.clear();
        controller.equipmentDescriptionText.clear();
        controller.equipmentPartPicker.setValue(null);
        controller.equipmentTypePicker.setValue(null);

        controller.consumableNameText.clear();
        controller.consumableDescriptionText.clear();

        setDisableUpdateAndDeleteButtons(true);
    }

    /**
     * To show information about item, by specifying its type
     *
     * @param itemType item type
     * @param item     item to show
     */
    public void setItemInformation(ItemType itemType, Item item) throws UnsupportedItemException {
        switch (itemType) {
            case WEAPONS -> setItemInformation(((Weapon) item));
            case EQUIPMENTS -> setItemInformation(((Equipment) item));
            case CONSUMABLES -> setItemInformation(((Consumable) item));
            default -> throw new UnsupportedItemException(itemType);
        }

        setDisableUpdateAndDeleteButtons(false);
    }

    /**
     * To show information about item in view
     *
     * @param weapon weapon to show
     */
    public void setItemInformation(Weapon weapon) {
        if (weapon == null) {
            clearItemInformation();
            return;
        }

        ItemManagerController controller = ItemManagerController.getInstance();

        controller.weaponNameText.setText(weapon.getName());
        controller.weaponDescriptionText.setText(weapon.getDescription());
        controller.weaponPropertiesText.setText(weapon.getProperties());
        controller.weaponTypePicker.setValue(weapon.getWeaponType());
        controller.damageTypePicker.setValue(weapon.getDamageType());
    }

    /**
     * To show information about item in view
     *
     * @param equipment equipment to show
     */
    public void setItemInformation(Equipment equipment) {
        if (equipment == null) {
            clearItemInformation();
            return;
        }

        ItemManagerController controller = ItemManagerController.getInstance();

        controller.equipmentNameText.setText(equipment.getName());
        controller.equipmentDescriptionText.setText(equipment.getDescription());
        controller.equipmentPartPicker.setValue(equipment.getEquipmentParts());
        controller.equipmentTypePicker.setValue(equipment.getEquipmentType());
    }

    /**
     * To show information about item in view
     *
     * @param consumable consumable to show
     */
    public void setItemInformation(Consumable consumable) {
        if (consumable == null) {
            clearItemInformation();
            return;
        }

        ItemManagerController controller = ItemManagerController.getInstance();

        controller.consumableNameText.setText(consumable.getName());
        controller.consumableDescriptionText.setText(consumable.getDescription());
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
