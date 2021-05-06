package gui;

import controller.ItemManagerController;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.gui.ItemManagerModel;
import model.items.Item;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentParts;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(ApplicationExtension.class)
@DisplayName("GUI Item Manager Tests")
public class ItemManagerTest {

    @Start
    public void start(Stage primaryStage) {
        new ItemManagerModel();
    }

    @Test
    @DisplayName("Test if Combo Box (Picker) and corresponding pane will be visible correctly for each type")
    public void typePickerPaneVisibilityTest(FxRobot robot) {
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

    @Test
    @DisplayName("Test if list view contains goods values")
    public void listViewTest(FxRobot robot) {
        ItemManagerModel model = ItemManagerModel.getInstance();
        model.setWeaponList(Collections.singletonList(new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING)));
        model.setEquipmentList(Collections.singletonList(new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentParts.HEAD, 0, EquipmentType.LIGHT, 0, 0, 0, 0, 0, 1, 0)));
        model.setConsumableList(Arrays.asList(new Consumable("A potion", "Unknown potion. Will you drink it ?"), new Consumable("Gameboy", "A simple gameboy.")));

        ListView<Item> listView = ((ListView<Item>) robot.lookup("#itemList").tryQuery().get());

        Assertions.assertEquals(0, listView.getItems().size());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN);
        Assertions.assertEquals(1, listView.getItems().size());
        Assertions.assertEquals("Axe", listView.getItems().get(0).getName());

        robot.type(KeyCode.DOWN);
        Assertions.assertEquals(1, listView.getItems().size());
        Assertions.assertEquals("Headphones", listView.getItems().get(0).getName());

        robot.type(KeyCode.DOWN);
        Assertions.assertEquals(2, listView.getItems().size());
        Assertions.assertEquals("Gameboy", listView.getItems().get(1).getName());
    }

    @Test
    @DisplayName("Test if buttons are enabled/disabled correctly")
    public void buttonsEnabledTest(FxRobot robot) {
        ItemManagerController controller = ItemManagerController.getInstance();

        Assertions.assertTrue(controller.newItemButton.isDisabled());
        Assertions.assertTrue(controller.updateItemButton.isDisabled());
        Assertions.assertTrue(controller.deleteItemButton.isDisabled());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN);

        Assertions.assertFalse(controller.newItemButton.isDisabled());
        Assertions.assertTrue(controller.updateItemButton.isDisabled());
        Assertions.assertTrue(controller.deleteItemButton.isDisabled());
    }
}
