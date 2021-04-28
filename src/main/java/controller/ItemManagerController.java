package controller;

import com.sun.javafx.scene.control.LabeledText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.items.Item;
import model.items.ItemType;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagerController implements Initializable {

    @FXML
    public ComboBox<ItemType> typePicker;
    @FXML
    public ListView<Item> itemList;
    @FXML
    public Label itemNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ItemType> list = FXCollections.observableArrayList(ItemType.values());
        typePicker.setItems(list);
    }

    @FXML
    public void itemTypeSelectedEvent() {
        System.out.println("EVENT");
        System.out.println("typePicker.getValue().toString() = " + typePicker.getValue().toString());
    }
}
