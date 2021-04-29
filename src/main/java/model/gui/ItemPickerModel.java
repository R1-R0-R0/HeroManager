package model.gui;

import controller.ItemPickerController;
import javafx.stage.Stage;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.Equipment;
import view.ItemPickerView;

public class ItemPickerModel {

    private static ItemPickerModel instance;

    public ItemPickerModel(Stage owner) {
        instance = this;
        new ItemPickerView();
    }

    public ItemPickerModel(Stage owner, ItemType itemType) {
        instance = this;
        new ItemPickerView();

        ItemPickerView.getInstance().setItemType(itemType);
    }

    public Item getSelectedItem() {
        return ItemPickerController.getInstance().selectedItem;
    }

    public static ItemPickerModel getInstance() {
        return instance;
    }

    public void back() {
        ItemPickerView.getInstance().close();
    }
}
