package utils.gui;

import javafx.scene.control.Alert;

public class PopUpDialog {

    private Alert alert;

    public PopUpDialog(Alert.AlertType alertType) {
        alert = new Alert(alertType);
        alert.setTitle("Title");
        alert.setHeaderText("Header");
        alert.setContentText("Context");
    }

    public void show() {
        alert.show();
    }

    public void showAndWait() {
        alert.showAndWait();
    }
}
