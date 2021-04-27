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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.items.Item;
import model.items.equipments.Equipment;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.JobType;
import model.spell.Spell;

import javax.tools.Tool;
import java.io.IOException;
import java.util.ArrayList;
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


    private static CharacterView instance;
    private Stage stage;

    public CharacterView() {
        try {
            instance = this;

            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/character.fxml"));
            stage.setTitle("HeroManager - Character");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            // stage.setResizable(false);
            stage.show();

            stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                updateHPBarWidth(((int) CharacterController.getInstance().borderHPBar.getWidth()));
            });

            Spell spell = new Spell("A", "Description", "School", "1 minute", "1 minute",
                    10, 50, JobType.CLERIC, true, null);
            Spell spell2 = new Spell("B", "Description 2", "No", "1 hour", "1 hour",
                    20, 40, JobType.DRUID, true, null);

            List<Spell> test = new ArrayList<>();
            test.add(spell);
            test.add(spell2);
            setSpellList(test);

            // TODO : For test only, remove later
            setJobInfo("Voleuse", "Humain", "Force 8 (+2)", "Agilité 20 (+10)", "Charisme 69 (+69)");
            setImprovementsInfo("Vol", "Camouflage", "Assassin");
            setCharacterName("Hiraye");
            setHP(50, 100);
            setLevel(3);
            List<Item> items = new ArrayList<>();
            items.add(new Weapon("Épée", "Une épée", "Propriétés", WeaponType.COMMON, DamageType.SLASHING));
            items.add(new Weapon("Hache", "Une hache", "Propriétés", WeaponType.COMMON, DamageType.BLUDGEONING));
            items.add(new Weapon("Arc", "Un arc", "Propriétés", WeaponType.COMMON, DamageType.PIERCING));
            setInventory(items);
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

    /**
     * Character Tab
     **/
    public void updateHPBarWidth(int newMaxHP) {

        Rectangle borderHPBar = CharacterController.getInstance().borderHPBar;
        Rectangle hpBar = CharacterController.getInstance().hpBar;
        Text hpText = CharacterController.getInstance().hpText;

        double newWidth = (((double) newMaxHP * ((double) MAX_HP_BAR_SIZE)) / ((double) DEFAULT_WINDOW_WIDTH));

        hpBar.setWidth(newWidth * hpBar.getWidth() / borderHPBar.getWidth());
        hpText.setTranslateX((borderHPBar.getWidth() / 2) - 20);
        borderHPBar.setWidth(newWidth);
    }

    public void setJobInfo(String className, String race, String... statistics) {
        TextFlow jobInfo = CharacterController.getInstance().jobInfo;
        ObservableList list = jobInfo.getChildren();

        Text title = new Text(className + " - " + race + "\n\n");
        title.setFont(new Font(30));

        Text titleStats = new Text("Statistiques" + "\n");
        titleStats.setFont(new Font(20));

        list.addAll(title, titleStats);

        for (String stat : statistics) {
            list.add(new Text("    - " + stat + "\n"));
        }
    }

    public void setImprovementsInfo(String... improvements) {
        TextFlow improvementsInfo = CharacterController.getInstance().improvementsInfo;
        ObservableList<javafx.scene.Node> list = improvementsInfo.getChildren();

        Text title = new Text("Aptitudes" + "\n\n");
        title.setFont(new Font(20));
        list.add(title);

        for (String improvement : improvements) {
            list.add(new Text("    - " + improvement + "\n"));
        }
    }

    public void setHP(int hp, int maxHP) {
        Rectangle hpBar = CharacterController.getInstance().hpBar;
        Rectangle maxHpBar = CharacterController.getInstance().borderHPBar;
        Text hpText = CharacterController.getInstance().hpText;

        hpBar.setWidth((((double) hp) / ((double) maxHP)) * maxHpBar.getWidth());
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
        Item item = new Weapon("Épée", "Une épée", "Propriétés", WeaponType.COMMON, DamageType.SLASHING);

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
            // TODO : Open Item creator
        }

        clickMenu.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
    }
}
