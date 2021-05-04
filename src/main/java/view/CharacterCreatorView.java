package view;

import controller.CharacterCreatorController;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Characteristics;
import model.gui.CharacterCreatorModel;
import model.job.Gender;

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

    public void changeJobImage(String path) {
        CharacterCreatorController.getInstance().jobImage.setImage(new Image(getClass().getResourceAsStream(path)));
    }

    public void selectedGender(Gender gender) {
        CharacterCreatorView view = CharacterCreatorView.getInstance();

        String classNameFile = CharacterCreatorController.getInstance().jobTypePicker.getValue().name().toLowerCase();
        String genderNameFile = (gender == Gender.MAN) ? "_m.jpg" : "_f.jpg";
        String imageJobPath = "/images/jobs/pictures/" + classNameFile + genderNameFile;

        view.changeJobImage(imageJobPath);
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

    public void setUpNewCharacter() {
        CharacterCreatorController.getInstance().paneCreation1.setVisible(false);
        CharacterCreatorController.getInstance().paneCreation2.setVisible(true);
    }

    public void updateStatisticsAvailablePoints(int availablePoints) {
        CharacterCreatorController.getInstance().labelRemainingPoints.setText(Integer.toString(availablePoints));
    }

    public void setSpinnerStatisticValue(Characteristics characteristic, int value) {
        assert (value >= 8 && value <= 15);
        switch (characteristic) {
            case STRENGTH -> CharacterCreatorController.getInstance().spinnerStrength.getValueFactory().setValue(value);
            case DEXTERITY -> CharacterCreatorController.getInstance().spinnerDexterity.getValueFactory().setValue(value);
            case INTELLIGENCE -> CharacterCreatorController.getInstance().spinnerIntelligence.getValueFactory().setValue(value);
            case WISDOM -> CharacterCreatorController.getInstance().spinnerWisdom.getValueFactory().setValue(value);
            case ROBUSTNESS -> CharacterCreatorController.getInstance().spinnerRobustness.getValueFactory().setValue(value);
            case CHARISMA -> CharacterCreatorController.getInstance().spinnerCharisma.getValueFactory().setValue(value);
        }
    }
}
