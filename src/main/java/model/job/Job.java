package model.job;

import model.items.Item;
import model.race.Race;
import model.spell.Spell;

import java.util.List;

public class Job {
    private int hp,
            armor,
            level,
            proficiencyLevel,
            strength,
            dexterity,
            intelligence,
            wisdom,
            charism,
            strengthBoost,
            dexterityBoost,
            intelligenceBoost,
            wisdomBoost,
            charismBoost;
    private Race race;
    private JobType jobType;
    private List<Item> inventory;
    private String name, description;
    private int[] spellSlots; // TODO : A modifier
    private List<Spell> spellInventory;
    private List<Improvement> improvements;
    private List<JobSkill> skills;


}
