package utils.gui;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.awt.*;
import java.util.Optional;

/**
 * Utility class usable to show simple dialog alert
 */
public class Dialog {

    private Alert alert;
    private Optional<ButtonType> result;

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
     * Used to get buttons linked to dialog
     * @return observable list of buttons of dialog
     */
    public ObservableList<ButtonType> getButtons() {
        return alert.getButtonTypes();
    }

    /**
     * To show dialog
     */
    public void show() {
        playSound();
        alert.show();
    }

    /**
     * To show dialog
     * @return result of dialog
     */
    public Optional<ButtonType> showAndWait() {
        playSound();
        return alert.showAndWait();
    }

    /**
     * Used to play Windows sound according to alert type
     */
    private void playSound() {
        Runnable sound = null;
        switch (alert.getAlertType()) {
            case ERROR -> sound = ((Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand"));
            case WARNING -> sound = ((Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation"));
            case INFORMATION -> sound = ((Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.asterisk"));
            case CONFIRMATION -> sound = ((Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.question"));
        }
        if (sound != null) sound.run();
    }
}
