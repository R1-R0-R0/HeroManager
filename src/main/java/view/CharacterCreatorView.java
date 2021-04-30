package view;

import controller.CharacterCreatorController;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterCreatorView {

    private Stage stage;
    private static CharacterCreatorView instance;

    public CharacterCreatorView(Stage owner) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/character_creator.fxml"));
            stage.setTitle("HeroManager - Character Creator");
            stage.getIcons().add(Main.APP_LOGO);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CharacterCreatorView getInstance() {
        return instance;
    }

    public void changeJobTypeImage(String path) {
        CharacterCreatorController.getInstance().jobTypeImage.setImage(new Image(getClass().getResourceAsStream(path)));
    }

    public void close() {
        stage.close();
    }

    public void enableInputs() {
        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        controller.jobNameText.setDisable(false);
        controller.jobDescriptionText.setDisable(false);
        controller.genderPicker.setDisable(false);
        controller.alignmentPicker.setDisable(false);
        controller.racePicker.setDisable(false);

        controller.createCharacterButton.setDisable(false);
    }

    public void disableInputs() {
        CharacterCreatorController controller = CharacterCreatorController.getInstance();

        controller.jobNameText.setDisable(true);
        controller.jobDescriptionText.setDisable(true);
        controller.genderPicker.setDisable(true);
        controller.alignmentPicker.setDisable(true);
        controller.racePicker.setDisable(true);

        controller.createCharacterButton.setDisable(true);
    }
}
