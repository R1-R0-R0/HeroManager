package gui;

import controller.ItemManagerController;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class ItemManagerTest {

    @Start
    public void start(Stage primaryStage) {
        new ItemManagerModel();
    }

    @Test
    public void test() throws InterruptedException {
        Assertions.assertFalse(ItemManagerController.getInstance().weaponsPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().equipmentPane.isVisible());
        Assertions.assertFalse(ItemManagerController.getInstance().consumablePane.isVisible());
    }
}
