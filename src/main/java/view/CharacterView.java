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

import java.io.IOException;

public class CharacterView {

    public final static int MAX_HP_BAR_SIZE = 300;
    private static CharacterView instance;

    public CharacterView() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/character.fxml"));
            stage.setTitle("HeroManager - Character");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            instance = this;

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
        Text hpText = CharacterController.getInstance().hpText;

        hpBar.setWidth((((double) hp) / ((double) maxHP)) * MAX_HP_BAR_SIZE);
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
}
