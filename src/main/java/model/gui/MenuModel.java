package model.gui;

import model.items.Item;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.*;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;
import view.MenuView;

import java.util.ArrayList;
import java.util.List;

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

    public void newGame() {
        // new NewGameModel();
        new CharacterCreatorModel(MenuView.getInstance().getStage());
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
