package view;

import controller.CharacterController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterView {

    private static CharacterView instance;

    public CharacterView() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/character.fxml"));
            stage.setTitle("HeroManager - Character");
            stage.setScene(new Scene(root));
            stage.show();

            instance = this;

            // TODO : For test only, remove later
            setJobInfo("Voleuse", "Force 8 (+2)", "Agilité 20 (+10)", "Charisme 69 (+69)");
            setImprovementsInfo("Vol", "Camouflage", "Assassin", "Bonne en chevauchement");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CharacterView getInstance() {
        return instance;
    }

    public void setJobInfo(String className, String... statistics) {
        TextFlow jobInfo = CharacterController.getInstance().jobInfo;
        ObservableList list = jobInfo.getChildren();

        Text title = new Text(className + "\n\n");
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
}
