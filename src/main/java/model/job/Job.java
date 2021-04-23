package model.job;

import model.items.Item;
import model.items.equipments.Equipment;
import model.race.Alignment;
import model.race.Language;
import model.race.RaceType;
import model.spell.Spell;

import java.util.List;

public class Job {
    private int hp,
            armor,
            level,
            strength,
            dexterity,
            intelligence,
            wisdom,
            charisma;
    private int age;
    private String sex;
    private Alignment alignment;
    private int size;
    private int weight;
    private int speed;
    private List<Language> languages;
    private RaceType race;
    private JobType jobType;
    private List<Item> inventory;
    private List<Equipment> equippedEquipments;
    private String name, description;
    private int[] spellSlots; // TODO : A modifier
    private List<Spell> spellInventory;
    private List<JobSkill> skills;

    public Job(String name, String description, Alignment alignment, int speed,
               int hp, int armor, int level, int strength, int dexterity, int intelligence,
               int wisdom, int charisma, RaceType race, JobType jobType, List<Item> inventory,
               int[] spellSlots, List<Spell> spellInventory, List<JobSkill> skills) {

        this.hp = hp;
        this.armor = armor;
        this.speed = speed;
        this.level = 1;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.race = race;
        this.jobType = jobType;
        this.inventory = inventory;
        this.name = name;
        this.description = description;
        this.spellSlots = spellSlots;
        this.spellInventory = spellInventory;

        this.skills = skills;


    }

    public Job(String name, String description, int hp, int armor, int strength, int dexterity, int intelligence,
               int wisdom, int charisma, RaceType race, JobType jobType, List<Spell> spellInventory,
               List<Improvement> improvements, List<JobSkill> skills) {


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
        double proficiency = 1 + (double) (level/4);
        return (int) Math.nextUp(proficiency);

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
        int result = 0;
        for (Improvement improv: getImprovements()) {
            result += improv.getStrengthBoost();
        }
        result += race.getStrengthBoost(); //TODO: + equippedEquipments.getStrengthBoost
        return result;
    }

    public int getDexterityBoost() {
        int result = 0;
        for (Improvement improv: getImprovements()) {
            result += improv.getDexterityBoost();
        }
        result += race.getDexterityBoost(); //TODO: + equippedEquipments.getDexterityBoost
        return result;
    }

    public int getRobustnessBoost() {
        int result = 0;
        for (Improvement improv: getImprovements()) {
            result += improv.getRobustnessBoost();
        }
        result += race.getRobustnessBoost(); //TODO: + equippedEquipments.getRobustnessBoost
        return result;
    }

    public int getIntelligenceBoost() {
        int result = 0;
        for (Improvement improv: getImprovements()) {
            result += improv.getIntelligenceBoost();
        }
        result += race.getIntelligenceBoost(); //TODO: + equippedEquipments.getIntelligenceBoost
        return result;
    }

    public int getWisdomBoost() {
        int result = 0;
        for (Improvement improv: getImprovements()) {
            result += improv.getWisdomBoost();
        }
        result += race.getWisdomBoost(); //TODO: + equippedEquipments.getWisdomBoost
        return result;
    }

    public int getCharismaBoost() {
        int result = 0;
        for (Improvement improv: getImprovements()) {
            result += improv.getCharismaBoost();
        }
        result += race.getCharismaBoost();
        return result; //TODO: + equippedEquipments.getCharismaBoost
    }

    public RaceType getRaceType() {
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
        return race.getImprovements();
    }

    public List<JobSkill> getSkills() {
        return skills;
    }
}
