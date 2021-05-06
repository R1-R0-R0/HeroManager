package gui;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.gui.LoadGameModel;
import model.gui.MenuModel;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import view.LoadGameView;
import view.MenuView;

import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(ApplicationExtension.class)
public class LoadGameTest {

    @Start
    public void start(Stage primaryStage) throws Exception {
        new MenuModel();
        new LoadGameModel();

        Job wizard = new Job("Grosflan", "A big Flan", Gender.MAN, Alignment.CHAOTIC_EVIL, Race.DRAGONBORN, JobType.WIZARD);
        Job warlock = new Job("Airels", "Airels nothing more to say", Gender.MAN, Alignment.NEUTRAL_GOOD, Race.GNOME, JobType.WARLOCK);
        Job paladin = new Job("M.", "I love open shoes", Gender.MAN, Alignment.LAWFUL_EVIL, Race.HALFLING, JobType.PALADIN);
        Job alreadyExistingBard = new Job("Hatsune", "Young virtual singer", Gender.WOMAN, Alignment.CHAOTIC_EVIL, Race.HALFLING, JobType.BARD, new ArrayList<>(), new ArrayList<>(),
                15, 14, 13, 12, 11, 10, 9, 8, 50, 50, 10, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        LoadGameView.getInstance().setGamesList(Arrays.asList(wizard, warlock, paladin, alreadyExistingBard));
    }

    @Test
    public void loadGameTest(FxRobot robot) throws InterruptedException {
        ListView<Job> jobs = ((ListView) robot.lookup("#charactersList").tryQuery().get());

        Assertions.assertEquals(4, jobs.getItems().size());

        Button loadButton = ((Button) robot.lookup("#loadButton").tryQuery().get());
        Assertions.assertTrue(loadButton.isDisabled());

        robot.clickOn("#charactersList");
        robot.type(KeyCode.ENTER);
        Assertions.assertFalse(loadButton.isDisabled());
        Thread.sleep(1000);

        robot.type(KeyCode.DOWN);
        Thread.sleep(1000);

        robot.type(KeyCode.DOWN);
        Thread.sleep(1000);

        robot.type(KeyCode.DOWN);
        Thread.sleep(1000);

        robot.clickOn(loadButton);

        Assertions.assertFalse(MenuView.getInstance().getStage().isShowing());
        Assertions.assertFalse(LoadGameView.getInstance().getStage().isShowing());
    }
}
