package utils.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PopUpDialog {

    private Alert alert;

    public PopUpDialog(Alert.AlertType alertType, String title, String text) {
        alert = new Alert(alertType);
        alert.setTitle("HeroManager - Error");
        alert.setHeaderText(title);
        alert.setContentText(text);
    }

    public void addButton(ButtonType buttonType) {
        alert.getButtonTypes().add(buttonType);
    }

    public void show() {
        alert.show();
    }

    public void showAndWait() {
        alert.showAndWait();
    }
}
