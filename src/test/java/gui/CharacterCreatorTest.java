package gui;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import controller.CharacterCreatorController;
import controller.Main;
import controller.MenuController;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.gui.CharacterCreatorModel;
import model.gui.MenuModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.service.query.impl.NodeQueryImpl;

@ExtendWith(ApplicationExtension.class)
public class CharacterCreatorTest extends Application {

    private int testID;

    @Start
    public void start(Stage primaryStage) throws Exception {
        new MenuModel();
    }

    @Test
    @DisplayName("GUI Test for character creation")
    public void characterCreationTest(FxRobot robot) {
        robot.clickOn("#newGameButton");

        Assertions.assertTrue(robot.lookup("#paneCreation1").tryQuery().get().isVisible());
        Assertions.assertFalse(robot.lookup("#paneCreation2").tryQuery().get().isVisible());

        Assertions.assertTrue(robot.lookup("#jobNameText").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#jobDescriptionText").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#genderPicker").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#alignmentPicker").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#racePicker").tryQuery().get().isDisable());

        robot.clickOn("#jobTypePicker");
        robot.type(KeyCode.DOWN, 9);
        robot.type(KeyCode.ENTER);

        Assertions.assertFalse(robot.lookup("#jobNameText").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#jobDescriptionText").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#genderPicker").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#alignmentPicker").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#racePicker").tryQuery().get().isDisable());

        robot.clickOn("#jobNameText");
        robot.write("Hiraye");

        robot.type(KeyCode.TAB);
        robot.write("A perfect woman");

        robot.clickOn("#genderPicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#alignmentPicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#racePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
    }

    @Test
    @DisplayName("GUI Test for distributions of statistics points")
    public void statisticsTest(FxRobot robot) {
        characterCreationTest(robot);

        robot.clickOn("#createCharacterButton");

        Assertions.assertFalse(robot.lookup("#paneCreation1").tryQuery().get().isVisible());
        Assertions.assertTrue(robot.lookup("#paneCreation2").tryQuery().get().isVisible());

        Assertions.assertEquals("12", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerStrength");
        robot.type(KeyCode.LEFT, 5);

        Assertions.assertEquals("14", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(8, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerStrength");
        robot.type(KeyCode.RIGHT, 7);

        Assertions.assertEquals("7", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerIntelligence");
        robot.type(KeyCode.RIGHT, 5);

        Assertions.assertEquals("2", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerCharisma");
        robot.type(KeyCode.RIGHT, 5);

        Assertions.assertEquals("0", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(12, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());
    }
}
