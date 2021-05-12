package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import model.gui.CharacterModel;
import model.gui.LevelUpModel;
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
public class CharacterController implements Controller {

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
     * Container's pane related to each part of character's equipment
     */
    @FXML
    public StackPane headPane, bodyPane, mantlePane, beltPane, legsPane, feetPane, amuletPane, ringPane1, ringPane2;

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

        spellList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> CharacterView.getInstance().setSpellDetails(newValue));

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

        Tooltip.install(headPane, headTip);
        Tooltip.install(bodyPane, bodyTip);
        Tooltip.install(mantlePane, mantleTip);
        Tooltip.install(beltPane, beltTip);
        Tooltip.install(legsPane, legsTip);
        Tooltip.install(feetPane, feetTip);
        Tooltip.install(amuletPane, amuletTip);
        Tooltip.install(ringPane1, ringTip);
        Tooltip.install(ringPane2, ringTip);
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
     * Called when user click on Pane related to the head equipment in Equipment tab
     * Event
     */
    @FXML
    public void headPaneOnCLick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.HEAD);
    }

    /**
     * Called when user click on Pane related to the body equipment in Equipment tab
     */
    @FXML
    public void bodyPaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.BODY);
    }

    /**
     * Called when user click on Pane related to the mantle equipment in Equipment tab
     */
    @FXML
    public void mantlePaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.MANTLE);
    }

    /**
     * Called when user click on Pane related to the belt equipment in Equipment tab
     */
    @FXML
    public void beltPaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.BELT);
    }

    /**
     * Called when user click on Pane related to the head equipment in Equipment tab
     */
    @FXML
    public void legsPaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.LEGS);
    }

    /**
     * Called when user click on Pane related to the feet equipment in Equipment tab
     */
    @FXML
    public void feetPaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.FEET);
    }

    /**
     * Called when user click on Pane related to the amulet equipment in Equipment tab
     */
    @FXML
    public void amuletPaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.AMULET);
    }

    /**
     * Called when user click on Pane related to the 1st ring equipment in Equipment tab
     */
    @FXML
    public void ring1PaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.RING);
    }

    /**
     * Called when user click on Pane related to the 2nd ring equipment in Equipment tab
     */
    @FXML
    public void ring2PaneOnClick() {
        CharacterModel.getInstance().equipmentPaneOnClick(EquipmentPart.RING2);
    }

    /**
     * Called when user want to level up his character
     */
    @FXML
    public void levelUpCharacter() {
        new LevelUpModel(CharacterView.getInstance().getStage());
    }
}
