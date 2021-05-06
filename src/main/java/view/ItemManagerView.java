package view;

import controller.ItemManagerController;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import model.items.Item;

import java.io.IOException;
import java.util.List;

/**
 * View manager of Item manager's view
 *
 * @see ItemManagerModel associated class model (MVC pattern)
 * @see controller.ItemManagerController associated class controller (MVC pattern)
 */
public class ItemManagerView {

    private static ItemManagerView instance;
    private Stage stage;

    /**
     * Should NOT BE CALLED directly, its corresponding model will call it automatically.
     * When called, load item manager's fxml
     */
    public ItemManagerView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_manager.fxml"));
            stage.setTitle("HeroManager - Item Manager");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> ItemManagerModel.getInstance().returnToMenu());
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return instance of this class
     */
    public static ItemManagerView getInstance() {
        return instance;
    }

    /**
     * To show items in list view ui
     *
     * @param items items to show
     */
    public void setItemsListView(List<Item> items) {
        ItemManagerController.getInstance().itemList.setItems(FXCollections.observableArrayList(items));
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
