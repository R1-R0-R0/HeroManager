package model.job;

import model.items.Item;

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
    private JobType jobType;
    private List<Item> inventory;
    private String name, description;
    private int[] spellSlots; // TODO : A modifier

    /*
    private List<Spell> spellInventory
    private List<Improvement> improvements
    private List<JobSkill> skills
    */

}
