package model.gui;

import model.items.Item;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;
import view.MenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of Menu view.
 * Main view of program, this class is the first view called by the software at startup
 *
 * @see MenuView associated class view (MVC pattern)
 * @see controller.MenuController associated class controller (MVC pattern)
 */
public class MenuModel implements Model {

    private static MenuModel instance;

    /**
     * Constructor. When called, show Menu view
     */
    public MenuModel() {
        instance = this;
        new MenuView();
    }

    /**
     * @return instance of this class
     */
    public static MenuModel getInstance() {
        return instance;
    }

    /**
     * Event triggered when user clicks on resume button.
     * It allows to open Character view with last game saved
     */
    public void resumeGame() {

        // Job expl = new Job("Hiraye", "A human", 100, 100, 1, 2, 3, 4, 5, )
        Spell spell = new Spell("A", "Description", "School", "1 minute", "1 minute",
                10, 50, JobType.CLERIC, true, null);
        Spell spell2 = new Spell("B", "Description 2", "No", "1 hour", "1 hour",
                20, 40, JobType.DRUID, true, null);
        List<Spell> spells = new ArrayList<>();
        spells.add(spell);
        spells.add(spell2);

        List<Item> items = new ArrayList<>();
        items.add(new Weapon("Épée", "Une épée", "Propriétés", WeaponType.COMMON, DamageType.SLASHING));
        items.add(new Weapon("Hache", "Une hache", "Propriétés", WeaponType.COMMON, DamageType.BLUDGEONING));
        items.add(new Weapon("Arc", "Un arc", "Propriétés", WeaponType.COMMON, DamageType.PIERCING));

        Job hiraye = new Job("Hiraye", "A woman", Gender.WOMAN, Alignment.NEUTRAL_NEUTRAL, Race.HUMAN, JobType.ROGUE);
        Job airels = new Job("Airels", "A man", Gender.MAN, Alignment.CHAOTIC_EVIL, Race.DRAGONBORN, JobType.WARLOCK);

        new CharacterModel(hiraye);
        MenuView.getInstance().close();
    }

    /**
     * Event triggered when user clicks on new game button.
     * When called, shows up character's creation view
     */
    public void newGame() {
        new CharacterCreatorModel(MenuView.getInstance().getStage());
    }

    /**
     * Event tiggered when user clicks on load game button.
     * When called, shows up view to load a saved character
     */
    public void loadGame() {
        new LoadGameModel();
    }

    /**
     * Event triggered when user clicks on item manager's button.
     * When called, open view to manage items
     */
    public void openItemManager() {
        MenuView.getInstance().close();
        new ItemManagerModel();
    }

    /**
     * Event triggered when user clicks on quit button.
     * When called, just close the whole program
     */
    public void quitProgram() {
        MenuView.getInstance().close();
        System.exit(0);
    }
}
