package model.gui;

import model.items.weapons.DamageType;
import model.items.weapons.WeaponType;
import view.ItemManagerView;

public class ItemManagerModel {

    private static ItemManagerModel instance;

    public ItemManagerModel() {
        instance = this;

        new ItemManagerView();
    }

    public static ItemManagerModel getInstance() {
        return instance;
    }

    /*
    public void newItem(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {

    }

    public void updateItem(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {

    } */

    public void deleteItemEvent(String name) {

    }

    public void returnToMenu() {
        ItemManagerView.getInstance().close();
        new MenuModel();
    }

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
