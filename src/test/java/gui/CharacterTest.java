package gui;

import controller.CharacterController;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.gui.CharacterModel;
import model.gui.MenuModel;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
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
    public void characterViewHpTest(FxRobot robot) {
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
        System.out.println("((ListView) robot.lookup(\"#spellList\").tryQuery().get()).getChildrenUnmodifiable().get(0) = " + ((ListView) robot.lookup("#spellList").tryQuery().get()).getChildrenUnmodifiable().get(0));
        robot.clickOn();

        Thread.sleep(1000000);

        robot.clickOn("#equipmentTabDongle");

        robot.clickOn("#inventoryTabDongle");

        robot.clickOn("#characterTab");

    }
}