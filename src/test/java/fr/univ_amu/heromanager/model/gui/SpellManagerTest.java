package fr.univ_amu.heromanager.model.gui;

import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.job.JobType;
import fr.univ_amu.heromanager.model.spell.Component;
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
@DisplayName("GUI Spell Manager Tests")
public class SpellManagerTest {

    private final Spell spell1 = new Spell(
            "Hyper Goner",
            "Turning yourself into a monstrous goat skull before devouring the entire timeline.",
            "Divination",
            "Instantaneous",
            "As much you need to swallow the timeline",
            9,
            Integer.MAX_VALUE,
            JobType.WARLOCK,
            true,
            Collections.singletonList(Component.MOVEMENT)
            );

    @Start
    public void start(Stage primaryStage) {
        new SpellManagerModel();
    }

    @Test
    @DisplayName("Spell creation test")
    public void spellCreationTest(FxRobot robot) {
        robot.clickOn("#spellNameLabel");
        robot.write("Hyper Goner");

        robot.clickOn("#spellDescLabel");
        robot.write("Turning yourself into a monstrous goat skull before devouring the entire timeline.");

        robot.clickOn("#spellSchoolLabel");
        robot.write("Divination");

        robot.clickOn("#spellCastingTimeLabel");
        robot.write("Instantaneous");

        robot.clickOn("#spellDurationLabel");
        robot.write("As much you need to swallow the timeline");

        robot.clickOn("#spellLevelSpinner");
        robot.type(KeyCode.UP, 8);

        robot.clickOn("#spellRangeSpinner");
        robot.type(KeyCode.BACK_SPACE);
        robot.write(Integer.toString(Integer.MAX_VALUE));

        robot.clickOn("#spellJobTypePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#spellDoDamagesButton");
        robot.clickOn("#spellDoDamagesButton");

        robot.clickOn("#spellComponent3Picker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#newSpellButton");

        Assertions.assertEquals(1, ((ListView<Spell>) robot.lookup("#spellList").tryQuery().get()).getItems().size());
    }

    @Test
    @DisplayName("Spell modification test")
    public void spellModificationTest(FxRobot robot) {
        SpellManagerModel.getInstance().setSpells(Collections.singletonList(spell1));

        Assertions.assertTrue(robot.lookup("#updateSpellButton").tryQuery().get().isDisabled());
        Assertions.assertTrue(robot.lookup("#deleteSpellButton").tryQuery().get().isDisabled());

        robot.clickOn("#spellList");
        robot.type(KeyCode.ENTER);

        Assertions.assertFalse(robot.lookup("#updateSpellButton").tryQuery().get().isDisabled());
        Assertions.assertFalse(robot.lookup("#deleteSpellButton").tryQuery().get().isDisabled());

        robot.clickOn("#spellNameLabel");
        robot.type(KeyCode.BACK_SPACE, 2);
        robot.write("a");
        robot.clickOn("#updateSpellButton");

        Assertions.assertEquals("Hyper Gona", SpellManagerModel.getInstance().getSpells().get(0).getName());
    }

    @Test
    @DisplayName("Spell deletion test")
    public void spellDeletiontest(FxRobot robot) {
        SpellManagerModel.getInstance().setSpells(Collections.singletonList(spell1));

        Assertions.assertTrue(robot.lookup("#updateSpellButton").tryQuery().get().isDisabled());
        Assertions.assertTrue(robot.lookup("#deleteSpellButton").tryQuery().get().isDisabled());

        robot.clickOn("#spellList");
        robot.type(KeyCode.ENTER);

        Assertions.assertFalse(robot.lookup("#updateSpellButton").tryQuery().get().isDisabled());
        Assertions.assertFalse(robot.lookup("#deleteSpellButton").tryQuery().get().isDisabled());

        robot.clickOn("#deleteSpellButton");

        Assertions.assertEquals(0, SpellManagerModel.getInstance().getSpells().size());
    }
}
