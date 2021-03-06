package fr.univ_amu.heromanager.view;

import fr.univ_amu.heromanager.controller.CharacterController;
import fr.univ_amu.heromanager.Main;
import fr.univ_amu.heromanager.model.gui.MenuModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import fr.univ_amu.heromanager.model.gui.CharacterModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentInventory;
import fr.univ_amu.heromanager.model.job.*;
import fr.univ_amu.heromanager.model.spell.Spell;
import fr.univ_amu.heromanager.utils.gui.ContainerPane;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * View manager of Character Card fr.univ_amu.heromanager.view.
 * Used to update fr.univ_amu.heromanager.view and its components
 *
 * @see CharacterModel associated class fr.univ_amu.heromanager.model (MVC pattern)
 * @see CharacterController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class CharacterView implements View {

    /* INVENTORY TAB SETTINGS */
    /**
     * Defines the size of the inventory.
     * This value is square root of number of created cells
     */
    public final static int INVENTORY_SIZE = 8;

    /**
     * Defines the size of images in inventory
     */
    public final static int INVENTORY_ITEM_IMAGE_SIZE = 40;

    /**
     * Path to PLUS (+) image
     */
    public final static String IMAGE_PLUS_PATH = "/images/ui/plus.png";

    /**
     * Path folder which contains all characters pictures
     */
    public final static String IMAGES_JOBS_FOLDER = "/images/jobs/pictures/";


    private static CharacterView instance;
    private Stage stage;

    /**
     * Constructor of this class.
     * Should NOT BE CALLED directly, CharacterModel automatically calls it.
     * When called, fxml fr.univ_amu.heromanager.view, its stage and all additional information, such as picture, or character's info is set up
     *
     * @see CharacterController manager of all fr.univ_amu.heromanager.view
     */
    public CharacterView() {
        try {
            instance = this;
            Job character = CharacterModel.getInstance().getCharacter();

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/character.fxml"));
            stage.setTitle("HeroManager - " + character.getName());
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            // stage.setResizable(false);
            stage.show();


            initResponsiveNodes();

            Gender gender = character.getGender();
            JobType jobType = character.getJobType();

            refreshView();

            String pictureNameJobType = jobType.name().toLowerCase();
            String pictureNameGender = (gender == Gender.MAN) ? "_m.jpg" : "_f.jpg";
            String pictureJobPath = IMAGES_JOBS_FOLDER + pictureNameJobType + pictureNameGender;
            Image pictureJob = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureJobPath)));

            BackgroundImage bgImg = new BackgroundImage(pictureJob,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false));
            CharacterController.getInstance().imageJob.setBackground(new Background(bgImg));
        } catch (IOException e) {
            new Dialog("An error occurred while opening Character fr.univ_amu.heromanager.view", e).showAndWait();
        }
    }

    /**
     * @return instance of this class
     */
    public static CharacterView getInstance() {
        return instance;
    }

    /**
     * @return handled stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Called by constructor to initialize responsive nodes, such as hp bar or or equipment tab
     */
    private void initResponsiveNodes() {
        CharacterController.getInstance().borderHpBar.widthProperty().addListener((observable, oldValue, newValue) -> {
            Pane hpBar = CharacterController.getInstance().hpBar;
            hpBar.setMaxWidth(newValue.doubleValue() * hpBar.getMaxWidth() / oldValue.doubleValue());
        });
    }

    /**
     * Character Tab
     **/

    /**
     * Used to show up character's general information
     *
     * @param name       name of character
     * @param race       race of character
     * @param statistics all statistics of character
     */
    public void setJobInfo(String name, String race, String... statistics) {
        TextFlow jobInfo = CharacterController.getInstance().jobInfo;
        ObservableList list = jobInfo.getChildren();
        list.clear();

        Text title = new Text(name + " - " + race + "\n\n");
        title.setFont(new Font(30));

        Text titleStats = new Text("Statistics" + "\n");
        titleStats.setFont(new Font(20));

        list.addAll(title, titleStats);

        for (String stat : statistics) {
            list.add(new Text("    - " + stat + "\n"));
        }
    }

    /**
     * Used to show up character's skills
     *
     * @param skills skills to show
     */
    public void setSkills(String... skills) {
        TextFlow skillsInfo = CharacterController.getInstance().skillsInfo;
        ObservableList<Node> list = skillsInfo.getChildren();
        list.clear();

        Text title = new Text("Skills" + "\n\n");
        title.setFont(new Font(20));
        list.add(title);

        if (skills.length == 0) {
            list.add(new Text("None"));
            return;
        }

        for (String skill : skills) {
            list.add(new Text("    - " + skill + "\n"));
        }
    }

    /**
     * Used to show up character's improvements
     *
     * @param improvements improvements to show
     */
    public void setImprovements(String... improvements) {
        TextFlow improvementsInfo = CharacterController.getInstance().improvementsInfo;
        ObservableList<Node> list = improvementsInfo.getChildren();
        list.clear();

        Text title = new Text("Improvements" + "\n\n");
        title.setFont(new Font(20));
        list.add(title);

        if (improvements.length == 0) {
            list.add(new Text("None"));
            return;
        }

        for (String improvement : improvements) {
            list.add(new Text("    - " + improvement + "\n"));
        }
    }

    /**
     * Used to show up character's current health points and max health points
     *
     * @param hp    health points
     * @param maxHP max health points
     */
    public void setHP(int hp, int maxHP) {
        Pane hpBar = CharacterController.getInstance().hpBar;
        Pane maxHPBar = CharacterController.getInstance().borderHpBar;
        Text hpText = CharacterController.getInstance().hpText;

        hpBar.setMaxWidth(((double) hp) / maxHP * maxHPBar.getWidth());
        hpText.setText(hp + " / " + maxHP);
    }

    /**
     * Used to show up character's current level
     *
     * @param level level of character
     * @throws IllegalArgumentException if level is above 99
     */
    public void setLevel(int level) throws IllegalArgumentException {
        if (level > 99) throw new IllegalArgumentException("Level cannot be higher than 99");

        String sLevel;

        if (level < 10)
            sLevel = "0" + level;
        else
            sLevel = Integer.toString(level);

        Text levelText = CharacterController.getInstance().levelText;
        levelText.setText("LVL " + sLevel);
    }

    /**
     * Used to show up character name on character's tab
     *
     * @param name name of character
     */
    public void setCharacterName(String name) {
        CharacterController.getInstance().characterTab.setText(name);
    }

    /**
     * Spell Tab
     **/

    /**
     * Used to show up all spells of character
     *
     * @param spells List of possessed spells
     */
    public void setSpellList(List<Spell> spells) {
        ListView<Spell> list = CharacterController.getInstance().spellList;
        ObservableList<Spell> items = list.getItems();
        items.clear();
        items.addAll(spells);
    }

    /**
     * Used to show up spell details when a spell is selected on list fr.univ_amu.heromanager.view
     *
     * @param spell selected spell
     */
    public void setSpellDetails(Spell spell) {
        if (spell == null) return;

        TextArea spellInfo = CharacterController.getInstance().spellInfo;
        TextArea spellDesc = CharacterController.getInstance().spellDesc;

        spellInfo.setText(spell.getName() + '\n'
                + "Casting time: " + spell.getCastingTime() + '\n'
                + "Duration: " + spell.getDuration()
        );

        spellDesc.setText(spell.getDescription());
    }

    /**
     * Inventory Tab
     **/

    /**
     * Called to create inventory grid wanted by INVENTORY_SIZE parameter
     *
     * @return inventory grid
     * @see CharacterView#INVENTORY_SIZE size of inventory
     */
    public GridPane createInventoryGrid() {
        GridPane inventory = new GridPane();
        inventory.setGridLinesVisible(true);

        Tooltip tooltip = new Tooltip("Empty slot");
        tooltip.setShowDelay(Duration.ONE);

        int counter = 0;

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            for (int j = 0; j < INVENTORY_SIZE; j++) {
                ContainerPane<Item> pane = new ContainerPane<>();
                pane.setId("inventorySlot" + counter);
                pane.setOnMouseClicked(this::inventoryMouseClickedEvent);
                pane.setCursor(Cursor.HAND);
                Tooltip.install(pane, tooltip);

                ImageView imgPlus = new ImageView();
                imgPlus.setImage(new Image(getClass().getResourceAsStream(IMAGE_PLUS_PATH)));
                imgPlus.setFitHeight(INVENTORY_ITEM_IMAGE_SIZE);
                imgPlus.setFitWidth(INVENTORY_ITEM_IMAGE_SIZE);

                pane.getChildren().add(imgPlus);
                inventory.add(pane, j, i);
                counter++;
            }
        }

        for (int i = 0; i < 8; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / INVENTORY_SIZE);
            inventory.getRowConstraints().add(rowConstraints);

            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / INVENTORY_SIZE);
            inventory.getColumnConstraints().add(columnConstraints);
        }

        return inventory;
    }

    /**
     * Used to show up all character's inventory in inventory grid
     *
     * @param items items to show
     */
    public void setInventory(List<Item> items) {
        int counter = 0;

        Tooltip emptyTooltip = new Tooltip("Empty slot");
        emptyTooltip.setShowDelay(Duration.ONE);

        ContainerPane<Item> pane;
        Tooltip tooltip;
        String selector;
        for (Item item : items) {
            if (item == null) continue;

            selector = "#inventorySlot" + counter;
            tooltip = new Tooltip(item.getDescription());
            tooltip.setShowDelay(Duration.ONE);

            pane = ((ContainerPane<Item>) CharacterController.getInstance().inventoryPane.getScene().lookup(selector));
            pane.setContainedObject(item);

            Tooltip.install(pane, tooltip);

            counter++;
        }

        for (int i = counter; i < INVENTORY_SIZE * INVENTORY_SIZE; i++) {
            selector = "#inventorySlot" + counter;
            pane = ((ContainerPane<Item>) CharacterController.getInstance().inventoryPane.getScene().lookup(selector));
            pane.clearContainedObject();

            pane.getChildren().add(generateImagePlus());
        }
    }

    public void setEquippedEquipments(EquipmentInventory equippedEquipments) {
        CharacterController controller = CharacterController.getInstance();

        StackPane headPane = controller.headPane;
        StackPane bodyPane = controller.bodyPane;
        StackPane mantlePane = controller.mantlePane;
        StackPane beltPane = controller.beltPane;
        StackPane legsPane = controller.legsPane;
        StackPane feetPane = controller.feetPane;
        StackPane amuletPane = controller.amuletPane;
        StackPane ringPane1 = controller.ringPane1;
        StackPane ringPane2 = controller.ringPane2;

        headPane.getChildren().clear();
        bodyPane.getChildren().clear();
        mantlePane.getChildren().clear();
        beltPane.getChildren().clear();
        legsPane.getChildren().clear();
        feetPane.getChildren().clear();
        amuletPane.getChildren().clear();
        ringPane1.getChildren().clear();
        ringPane2.getChildren().clear();


        if (equippedEquipments.getHead() == null)
            headPane.getChildren().add(generateImagePlus());
        else
            headPane.getChildren().add(new ContainerPane<>(equippedEquipments.getHead()));

        if (equippedEquipments.getBody() == null)
            bodyPane.getChildren().add(generateImagePlus());
        else
            bodyPane.getChildren().add(new ContainerPane<>(equippedEquipments.getBody()));

        if (equippedEquipments.getMantle() == null)
            mantlePane.getChildren().add(generateImagePlus());
        else
            mantlePane.getChildren().add(new ContainerPane<>(equippedEquipments.getMantle()));

        if (equippedEquipments.getBelt() == null)
            beltPane.getChildren().add(generateImagePlus());
        else
            beltPane.getChildren().add(new ContainerPane<>(equippedEquipments.getBelt()));

        if (equippedEquipments.getLegs() == null)
            legsPane.getChildren().add(generateImagePlus());
        else
            legsPane.getChildren().add(new ContainerPane<>(equippedEquipments.getLegs()));

        if (equippedEquipments.getFeet() == null)
            feetPane.getChildren().add(generateImagePlus());
        else
            feetPane.getChildren().add(new ContainerPane<>(equippedEquipments.getFeet()));

        if (equippedEquipments.getAmulet() == null)
            amuletPane.getChildren().add(generateImagePlus());
        else
            amuletPane.getChildren().add(new ContainerPane<>(equippedEquipments.getAmulet()));

        if (equippedEquipments.getLeftRing() == null)
            ringPane1.getChildren().add(generateImagePlus());
        else
            ringPane1.getChildren().add(new ContainerPane<>(equippedEquipments.getLeftRing()));

        if (equippedEquipments.getRightRing() == null)
            ringPane2.getChildren().add(generateImagePlus());
        else
            ringPane2.getChildren().add(new ContainerPane<>(equippedEquipments.getRightRing()));
    }

    /**
     * Used by inventory and equipment tab to show a + image when there is no item/equipment
     *
     * @return image fr.univ_amu.heromanager.view of +
     */
    private ImageView generateImagePlus() {
        ImageView imgPlus = new ImageView();
        imgPlus.setImage(new Image(getClass().getResourceAsStream(IMAGE_PLUS_PATH)));
        imgPlus.setFitHeight(INVENTORY_ITEM_IMAGE_SIZE);
        imgPlus.setFitWidth(INVENTORY_ITEM_IMAGE_SIZE);

        return imgPlus;
    }

    /**
     * To refresh fr.univ_amu.heromanager.view when character got his attributes modified
     */
    public void refreshView() {
        Job character = CharacterModel.getInstance().getCharacter();

        setJobInfo(character.getJobType().name(), character.getRaceType().name(),
                "Strength:      " + character.getTotalStrength() + " (" + character.getStrength() + " + " + character.getStrengthBoost() + ")",
                "Dexterity:     " + character.getTotalDexterity() + " (" + character.getDexterity() + " + " + character.getDexterityBoost() + ")",
                "Intelligence: " + character.getTotalIntelligence() + " (" + character.getIntelligence() + " + " + character.getIntelligenceBoost() + ")",
                "Wisdom:       " + character.getTotalWisdom() + " (" + character.getWisdom() + " + " + character.getWisdomBoost() + ")",
                "Robustness:  " + character.getTotalRobustness() + " (" + character.getRobustness() + " + " + character.getRobustnessBoost() + ")",
                "Charisma:      " + character.getTotalCharisma() + " (" + character.getCharisma() + " + " + character.getCharismaBoost() + ")",
                "Armor:           " + character.getTotalArmor() + " (" + character.getArmor() + " + " + character.getArmorBoost() + ")");

        List<Improvement> improvements = character.getImprovements();
        String[] improvementStrings = new String[improvements.size()];
        for (int i = 0; i < improvements.size(); i++)
            improvementStrings[i] = improvements.get(i).name();
        setImprovements(improvementStrings);

        List<JobSkill> skills = character.getSkills();
        String[] skillStrings = new String[skills.size()];
        for (int i = 0; i < skills.size(); i++)
            skillStrings[i] = skills.get(i).getName();
        setSkills(skillStrings);

        setCharacterName(character.getName());
        setHP(character.getHealthPoints(), character.getMaxHp());
        setLevel(character.getLevel());
        setInventory(character.getInventory());
        setSpellList(character.getSpellInventory());
        setEquippedEquipments(character.getEquippedEquipments());
    }

    /**
     * Method event called when item in inventory is clicked and show up contextual menu or open item picker
     *
     * @param event mouse event captured
     */
    private void inventoryMouseClickedEvent(MouseEvent event) {
        ContainerPane<Item> source = ((ContainerPane<Item>) event.getSource());
        Item item = source.getContainedObject();
        CharacterModel.getInstance().inventoryClickedEvent(item, event);
    }

    /**
     * Allows to close character view and return to menu
     */
    public void returnToMenu() {
        close();
        new MenuModel();
    }

    /**
     * To close view
     */
    public void close() {
        stage.close();
    }
}
