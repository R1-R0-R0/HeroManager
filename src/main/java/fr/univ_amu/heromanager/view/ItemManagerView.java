package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.Main;
import fr.univ_amu.heromanager.controller.ItemManagerController;
import fr.univ_amu.heromanager.exceptions.UnsupportedItemException;
import fr.univ_amu.heromanager.model.gui.ItemManagerModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * View manager of Item manager's fr.univ_amu.heromanager.view
 *
 * @see ItemManagerModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see fr.univ_amu.heromanager.controller.ItemManagerController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class ItemManagerView implements View {

    private static ItemManagerView instance;
    private Stage stage;

    /**
     * Should NOT BE CALLED directly, its corresponding fr.univ_amu.heromanager.model will call it automatically.
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
            new Dialog("An error occurred while opening Item Manager fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * 2nd constructor used when another view apart from menu is opening item manager
     *
     * @param owner view caller
     */
    public ItemManagerView(Stage owner) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_manager.fxml"));
            stage.setTitle("HeroManager - Item Manager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> ItemManagerModel.getInstance().returnToMenu());
            stage.initOwner(owner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            instance = this;
        } catch (IOException e) {
            new Dialog("An error occurred while opening Item Manager fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static ItemManagerView getInstance() {
        return instance;
    }

    /**
     * To update fr.univ_amu.heromanager.view when user selected an item type in picker (combobox)
     */
    public void itemTypeSelectedEvent() {
        ItemManagerController controller = ItemManagerController.getInstance();
        ItemType selectedType = controller.typePicker.getValue();

        if (selectedType == null) return;

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
     * To reload ListViews after their modifications
     */
    public void updateListViews() {
        itemTypeSelectedEvent();
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
     * To clear all information in item edition fr.univ_amu.heromanager.view
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
        controller.spinnerStrength.getValueFactory().setValue(0);
        controller.spinnerDexterity.getValueFactory().setValue(0);
        controller.spinnerRobustness.getValueFactory().setValue(0);
        controller.spinnerIntelligence.getValueFactory().setValue(0);
        controller.spinnerWisdom.getValueFactory().setValue(0);
        controller.spinnerCharisma.getValueFactory().setValue(0);
        controller.spinnerSpeed.getValueFactory().setValue(0);

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
     * To show information about item in fr.univ_amu.heromanager.view
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
     * To show information about item in fr.univ_amu.heromanager.view
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
        controller.equipmentPartPicker.setValue(equipment.getEquipmentPart());
        controller.equipmentTypePicker.setValue(equipment.getEquipmentType());

        controller.spinnerArmorBonus.getValueFactory().setValue(equipment.getArmorBonus());
        controller.spinnerStrength.getValueFactory().setValue(equipment.getStrengthBoost());
        controller.spinnerDexterity.getValueFactory().setValue(equipment.getDexterityBoost());
        controller.spinnerRobustness.getValueFactory().setValue(equipment.getRobustnessBoost());
        controller.spinnerIntelligence.getValueFactory().setValue(equipment.getIntelligenceBoost());
        controller.spinnerWisdom.getValueFactory().setValue(equipment.getWisdomBoost());
        controller.spinnerCharisma.getValueFactory().setValue(equipment.getCharismaBoost());
        controller.spinnerSpeed.getValueFactory().setValue(equipment.getSpeedBoost());
    }

    /**
     * To show information about item in fr.univ_amu.heromanager.view
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
     * Getter of Stage current view
     *
     * @return stage of view
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * To close fr.univ_amu.heromanager.view
     */
    public void close() {
        stage.close();
    }
}
