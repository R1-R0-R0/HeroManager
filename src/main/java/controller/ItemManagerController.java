package controller;

import com.sun.javafx.scene.control.LabeledText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagerController implements Initializable {

    @FXML
    public ComboBox<Text> typePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text weaponsText = new Text("Weapons");
        Text equipmentText = new Text("Equipment");
        Text consumablesText = new Text("Consumables");

        typePicker.setValue(new Text("Select item type"));

        ObservableList<Text> list = FXCollections.observableArrayList(weaponsText, equipmentText, consumablesText);
        typePicker.setItems(list);
    }

    @FXML
    public void itemTypeSelectedEvent() {
        System.out.println("EVENT");
        System.out.println("typePicker.getValue().getText() = " + typePicker.getValue().getText());
    }
}
