package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import view.CharacterView;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    private static CharacterController instance;

    @FXML
    public ImageView imageJob;
    @FXML
    public Pane paneImageJob;
    @FXML
    public TextFlow jobInfo;
    @FXML
    public TextFlow improvementsInfo;
    @FXML
    public Rectangle hpBar;
    @FXML
    public Text hpText;
    @FXML
    public Text levelText;
    @FXML
    public VBox window;
    @FXML
    public Rectangle borderHPBar;
    @FXML
    public Tab characterTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        imageJob.fitWidthProperty().bind(paneImageJob.widthProperty());
        imageJob.fitHeightProperty().bind(paneImageJob.heightProperty());

        window.widthProperty().addListener(((observable, oldValue, newValue) -> {
            CharacterView.getInstance().updateHPBarWidth(newValue.intValue());
        }));

        /*
        paneImageJob.heightProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Updated");
            System.out.println("newValue.doubleValue() = " + newValue.doubleValue());
            System.out.println("paneImageJob.getLayoutY() = " + paneImageJob.getScaleY());
            System.out.println("imageJob.getFitHeight() = " + imageJob.getFitHeight());
            double paneCenter = (paneImageJob.getLayoutY() + (newValue.doubleValue() / 2));
            // imageJob.setY(paneCenter - imgCenter);
        }); */
    }

    @FXML
    public void hpBarOnClick() {
        CharacterModel.getInstance().hpBarOnClickEvent();
    }

    @FXML
    public void diceMenuOnAction() {
        CharacterModel.getInstance().openDiceWindow();
    }

    public static CharacterController getInstance() {
        return instance;
    }
}
