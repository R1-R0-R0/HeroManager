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
            charisma,
            strengthBoost,
            dexterityBoost,
            intelligenceBoost,
            wisdomBoost,
            charismaBoost;
    private Race race;
    private JobType jobType;
    private List<Item> inventory;
    private String name, description;
    private int[] spellSlots; // TODO : A modifier
    private List<Spell> spellInventory;
    private List<Improvement> improvements;
    private List<JobSkill> skills;

    public Job(int hp, int armor, int level, int proficiencyLevel, int strength, int dexterity, int intelligence,
               int wisdom, int charisma, int strengthBoost, int dexterityBoost, int intelligenceBoost, int wisdomBoost,
               int charismaBoost, Race race, JobType jobType, List<Item> inventory, String name, String description,
               int[] spellSlots, List<Spell> spellInventory, List<Improvement> improvements, List<JobSkill> skills) {
        this.hp = hp;
        this.armor = armor;
        this.level = level;
        this.proficiencyLevel = proficiencyLevel;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.strengthBoost = strengthBoost;
        this.dexterityBoost = dexterityBoost;
        this.intelligenceBoost = intelligenceBoost;
        this.wisdomBoost = wisdomBoost;
        this.charismaBoost = charismaBoost;
        this.race = race;
        this.jobType = jobType;
        this.inventory = inventory;
        this.name = name;
        this.description = description;
        this.spellSlots = spellSlots;
        this.spellInventory = spellInventory;
        this.improvements = improvements;
        this.skills = skills;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public int getLevel() {
        return level;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getStrengthBoost() {
        return strengthBoost;
    }

    public int getDexterityBoost() {
        return dexterityBoost;
    }

    public int getIntelligenceBoost() {
        return intelligenceBoost;
    }

    public int getWisdomBoost() {
        return wisdomBoost;
    }

    public int getCharismBoost() {
        return charismaBoost;
    }

    public Race getRace() {
        return race;
    }

    public JobType getJobType() {
        return jobType;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int[] getSpellSlots() {
        return spellSlots;
    }

    public List<Spell> getSpellInventory() {
        return spellInventory;
    }

    public List<Improvement> getImprovements() {
        return improvements;
    }

    public List<JobSkill> getSkills() {
        return skills;
    }
}
