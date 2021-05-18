package fr.univ_amu.heromanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import fr.univ_amu.heromanager.model.gui.CharacterModel;
import fr.univ_amu.heromanager.model.gui.LevelUpModel;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.model.job.JobType;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.view.CharacterView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of Character Card fr.univ_amu.heromanager.view.
 * It implements all listeners and init views of all tabs contained.
 *
 * @see CharacterView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see CharacterModel associated class fr.univ_amu.heromanager.model (MVC pattern)
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
     * All window, root of all nodes in fr.univ_amu.heromanager.view
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
     * Tab of spells
     */
    @FXML
    public Tab spellsTabDongle;

    /**
     * List of spells that characters possess
     */
    @FXML
    public ListView<Spell> spellList;

    /**
     * Global information fr.univ_amu.heromanager.view on selected spell in spellList
     */
    @FXML
    public TextArea spellInfo;

    /**
     * Additionnal information fr.univ_amu.heromanager.view on selected spell in spellList
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
     * Node containing all sub-nodes related to inventory tab. Its content is created when fr.univ_amu.heromanager.view is loading
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
     * Entry of fr.univ_amu.heromanager.controller, called when fr.univ_amu.heromanager.view is loading.
     * It loads all images, tooltips and events required to the fr.univ_amu.heromanager.view to work.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        boolean spellTabDisabled = true;
        Job character = CharacterModel.getInstance().getCharacter();
        for (JobType jobType : JobType.JOBS_SPELLS_AUTHORIZED) {
            if (character.getJobType() == jobType) {
                spellTabDisabled = false;
                break;
            }
        }
        spellsTabDongle.setDisable(spellTabDisabled);

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

        equipmentTab.widthProperty().addListener((observable, oldValue, newValue) -> {
            double oldV = oldValue.doubleValue(),
                    newV = newValue.doubleValue();

            if (oldV == 0) return;

            headPane.setLayoutX(newV * headPane.getLayoutX() / oldV);
            bodyPane.setLayoutX(newV * bodyPane.getLayoutX() / oldV);
            mantlePane.setLayoutX(newV * mantlePane.getLayoutX() / oldV);
            beltPane.setLayoutX(newV * beltPane.getLayoutX() / oldV);
            legsPane.setLayoutX(newV * legsPane.getLayoutX() / oldV);
            feetPane.setLayoutX(newV * feetPane.getLayoutX() / oldV);
            amuletPane.setLayoutX(newV * amuletPane.getLayoutX() / oldV);
            ringPane1.setLayoutX(newV * ringPane1.getLayoutX() / oldV);
            ringPane2.setLayoutX(newV * ringPane2.getLayoutX() / oldV);
        });

        equipmentTab.heightProperty().addListener((observable, oldValue, newValue) -> {
            double oldV = oldValue.doubleValue(),
                    newV = newValue.doubleValue();

            if (oldV == 0) return;

            headPane.setLayoutY(newV * headPane.getLayoutY() / oldV);
            bodyPane.setLayoutY(newV * bodyPane.getLayoutY() / oldV);
            mantlePane.setLayoutY(newV * mantlePane.getLayoutY() / oldV);
            beltPane.setLayoutY(newV * beltPane.getLayoutY() / oldV);
            legsPane.setLayoutY(newV * legsPane.getLayoutY() / oldV);
            feetPane.setLayoutY(newV * feetPane.getLayoutY() / oldV);
            amuletPane.setLayoutY(newV * amuletPane.getLayoutY() / oldV);
            ringPane1.setLayoutY(newV * ringPane1.getLayoutY() / oldV);
            ringPane2.setLayoutY(newV * ringPane2.getLayoutY() / oldV);
        });
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

    /**
     * Called when user click on save in Game menu
     */
    @FXML
    public void saveGameMenuOnClick() {
        CharacterModel.getInstance().saveGame();
    }

    /**
     * Called when user click on Open Item Manager in Game menu
     */
    @FXML
    public void openItemManagerMenuOnClick() {
        CharacterModel.getInstance().openItemManager();
    }

    /**
     * Called when user click on Open Spell Manager in Game menu
     */
    @FXML
    public void openSpellManagerMenuOnClick() {
        CharacterModel.getInstance().openSpellManager();
    }

    /**
     * Called when user click on back to menu in Game menu
     */
    @FXML
    public void backToMenuMenuOnClick() {
        CharacterView.getInstance().returnToMenu();
    }

    /**
     * Called when user click on save in Game menu
     */
    @FXML
    public void quitMenuOnClick() {
        CharacterView.getInstance().close();
    }
}
