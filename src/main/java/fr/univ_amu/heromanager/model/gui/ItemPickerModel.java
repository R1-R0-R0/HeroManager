package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.CharacterCreatorController;
import fr.univ_amu.heromanager.controller.ItemPickerController;
import fr.univ_amu.heromanager.exceptions.UnsupportedItemException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.utils.ItemPickerCloseEvent;
import fr.univ_amu.heromanager.utils.ListenableArrayList;
import fr.univ_amu.heromanager.utils.SimpleListener;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.CharacterCreatorView;
import fr.univ_amu.heromanager.view.ItemPickerView;

import java.util.Arrays;
import java.util.List;

/**
 * Model of Item Picker fr.univ_amu.heromanager.view.
 * Called to show Item Picker fr.univ_amu.heromanager.view and handle all fr.univ_amu.heromanager.view logic.
 *
 * @see CharacterCreatorView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see CharacterCreatorController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class ItemPickerModel implements Model {

    private static ItemPickerModel instance;
    private final ListenableArrayList<Weapon> weapons;
    private final ListenableArrayList<Equipment> equipments;
    private final ListenableArrayList<Consumable> consumables;
    private final ItemPickerCloseEvent closeListener;
    private Item selectedItem;

    /**
     * 1st constructor of this class.
     * When called, init Item Picker fr.univ_amu.heromanager.view and calling fr.univ_amu.heromanager.view.
     *
     * @param owner         calling fr.univ_amu.heromanager.view
     * @param closeListener listener to call when context is closing
     */
    public ItemPickerModel(Stage owner, ItemPickerCloseEvent closeListener) {
        instance = this;
        this.closeListener = closeListener;

        weapons = new ListenableArrayList<>();
        equipments = new ListenableArrayList<>();
        consumables = new ListenableArrayList<>();

        setWeaponList(HeroManagerDB.getWeapons());
        setEquipmentList(HeroManagerDB.getEquipments());
        setConsumableList(HeroManagerDB.getConsumables());

        SimpleListener updateListener = () -> {
            try {
                itemTypeSelectedEvent();
            } catch (UnsupportedItemException e) {
                e.printStackTrace();
                new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()));
            }
        };

        weapons.addListenerForAllActions(updateListener);
        equipments.addListenerForAllActions(updateListener);
        consumables.addListenerForAllActions(updateListener);

        new ItemPickerView(owner);
    }

    /**
     * 2nd constructor of this class.
     * When called, init Item Picker fr.univ_amu.heromanager.view, calling fr.univ_amu.heromanager.view and wanted item type.
     * Use this constructor to force user to select specific item type
     *
     * @param owner         calling fr.univ_amu.heromanager.view
     * @param itemType      item type user needs to select
     * @param closeListener listener to call when context is closing and returning selected item
     */
    public ItemPickerModel(Stage owner, ItemType itemType, ItemPickerCloseEvent closeListener) {
        this(owner, closeListener);

        try {
            ItemPickerView.getInstance().setItemType(itemType);
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace())).showAndWait();
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
     * Called when user selected an item type. (Also called by fr.univ_amu.heromanager.model if item lists are updated)
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
     * @return setted up weapon list for list fr.univ_amu.heromanager.view ui
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
     * @return setted up equipment list for list fr.univ_amu.heromanager.view ui
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
     * @return setted up consumables list for list fr.univ_amu.heromanager.view ui
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
     * @param item selected item
     */
    public void setSelectedItem(Item item) {
        selectedItem = item;

        try {
            ItemPickerView.getInstance().showItemInfo(item);
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace())).showAndWait();
            System.exit(1);
        }
    }

    /**
     * To confirm user selection and close fr.univ_amu.heromanager.view
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
