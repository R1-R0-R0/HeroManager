package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import model.gui.CharacterModel;
import model.items.equipments.*;
import model.spell.Spell;
import view.CharacterView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Character Card view.
 * It implements all listeners and init views of all tabs contained.
 *
 * @see CharacterView associated class view (MVC pattern)
 * @see CharacterModel associated class model (MVC pattern)
 */
public class CharacterController implements Initializable {

    /**
     * Instance of this class
     */
    private static CharacterController instance;

    /*
      Character Tab
     */

    /**
     * Image location of character's picture in character tab
     */
    @FXML
    public AnchorPane imageJob;

    /**
     * Texts flows related to characters information (general info, skills and improvements) in character tab
     */
    @FXML
    public TextFlow jobInfo, improvementsInfo, skillsInfo;

    /**
     * Texts related to health and level information in character tab
     */
    @FXML
    public Text hpText, levelText;

    /**
     * All window, root of all nodes in view
     */
    @FXML
    public VBox window;

    /**
     * Pane containing all hp information (bar, border and text)
     */
    @FXML
    public StackPane hpBarContainer;

    /**
     * Graphically represents hp and it's border
     */
    @FXML
    public Pane hpBar, borderHpBar;

    /**
     * Tab character, used to custom its text
     */
    @FXML
    public Tab characterTab;

    /*
      Spell Tab
     */

    /**
     * List of spells that characters possess
     */
    @FXML
    public ListView<Spell> spellList;

    /**
     * Global information view on selected spell in spellList
     */
    @FXML
    public TextArea spellInfo;

    /**
     * Additionnal information view on selected spell in spellList
     */
    @FXML
    public TextArea spellDesc;

    /*
      Equipment Tab
     */

    /**
     * Node containing ImageView and all nodes related to equipment tab
     */
    @FXML
    public AnchorPane equipmentTab;

    /**
     * Images views related to each part of character's equipment
     */
    @FXML
    public ImageView headImage, bodyImage, mantleImage, beltImage, legsImage, feetImage, amuletImage, ringImage1, ringImage2;

    /*
      Inventory Tab
     */

    /**
     * Node containing all sub-nodes related to inventory tab. Its content is created when view is loading
     */
    @FXML
    public AnchorPane inventoryPane;

    /**
     * @return instance of this class
     */
    public static CharacterController getInstance() {
        return instance;
    }

    /*
      ----
     */

    /**
     * Entry of controller, called when view is loading.
     * It loads all images, tooltips and events required to the view to work.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

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

    /*
      EVENTS
     */

    /**
     * Called when Dice thrower is clicked on Table menu
     */
    @FXML
    public void diceMenuOnAction() {
        CharacterModel.getInstance().openDiceWindow();
    }

    /* CHARACTERS EVENT */

    /**
     * Called when user click on HP bar or hp text on Character tab to edit it
     */
    @FXML
    public void hpBarOnClick() {
        CharacterModel.getInstance().hpBarOnClickEvent();
    }

    /* EQUIPMENT EVENT */

    /**
     * Called when user click on Image View related to the head equipment in Equipment tab
     * Event
     */
    @FXML
    public void headImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.HEAD);
    }

    /**
     * Called when user click on Image View related to the body equipment in Equipment tab
     */
    @FXML
    public void bodyImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.BODY);
    }

    /**
     * Called when user click on Image View related to the mantle equipment in Equipment tab
     */
    @FXML
    public void mantleImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.MANTLE);
    }

    /**
     * Called when user click on Image View related to the belt equipment in Equipment tab
     */
    @FXML
    public void beltImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.BELT);
    }

    /**
     * Called when user click on Image View related to the head equipment in Equipment tab
     */
    @FXML
    public void legsImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.LEGS);
    }

    /**
     * Called when user click on Image View related to the feet equipment in Equipment tab
     */
    @FXML
    public void feetImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.FEET);
    }

    /**
     * Called when user click on Image View related to the amulet equipment in Equipment tab
     */
    @FXML
    public void amuletImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.AMULET);
    }

    /**
     * Called when user click on Image View related to the 1st ring equipment in Equipment tab
     */
    @FXML
    public void ring1ImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.RING);
    }

    /**
     * Called when user click on Image View related to the 2nd ring equipment in Equipment tab
     */
    @FXML
    public void ring2ImageOnClick() {
        CharacterModel.getInstance().equipmentImageOnClick(EquipmentParts.RING);
    }
}
