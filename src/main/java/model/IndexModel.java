package model;

import controller.IndexController;
import view.IndexView;

public class IndexModel {

    private boolean bool = false;

    public IndexModel() {

    }

    public void simpleButtonTrigger() {
        IndexView.getInstance().updateButtonText(bool ? ":)" : ":(");
        bool = !bool;
    }
}
