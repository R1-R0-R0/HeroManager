package utils.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Utility class usable to show simple dialog alert
 */
public class Dialog {

    private Alert alert;

    /**
     * Constructor of this class, who allows to init desired dialog
     * @param alertType type of alert (ERROR or WARNING)
     * @param title title of the dialog
     * @param text text of the dialog
     */
    public Dialog(Alert.AlertType alertType, String title, String text) {
        alert = new Alert(alertType);
        alert.setTitle("HeroManager - " + alertType.name().toLowerCase());
        alert.setHeaderText(title);
        alert.setContentText(text);
    }

    /**
     * To show dialog
     */
    public void show() {
        alert.show();
    }

    /**
     * To show dialog
     */
    public void showAndWait() {
        alert.showAndWait();
    }
}
