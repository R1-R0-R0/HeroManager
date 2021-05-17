package utils.gui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * Utility class usable to show simple dialog alert
 */
public class Dialog {

    private Alert alert;
    private Optional<ButtonType> result;
    private boolean exceptionAlert;

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

        exceptionAlert = false;
    }

    /**
     * 2nd constructor, used to show an error pop up with an exception.
     * Print stack trace in standard output, and end program
     * @param title title of the dialog
     * @param e exception to show
     */
    public Dialog(String title, Exception e) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("HeroManager - Critical error");
        alert.setHeaderText(title);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Exception stacktrace :");

        TextArea exceptionTextArea = new TextArea(exceptionText);
        exceptionTextArea.setEditable(false);
        exceptionTextArea.setWrapText(true);
        exceptionTextArea.setMaxWidth(Double.MAX_VALUE);
        exceptionTextArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(exceptionTextArea, Priority.ALWAYS);
        GridPane.setHgrow(exceptionTextArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(exceptionTextArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        e.printStackTrace();

        exceptionAlert = true;
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

        if (exceptionAlert)
            alert.setOnCloseRequest(event -> System.exit(1));
    }

    /**
     * To show dialog
     * @return result of dialog
     */
    public Optional<ButtonType> showAndWait() {
        playSound();

        if (exceptionAlert) {
            alert.showAndWait();
            System.exit(1);
            return Optional.empty();
        } else
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
