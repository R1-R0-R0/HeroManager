package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.controller.CharacterCreatorController;
import fr.univ_amu.heromanager.model.files.HeroManagerDB;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.model.job.JobSkill;
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
import fr.univ_amu.heromanager.utils.gui.character_creator.JobSkillItem;
import fr.univ_amu.heromanager.utils.gui.character_creator.SpellItem;
import fr.univ_amu.heromanager.view.CharacterCreatorView;
import fr.univ_amu.heromanager.view.CharacterView;
import fr.univ_amu.heromanager.view.MenuView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(ApplicationExtension.class)
public class CharacterCreatorTest extends Application {

    @Start
    public void start(Stage primaryStage) throws IOException {
        HeroManagerDB.init();
        new MenuModel();
    }

    @Test
    @DisplayName("GUI Test for character creation")
    public void characterCreationTest(FxRobot robot) {
        robot.clickOn("#newGameButton");

        Assertions.assertTrue(robot.lookup("#paneCreation1").tryQuery().get().isVisible());
        Assertions.assertFalse(robot.lookup("#paneCreation2").tryQuery().get().isVisible());

        Assertions.assertTrue(robot.lookup("#jobNameText").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#jobDescriptionText").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#genderPicker").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#alignmentPicker").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#racePicker").tryQuery().get().isDisable());

        robot.clickOn("#jobTypePicker");
        robot.type(KeyCode.DOWN, 9);
        robot.type(KeyCode.ENTER);

        Assertions.assertFalse(robot.lookup("#jobNameText").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#jobDescriptionText").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#genderPicker").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#alignmentPicker").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#racePicker").tryQuery().get().isDisable());

        robot.clickOn("#jobNameText");
        robot.write("Hiraye");

        robot.type(KeyCode.TAB);
        robot.write("A perfect woman");

        robot.clickOn("#genderPicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#alignmentPicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#racePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
    }

    @Test
    @DisplayName("GUI Test for distributions of statistics points")
    public void statisticsTest(FxRobot robot) {
        characterCreationTest(robot);

        robot.clickOn("#createCharacterButton");

        Assertions.assertFalse(robot.lookup("#paneCreation1").tryQuery().get().isVisible());
        Assertions.assertTrue(robot.lookup("#paneCreation2").tryQuery().get().isVisible());

        Assertions.assertEquals("12", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerStrength");
        robot.type(KeyCode.LEFT, 5);

        Assertions.assertEquals("14", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(8, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerStrength");
        robot.type(KeyCode.RIGHT, 7);

        Assertions.assertEquals("7", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerIntelligence");
        robot.type(KeyCode.RIGHT, 5);

        Assertions.assertEquals("2", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());

        robot.clickOn("#spinnerCharisma");
        robot.type(KeyCode.RIGHT, 5);

        Assertions.assertEquals("0", ((Label) robot.lookup("#labelRemainingPoints").tryQuery().get()).getText());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerStrength").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerDexterity").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(15, ((Integer) ((Spinner) robot.lookup("#spinnerIntelligence").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerWisdom").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(10, ((Integer) ((Spinner) robot.lookup("#spinnerRobustness").tryQuery().get()).getValue()).intValue());
        Assertions.assertEquals(12, ((Integer) ((Spinner) robot.lookup("#spinnerCharisma").tryQuery().get()).getValue()).intValue());
    }

    /**
     * Used to create warlock for spell tab test
     *
     * @param robot fx robot
     */
    private void createWarlock(FxRobot robot) {
        robot.clickOn("#newGameButton");

        Assertions.assertTrue(robot.lookup("#paneCreation1").tryQuery().get().isVisible());
        Assertions.assertFalse(robot.lookup("#paneCreation2").tryQuery().get().isVisible());

        Assertions.assertTrue(robot.lookup("#jobNameText").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#jobDescriptionText").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#genderPicker").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#alignmentPicker").tryQuery().get().isDisable());
        Assertions.assertTrue(robot.lookup("#racePicker").tryQuery().get().isDisable());

        robot.clickOn("#jobTypePicker");
        robot.type(KeyCode.DOWN, 11);
        robot.type(KeyCode.ENTER);

        Assertions.assertFalse(robot.lookup("#jobNameText").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#jobDescriptionText").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#genderPicker").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#alignmentPicker").tryQuery().get().isDisable());
        Assertions.assertFalse(robot.lookup("#racePicker").tryQuery().get().isDisable());

        robot.clickOn("#jobNameText");
        robot.write("Hiraye");

        robot.type(KeyCode.TAB);
        robot.write("A perfect woman");

        robot.clickOn("#genderPicker");
        robot.type(KeyCode.DOWN, KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#alignmentPicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);

        robot.clickOn("#racePicker");
        robot.type(KeyCode.DOWN, KeyCode.ENTER);
    }

    @Test
    @DisplayName("GUI Character creation test spell tab enabled for specific job type")
    public void spellTabTest(FxRobot robot) throws InterruptedException {
        characterCreationTest(robot);

        robot.clickOn("#createCharacterButton");
        Assertions.assertTrue(CharacterCreatorController.getInstance().spellsTab.isDisabled());
        robot.clickOn("#backButton2");

        createWarlock(robot);

        robot.clickOn("#createCharacterButton");
        Assertions.assertFalse(CharacterCreatorController.getInstance().spellsTab.isDisabled());
    }

    @Test
    @DisplayName("GUI Character creation test fully character creation")
    public void fullCharacterCreationTest(FxRobot robot) throws InterruptedException {
        Spell fireBall = new Spell("Fireball", "Launch a ball of fire dealing magical damages", "Fire", "1 turn",
                "no duration", 5, 35, JobType.WIZARD, true,
                Collections.singletonList(Component.VOCAL));
        Spell frostSpike = new Spell("Frostspike", "Launch a spike of ice dealing magical movement impairment", "Ice", "instant",
                "3 turns", 6, 40, JobType.WARLOCK, false,
                Arrays.asList(Component.VOCAL, Component.MATERIAL));
        List<Spell> spells = Arrays.asList(fireBall, frostSpike);

        createWarlock(robot);

        robot.clickOn("#createCharacterButton");
        robot.clickOn("#spinnerStrength");

        for (int i = 0; i < 6; i++) {
            robot.type(KeyCode.RIGHT, KeyCode.RIGHT, KeyCode.TAB);
        }

        robot.clickOn("#skillsTab");
        Assertions.assertEquals(JobSkill.values().length, ((ListView<JobSkillItem>) robot.lookup("#skillsListView").tryQuery().get()).getItems().size());

        Platform.runLater(() -> CharacterCreatorView.getInstance().setSpellsListView(spells));
        robot.clickOn("#spellsTab");
        Assertions.assertEquals(2, ((ListView<SpellItem>) robot.lookup("#spellsListView").tryQuery().get()).getItems().size());
        if (spells.size() > 0) {
            robot.type(KeyCode.TAB);

            for (int i = 0; i < spells.size(); i++)
                robot.type(KeyCode.TAB, KeyCode.ENTER);
        }


        robot.clickOn("#launchGameButton");
        Assertions.assertTrue(CharacterView.getInstance().getStage().isShowing());
        Assertions.assertFalse(CharacterCreatorView.getInstance().getStage().isShowing());
        Assertions.assertFalse(MenuView.getInstance().getStage().isShowing());

        Job character = CharacterModel.getInstance().getCharacter();
        Assertions.assertEquals(1, character.getLevel());
        Assertions.assertEquals(0, character.getInventory().size());
        Assertions.assertEquals(spells.size(), character.getSpellInventory().size());

        robot.clickOn("#spellsTabDongle");
        Assertions.assertEquals(spells.size(), ((ListView) robot.lookup("#spellList").tryQuery().get()).getItems().size());
    }
}
