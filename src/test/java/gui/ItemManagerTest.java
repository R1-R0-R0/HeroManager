package gui;

import controller.ItemManagerController;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class ItemManagerTest {

    @Start
    public void start(Stage primaryStage) {
        new ItemManagerModel();
    }

    @Test
    @DisplayName("GUI Item Manager test if Combo Box (Picker) and corresponding pane will be visible correctly for each type")
    public void typePickerPaneVisibilityTest(FxRobot robot) throws InterruptedException {
        Assertions.assertFalse(ItemManagerController.getInstance().weaponsPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().equipmentPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().consumablePane.isVisible());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN);

        Assertions.assertTrue(ItemManagerController.getInstance().weaponsPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().equipmentPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().consumablePane.isVisible());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN);

        Assertions.assertFalse(ItemManagerController.getInstance().weaponsPane.isVisible());
        Assertions.assertTrue(ItemManagerController.getInstance().equipmentPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().consumablePane.isVisible());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN);

        Assertions.assertFalse(ItemManagerController.getInstance().weaponsPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().equipmentPane.isVisible());
        Assertions.assertTrue(ItemManagerController.getInstance().consumablePane.isVisible());
    }
}
