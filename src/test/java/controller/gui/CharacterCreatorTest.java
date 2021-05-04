package controller.gui;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import controller.CharacterCreatorController;
import controller.Main;
import controller.MenuController;
import javafx.application.Application;
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


    }
}
