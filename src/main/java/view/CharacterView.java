package view;

import controller.CharacterController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.io.IOException;

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

    public CharacterView() {
        try {
            instance = this;

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/character.fxml"));
            stage.setTitle("HeroManager - Character");
            stage.setScene(new Scene(root));
            // stage.setResizable(false);
            stage.show();
            
            stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                updateHPBarWidth(((int) CharacterController.getInstance().borderHPBar.getWidth()));
            });

            // TODO : For test only, remove later
            setJobInfo("Voleuse", "Humain", "Force 8 (+2)", "Agilité 20 (+10)", "Charisme 69 (+69)");
            setImprovementsInfo("Vol", "Camouflage", "Assassin");
            setCharacterName("Hiraye");
            setHP(50, 100);
            setLevel(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CharacterView getInstance() {
        return instance;
    }

    public void updateHPBarWidth(int newMaxHP) {

        Rectangle borderHPBar = CharacterController.getInstance().borderHPBar;
        Rectangle hpBar = CharacterController.getInstance().hpBar;
        Text hpText = CharacterController.getInstance().hpText;

        double newWidth = (((double) newMaxHP * ((double) MAX_HP_BAR_SIZE)) / ((double) DEFAULT_WINDOW_WIDTH));

        hpBar.setWidth(newWidth * hpBar.getWidth() / borderHPBar.getWidth());
        hpText.setTranslateX((borderHPBar.getWidth()/2) - 20);
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
        ObservableList list = improvementsInfo.getChildren();

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

    public void blockWindow() {
        CharacterController.getInstance().window.setDisable(true);
    }

    public void unblockWindow() {
        CharacterController.getInstance().window.setDisable(false);
    }

    public GridPane createInventoryGrid() {
        GridPane inventory = new GridPane();
        inventory.setGridLinesVisible(true);

        int counter = 0;

        for (int i = 0; i < INVENTORY_SIZE; i++) {
            for (int j = 0; j < INVENTORY_SIZE; j++) {
                StackPane pane = new StackPane();
                pane.setId("inventorySlot" + counter);
                pane.setOnMouseClicked(event -> System.out.println(event));
                pane.setCursor(Cursor.HAND);

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
            rowConstraints.setPercentHeight(100.0/INVENTORY_SIZE);
            inventory.getRowConstraints().add(rowConstraints);

            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0/INVENTORY_SIZE);
            inventory.getColumnConstraints().add(columnConstraints);
        }

        return inventory;
    }
}
