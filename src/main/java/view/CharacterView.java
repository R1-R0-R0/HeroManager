package view;

import controller.CharacterController;
import controller.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.gui.CharacterModel;
import model.gui.ItemPickerModel;
import model.items.Item;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.Gender;
import model.job.Improvement;
import model.job.Job;
import model.job.JobType;
import model.spell.Spell;

import java.io.IOException;
import java.util.List;

public class CharacterView {

    /* GENERAL SETTINGS */
    public final static int DEFAULT_WINDOW_WIDTH = 800;

    /* CHARACTER TAB SETTINGS */
    public final static int MAX_HP_BAR_SIZE = 300;

    /* INVENTORY TAB SETTINGS */
    /**
     * Defines the size of the inventory.
     * This value is square root of number of created cells
     */
    public final static int INVENTORY_SIZE = 8;
    public final static int IMAGE_SIZE = 40;
    public final static String IMAGE_PLUS_PATH = "/images/ui/plus.png";
    public final static String IMAGES_JOBS_FOLDER = "/images/jobs/pictures/";


    private static CharacterView instance;
    private Stage stage;

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

            setJobInfo(jobType.name(), character.getRaceType().name(),
                    "Strength:      " + character.getTotalStrength() + " (" + character.getStrength() + " + " + character.getStrengthBoost() + ")",
                    "Dexterity:     " + character.getTotalDexterity() + " (" + character.getDexterity() + " + " + character.getDexterityBoost() + ")",
                    "Intelligence: " + character.getTotalIntelligence() + " (" + character.getIntelligence() + " + " + character.getIntelligenceBoost() + ")",
                    "Wisdom:       " + character.getTotalWisdom() + " (" + character.getWisdom() + " + " + character.getWisdomBoost() + ")",
                    "Robustness:  " + character.getTotalRobustness() + " (" + character.getRobustness() + " + " + character.getRobustnessBoost() + ")",
                    "Charisma:      " + character.getTotalCharisma() + " (" + character.getCharisma() + " + " + character.getCharismaBoost() + ")",
                    "Armor:           " + character.getTotalArmor() + " (" + character.getArmor() + " + " + character.getArmorBoost() + ")");

            List<Improvement> improvements = character.getImprovements();
            String[] improvementStrings = new String[improvements.size()];
            System.out.println("improvements.size() = " + improvements.size());
            for (int i = 0; i < improvements.size(); i++)
                improvementStrings[i] = improvements.get(i).name();

            setImprovements(improvementStrings);
            setCharacterName(character.getName());
            setHP(50, 100); // TODO
            setLevel(character.getLevel());
            setInventory(character.getInventory());
            setSpellList(character.getSpellInventory());

            String pictureNameJobType = jobType.name().toLowerCase();
            String pictureNameGender = (gender == Gender.MAN) ? "_m.jpg" : "_f.jpg";
            String pictureJobPath = ".." + IMAGES_JOBS_FOLDER + pictureNameJobType + pictureNameGender;
            Image pictureJob = new Image(getClass().getResourceAsStream(pictureJobPath));

