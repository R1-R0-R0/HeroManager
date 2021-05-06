package model.job;

import model.items.Item;
import model.items.equipments.Equipment;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Job {
    public final static int BASE_STATS = 10;
    public final static int ADDITIONAL_STATS = 12;

    private int level,
                strength,
                dexterity,
                intelligence,
                wisdom,
                robustness,
                charisma,
                armor,
                healthPoints;
    private Gender gender;
    private int statsPoints;
    private Alignment alignment;
    private final Race race;
    private final JobType jobType;
    private final List<Item> inventory;
    private List<Equipment> equippedEquipments = new ArrayList<>();
    private final String name;
    private final String description;
    private int[] spellSlots;
    private final List<Spell> spellInventory;
    private final List<JobSkill> skills;
    private final List<Improvement> improvements;

    public Job(String name, String description, Gender gender, Alignment alignment, Race race, JobType jobType) {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.alignment = alignment;
        this.race = race;
        this.jobType = jobType;
        this.improvements = race.getImprovements();

        inventory = new ArrayList<>();
        spellInventory = new ArrayList<>();
        skills = new ArrayList<>();

        level = 1;
        armor = BASE_STATS;
        strength = BASE_STATS;
        dexterity = BASE_STATS;
        intelligence = BASE_STATS;
        robustness = BASE_STATS;
        wisdom = BASE_STATS;
        charisma = BASE_STATS;
        statsPoints = ADDITIONAL_STATS;

        setSpellSlots(1);
    }

    public Job(String name, String description, Gender gender, Alignment alignment, Race race, JobType jobType, List<Spell> spellInventory, List<JobSkill> skills,
               int level, int strength, int dexterity, int intelligence, int robustness, int wisdom, int charisma, int armor,
               int statsPoints, List<Improvement> improvements, List<Equipment> equippedEquipments, List<Item> inventory) {

        this.name = name;
        this.description = description;
        this.gender = gender;
        this.alignment = alignment;
        this.race = race;
        this.jobType = jobType;
        this.spellInventory = spellInventory;
        this.skills = skills;
        this.level = level;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.dexterity = dexterity;
        this.robustness = robustness;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.armor = armor;
        this.statsPoints = statsPoints;
        this.improvements = improvements;
        this.equippedEquipments = equippedEquipments;
        this.inventory = inventory;

    }

    private void setSpellSlots(int level){

        switch (jobType) {
            case SORCERER, DRUID, CLERIC, BARD, WIZARD ->{
                switch (level){
                    case 1      -> spellSlots = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 2      -> spellSlots = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 3      -> spellSlots = new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0};
                    case 4      -> spellSlots = new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0};
                    case 5      -> spellSlots = new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0};
                    case 6      -> spellSlots = new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0};
                    case 7      -> spellSlots = new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0};
                    case 8      -> spellSlots = new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0};
                    case 9      -> spellSlots = new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0};
                    case 10     -> spellSlots = new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0};
                    case 11,12  -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0};
                    case 13,14  -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0};
                    case 15,16  -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0};
                    case 17     -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 1, 1, 1};
                    case 18     -> spellSlots = new int[]{4, 3, 3, 3, 3, 1, 1, 1, 1};
                    case 19     -> spellSlots = new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1};
                    default     -> spellSlots = new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1};
                }
            }
            case PALADIN, RANGER ->{
                switch (level){
                    case 1      -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 2      -> spellSlots = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 3,4    -> spellSlots = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 5,6    -> spellSlots = new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0};
                    case 7,8    -> spellSlots = new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0};
                    case 9,10   -> spellSlots = new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0};
                    case 11,12  -> spellSlots = new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0};
                    case 13,14  -> spellSlots = new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0};
                    case 15,16  -> spellSlots = new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0};
                    case 17,18  -> spellSlots = new int[]{4, 3, 3, 3, 1, 0, 0 ,0, 0};
                    default     -> spellSlots = new int[]{4, 3, 3, 3, 2, 0, 0 ,0, 0};
                }
            }
            case WARLOCK ->{
                switch (level){
                    case 1      -> spellSlots = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 2      -> spellSlots = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 3,4    -> spellSlots = new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0};
                    case 5,6    -> spellSlots = new int[]{0 ,0, 2, 0, 0, 0, 0, 0, 0};
                    case 7,8    -> spellSlots = new int[]{0, 0, 0, 2, 0, 0, 0, 0, 0};
                    case 9,10   -> spellSlots = new int[]{0, 0, 0, 0, 2, 0, 0, 0, 0};
                    case 11,12  -> spellSlots = new int[]{0, 0, 0, 0, 0, 3, 0, 0, 0};
                    case 13,14  -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 3, 0, 0};
                    case 15,16  -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 3, 0};
                    default     -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 4};
                }
            }
            default -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }
    }

    public int getMaxHp() { //TODO: do JOBTYPE enum with values of Dlife to replace value;

        return 100;
        // return (int)((value + getModificator(robustness)
              //  + (level - 1)*((Math.nextUp(((double) value + 1)/2)) + getModificator(robustness))));
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getModificator(int statValue){
        return switch (statValue) {
            case 2, 3 -> -4;
            case 4, 5 -> -3;
            case 6, 7 -> -2;
            case 8, 9 -> -1;
            case 12, 13 -> 1;
            case 14, 15 -> 2;
            case 16, 17 -> 3;
            case 18, 19 -> 4;
            case 20, 21 -> 5;
            default -> 0;
        };
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

    public int getRobustness(){
        return robustness;
    }

    public int getSpeed(){ return race.getSpeed(); }

    public int getArmorBoost(){
        int result = 0;
        for (Equipment equip: equippedEquipments) {
            result += equip.getArmorBonus();
        }
        return result;
    }

    public int getStrengthBoost() {
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getStrengthBoost();
        }

        result += race.getStrengthBoost();

        for (Equipment equip: equippedEquipments) {
            result += equip.getStrengthBoost();

        }
        return result;
    }

    public int getDexterityBoost() {
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getDexterityBoost();
        }

        result += race.getDexterityBoost();

        for (Equipment equip: equippedEquipments) {
            result += equip.getDexterityBoost();

        }
        return result;
    }

    public int getRobustnessBoost() {
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getRobustnessBoost();
        }

        result += race.getRobustnessBoost();

        for (Equipment equip: equippedEquipments) {
            result += equip.getRobustnessBoost();

        }
        return result;
    }

    public int getIntelligenceBoost() {
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getIntelligenceBoost();
        }

        result += race.getIntelligenceBoost();

        for (Equipment equip: equippedEquipments) {
            result += equip.getIntelligenceBoost();

        }
        return result;
    }

    public int getWisdomBoost() {
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getWisdomBoost();
        }

        result += race.getWisdomBoost();

        for (Equipment equip: equippedEquipments) {
            result += equip.getWisdomBoost();

        }
        return result;
    }

    public int getCharismaBoost() {
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getCharismaBoost();
        }

        result += race.getCharismaBoost();

        for (Equipment equip: equippedEquipments) {
            result += equip.getCharismaBoost();

        }
        return result;
    }

    public int getSpeedBoost(){
        int result = 0;

        for (Improvement improve: getImprovements()) {
            result += improve.getSpeedBoost();
        }

        result += race.getSpeed();

        for (Equipment equip: equippedEquipments) {
            result += equip.getSpeedBoost();

        }
        return result;
    }

    public void setArmor(int value){
        armor = value;
    }

    public int getTotalArmor(){
        return getArmor() + getArmorBoost();
    }

    public int getTotalStrength(){
        return getStrength() + getStrengthBoost();
    }

    public int getTotalDexterity(){
        return getDexterity() + getDexterityBoost();
    }

    public int getTotalRobustness(){
        return getRobustness() + getRobustnessBoost();
    }

    public int getTotalIntelligence(){
        return getIntelligence() + getIntelligenceBoost();
    }

    public int getTotalWisdom(){
        return getWisdom() + getWisdomBoost();
    }

    public int getTotalCharisma(){
        return getCharisma() + getCharismaBoost();
    }

    public int getTotalSpeed(){
        return getSpeed() + getSpeedBoost();
    }

    public Race getRaceType() {
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

    public void increaseStrength(){
        if(statsPoints > 0) {
            if (level == 1) {
                if (strength < 15) {
                    strength++;
                    statsPoints--;
                }
            } else {
                strength++;
                statsPoints--;
            }
        }
    }

    public void decreaseStrength(){
        if(level == 1){
            if (strength >8 && strength <= 10){
                strength--;
                statsPoints++;
            }
        }else{
            strength--;
        }
    }

    public void increaseDexterity(){
        if(statsPoints > 0) {
            if (level == 1) {
                if (dexterity < 15) {
                    dexterity++;
                    statsPoints--;
                }
            } else {
                dexterity++;
                statsPoints--;
            }
        }

    }

    public void decreaseDexterity(){
        if(level == 1){
            if (dexterity >8 && dexterity <= 10){
                dexterity--;
                statsPoints++;
            }
        }else{
            dexterity--;
        }
    }

    public void increaseRobustness(){
        if(statsPoints > 0) {
            if (level == 1) {
                if (robustness < 15) {
                    robustness++;
                    statsPoints--;
                }
            } else {
                robustness++;
                statsPoints--;
            }
        }

    }

    public void decreaseRobustness(){
        if(level == 1){
            if (robustness >8 && intelligence <= 10){
                robustness--;
                statsPoints++;
            }
        }else{
            robustness--;
        }
    }

    public void increaseIntelligence(){
        if(statsPoints > 0) {
            if (level == 1) {
                if (intelligence < 15) {
                    intelligence++;
                    statsPoints--;
                }
            } else {
                intelligence++;
                statsPoints--;
            }
        }

    }

    public void decreaseIntelligence(){
        if(level == 1){
            if (intelligence >8 && intelligence <= 10){
                intelligence--;
                statsPoints++;
            }
        }else{
            intelligence--;
        }
    }

    public void increaseWisdom(){
        if(statsPoints > 0) {
            if (level == 1) {
                if (wisdom < 15) {
                    wisdom++;
                    statsPoints--;
                }
            } else {
                wisdom++;
                statsPoints--;
            }
        }

    }

    public void decreaseWisdom(){
        if(level == 1){
            if (wisdom >8 && wisdom <= 10){
                strength--;
                statsPoints++;
            }
        }else{
            wisdom--;
        }
    }

    public void increaseCharisma(){
        if(statsPoints > 0) {
            if (level == 1) {
                if (charisma < 15) {
                    charisma++;
                    statsPoints--;
                }
            } else {
                charisma++;
                statsPoints--;
            }
        }

    }

    public void decreaseCharisma(){
        if(level == 1){
            if (charisma > 8 && charisma <= 10){
                charisma--;
                statsPoints++;
            }
        }else{
            charisma--;
        }
    }

    public void levelUp(){
        level++;
        setSpellSlots(level);
    }

    public void setStatsPoints(int statsPoints) {
        this.statsPoints = statsPoints;
    }

    public void addImprovement(Improvement improvement){
        improvements.add(improvement);
    }

    public void addSpell(Spell spell){
        spellInventory.add(spell);
    }

    public void addJobSkills(JobSkill jobSkill){
        skills.add(jobSkill);
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString(){
        return name;
    }
}
