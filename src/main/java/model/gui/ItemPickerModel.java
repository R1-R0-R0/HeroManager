package model.gui;

import controller.CharacterCreatorController;
import controller.ItemPickerController;
import exceptions.UnsupportedItemException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.items.Item;
import model.items.ItemType;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import utils.ItemPickerCloseEvent;
import utils.ListenableArrayList;
import utils.SimpleListener;
import utils.gui.Dialog;
import view.CharacterCreatorView;
import view.ItemPickerView;

import java.util.List;

/**
 * Model of Item Picker view.
 * Called to show Item Picker view and handle all view logic.
 *
 * @see CharacterCreatorView associated class view (MVC pattern)
 * @see CharacterCreatorController associated class controller (MVC pattern)
 */
public class ItemPickerModel {

    private static ItemPickerModel instance;
    private final ListenableArrayList<Weapon> weapons;
    private final ListenableArrayList<Equipment> equipments;
    private final ListenableArrayList<Consumable> consumables;
    private Item selectedItem;
    private final ItemPickerCloseEvent closeListener;

    /**
     * 1st constructor of this class.
     * When called, init Item Picker view and calling view.
     *
     * @param owner calling view
     * @param closeListener listener to call when context is closing
     */
    public ItemPickerModel(Stage owner, ItemPickerCloseEvent closeListener) {
        instance = this;
        this.closeListener = closeListener;

        weapons = new ListenableArrayList<>();
        equipments = new ListenableArrayList<>();
        consumables = new ListenableArrayList<>();

        SimpleListener updateListener = () -> {
            try {
                itemTypeSelectedEvent();
            } catch (UnsupportedItemException e) {
                e.printStackTrace();
                new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage());
            }
        };

        weapons.addListenerForAllActions(updateListener);
        equipments.addListenerForAllActions(updateListener);
        consumables.addListenerForAllActions(updateListener);

        /*
            TODO

             setWeaponsList(get depuis bdd)
             setEquipmentList(get depuis bdd)
             setConsumablesList(...)
         */

        new ItemPickerView(owner);
    }

    /**
     * 2nd constructor of this class.
     * When called, init Item Picker view, calling view and wanted item type.
     * Use this constructor to force user to select specific item type
     *
     * @param owner    calling view
     * @param itemType item type user needs to select
     * @param closeListener listener to call when context is closing and returning selected item
     */
    public ItemPickerModel(Stage owner, ItemType itemType, ItemPickerCloseEvent closeListener) {
        this(owner, closeListener);

        try {
            ItemPickerView.getInstance().setItemType(itemType);
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage()).showAndWait();
            System.exit(1);
        }
    }

    /**
     * @return instance of this class
     */
    public static ItemPickerModel getInstance() {
        return instance;
    }

    /**
     * Called when user selected an item type. (Also called by model if item lists are updated)
     */
    public void itemTypeSelectedEvent() throws UnsupportedItemException {
        ItemPickerView view = ItemPickerView.getInstance();
        ItemType selectedType = ItemPickerController.getInstance().typePicker.getValue();

        if (selectedType == null) return;

        switch (selectedType) {
            case WEAPONS -> view.setListView(weapons);
            case EQUIPMENTS -> view.setListView(equipments);
            case CONSUMABLES -> view.setListView(consumables);
            default -> throw new UnsupportedItemException(selectedType);
        }
    }

    /**
     * Used to set weapons list to show when user select weapons
     *
     * @param weapons array list of weapons to set
     */
    public void setWeaponList(List<Weapon> weapons) {
        this.weapons.clear();
        this.weapons.addAll(weapons);
    }

    /**
     * @return setted up weapon list for list view ui
     * @see ItemManagerModel#setWeaponList(List) to set returned list
     */
    public List<Weapon> getWeaponsList() {
        return weapons;
    }

    /**
     * Used to set equipment list to show when user select equipment
     *
     * @param equipments list of equipments to set
     */
    public void setEquipmentList(List<Equipment> equipments) {
        this.equipments.clear();
        this.equipments.addAll(equipments);
    }

    /**
     * @return setted up equipment list for list view ui
     * @see ItemManagerModel#setEquipmentList(List) to set returned list
     */
    public List<Equipment> getEquipmentsList() {
        return equipments;
    }

    /**
     * Used to set consumable list to show when user select consumables
     *
     * @param consumables list of consumables to set
     */
    public void setConsumableList(List<Consumable> consumables) {
        this.consumables.clear();
        this.consumables.addAll(consumables);
    }

    /**
     * @return setted up consumables list for list view ui
     * @see ItemManagerModel#setConsumableList(List) to set returned list
     */
    public List<Consumable> getConsumablesList() {
        return consumables;
    }

    /**
     * @return user selected item
     */
    public Item getSelectedItem() {
        return selectedItem;
    }

    /**
     * Save selected item and show its information
     *
     * @param item
     */
    public void setSelectedItem(Item item) {
        selectedItem = item;

        try {
            ItemPickerView.getInstance().showItemInfo(item);
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage()).showAndWait();
            System.exit(1);
        }
    }

    /**
     * To confirm user selection and close view
     */
    public void confirmItemSelection() {
        ItemPickerView.getInstance().close();
        closeListener.event(selectedItem);
    }

    /**
     * Cancel Item pick
     */
    public void back() {
        selectedItem = null;
        ItemPickerView.getInstance().close();
        closeListener.event(selectedItem);
    }
}
