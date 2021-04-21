package model;

import controller.IndexController;

public class IndexModel {

    private boolean bool = false;

    public IndexModel() {

    }

    public void simpleButtonTrigger() {
        IndexController.instance.simpleButton.setText(bool ? ":)" : ":(");
        bool = !bool;
    }
}
