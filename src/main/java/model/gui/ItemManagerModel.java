package model.gui;

import controller.ItemManagerController;
import exceptions.UnsupportedItemException;
import javafx.scene.control.Alert;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentEffect;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import utils.gui.Dialog;
import view.ItemManagerView;

import java.util.List;

/**
 * Model of Item Manager view.
 * Called to show item manager and handle its components
 *
 * @see ItemManagerView associated class view (MVC pattern)
 * @see controller.ItemManagerController associated class controller (MVC pattern)
 */
public class ItemManagerModel {

    private static ItemManagerModel instance;

    private List<Weapon> weapons;
    private List<Equipment> equipments;
    private List<Consumable> consumables;

    /**
     * Constructor of this class.
     * When called, calls method to init view
     */
    public ItemManagerModel() {
        instance = this;

        /*
            TODO

             setWeaponsList(get depuis bdd)
             setEquipmentList(get depuis bdd)
             setConsumablesList(...)
         */

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
     * @param weapons list of weapons to set
     */
    public void setWeaponList(List<Weapon> weapons) {
        this.weapons = weapons;
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
        this.equipments = equipments;
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
        this.consumables = consumables;
    }

    /**
     * @return setted up consumables list for list view ui
     * @see ItemManagerModel#setConsumableList(List) to set returned list
     */
    public List<Consumable> getConsumablesList() {
        return consumables;
    }

    /**
     * Called when event item creation is triggered by view.
     * If some required fields are empty, an error pop up will be throw
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
                        || controller.equipmentTypePicker.getValue() == null
                        || controller.equipmentEffectPicker.getValue() == null) {
                    errDialog.showAndWait();
                    return;
                }

                newEquipment(
                        controller.equipmentNameText.getText(),
                        controller.equipmentDescriptionText.getText(),
                        controller.equipmentPartPicker.getValue(),
                        controller.equipmentTypePicker.getValue(),
                        controller.equipmentEffectPicker.getValue()
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
                        || controller.equipmentTypePicker.getValue() == null
                        || controller.equipmentEffectPicker.getValue() == null) {
                    errDialog.showAndWait();
                    return;
                }

                updateEquipment(
                        controller.equipmentNameText.getText(),
                        controller.equipmentDescriptionText.getText(),
                        controller.equipmentPartPicker.getValue(),
                        controller.equipmentTypePicker.getValue(),
                        controller.equipmentEffectPicker.getValue()
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
        // TODO
    }

    /**
     * Method used to update an existing weapon in software's database
     *
     * @param name        name of weapon (must exist)
     * @param description description of weapon
     * @param properties  properties of weapons
     * @param weaponType  type of weapon
     * @param damageType  damages that weapon inflict
     */
    public void updateWeapon(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {
        // TODO
    }

    /**
     * Method used to create a new equipment in software's database
     *
     * @param name            name of equipment
     * @param description     description of equipment
     * @param equipmentPart   body part or equipment must be worn
     * @param equipmentType   type of equipment
     * @param equipmentEffect effect of equipment (if null, considered as no effect)
     */
    public void newEquipment(String name, String description, EquipmentPart equipmentPart, EquipmentType equipmentType, EquipmentEffect equipmentEffect) {
        // TODO
    }

    /**
     * Method used to update an existing equipment in software's database.
     *
     * @param name            name of equipment
     * @param description     description of equipment
     * @param equipmentPart   body part or equipment must be worn
     * @param equipmentType   type of equipment
     * @param equipmentEffect effect of equipment (if null, considered as no effect)
     */
    public void updateEquipment(String name, String description, EquipmentPart equipmentPart, EquipmentType equipmentType, EquipmentEffect equipmentEffect) {
        // TODO
    }

    /**
     * Method used to create a new equipment without effect in software's database
     *
     * @param name          name of equipment
     * @param description   description of equipment
     * @param equipmentPart body part or equipment must be worn
     * @param equipmentType type of equipment
     */
    public void newEquipment(String name, String description, EquipmentPart equipmentPart, EquipmentType equipmentType) {
        newEquipment(name, description, equipmentPart, equipmentType, null);
    }

    /**
     * Method used to update an existing equipment without effect in software's database
     *
     * @param name          name of equipment
     * @param description   description of equipment
     * @param equipmentPart body part or equipment must be worn
     * @param equipmentType type of equipment
     */
    public void updateEquipment(String name, String description, EquipmentPart equipmentPart, EquipmentType equipmentType) {
        updateEquipment(name, description, equipmentPart, equipmentType, null);
    }

    /**
     * Method used to create a new consumable in software's database
     *
     * @param name        name of consumable
     * @param description description of consumable (effects...)
     */
    public void newConsumable(String name, String description) {
        // TODO
    }

    /**
     * Method used to update an existing consumable in software's database
     *
     * @param name
     * @param description
     */
    public void updateConsumable(String name, String description) {
        // TODO
    }

    /**
     * Delete item of software database
     *
     * @param name name of item to delete
     */
    public void deleteItem(String name) {
        // TODO
    }

    /**
     * Allows to close view and return to menu
     */
    public void returnToMenu() {
        ItemManagerView.getInstance().close();
        new MenuModel();
    }

    /**
     * Used to list all equipement parts of body
     */
    public enum EquipmentPart {
        HEAD,
        BODY,
        BELT,
        LEGS,
        FEET,
        AMULET,
        HANDS,
        MANTLE,
        RING
    }
}
