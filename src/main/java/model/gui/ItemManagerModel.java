package model.gui;

import controller.ItemManagerController;
import exceptions.UnsupportedItemException;
import javafx.scene.control.Alert;
import model.files.HeroManagerDB;
import model.items.ItemType;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentPart;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import utils.ListenableArrayList;
import utils.gui.Dialog;
import view.ItemManagerView;

import java.util.Arrays;
import java.util.List;

/**
 * Model of Item Manager view.
 * Called to show item manager and handle its components
 *
 * @see ItemManagerView associated class view (MVC pattern)
 * @see controller.ItemManagerController associated class controller (MVC pattern)
 */
public class ItemManagerModel implements Model {

    private static ItemManagerModel instance;

    private final ListenableArrayList<Weapon> weapons;
    private final ListenableArrayList<Equipment> equipments;
    private final ListenableArrayList<Consumable> consumables;

    /**
     * Constructor of this class.
     * When called, calls method to init view
     */
    public ItemManagerModel() {
        instance = this;

        weapons = new ListenableArrayList<>();
        equipments = new ListenableArrayList<>();
        consumables = new ListenableArrayList<>();

        setWeaponList(HeroManagerDB.getWeapons());
        setEquipmentList(HeroManagerDB.getEquipments());
        setConsumableList(HeroManagerDB.getConsumables());

        weapons.addListenerForAllActions(() -> ItemManagerView.getInstance().updateListViews());
        equipments.addListenerForAllActions(() -> ItemManagerView.getInstance().updateListViews());
        consumables.addListenerForAllActions(() -> ItemManagerView.getInstance().updateListViews());

        new ItemManagerView();
    }

