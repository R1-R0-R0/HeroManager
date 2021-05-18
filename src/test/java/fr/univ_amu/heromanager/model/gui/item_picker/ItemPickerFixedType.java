package fr.univ_amu.heromanager.model.gui.item_picker;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.gui.ItemPickerModel;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
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

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(ApplicationExtension.class)
@DisplayName("Item Picker fr.univ_amu.heromanager.view tests for a fixed value")
public class ItemPickerFixedType {

    private static final Weapon weapon = new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING);
    private static final Equipment equipment = new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentPart.HEAD, 11, EquipmentType.LIGHT, 22, 33, 44, 55, 66, 77, 88);
    private static final Consumable
            consumable1 = new Consumable("A potion", "Unknown potion. Will you drink it ?"),
            consumable2 = new Consumable("Gameboy", "A simple gameboy.");

    @Start
    public void start(Stage primaryStage) {
        ItemPickerModel model = new ItemPickerModel(primaryStage, ItemType.CONSUMABLES, selectedItem -> {});
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
    @DisplayName("Check if list fr.univ_amu.heromanager.view already show imposed items")
    public void itemListPresetTest(FxRobot robot) {
        Assertions.assertEquals(2, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }
}
