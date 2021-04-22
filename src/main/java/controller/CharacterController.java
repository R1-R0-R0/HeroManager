package controller;

import javafx.beans.property.DoublePropertyBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        imageJob.fitWidthProperty().bind(paneImageJob.widthProperty());
        imageJob.fitHeightProperty().bind(paneImageJob.heightProperty());

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

    public static CharacterController getInstance() {
        return instance;
    }
}
