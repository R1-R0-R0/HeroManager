package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import model.gui.CharacterModel;
import model.items.equipments.*;
import model.spell.Spell;
import view.CharacterView;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    private static CharacterController instance;

    /**
     * Character Tab
     **/
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

    /**
     * Spell Tab
     **/
    @FXML
    public ListView<Spell> spellList;
    @FXML
    public TextArea spellInfo;
    @FXML
    public TextArea spellDesc;

    /**
     * Equipment Tab
     **/
    @FXML
    public ImageView headImage, bodyImage, mantleImage, beltImage, legsImage, feetImage, amuletImage, ringImage1, ringImage2;

    /**
     * Inventory Tab
     **/
    @FXML
    public AnchorPane inventoryPane;

    public static CharacterController getInstance() {
        return instance;
    }

    /**
     * ----
     **/

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

        headImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        bodyImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        mantleImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        beltImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        legsImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        feetImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        amuletImage.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        ringImage1.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));
        ringImage2.setImage(new Image(getClass().getResourceAsStream(CharacterView.IMAGE_PLUS_PATH)));

        Tooltip headTip = new Tooltip("Head");
        headTip.setShowDelay(Duration.ONE);
        Tooltip bodyTip = new Tooltip("Body");
        bodyTip.setShowDelay(Duration.ONE);
        Tooltip mantleTip = new Tooltip("Mantle");
        mantleTip.setShowDelay(Duration.ONE);
        Tooltip beltTip = new Tooltip("Belt");
        beltTip.setShowDelay(Duration.ONE);
        Tooltip legsTip = new Tooltip("Legs");
        legsTip.setShowDelay(Duration.ONE);
        Tooltip feetTip = new Tooltip("Feet");
        feetTip.setShowDelay(Duration.ONE);
        Tooltip amuletTip = new Tooltip("Amulet");
        amuletTip.setShowDelay(Duration.ONE);
        Tooltip ringTip = new Tooltip("Ring");
        ringTip.setShowDelay(Duration.ONE);

        Tooltip.install(headImage, headTip);
        Tooltip.install(bodyImage, bodyTip);
        Tooltip.install(mantleImage, mantleTip);
        Tooltip.install(beltImage, beltTip);
        Tooltip.install(legsImage, legsTip);
        Tooltip.install(feetImage, feetTip);
        Tooltip.install(amuletImage, amuletTip);
        Tooltip.install(ringImage1, ringTip);
        Tooltip.install(ringImage2, ringTip);
    }

    /** EVENTS **/

    @FXML
    public void diceMenuOnAction() {
        CharacterModel.getInstance().openDiceWindow();
    }

    /* CHARACTERS EVENT */

    @FXML
    public void hpBarOnClick() {
        CharacterModel.getInstance().hpBarOnClickEvent();
    }

    /* EQUIPMENT EVENT */
    @FXML
    public void headImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(HeadEquipment.class);
    }

    @FXML
    public void bodyImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(BodyEquipment.class);
    }

    @FXML
    public void mantleImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(MantleEquipment.class);
    }

    @FXML
    public void beltImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(BeltEquipment.class);
    }

    @FXML
    public void legsImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(LegsEquipment.class);
    }

    @FXML
    public void feetImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(FeetEquipment.class);
    }

    @FXML
    public void amuletImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(AmuletEquipment.class);
    }

    @FXML
    public void ring1ImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(RingEquipment.class);
    }

    @FXML
    public void ring2ImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(RingEquipment.class);
    }
}
