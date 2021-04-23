package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import view.CharacterView;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    private static CharacterController instance;

    @FXML
    public ImageView imageJob;
    @FXML
    public Pane paneImageJob;
    @FXML
    public TextFlow jobInfo;
    @FXML
    public TextFlow improvementsInfo;
    @FXML
    public Rectangle hpBar;
    @FXML
    public Text hpText;
    @FXML
    public Text levelText;
    @FXML
    public VBox window;
    @FXML
    public Rectangle borderHPBar;
    @FXML
    public Tab characterTab;
    @FXML
    public AnchorPane inventoryPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        imageJob.fitWidthProperty().bind(paneImageJob.widthProperty());
        imageJob.fitHeightProperty().bind(paneImageJob.heightProperty());

        window.widthProperty().addListener(((observable, oldValue, newValue) -> {
            CharacterView.getInstance().updateHPBarWidth(newValue.intValue());
        }));

        GridPane inventory = new GridPane();
        inventory.setGridLinesVisible(true);

        for (int i = 0; i < 10; i++) {
            inventory.addColumn(i, new Text(String.valueOf(i)));

            for (int j = 0; j < 10; j++) {
                inventory.addColumn(j, new Text(String.valueOf(j)));
            }
        }

        inventoryPane.getChildren().add(inventory);
    }

    @FXML
    public void hpBarOnClick() {
        CharacterModel.getInstance().hpBarOnClickEvent();
    }

    @FXML
    public void diceMenuOnAction() {
        CharacterModel.getInstance().openDiceWindow();
    }

    public static CharacterController getInstance() {
        return instance;
    }
}
