package model.gui;

import view.ItemManagerView;
import view.ItemPickerView;

public class ItemPickerModel {

    private static ItemPickerModel instance;

    public ItemPickerModel() {
        instance = this;

        new ItemPickerView();
    }

    public static ItemPickerModel getInstance() {
        return instance;
    }

    public void back() {
        ItemPickerView.getInstance().close();
    }
}
