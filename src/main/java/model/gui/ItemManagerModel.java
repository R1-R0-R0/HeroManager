package model.gui;

import view.ItemManagerView;

/**
 * Model of Item Manager view.
 * Called to show item manager and handle its components
 *
 * @see ItemManagerView associated class view (MVC pattern)
 * @see controller.ItemManagerController associated class controller (MVC pattern)
 */
public class ItemManagerModel {

    private static ItemManagerModel instance;

    /**
     * Constructor of this class.
     * When called, calls method to init view
     */
    public ItemManagerModel() {
        instance = this;

        new ItemManagerView();
    }

    /**
     * @return instance of this class
     */
    public static ItemManagerModel getInstance() {
        return instance;
    }

    /*
    public void newItem(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {

    }

    public void updateItem(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {

    } */

    /**
     * Delete item of software database
     *
     * @param name name of item to delete
     */
    public void deleteItemEvent(String name) {

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