            BackgroundImage bgImg = new BackgroundImage(pictureJob,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false));
            CharacterController.getInstance().imageJob.setBackground(new Background(bgImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CharacterView getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    private void initResponsiveNodes() {
        CharacterController.getInstance().borderHpBar.widthProperty().addListener((observable, oldValue, newValue) -> {
            Pane hpBar = CharacterController.getInstance().hpBar;
            hpBar.setMaxWidth(newValue.doubleValue() * hpBar.getMaxWidth() / oldValue.doubleValue());
        });

        CharacterController.getInstance().equipmentTab.widthProperty().addListener((observable, oldValue, newValue) -> {
            ImageView
                    headImage = CharacterController.getInstance().headImage,
                    bodyImage = CharacterController.getInstance().bodyImage,
                    mantleImage = CharacterController.getInstance().mantleImage,
                    beltImage = CharacterController.getInstance().beltImage,
                    legsImage = CharacterController.getInstance().legsImage,
                    feetImage = CharacterController.getInstance().feetImage,
                    amuletImage = CharacterController.getInstance().amuletImage,
                    ringImage1 = CharacterController.getInstance().ringImage1,
                    ringImage2 = CharacterController.getInstance().ringImage2;


            headImage.setLayoutX(newValue.doubleValue() * headImage.getLayoutX() / oldValue.doubleValue());
            bodyImage.setLayoutX(newValue.doubleValue() * bodyImage.getLayoutX() / oldValue.doubleValue());
            mantleImage.setLayoutX(newValue.doubleValue() * mantleImage.getLayoutX() / oldValue.doubleValue());
            beltImage.setLayoutX(newValue.doubleValue() * beltImage.getLayoutX() / oldValue.doubleValue());
            legsImage.setLayoutX(newValue.doubleValue() * legsImage.getLayoutX() / oldValue.doubleValue());
            feetImage.setLayoutX(newValue.doubleValue() * feetImage.getLayoutX() / oldValue.doubleValue());
            amuletImage.setLayoutX(newValue.doubleValue() * amuletImage.getLayoutX() / oldValue.doubleValue());
            ringImage1.setLayoutX(newValue.doubleValue() * ringImage1.getLayoutX() / oldValue.doubleValue());
            ringImage2.setLayoutX(newValue.doubleValue() * ringImage2.getLayoutX() / oldValue.doubleValue());
        });

        CharacterController.getInstance().equipmentTab.heightProperty().addListener((observable, oldValue, newValue) -> {
            ImageView
                    headImage = CharacterController.getInstance().headImage,
                    bodyImage = CharacterController.getInstance().bodyImage,
                    mantleImage = CharacterController.getInstance().mantleImage,
                    beltImage = CharacterController.getInstance().beltImage,
                    legsImage = CharacterController.getInstance().legsImage,
                    feetImage = CharacterController.getInstance().feetImage,
                    amuletImage = CharacterController.getInstance().amuletImage,
                    ringImage1 = CharacterController.getInstance().ringImage1,
                    ringImage2 = CharacterController.getInstance().ringImage2;


            headImage.setLayoutY(newValue.doubleValue() * headImage.getLayoutY() / oldValue.doubleValue());
            bodyImage.setLayoutY(newValue.doubleValue() * bodyImage.getLayoutY() / oldValue.doubleValue());
            mantleImage.setLayoutY(newValue.doubleValue() * mantleImage.getLayoutY() / oldValue.doubleValue());
            beltImage.setLayoutY(newValue.doubleValue() * beltImage.getLayoutY() / oldValue.doubleValue());
            legsImage.setLayoutY(newValue.doubleValue() * legsImage.getLayoutY() / oldValue.doubleValue());
            feetImage.setLayoutY(newValue.doubleValue() * feetImage.getLayoutY() / oldValue.doubleValue());
            amuletImage.setLayoutY(newValue.doubleValue() * amuletImage.getLayoutY() / oldValue.doubleValue());
            ringImage1.setLayoutY(newValue.doubleValue() * ringImage1.getLayoutY() / oldValue.doubleValue());
            ringImage2.setLayoutY(newValue.doubleValue() * ringImage2.getLayoutY() / oldValue.doubleValue());
        });
    }

    /**
     * Character Tab
     **/

    public void setJobInfo(String className, String race, String... statistics) {
        TextFlow jobInfo = CharacterController.getInstance().jobInfo;
        ObservableList list = jobInfo.getChildren();

        Text title = new Text(className + " - " + race + "\n\n");
        title.setFont(new Font(30));

        Text titleStats = new Text("Statistics" + "\n");
        titleStats.setFont(new Font(20));

        list.addAll(title, titleStats);

        for (String stat : statistics) {
            list.add(new Text("    - " + stat + "\n"));
        }
    }

    public void setImprovements(String... improvements) {
        TextFlow improvementsInfo = CharacterController.getInstance().improvementsInfo;
        ObservableList<javafx.scene.Node> list = improvementsInfo.getChildren();

        Text title = new Text("Improvements" + "\n\n");
        title.setFont(new Font(20));
        list.add(title);

        for (String improvement : improvements) {
            list.add(new Text("    - " + improvement + "\n"));
        }
    }

    public void setHP(int hp, int maxHP) {
        Pane hpBar = CharacterController.getInstance().hpBar;
        Pane maxHPBar = CharacterController.getInstance().borderHpBar;
        Text hpText = CharacterController.getInstance().hpText;

        hpBar.setMaxWidth(((double) hp) / maxHP * maxHPBar.getWidth());
        hpText.setText(hp + " / " + maxHP);
    }

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

    public void setCharacterName(String name) {
        CharacterController.getInstance().characterTab.setText(name);
    }

    /**
     * Spell Tab
     **/
    public void setSpellList(List<Spell> spells) {
        ListView list = CharacterController.getInstance().spellList;
        ObservableList items = list.getItems();
        items.addAll(spells);
    }

    public void setSpellDetails(Spell spell) {
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
    public GridPane createInventoryGrid() {
        GridPane inventory = new GridPane();
        inventory.setGridLinesVisible(true);

        Tooltip tooltip = new Tooltip("Empty slot");
        tooltip.setShowDelay(Duration.ONE);

        int counter = 0;

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            for (int j = 0; j < INVENTORY_SIZE; j++) {
                StackPane pane = new StackPane();
                pane.setId("inventorySlot" + counter);
                pane.setOnMouseClicked(this::inventoryMouseClickedEvent);
                pane.setCursor(Cursor.HAND);
                Tooltip.install(pane, tooltip);

                ImageView img = new ImageView();
                img.setImage(new Image(getClass().getResourceAsStream(IMAGE_PLUS_PATH)));
                img.setFitHeight(IMAGE_SIZE);
                img.setFitWidth(IMAGE_SIZE);

                pane.getChildren().add(img);
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

    public void setInventory(List<Item> items) {
        int counter = 0;

        StackPane pane;
        Tooltip tooltip;
        String selector;
        for (Item item : items) {
            selector = "#inventorySlot" + counter;
            tooltip = new Tooltip(item.getDescription());
            tooltip.setShowDelay(Duration.ONE);

            pane = ((StackPane) CharacterController.getInstance().inventoryPane.getScene().lookup(selector));
            pane.getChildren().clear();
            pane.getChildren().add(new Text(item.getName()));

            Tooltip.install(pane, tooltip);

            counter++;
        }
    }

    // TODO : To implement when item pickers are here
    private void inventoryMouseClickedEvent(MouseEvent event) {
        System.out.println("event = " + event);
        StackPane source = ((StackPane) event.getSource());
        System.out.println("source.getId() = " + source.getId());

        ContextMenu clickMenu = new ContextMenu();
        Item item = null; // new Weapon("Épée", "Une épée", "Propriétés", WeaponType.COMMON, DamageType.SLASHING);

        if (item != null) {
            if (item instanceof Weapon || item instanceof Equipment) {
                MenuItem equip = new MenuItem("Equip");
                clickMenu.getItems().add(equip);
            }

            MenuItem info = new MenuItem("Info");

            SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
            MenuItem discard = new MenuItem("Discard");

            clickMenu.getItems().addAll(info, separatorMenuItem, discard);
        } else {
            new ItemPickerModel(getStage());
        }

        clickMenu.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
    }
}
