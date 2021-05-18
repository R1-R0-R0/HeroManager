package fr.univ_amu.heromanager.model.gui.item_picker;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import fr.univ_amu.heromanager.model.gui.ItemPickerModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import fr.univ_amu.heromanager.model.items.weapons.DamageType;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.model.items.weapons.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import fr.univ_amu.heromanager.utils.ItemPickerCloseEvent;
import fr.univ_amu.heromanager.view.ItemPickerView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@ExtendWith(ApplicationExtension.class)
@DisplayName("Item Picker fr.univ_amu.heromanager.view tests")
class ItemPickerTest {

    private static final Weapon weapon = new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING);
    private static final Equipment equipment = new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentPart.HEAD, 11, EquipmentType.LIGHT, 22, 33, 44, 55, 66, 77, 88);
    private static final Consumable
            consumable1 = new Consumable("A potion", "Unknown potion. Will you drink it ?"),
            consumable2 = new Consumable("Gameboy", "A simple gameboy.");

    private final ItemPickerCloseEvent itemPickerCloseEvent = item -> selectedItem = item;
    private Item selectedItem;

    @Start
    public void start(Stage primaryStage) throws IOException {
        HeroManagerDB.init();

        ItemPickerModel model = new ItemPickerModel(primaryStage, itemPickerCloseEvent);
        model.setWeaponList(Collections.singletonList(weapon));
        model.setEquipmentList(Collections.singletonList(equipment));
        model.setConsumableList(Arrays.asList(consumable1, consumable2));
    }

    @Test
    @DisplayName("Check if selected type in picker show corrects values")
    public void typePickerTest(FxRobot robot) {
        Assertions.assertFalse(robot.lookup("#typePicker").tryQuery().get().isDisabled());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertEquals(1, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertEquals(1, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertEquals(2, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Check if item selection is correct")
    public void itemSelectionTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);
        Assertions.assertEquals(weapon.getName(), ((Label) robot.lookup("#itemNameLabel").tryQuery().get()).getText());
        Assertions.assertNotEquals(0, ((AnchorPane) robot.lookup("#itemInfoPane").tryQuery().get()).getChildren().size());
        Assertions.assertEquals(weapon, ItemPickerModel.getInstance().getSelectedItem());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);
        Assertions.assertEquals(equipment.getName(), ((Label) robot.lookup("#itemNameLabel").tryQuery().get()).getText());
        Assertions.assertNotEquals(0, ((AnchorPane) robot.lookup("#itemInfoPane").tryQuery().get()).getChildren().size());
        Assertions.assertEquals(equipment, ItemPickerModel.getInstance().getSelectedItem());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);
        Assertions.assertEquals(consumable1.getName(), ((Label) robot.lookup("#itemNameLabel").tryQuery().get()).getText());
        Assertions.assertNotEquals(0, ((AnchorPane) robot.lookup("#itemInfoPane").tryQuery().get()).getChildren().size());
        Assertions.assertEquals(consumable1, ItemPickerModel.getInstance().getSelectedItem());

        robot.clickOn("#itemList");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
        Assertions.assertEquals(consumable2.getName(), ((Label) robot.lookup("#itemNameLabel").tryQuery().get()).getText());
        Assertions.assertNotEquals(0, ((AnchorPane) robot.lookup("#itemInfoPane").tryQuery().get()).getChildren().size());
        Assertions.assertEquals(consumable2, ItemPickerModel.getInstance().getSelectedItem());
    }

    @Test
    @DisplayName("Check if selection button is enabled/disabled correctly")
    public void selectItemButtonBehaviourTest(FxRobot robot) {
        Assertions.assertTrue(robot.lookup("#selectItemButton").tryQuery().get().isDisabled());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertTrue(robot.lookup("#selectItemButton").tryQuery().get().isDisabled());

        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);

        Assertions.assertFalse(robot.lookup("#selectItemButton").tryQuery().get().isDisabled());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertTrue(robot.lookup("#selectItemButton").tryQuery().get().isDisabled());

        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);
        Assertions.assertFalse(robot.lookup("#selectItemButton").tryQuery().get().isDisabled());

        robot.clickOn("#itemList");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
        Assertions.assertFalse(robot.lookup("#selectItemButton").tryQuery().get().isDisabled());
    }

    @Test
    @DisplayName("Check if selection button event is correct")
    public void selectItemButtonTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#selectItemButton");
        Assertions.assertNotEquals(null, selectedItem);
        Assertions.assertFalse(ItemPickerView.getInstance().getStage().isShowing());
    }

    @Test
    @DisplayName("Check if back button event is correct")
    public void backButtonTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#itemList");
        robot.type(KeyCode.ENTER);
        Assertions.assertEquals(weapon, ItemPickerModel.getInstance().getSelectedItem());

        robot.clickOn("#backButton");
        Assertions.assertEquals(null, selectedItem);
        Assertions.assertFalse(ItemPickerView.getInstance().getStage().isShowing());
    }
}