    /**
     * @return instance of this class
     */
    public static ItemManagerModel getInstance() {
        return instance;
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
     * Logical method when user selected an item in list view
     */
    public void itemSelectedEvent() {
        ItemManagerController controller = ItemManagerController.getInstance();

        try {
            ItemManagerView.getInstance().setItemInformation(controller.typePicker.getValue(), controller.itemList.getSelectionModel().getSelectedItem());
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace())).showAndWait();
            System.exit(1);
        }
    }

    /**
     * Called when event item creation is triggered by view.
     * If some required fields are empty, an error pop up will be throw
     *
     * @throws UnsupportedItemException when item isn't handled
     */
    public void createItem() throws UnsupportedItemException {
        ItemManagerController controller = ItemManagerController.getInstance();
        Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to create your new item");

        switch (controller.typePicker.getValue()) {
            case WEAPONS -> {
                if (controller.weaponNameText.getText().matches("^(\\s)*$")
                        || controller.weaponDescriptionText.getText().matches("^(\\s)*$")
                        || controller.weaponPropertiesText.getText().matches("^(\\s)*$")
                        || controller.weaponTypePicker.getValue() == null
                        || controller.damageTypePicker.getValue() == null) {
                    errDialog.showAndWait();
                    return;
                }

                newWeapon(
                        controller.weaponNameText.getText(),
                        controller.weaponDescriptionText.getText(),
                        controller.weaponPropertiesText.getText(),
                        controller.weaponTypePicker.getValue(),
                        controller.damageTypePicker.getValue()
                );
            }
            case EQUIPMENTS -> {
                if (controller.equipmentNameText.getText().matches("^(\\s)*$")
                        || controller.equipmentDescriptionText.getText().matches("^(\\s)*$")
                        || controller.equipmentPartPicker.getValue() == null
                        || controller.equipmentTypePicker.getValue() == null) {
                    errDialog.showAndWait();
                    return;
                }

                newEquipment(
                        controller.equipmentNameText.getText(),
                        controller.equipmentDescriptionText.getText(),
                        controller.equipmentPartPicker.getValue(),
                        controller.equipmentTypePicker.getValue(),
                        controller.spinnerArmorBonus.getValue(),
                        controller.spinnerStrength.getValue(),
                        controller.spinnerDexterity.getValue(),
                        controller.spinnerRobustness.getValue(),
                        controller.spinnerIntelligence.getValue(),
                        controller.spinnerWisdom.getValue(),
                        controller.spinnerCharisma.getValue(),
                        controller.spinnerSpeed.getValue()
                );
            }
            case CONSUMABLES -> {
                if (controller.consumableNameText.getText().matches("^(\\s)*$")
                        || controller.consumableDescriptionText.getText().matches("^(\\s)*$")) {
                    errDialog.showAndWait();
                    return;
                }

                newConsumable(
                        controller.consumableNameText.getText(),
                        controller.consumableDescriptionText.getText()
                );
            }
            default -> throw new UnsupportedItemException(controller.typePicker.getValue());
        }
    }

    /**
     * Called when event item modification is triggered by view.
     * If some required fields are empty, an error pop up will be throw
     *
     * @throws UnsupportedItemException when item isn't handled
     */
    public void updateItem() throws UnsupportedItemException {
        ItemManagerController controller = ItemManagerController.getInstance();
        Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Some values are empty", "Please fill all values to update your item");

        switch (controller.typePicker.getValue()) {
            case WEAPONS -> {
                if (controller.weaponNameText.getText().matches("^(\\s)*$")
                        || controller.weaponDescriptionText.getText().matches("^(\\s)*$")
                        || controller.weaponPropertiesText.getText().matches("^(\\s)*$")
                        || controller.weaponTypePicker.getValue() == null
                        || controller.damageTypePicker.getValue() == null) {
                    errDialog.showAndWait();
                    return;
                }

                updateWeapon(
                        controller.weaponNameText.getText(),
                        controller.weaponDescriptionText.getText(),
                        controller.weaponPropertiesText.getText(),
                        controller.weaponTypePicker.getValue(),
                        controller.damageTypePicker.getValue()
                );
            }
            case EQUIPMENTS -> {
                if (controller.equipmentNameText.getText().matches("^(\\s)*$")
                        || controller.equipmentDescriptionText.getText().matches("^(\\s)*$")
                        || controller.equipmentPartPicker.getValue() == null
                        || controller.equipmentTypePicker.getValue() == null) {
                    errDialog.showAndWait();
                    return;
                }

                updateEquipment(
                        controller.equipmentNameText.getText(),
                        controller.equipmentDescriptionText.getText(),
                        controller.equipmentPartPicker.getValue(),
                        controller.equipmentTypePicker.getValue(),
                        controller.spinnerArmorBonus.getValue(),
                        controller.spinnerStrength.getValue(),
                        controller.spinnerDexterity.getValue(),
                        controller.spinnerRobustness.getValue(),
                        controller.spinnerIntelligence.getValue(),
                        controller.spinnerWisdom.getValue(),
                        controller.spinnerCharisma.getValue(),
                        controller.spinnerSpeed.getValue()
                );
            }
            case CONSUMABLES -> {
                if (controller.consumableNameText.getText().matches("^(\\s)*$")
                        || controller.consumableDescriptionText.getText().matches("^(\\s)*$")) {
                    errDialog.showAndWait();
                    return;
                }

                updateConsumable(
                        controller.consumableNameText.getText(),
                        controller.consumableDescriptionText.getText()
                );
            }
            default -> throw new UnsupportedItemException(controller.typePicker.getValue());
        }
    }

    /**
     * Called when event item deletion is triggered by view.
     * If name's field is empty, an error pop up will be throw
     *
     * @throws UnsupportedItemException when item isn't handled
     */
    public void deleteItem() throws UnsupportedItemException {
        ItemManagerController controller = ItemManagerController.getInstance();
        Dialog errDialog = new Dialog(Alert.AlertType.ERROR, "Name's value empty", "Please enter at least name of item you want to delete");

        switch (controller.typePicker.getValue()) {
            case WEAPONS -> {
                if (controller.weaponNameText.getText().matches("^(\\s)*$")) {
                    errDialog.showAndWait();
                    return;
                }

                deleteItem(controller.weaponNameText.getText());
            }
            case EQUIPMENTS -> {
                if (controller.equipmentNameText.getText().matches("^(\\s)*$")) {
                    errDialog.showAndWait();
                    return;
                }

                deleteItem(controller.equipmentNameText.getText());
            }
            case CONSUMABLES -> {
                if (controller.consumableNameText.getText().matches("^(\\s)*$")) {
                    errDialog.showAndWait();
                    return;
                }

                deleteItem(controller.consumableNameText.getText());
            }
            default -> throw new UnsupportedItemException(controller.typePicker.getValue());
        }
    }

    /**
     * Method used to create a new weapon in software's database
     *
     * @param name        name of weapon
     * @param description description of weapon
     * @param properties  properties of weapons
     * @param weaponType  type of weapon
     * @param damageType  damages that weapon inflict
     */
    public void newWeapon(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {
        Weapon newWeapon = new Weapon(name, description, properties, weaponType, damageType);

        if (HeroManagerDB.addWeapon(newWeapon))
            weapons.add(newWeapon);
        else
            new Dialog(Alert.AlertType.ERROR, "Item already exist", "This item already exist, or an error occurred during item creation").showAndWait();
    }

    /**
     * Method used to update an existing weapon in software's database
     *
     * @param name        name of weapon
     * @param description description of weapon
     * @param properties  properties of weapons
     * @param weaponType  type of weapon
     * @param damageType  damages that weapon inflict
     */
    public void updateWeapon(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {
        Weapon selectedWeapon = (Weapon) ItemManagerController.getInstance().itemList.getSelectionModel().getSelectedItem();
        Weapon newWeapon = new Weapon(name, description, properties, weaponType, damageType);

        int size = weapons.size();
        for (int index = 0; index < size; index++) {
            if (weapons.get(index) == selectedWeapon) {
                weapons.set(index, newWeapon);
            }
        }

        if (selectedWeapon.getName().equals(name))
            HeroManagerDB.modifyWeapon(newWeapon);
        else {
            HeroManagerDB.removeWeapon(selectedWeapon.getName());
            HeroManagerDB.addWeapon(newWeapon);
        }
    }

    /**
     * Method used to create a new equipment in software's database
     *
     * @param name          name of equipment
     * @param description   description of equipment
     * @param equipmentPart body part or equipment must be worn
     * @param equipmentType type of equipment
     */
    public void newEquipment(String name, String description, EquipmentPart equipmentPart, EquipmentType equipmentType,
                             int armorBonus, int strengthBoost, int dexterityBoost, int robustnessBoost,
                             int intelligenceBoost, int wisdomBoost, int charismaBoost, int speedBoost) {
        Equipment newEquipment = new Equipment(name, description, equipmentPart, armorBonus, equipmentType,
                strengthBoost, dexterityBoost, robustnessBoost, intelligenceBoost, wisdomBoost, charismaBoost, speedBoost);

        if (HeroManagerDB.addEquipment(newEquipment))
            equipments.add(newEquipment);
        else
            new Dialog(Alert.AlertType.ERROR, "Item already exist", "This item already exist, or an error occurred during item creation").showAndWait();
    }

    /**
     * Method used to update an existing equipment in software's database
     *
     * @param name          name of equipment
     * @param description   description of equipment
     * @param equipmentPart body part or equipment must be worn
     * @param equipmentType type of equipment
     */
    public void updateEquipment(String name, String description, EquipmentPart equipmentPart, EquipmentType equipmentType,
                                int armorBonus, int strengthBoost, int dexterityBoost, int robustnessBoost,
                                int intelligenceBoost, int wisdomBoost, int charismaBoost, int speedBoost) {
        Equipment selectedEquipment = (Equipment) ItemManagerController.getInstance().itemList.getSelectionModel().getSelectedItem();
        Equipment newEquipment = new Equipment(name, description, equipmentPart, armorBonus, equipmentType,
                strengthBoost, dexterityBoost, robustnessBoost, intelligenceBoost, wisdomBoost, charismaBoost, speedBoost);

        int size = equipments.size();
        for (int index = 0; index < size; index++) {
            if (equipments.get(index) == selectedEquipment) {
                equipments.set(index, newEquipment);
            }
        }

        if (selectedEquipment.getName().equals(name))
            HeroManagerDB.modifyEquipment(newEquipment);
        else {
            HeroManagerDB.removeEquipment(selectedEquipment.getName());
            HeroManagerDB.addEquipment(newEquipment);
        }
    }

    /**
     * Method used to create a new consumable in software's database
     *
     * @param name        name of consumable
     * @param description description of consumable (effects...)
     */
    public void newConsumable(String name, String description) {
        Consumable newConsumable = new Consumable(name, description);

        if (HeroManagerDB.addConsumable(newConsumable))
            consumables.add(newConsumable);
    }

    /**
     * Method used to update an existing consumable in software's database
     *
     * @param name        name of consumable
     * @param description description of consumable
     */
    public void updateConsumable(String name, String description) {
        Consumable selectedConsumable = (Consumable) ItemManagerController.getInstance().itemList.getSelectionModel().getSelectedItem();
        Consumable newConsumable = new Consumable(name, description);

        int size = consumables.size();
        for (int index = 0; index < size; index++) {
            if (consumables.get(index) == selectedConsumable) {
                consumables.set(index, newConsumable);
                break;
            }
        }

        if (selectedConsumable.getName().equals(name))
            HeroManagerDB.modifyConsumable(newConsumable);
        else {
            HeroManagerDB.removeConsumable(selectedConsumable.getName());
            HeroManagerDB.addConsumable(newConsumable);
        }
    }

    /**
     * Delete item of software database
     *
     * @param name name of item to delete
     */
    public void deleteItem(String name) throws UnsupportedItemException {
        ItemType itemType = ItemManagerController.getInstance().typePicker.getValue();

        Dialog dbErr = new Dialog(
                Alert.AlertType.ERROR,
                "Critical error occured",
                "An error occured while item registration, please try to restart HeroManager."
        );

        Dialog notFound = new Dialog(
                Alert.AlertType.WARNING,
                itemType + " not found",
                "Your " + itemType.toString().toLowerCase() + " named " + name + " don't exist.\nPlease verify entered name."
        );

        switch (itemType) {
            case WEAPONS -> {
                for (Weapon weapon : weapons) {
                    if (weapon.getName().equals(name)) {
                        if (HeroManagerDB.removeWeapon(name))
                            weapons.remove(weapon);
                        else dbErr.showAndWait();

                        return;
                    }
                }

                notFound.showAndWait();
            }
            case EQUIPMENTS -> {
                for (Equipment equipment : equipments) {
                    if (equipment.getName().equals(name)) {
                        if (HeroManagerDB.removeEquipment(name))
                            equipments.remove(equipment);
                        else dbErr.showAndWait();

                        return;
                    }
                }

                notFound.showAndWait();
            }
            case CONSUMABLES -> {
                for (Consumable consumable : consumables) {
                    if (consumable.getName().equals(name)) {
                        if (HeroManagerDB.removeConsumable(name))
                            consumables.remove(consumable);
                        else dbErr.showAndWait();

                        return;
                    }
                }

                notFound.showAndWait();
            }
            default -> throw new UnsupportedItemException(itemType);
        }
    }

    /**
     * Allows to close view and return to menu
     */
    public void returnToMenu() {
        ItemManagerView.getInstance().close();
        new MenuModel();
    }
}
