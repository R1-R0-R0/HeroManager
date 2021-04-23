package view;

import controller.CharacterController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.gui.CharacterModel;

import java.io.IOException;

public class CharacterView {

    public final static int DEFAULT_WINDOW_WIDTH = 800;
    public final static int MAX_HP_BAR_SIZE = 300;

    private int maxWidthHPBar;
    private static CharacterView instance;
    private static CharacterModel model;

    public CharacterView() {
        try {
            instance = this;
            model = new CharacterModel();

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/character.fxml"));
            stage.setTitle("HeroManager - Character");
            stage.setScene(new Scene(root));
            // stage.setResizable(false);
            stage.show();
            
            stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("UPDATED");
                updateHPBarWidth(((int) CharacterController.getInstance().borderHPBar.getWidth()));
            });

            maxWidthHPBar = MAX_HP_BAR_SIZE;

            // TODO : For test only, remove later
            setJobInfo("Voleuse", "Humain", "Force 8 (+2)", "Agilité 20 (+10)", "Charisme 69 (+69)");
            setImprovementsInfo("Vol", "Camouflage", "Assassin");
            setHP(50, 100);
            setLevel(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CharacterView getInstance() {
        return instance;
    }

    public static CharacterModel getModel() {
        return model;
    }

    public void updateHPBarWidth(int newMaxHP) {
        maxWidthHPBar = newMaxHP;

        Rectangle borderHPBar = CharacterController.getInstance().borderHPBar;
        Rectangle hpBar = CharacterController.getInstance().hpBar;
        Text hpText = CharacterController.getInstance().hpText;

        double newWidth = (((double) newMaxHP * ((double) MAX_HP_BAR_SIZE)) / ((double) DEFAULT_WINDOW_WIDTH));
        maxWidthHPBar = ((int) newWidth);

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

    public void blockWindow() {
        CharacterController.getInstance().window.setDisable(true);
    }

    public void unblockWindow() {
        CharacterController.getInstance().window.setDisable(false);
    }
}
