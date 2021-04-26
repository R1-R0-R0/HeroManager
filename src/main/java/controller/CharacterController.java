package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import model.spell.Spell;
import view.CharacterView;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    private static CharacterController instance;

    /** Character Tab **/
    @FXML
    public ImageView imageJob;
    @FXML
    public AnchorPane paneImageJob;
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

    /** Spell Tab **/
    @FXML
    public ListView<Spell> spellList;
    @FXML
    public TextArea spellInfo;
    @FXML
    public TextArea spellDesc;

    /** Inventory Tab **/
    @FXML
    public AnchorPane inventoryPane;

    /** ---- **/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        // imageJob.fitWidthProperty().bind(paneImageJob.widthProperty());
        // imageJob.fitHeightProperty().bind(paneImageJob.heightProperty());

        window.widthProperty().addListener(((observable, oldValue, newValue) -> {
            CharacterView.getInstance().updateHPBarWidth(newValue.intValue());
        }));

        GridPane inventory = CharacterView.getInstance().createInventoryGrid();

        AnchorPane.setTopAnchor(inventory, 10.0);
        AnchorPane.setRightAnchor(inventory, 10.0);
        AnchorPane.setBottomAnchor(inventory, 10.0);
        AnchorPane.setLeftAnchor(inventory, 10.0);
        inventoryPane.getChildren().add(inventory);

        spellList.setOnMouseClicked(event -> CharacterView.getInstance().setSpellDetails(spellList.getSelectionModel().getSelectedItem()));
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
