package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.controller.ItemPickerController;
import fr.univ_amu.heromanager.Main;
import fr.univ_amu.heromanager.exceptions.UnsupportedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.gui.ItemPickerModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Item Picker fr.univ_amu.heromanager.view.
 *
 * @see fr.univ_amu.heromanager.model.gui.ItemPickerModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see ItemPickerController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class ItemPickerView implements View {

    private static ItemPickerView instance;
    private Stage stage;

    /**
     * 1st constructor of this class, with another fr.univ_amu.heromanager.view as owner.
     * Should NOT BE CALLED directly, ItemPickerModel automatically calls it.
     * When called, init fr.univ_amu.heromanager.view and its fxml.
     *
     * @param owner caller of fr.univ_amu.heromanager.view
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
            new Dialog("An error occurred while opening Item Picker fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * 2nd constructor of this class, without another fr.univ_amu.heromanager.view as owner.
     * Should NOT BE CALLED directly, ItemPickerModel automatically calls it.
     * When called, init fr.univ_amu.heromanager.view and its fxml.
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
            new Dialog("An error occurred while opening Item Picker fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static ItemPickerView getInstance() {
        return instance;
    }

    /**
     * To close fr.univ_amu.heromanager.view
     */
    public void close() {
        stage.close();
    }

    /**
     * Used to force an item type to user
     *
     * @param itemType selected item type
     */
    public void setItemType(ItemType itemType) throws UnsupportedItemException {
        setTypePickerDisabled(true);
        ItemPickerController.getInstance().typePicker.setValue(itemType);
        ItemPickerModel.getInstance().itemTypeSelectedEvent();
    }

    /**
     * Allows to block item type selection
     *
     * @param disabled TRUE to disable, FALSE to enable
     */
    public void setTypePickerDisabled(boolean disabled) {
        ItemPickerController.getInstance().typePicker.setDisable(disabled);
    }

    /**
     * To update item list fr.univ_amu.heromanager.view
     */
    public void setListView(List<? extends Item> items) {
        ItemPickerController.getInstance().itemList.setItems(FXCollections.observableArrayList(items));
    }

    /**
     * Show information about given item
     *
     * @param item to show information about
     * @throws UnsupportedItemException if type of selected item isn't recognized
     */
    public void showItemInfo(Item item) throws UnsupportedItemException {
        ItemPickerView.getInstance().clearItemInfo();
        if (item == null) {
            ItemPickerView.getInstance().setSelectItemButtonDisable(true);
            return;
        }

        ItemPickerView.getInstance().setSelectItemButtonDisable(false);

        if (item instanceof Weapon)
            showWeaponInfo(((Weapon) item));
        else if (item instanceof Equipment)
            showEquipmentInfo(((Equipment) item));
        else if (item instanceof Consumable)
            showConsumableInfo(((Consumable) item));
        else
            throw new UnsupportedItemException("Unsupported item " + item);

        ItemPickerController.getInstance().itemNameLabel.setText(item.getName());
    }

    /**
     * Clear all information shown about an item
     */
    public void clearItemInfo() {
        ItemPickerController controller = ItemPickerController.getInstance();
        controller.itemNameLabel.setText("");
        controller.itemInfoPane.getChildren().clear();
    }

    /**
     * Show information about a weapon, with its unique item properties
     *
     * @param weapon to show information about
     */
    private void showWeaponInfo(Weapon weapon) {
        ObservableList<Node> info = ItemPickerController.getInstance().itemInfoPane.getChildren();

        Text mainInfo = new Text(
                "Weapon type: " + weapon.getWeaponType() +
                        "\nDamage type inflicted: " + weapon.getDamageType()
        );

        Text properties = new Text(
                "\n\nProperties: " + weapon.getProperties()
        );

        Text desc = new Text(
                "\n\n---\n\n" + weapon.getDescription()
        );

        info.add(new TextFlow(mainInfo, properties, desc));
    }

    /**
     * Show information about an equipment, with its unique item properties
     *
     * @param equipment to show information about
     */
    private void showEquipmentInfo(Equipment equipment) {
        ObservableList<Node> info = ItemPickerController.getInstance().itemInfoPane.getChildren();

        Text mainInfo = new Text(
                "Can be worn on: " + equipment.getEquipmentPart() +
                        "\nEquipment type: " + equipment.getEquipmentType()
        );

        Text stats = new Text(
                "\n\nStatistics:" +
                        "\n\t- Armor Bonus:\t\t" + equipment.getArmorBonus() +
                        "\n\t- Strength Boost:\t\t" + equipment.getStrengthBoost() +
                        "\n\t- Dexterity Boost:\t\t" + equipment.getDexterityBoost() +
                        "\n\t- Robustness Boost:\t\t" + equipment.getRobustnessBoost() +
                        "\n\t- Intelligence Boost:\t\t" + equipment.getIntelligenceBoost() +
                        "\n\t- Wisdom Boost:\t\t" + equipment.getWisdomBoost() +
                        "\n\t- Charisma Boost:\t\t" + equipment.getCharismaBoost() +
                        "\n\t- Speed Boost:\t\t\t" + equipment.getSpeedBoost()
        );

        Text desc = new Text(
                "\n\n---\n\n" + equipment.getDescription()
        );


        info.add(new TextFlow(mainInfo, stats, desc));
    }

    /**
     * Show information about a consumable, with its unique item properties
     *
     * @param consumable to show information about
     */
    private void showConsumableInfo(Consumable consumable) {
        ItemPickerController.getInstance().itemInfoPane.getChildren().add(
                new TextFlow(new Text(consumable.getDescription()))
        );
    }

    /**
     * Enable or disable select button
     *
     * @param disabled TRUE to disable it, FALSE otherwise
     */
    public void setSelectItemButtonDisable(boolean disabled) {
        ItemPickerController.getInstance().selectItemButton.setDisable(disabled);
    }

    /**
     * @return stage of fr.univ_amu.heromanager.view
     */
    public Stage getStage() {
        return stage;
    }
}
