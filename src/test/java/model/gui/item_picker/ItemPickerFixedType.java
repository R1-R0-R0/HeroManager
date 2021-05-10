package model.gui.item_picker;

import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.gui.ItemPickerModel;
import model.items.Item;
import model.items.ItemType;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentPart;
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

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(ApplicationExtension.class)
@DisplayName("Item Picker view tests for a fixed value")
public class ItemPickerFixedType {

    private static final Weapon weapon = new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING);
    private static final Equipment equipment = new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentPart.HEAD, 11, EquipmentType.LIGHT, 22, 33, 44, 55, 66, 77, 88);
    private static final Consumable
            consumable1 = new Consumable("A potion", "Unknown potion. Will you drink it ?"),
            consumable2 = new Consumable("Gameboy", "A simple gameboy.");

    @Start
    public void start(Stage primaryStage) {
        ItemPickerModel model = new ItemPickerModel(primaryStage, ItemType.CONSUMABLES, () -> {});
        model.setWeaponList(Collections.singletonList(weapon));
        model.setEquipmentList(Collections.singletonList(equipment));
        model.setConsumableList(Arrays.asList(consumable1, consumable2));
    }

    @Test
    @DisplayName("Check type picker disable status")
    public void typePickerTest(FxRobot robot) {
        Assertions.assertTrue(robot.lookup("#typePicker").tryQuery().get().isDisable());
    }

    @Test
    @DisplayName("Check if list view already show imposed items")
    public void itemListPresetTest(FxRobot robot) {
        Assertions.assertEquals(2, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }
}
