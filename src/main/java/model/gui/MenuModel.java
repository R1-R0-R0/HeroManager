package model.gui;

public class MenuModel {

    private static MenuModel instance;

    public MenuModel() {
        instance = this;
    }

    public static MenuModel getInstance() {
        return instance;
    }
}
