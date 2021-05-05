package model.gui;

import controller.CharacterCreatorController;
import controller.ItemPickerController;
import javafx.stage.Stage;
import model.items.Item;
import model.items.ItemType;
import view.CharacterCreatorView;
import view.ItemPickerView;

/**
 * Model of Item Picker view.
 * Called to show Item Picker view and handle all view logic.
 *
 * @see CharacterCreatorView associated class view (MVC pattern)
 * @see CharacterCreatorController associated class controller (MVC pattern)
 */
public class ItemPickerModel {

    private static ItemPickerModel instance;

    /**
     * 1st constructor of this class.
     * When called, init Item Picker view and calling view.
     *
     * @param owner calling view
     */
    public ItemPickerModel(Stage owner) {
        instance = this;
        new ItemPickerView(owner);
    }

    /**
     * 2nd constructor of this class.
     * When called, init Item Picker view, calling view and wanted item type.
     * Use this constructor to force user to select specific item type
     *
     * @param owner    calling view
     * @param itemType item type user needs to select
     */
    public ItemPickerModel(Stage owner, ItemType itemType) {
        instance = this;
        new ItemPickerView(owner);

        ItemPickerView.getInstance().setItemType(itemType);
    }

    /**
     * @return instance of this class
     */
    public static ItemPickerModel getInstance() {
        return instance;
    }

    /**
     * @return user selected item
     */
    public Item getSelectedItem() {
        return ItemPickerController.getInstance().selectedItem;
    }

    /**
     * Cancel Item pick
     */
    public void back() {
        ItemPickerView.getInstance().close();
    }
}
