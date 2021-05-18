package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.CharacterController;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import fr.univ_amu.heromanager.model.items.weapons.DamageType;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.model.items.weapons.WeaponType;
import fr.univ_amu.heromanager.model.job.Gender;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.model.job.JobType;
import fr.univ_amu.heromanager.model.race.Alignment;
import fr.univ_amu.heromanager.model.race.Race;
import fr.univ_amu.heromanager.model.spell.Spell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Collections;

@ExtendWith(ApplicationExtension.class)
@DisplayName("Character fr.univ_amu.heromanager.view tests")
public class CharacterTest {

    @Start
    public void start(Stage primaryStage) throws Exception {
        Job job = new Job("Hiraye", "A perfect woman", Gender.WOMAN, Alignment.NEUTRAL_NEUTRAL, Race.HUMAN, JobType.ROGUE);
        job.addSpell(new Spell("A", "A spell", "School", "3sec", "3sec", 9, 10, JobType.ROGUE, true, null));
        job.addSpell(new Spell("B", "B spell", "School", "3sec", "3sec", 9, 10, JobType.ROGUE, true, null));
        job.getInventory().add(new Weapon("Weapon", "Description", "Properties", WeaponType.WAR, DamageType.SLASHING));

        new CharacterModel(job);
    }

    @Test
    @DisplayName("Test setting up new hp amount")
    public void characterHpViewTest(FxRobot robot) {
        Job character = CharacterModel.getInstance().getCharacter();
        character.setHealthPoints(50);
        Assertions.assertEquals(50, character.getHealthPoints());

        robot.clickOn("#hpBar");
        robot.clickOn("#newHPInput");
        robot.write("25");
        robot.clickOn("#setButton");

        Assertions.assertEquals(25, character.getHealthPoints());
        Assertions.assertEquals("25 / 100", CharacterController.getInstance().hpText.getText());
    }

    @Test
    @DisplayName("Dice thrower tests")
    public void characterDiceThrower(FxRobot robot) {
        robot.clickOn("#tableMenu");
        robot.clickOn("#diceThrowerMenuItem");
        robot.clickOn("#dicesText");
        robot.write("3d4 + 2D2+3d3");
        robot.clickOn("#rollDicesButton");

        int res = Integer.parseInt(((Text) robot.lookup("#diceResultText").tryQuery().get()).getText());
        Assertions.assertTrue(res >= 9 && res <= 25);

        String details = ((Text) robot.lookup("#diceResultDetailsText").tryQuery().get()).getText();
        Assertions.assertEquals(24, details.length());

        robot.clickOn("#howItWorksButton");
    }

    @Test
    @DisplayName("")
    public void characterViewTest(FxRobot robot) throws InterruptedException {
        TabPane tabPane = ((TabPane) robot.lookup("#tabPane").tryQuery().get());

        Assertions.assertEquals("Hiraye", tabPane.getTabs().get(0).getText());
        Assertions.assertEquals("Spells", tabPane.getTabs().get(1).getText());
        Assertions.assertEquals("Equipment", tabPane.getTabs().get(2).getText());
        Assertions.assertEquals("Inventory", tabPane.getTabs().get(3).getText());

        Assertions.assertEquals(9, ((TextFlow) robot.lookup("#jobInfo").tryQuery().get()).getChildren().size());
        Assertions.assertEquals(2, ((TextFlow) robot.lookup("#skillsInfo").tryQuery().get()).getChildren().size());
        Assertions.assertEquals(2, ((TextFlow) robot.lookup("#improvementsInfo").tryQuery().get()).getChildren().size());
        // Assertions.assertEquals("100 / 100", ((Text) robot.lookup("#hpText").tryQuery().get()).getText());
        Assertions.assertEquals("LVL 01", ((Text) robot.lookup("#levelText").tryQuery().get()).getText());

        robot.clickOn("#spellsTabDongle");
        robot.clickOn("#spellList");
        robot.type(KeyCode.ENTER, KeyCode.DOWN);

        Assertions.assertEquals("B spell", ((TextArea) robot.lookup("#spellDesc").tryQuery().get()).getText());

        /*
        robot.clickOn("#equipmentTabDongle");

        

        robot.clickOn("#characterTab");

         */

    }

    @Test
    @DisplayName("Inventory adding item tests")
    public void inventoryAddItemTest(FxRobot robot) {
        robot.clickOn("#inventoryTabDongle");
        Assertions.assertFalse(((StackPane) robot.lookup("#inventorySlot0").tryQuery().get()).getChildren().get(0) instanceof ImageView);
        // Assertions.assertEquals("Weapon", ((Text) ((StackPane) robot.lookup("#inventorySlot0").tryQuery().get()).getChildren().get(0)).getText());
        Assertions.assertTrue(((StackPane) robot.lookup("#inventorySlot1").tryQuery().get()).getChildren().get(0) instanceof ImageView);

        robot.clickOn("#inventorySlot1");

        ItemPickerModel.getInstance().setWeaponList(Collections.singletonList(new Weapon("Axe", "An axe", "She's sharp", WeaponType.COMMON, DamageType.SLASHING)));
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#selectItemButton");

        Assertions.assertFalse(((StackPane) robot.lookup("#inventorySlot1").tryQuery().get()).getChildren().get(0) instanceof ImageView);

        robot.clickOn("#inventorySlot1");
        robot.clickOn("#discardAction");
        robot.clickOn(robot.lookup(node -> node instanceof Button && ((Button) node).getText().equals("No")).tryQuery().get());

        Assertions.assertFalse(((StackPane) robot.lookup("#inventorySlot1").tryQuery().get()).getChildren().get(0) instanceof ImageView);

        robot.clickOn("#inventorySlot1");
        robot.clickOn("#discardAction");
        robot.clickOn(robot.lookup(node -> node instanceof Button && ((Button) node).getText().equals("Yes")).tryQuery().get());

        Assertions.assertTrue(((StackPane) robot.lookup("#inventorySlot1").tryQuery().get()).getChildren().get(0) instanceof ImageView);
    }

    @Test
    @DisplayName("Equip equipment in inventory test")
    public void equipEquipmentInventoryTest(FxRobot robot) throws InterruptedException {
        robot.clickOn("#inventoryTabDongle");
        robot.clickOn("#inventorySlot1");

        Equipment headphones = new Equipment("Headphones", "A simple pair of headphones, useless in fight", EquipmentPart.HEAD, 11, EquipmentType.LIGHT, 22, 33, 44, 55, 66, 77, 88);

        ItemPickerModel.getInstance().setEquipmentList(Collections.singletonList(headphones));
        robot.clickOn("#typePicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER, KeyCode.TAB, KeyCode.ENTER);
        robot.clickOn("#selectItemButton");
        robot.clickOn("#inventorySlot1");

        robot.clickOn("#equipAction");

        Assertions.assertEquals(headphones, CharacterModel.getInstance().getCharacter().getEquippedEquipments().getHead());

        robot.clickOn("#equipmentTabDongle");
        robot.clickOn("#headPane");
        robot.clickOn("#unequipAction");
        robot.sleep(250);
        robot.clickOn("#unequipAction");

        Assertions.assertNull(CharacterModel.getInstance().getCharacter().getEquippedEquipments().getHead());
    }
}
