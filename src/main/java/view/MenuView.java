package view;

import controller.Main;
import controller.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.job.Job;

import java.io.IOException;

/**
 * View manager of Menu
 *
 * @see model.gui.MenuModel associated class model (MVC pattern)
 * @see controller.MenuController associated class controller (MVC pattern)
 */
public class MenuView implements View {

    private static MenuView instance;
    private Stage stage;

    /**
     * Should NOT BE CALLED directly, its corresponding model will call it automatically.
     * When called, loads fxml and its components
     */
    public MenuView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage.setTitle("HeroManager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return instance of this class
     */
    public static MenuView getInstance() {
        return instance;
    }

    /**
     * Allows to set last played game button, to quickly resume last played game
     *
     * @param job last played job to show, null if there is none
     */
    public void setLastGame(Job job) {
        MenuController controller = MenuController.getInstance();

        if (job == null) {
            controller.resumeButton.setDisable(true);
            controller.resumeText.setText("");
        } else {
            controller.resumeButton.setDisable(false);
            controller.resumeText.setText(
                    job.getName() + " - "
                            + job.getJobType() + " - "
                            +  "LVL " + job.getLevel()
            );
        }
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }

    /**
     * @return window of this view
     */
    public Stage getStage() {
        return stage;
    }
}
