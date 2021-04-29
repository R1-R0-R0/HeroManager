package view;

import controller.ItemPickerController;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.Equipment;

import java.io.IOException;

public class ItemPickerView {

    private Stage stage;
    private static ItemPickerView instance;

    public ItemPickerView() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/item_picker.fxml"));
            stage.setTitle("HeroManager - Item Picker");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(CharacterView.getInstance().getStage());
            stage.setResizable(false);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }

    public void setItemType(ItemType itemType) {
        ItemPickerController.getInstance().typePicker.setDisable(true);
        ItemPickerController.getInstance().typePicker.setValue(itemType);
    }

    public void updateList() {
        ListView<Item> listView = ItemPickerController.getInstance().itemList;
        // TODO
    }

    public static ItemPickerView getInstance() {
        return instance;
    }
}
