package gui;

import controller.ItemManagerController;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(ApplicationExtension.class)
@DisplayName("GUI Item Manager Tests")
public class ItemManagerTest {

    private static final Weapon weapon = new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING);
    private static final Equipment equipment = new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentParts.HEAD, 0, EquipmentType.LIGHT, 0, 0, 0, 0, 0, 1, 0);
    private static final Consumable
            consumable1 = new Consumable("A potion", "Unknown potion. Will you drink it ?"),
            consumable2 = new Consumable("Gameboy", "A simple gameboy.");

    @Start
    public void start(Stage primaryStage) {
        new ItemManagerModel();
    }

    @BeforeEach
    @DisplayName("To initialize item list view")
    public void initItemList() {
        ItemManagerModel model = ItemManagerModel.getInstance();
        model.setWeaponList(Collections.singletonList(weapon));
        model.setEquipmentList(Collections.singletonList(equipment));
        model.setConsumableList(Arrays.asList(consumable1, consumable2));
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
    @DisplayName("Test if fields are correctly filled up on item selection")
    public void fieldsOnItemSelectionTest(FxRobot robot) throws InterruptedException {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        Assertions.assertEquals(weapon.getName(), ((TextField) robot.lookup("#weaponNameText").tryQuery().get()).getText());
        Assertions.assertEquals(weapon.getDescription(), ((TextArea) robot.lookup("#weaponDescriptionText").tryQuery().get()).getText());
        Assertions.assertEquals(weapon.getProperties(), ((TextArea) robot.lookup("#weaponPropertiesText").tryQuery().get()).getText());
        Assertions.assertEquals(weapon.getWeaponType(), ((ChoiceBox<WeaponType>) robot.lookup("#weaponTypePicker").tryQuery().get()).getValue());
        Assertions.assertEquals(weapon.getDamageType(), ((ChoiceBox<DamageType>) robot.lookup("#damageTypePicker").tryQuery().get()).getValue());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        Assertions.assertEquals(equipment.getName(), ((TextField) robot.lookup("#equipmentNameText").tryQuery().get()).getText());
        Assertions.assertEquals(equipment.getDescription(), ((TextArea) robot.lookup("#equipmentDescriptionText").tryQuery().get()).getText());
        Assertions.assertEquals(equipment.getEquipmentParts(), ((ChoiceBox<EquipmentParts>) robot.lookup("#equipmentPartPicker").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getEquipmentType(), ((ChoiceBox<EquipmentType>) robot.lookup("#equipmentTypePicker").tryQuery().get()).getValue());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        Assertions.assertEquals(consumable1.getName(), ((TextField) robot.lookup("#consumableNameText").tryQuery().get()).getText());
        Assertions.assertEquals(consumable1.getDescription(), ((TextArea) robot.lookup("#consumableDescriptionText").tryQuery().get()).getText());

        robot.type(KeyCode.DOWN);
        robot.clickOn("#itemList");

        Assertions.assertEquals(consumable2.getName(), ((TextField) robot.lookup("#consumableNameText").tryQuery().get()).getText());
        Assertions.assertEquals(consumable2.getDescription(), ((TextArea) robot.lookup("#consumableDescriptionText").tryQuery().get()).getText());
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

        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER, KeyCode.TAB);

        Assertions.assertFalse(controller.newItemButton.isDisabled());
        Assertions.assertFalse(controller.updateItemButton.isDisabled());
        Assertions.assertFalse(controller.deleteItemButton.isDisabled());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN);

        Assertions.assertFalse(controller.newItemButton.isDisabled());
        Assertions.assertTrue(controller.updateItemButton.isDisabled());
        Assertions.assertTrue(controller.deleteItemButton.isDisabled());
    }
}
