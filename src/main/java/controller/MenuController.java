package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.gui.MenuModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private static MenuController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
    }

    public static MenuController getInstance() {
        return instance;
    }

    @FXML
    public void resumeButtonOnClick() {
        MenuModel.getInstance().resumeGame();
    }

    @FXML
    public void newGameButtonOnClick() {
        MenuModel.getInstance().newGame();
    }

    @FXML
    public void loadGameButtonOnClick() {
        MenuModel.getInstance().loadGame();
    }

    @FXML
    public void manageItemsButtonOnClick() {

    }

    @FXML
    public void settingsButtonOnClick() {

    }
}
