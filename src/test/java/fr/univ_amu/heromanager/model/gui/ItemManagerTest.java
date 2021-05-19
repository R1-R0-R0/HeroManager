package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.ItemManagerController;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import fr.univ_amu.heromanager.model.items.weapons.DamageType;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.model.items.weapons.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import fr.univ_amu.heromanager.utils.ListenableArrayList;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
@DisplayName("GUI Item Manager Tests")
public class ItemManagerTest {

    private static final Weapon weapon = new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING);
    private static final Equipment equipment = new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentPart.HEAD, 11, EquipmentType.LIGHT, 22, 33, 44, 55, 66, 77, 88);
    private static final Consumable
            consumable1 = new Consumable("A potion", "Unknown potion. Will you drink it ?"),
            consumable2 = new Consumable("Gameboy", "A simple gameboy.");

    @Start
    public void start(Stage primaryStage) throws IOException {
        HeroManagerDB.init();
        new ItemManagerModel();
    }

    @BeforeEach
    @DisplayName("To initialize item list fr.univ_amu.heromanager.view")
    public void initItemList() {
        ItemManagerModel model = ItemManagerModel.getInstance();
        model.setWeaponList(new ListenableArrayList<>() {{
            add(weapon);
        }});
        model.setEquipmentList(new ListenableArrayList<>() {{
            add(equipment);
        }});
        model.setConsumableList(new ListenableArrayList<>() {{
            add(consumable1);
            add(consumable2);
        }});
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
    @DisplayName("Test if list fr.univ_amu.heromanager.view contains goods values")
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

        Assertions.assertEquals(weapon.getName(), ((TextField) robot.lookup("#weaponNameText").tryQuery().get()).getText());
        Assertions.assertEquals(weapon.getDescription(), ((TextArea) robot.lookup("#weaponDescriptionText").tryQuery().get()).getText());
        Assertions.assertEquals(weapon.getProperties(), ((TextArea) robot.lookup("#weaponPropertiesText").tryQuery().get()).getText());
        Assertions.assertEquals(weapon.getWeaponType(), ((ChoiceBox<WeaponType>) robot.lookup("#weaponTypePicker").tryQuery().get()).getValue());
        Assertions.assertEquals(weapon.getDamageType(), ((ChoiceBox<DamageType>) robot.lookup("#damageTypePicker").tryQuery().get()).getValue());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);

        Assertions.assertEquals(equipment.getName(), ((TextField) robot.lookup("#equipmentNameText").tryQuery().get()).getText());
        Assertions.assertEquals(equipment.getDescription(), ((TextArea) robot.lookup("#equipmentDescriptionText").tryQuery().get()).getText());
        Assertions.assertEquals(equipment.getEquipmentPart(), ((ChoiceBox<EquipmentPart>) robot.lookup("#equipmentPartPicker").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getEquipmentType(), ((ChoiceBox<EquipmentType>) robot.lookup("#equipmentTypePicker").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getArmorBonus(), ((Spinner<Integer>) robot.lookup("#spinnerArmorBonus").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getStrengthBoost(), ((Spinner<Integer>) robot.lookup("#spinnerStrength").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getDexterityBoost(), ((Spinner<Integer>) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getRobustnessBoost(), ((Spinner<Integer>) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getIntelligenceBoost(), ((Spinner<Integer>) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getWisdomBoost(), ((Spinner<Integer>) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getCharismaBoost(), ((Spinner<Integer>) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue());
        Assertions.assertEquals(equipment.getSpeedBoost(), ((Spinner<Integer>) robot.lookup("#spinnerSpeed").tryQuery().get()).getValue());

        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);

        Assertions.assertEquals(consumable1.getName(), ((TextField) robot.lookup("#consumableNameText").tryQuery().get()).getText());
        Assertions.assertEquals(consumable1.getDescription(), ((TextArea) robot.lookup("#consumableDescriptionText").tryQuery().get()).getText());

        robot.type(KeyCode.DOWN);

        Assertions.assertEquals(consumable2.getName(), ((TextField) robot.lookup("#consumableNameText").tryQuery().get()).getText());
        Assertions.assertEquals(consumable2.getDescription(), ((TextArea) robot.lookup("#consumableDescriptionText").tryQuery().get()).getText());
    }

    @Test
    @DisplayName("Test if buttons are enabled/disabled correctly")
    public void buttonsEnabledTest(FxRobot robot) throws InterruptedException {
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

    @Test
    @DisplayName("Check if weapon is created correctly")
    public void weaponCreationTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertEquals(1, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());

        robot.clickOn("#weaponNameText");
        robot.write("Master Sword");

        robot.clickOn("#weaponDescriptionText");
        robot.write("Who doesn't know her ?");

        robot.clickOn("#weaponPropertiesText");
        robot.write("Kills everything in one shot");

        robot.clickOn("#weaponTypePicker");
        robot.type(KeyCode.ENTER);

        robot.clickOn("#damageTypePicker");
        robot.type(KeyCode.ENTER);

        robot.clickOn("#newItemButton");

        Assertions.assertEquals(2, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Check if equipment is created correctly")
    public void equipmentCreationTest(FxRobot robot) throws InterruptedException {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertEquals(1, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());

        robot.clickOn("#equipmentNameText");
        robot.write("Hylian Shield");

        robot.clickOn("#equipmentDescriptionText");
        robot.write("The ultimate shield");

        robot.clickOn("#equipmentPartPicker");
        robot.type(KeyCode.TAB, 6);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#equipmentTypePicker");
        robot.type(KeyCode.ENTER);

        robot.clickOn("#spinnerArmorBonus");
        robot.write(Integer.toString(Integer.MAX_VALUE));

        robot.clickOn("#spinnerStrength");
        robot.write("255");

        robot.clickOn("#spinnerDexterity");
        robot.write("255");

        robot.clickOn("#spinnerRobustness");
        robot.write("255");

        robot.clickOn("#spinnerIntelligence");
        robot.write("255");

        robot.clickOn("#spinnerWisdom");
        robot.write("255");

        robot.clickOn("#spinnerCharisma");
        robot.write("255");

        robot.clickOn("#spinnerSpeed");
        robot.write("255");

        robot.clickOn("#newItemButton");

        Assertions.assertEquals(2, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Check if consumable is created correctly")
    public void consumableCreationTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        Assertions.assertEquals(2, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());

        robot.clickOn("#consumableNameText");
        robot.write("Potion");

        robot.clickOn("#consumableDescriptionText");
        robot.write("A simple potion.");

        robot.clickOn("#newItemButton");

        Assertions.assertEquals(3, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Test if weapon is updated correctly")
    public void weaponUpdateTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.type(KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        robot.clickOn("#weaponNameText");
        robot.type(KeyCode.BACK_SPACE, weapon.getName().length());
        robot.write("Master Sword");

        robot.clickOn("#weaponDescriptionText");
        robot.type(KeyCode.BACK_SPACE, weapon.getDescription().length());
        robot.write("Who doesn't know her ?");

        robot.clickOn("#weaponPropertiesText");
        robot.type(KeyCode.BACK_SPACE, weapon.getProperties().length());
        robot.write("Kills everything in one shot");

        robot.clickOn("#weaponTypePicker");
        robot.type(KeyCode.TAB, KeyCode.ENTER);

        robot.clickOn("#damageTypePicker");
        robot.type(KeyCode.TAB, KeyCode.ENTER);

        robot.clickOn("#updateItemButton");

        Weapon w = ((Weapon) ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().get(0));
        Assertions.assertEquals("Master Sword", w.getName());
        Assertions.assertEquals("Who doesn't know her ?", w.getDescription());
        Assertions.assertEquals("Kills everything in one shot", w.getProperties());
        Assertions.assertNotEquals(weapon.getWeaponType(), w.getWeaponType());
        Assertions.assertNotEquals(weapon.getDamageType(), w.getDamageType());
    }

    @Test
    @DisplayName("Test if equipment is updated correctly")
    public void equipmentUpdateTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.type(KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        robot.clickOn("#equipmentNameText");
        robot.type(KeyCode.BACK_SPACE, equipment.getName().length());
        robot.write("Hylian Shield");

        robot.clickOn("#equipmentDescriptionText");
        robot.type(KeyCode.BACK_SPACE, equipment.getDescription().length());
        robot.write("The ultimate shield");

        robot.clickOn("#equipmentPartPicker");
        robot.type(KeyCode.TAB, KeyCode.ENTER);

        robot.clickOn("#equipmentTypePicker");
        robot.type(KeyCode.TAB, KeyCode.ENTER);

        robot.clickOn("#spinnerArmorBonus");
        robot.type(KeyCode.BACK_SPACE, Integer.toString(equipment.getArmorBonus()).length());
        robot.write(Integer.toString(Integer.MAX_VALUE));

        robot.clickOn("#spinnerStrength");
        robot.write("255");

        robot.clickOn("#spinnerDexterity");
        robot.write("255");

        robot.clickOn("#spinnerRobustness");
        robot.write("255");

        robot.clickOn("#spinnerIntelligence");
        robot.write("255");

        robot.clickOn("#spinnerWisdom");
        robot.write("255");

        robot.clickOn("#spinnerCharisma");
        robot.write("255");

        robot.clickOn("#spinnerSpeed");
        robot.write("255");

        robot.clickOn("#updateItemButton");

        Equipment e = ((Equipment) ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().get(0));
        Assertions.assertNotEquals(equipment.getName(), e.getName());
        Assertions.assertNotEquals(equipment.getDescription(), e.getDescription());
        Assertions.assertNotEquals(equipment.getEquipmentPart(), e.getEquipmentPart());
        Assertions.assertNotEquals(equipment.getEquipmentType(), e.getEquipmentType());
        Assertions.assertNotEquals(equipment.getArmorBonus(), e.getArmorBonus());
        Assertions.assertNotEquals(equipment.getStrengthBoost(), e.getStrengthBoost());
        Assertions.assertNotEquals(equipment.getDexterityBoost(), e.getDexterityBoost());
        Assertions.assertNotEquals(equipment.getRobustnessBoost(), e.getRobustnessBoost());
        Assertions.assertNotEquals(equipment.getIntelligenceBoost(), e.getIntelligenceBoost());
        Assertions.assertNotEquals(equipment.getWisdomBoost(), e.getWisdomBoost());
        Assertions.assertNotEquals(equipment.getCharismaBoost(), e.getCharismaBoost());
        Assertions.assertNotEquals(equipment.getSpeedBoost(), e.getSpeedBoost());
    }

    @Test
    @DisplayName("Check if consumable is updated correctly")
    public void consumableUpdateTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.type(KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        robot.clickOn("#consumableNameText");
        robot.type(KeyCode.BACK_SPACE, consumable1.getName().length());
        robot.write("Potion");

        robot.clickOn("#consumableDescriptionText");
        robot.type(KeyCode.BACK_SPACE, consumable1.getDescription().length());
        robot.write("A simple potion.");

        robot.clickOn("#updateItemButton");

        Consumable e = ((Consumable) ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().get(0));
        Assertions.assertEquals("Potion", e.getName());
        Assertions.assertEquals("A simple potion.", e.getDescription());
    }

    @Test
    @DisplayName("Check if weapon is deleted correctly")
    public void weaponRemoveTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.type(KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        robot.clickOn("#deleteItemButton");

        Assertions.assertEquals(0, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Check if equipment is deleted correctly")
    public void equipmentRemoveTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.type(KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        robot.clickOn("#deleteItemButton");

        Assertions.assertEquals(0, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Check if consumable is deleted correctly")
    public void consumableRemoveTest(FxRobot robot) {
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.type(KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#itemList");

        robot.clickOn("#deleteItemButton");

        Assertions.assertEquals(1, ((ListView<Item>) robot.lookup("#itemList").tryQuery().get()).getItems().size());
    }
}
