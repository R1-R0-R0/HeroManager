package model.gui;

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
}
