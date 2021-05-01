package model.gui;

import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.RaceType;
import view.CharacterView;
import view.MenuView;

import java.util.ArrayList;

public class MenuModel {

    private static MenuModel instance;

    public MenuModel() {
        instance = this;
        new MenuView();
    }

    public static MenuModel getInstance() {
        return instance;
    }

    public void resumeGame() {

        // Job expl = new Job("Hiraye", "A human", 100, 100, 1, 2, 3, 4, 5, )
        Job hiraye = new Job("Hiraye", "A woman", Gender.WOMAN, Alignment.NEUTRAL, RaceType.HUMAN, JobType.ROGUE, new ArrayList<>(), new int[6], new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Job airels = new Job("Airels", "A man", Gender.MAN, Alignment.CHAOTIC_EVIL, RaceType.HUMAN, JobType.PALADIN, new ArrayList<>(), new int[6], new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        new CharacterModel(hiraye);
        MenuView.getInstance().close();
    }

    public void newGame() {
        new NewGameModel();
    }

    public void loadGame() {
        new LoadGameModel();
    }

    public void openItemManager() {
        MenuView.getInstance().close();
        new ItemManagerModel();
    }

    public void quitProgram() {
        System.exit(0);
    }
}
