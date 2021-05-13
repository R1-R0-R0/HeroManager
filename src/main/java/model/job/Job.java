package model.job;

import model.items.Item;
import model.items.equipments.Equipment;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition of a character
 */
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
                healthPoints,
                speed;
    private Gender gender;
    private int additionalStatPoints;
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
        additionalStatPoints = ADDITIONAL_STATS + race.getBonusStats();
        speed = race.getSpeed();

        setSpellSlots(1);
    }

    public Job(String name, String description, Gender gender, Alignment alignment, Race race, JobType jobType, List<Spell> spellInventory, List<JobSkill> skills,
               int level, int strength, int dexterity, int intelligence, int robustness, int wisdom, int charisma,int speed,int healthPoints, int armor,
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
        this.speed = speed;
        this.strength = strength;
        this.dexterity = dexterity;
        this.robustness = robustness;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.armor = armor;
        this.additionalStatPoints = statsPoints;
        this.improvements = improvements;
        this.equippedEquipments = equippedEquipments;
        this.inventory = inventory;

    }

    /**
     * allow to define the number of spell you can use for each level and each class
     * @param level
     */
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

    /**
     *
     * @return the maximum health points of a character
     */
    public int getMaxHp() { //TODO: do JOBTYPE enum with values of Dlife to replace value;

        return 100;
        // return (int)((value + getModificator(robustness)
              //  + (level - 1)*((Math.nextUp(((double) value + 1)/2)) + getModificator(robustness))));
    }

    /**
     *
     * @return current health points of a character
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * allow you to set the amount of health points of a character
     * @param healthPoints
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     *
     * @param statValue
     * @return a value proportional to the parameter
     */
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

    /**
     *
     * @return amount of base armor of the character
     */
    public int getArmor() {
        return armor;
    }

    /**
     *
     * @return level of the character
     */
    public int getLevel() {
        return level;
    }

    /**
     * Value used in addition to the level (for dice rolls as an example)
     * @return value of proficiency level
     */
    public int getProficiencyLevel() {
        double proficiency = 1 + (double)(level/4);
        return (int) Math.ceil(proficiency);

    }

    /**
     * @return base strength of the character
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return base dexterity of the character
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * @return base Intelligence of the character
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     *
     * @return base wisdom of the character
     */
    public int getWisdom() {
        return wisdom;
    }

    /**
     *
     * @return base charisma of the character
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * @return base robustness of the character
     */
    public int getRobustness(){
        return robustness;
    }

    /**
     *
     * @return base speed of the character
     */
    public int getSpeed(){ return speed; }

    /**
     *
     * @return armorBoost coming from equipments
     */
    public int getArmorBoost(){
        int result = 0;
        for (Equipment equip: equippedEquipments) {
            result += equip.getArmorBonus();
        }
        return result;
    }

    /**
     *
     * @return strength boost coming from equipments and improvements
     */
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
    /**
     *
     * @return dexterity boost coming from equipments and improvements
     */
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
    /**
     *
     * @return robustness boost coming from equipments and improvements
     */
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
    /**
     *
     * @return intelligence boost coming from equipments and improvements
     */
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
    /**
     *
     * @return wisdom boost coming from equipments and improvements
     */
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
    /**
     *
     * @return charisma boost coming from equipments and improvements
     */
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
    /**
     *
     * @return speed boost coming from equipments and improvements
     */
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

    /**
     * set the armor value
     * @param value
     */
    public void setArmor(int value){
        armor = value;
    }

    /**
     * Calculate the total amount of armor
     * @return base armor + armor boost
     */
    public int getTotalArmor(){
        return getArmor() + getArmorBoost();
    }

    /**
     * Calculate the total amount of strength
     * @return base strength + armor strength
     */
    public int getTotalStrength(){
        return getStrength() + getStrengthBoost();
    }
    /**
     * Calculate the total amount of dexterity
     * @return base dexterity + armor dexterity
     */
    public int getTotalDexterity(){
        return getDexterity() + getDexterityBoost();
    }

    /**
     * Calculate the total amount of robustness
     * @return base robustness + armor robustness
     */
    public int getTotalRobustness(){
        return getRobustness() + getRobustnessBoost();
    }

    /**
     * Calculate the total amount of intelligence
     * @return base intelligence + armor intelligence
     */
    public int getTotalIntelligence(){
        return getIntelligence() + getIntelligenceBoost();
    }
    /**
     * Calculate the total amount of wisdom
     * @return base wisdom + armor wisdom
     */
    public int getTotalWisdom(){
        return getWisdom() + getWisdomBoost();
    }

    /**
     * Calculate the total amount of charisma
     * @return base charisma + armor charisma
     */
    public int getTotalCharisma(){
        return getCharisma() + getCharismaBoost();
    }

    /**
     * Calculate the total amount of speed
     * @return base speed + armor speed
     */
    public int getTotalSpeed(){
        return getSpeed() + getSpeedBoost();
    }

    /**
     * @return race of the character
     */
    public Race getRaceType() {
        return race;
    }

    /**
     * @return jobtype of the character
     */
    public JobType getJobType() {
        return jobType;
    }

    /**
     * @return the inventory of the character
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the character
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return spell slots of the character
     */
    public int[] getSpellSlots() {
        return spellSlots;
    }

    /**
     *
     * @return list of spells of the character
     */
    public List<Spell> getSpellInventory() {
        return spellInventory;
    }

    /**
     *
     * @return list of improvements of the character
     */
    public List<Improvement> getImprovements() {
        return improvements;
    }

    /**
     *
     * @return list of jobskills of the character
     */
    public List<JobSkill> getSkills() {
        return skills;
    }

    /**
     * allow to increase base Strength can't be superior from 15 at character creation
     */
    public void increaseStrength(){
        if(additionalStatPoints > 0) {
            if (level == 1) {
                if (strength < 15) {
                    strength++;
                    additionalStatPoints--;
                }
            } else {
                strength++;
                additionalStatPoints--;
            }
        }
    }
    /**
     * allow to decrease base Strength can't be inferior from 8 at character creation
     */
    public void decreaseStrength(){
        if(level == 1){
            if (strength >8 && strength <= 10){
                strength--;
                additionalStatPoints++;
            }
        }else{
            strength--;
        }
    }
    /**
     * allow to increase base dexterity can't be superior from 15 at character creation
     */
    public void increaseDexterity(){
        if(additionalStatPoints > 0) {
            if (level == 1) {
                if (dexterity < 15) {
                    dexterity++;
                    additionalStatPoints--;
                }
            } else {
                dexterity++;
                additionalStatPoints--;
            }
        }

    }
    /**
     * allow to decrease base dexterity can't be inferior from 8 at character creation
     */
    public void decreaseDexterity(){
        if(level == 1){
            if (dexterity >8 && dexterity <= 10){
                dexterity--;
                additionalStatPoints++;
            }
        }else{
            dexterity--;
        }
    }

    /**
     * allow to increase base Robustness can't be superior from 15 at character creation
     */
    public void increaseRobustness(){
        if(additionalStatPoints > 0) {
            if (level == 1) {
                if (robustness < 15) {
                    robustness++;
                    additionalStatPoints--;
                }
            } else {
                robustness++;
                additionalStatPoints--;
            }
        }

    }
    /**
     * allow to decrease base robustness can't be inferior from 8 at character creation
     */
    public void decreaseRobustness(){
        if(level == 1){
            if (robustness >8 && intelligence <= 10){
                robustness--;
                additionalStatPoints++;
            }
        }else{
            robustness--;
        }
    }
    /**
     * allow to increase base intelligence can't be superior from 15 at character creation
     */
    public void increaseIntelligence(){
        if(additionalStatPoints > 0) {
            if (level == 1) {
                if (intelligence < 15) {
                    intelligence++;
                    additionalStatPoints--;
                }
            } else {
                intelligence++;
                additionalStatPoints--;
            }
        }

    }
    /**
     * allow to decrease base intelligence can't be inferior from 8 at character creation
     */
    public void decreaseIntelligence(){
        if(level == 1){
            if (intelligence >8 && intelligence <= 10){
                intelligence--;
                additionalStatPoints++;
            }
        }else{
            intelligence--;
        }
    }
    /**
     * allow to increase base wisdom can't be superior from 15 at character creation
     */
    public void increaseWisdom(){
        if(additionalStatPoints > 0) {
            if (level == 1) {
                if (wisdom < 15) {
                    wisdom++;
                    additionalStatPoints--;
                }
            } else {
                wisdom++;
                additionalStatPoints--;
            }
        }

    }
    /**
     * allow to decrease base wisdom can't be inferior from 8 at character creation
     */
    public void decreaseWisdom(){
        if(level == 1){
            if (wisdom >8 && wisdom <= 10){
                strength--;
                additionalStatPoints++;
            }
        }else{
            wisdom--;
        }
    }
    /**
     * allow to increase base charisma can't be superior from 15 at character creation
     */
    public void increaseCharisma(){
        if(additionalStatPoints > 0) {
            if (level == 1) {
                if (charisma < 15) {
                    charisma++;
                    additionalStatPoints--;
                }
            } else {
                charisma++;
                additionalStatPoints--;
            }
        }

    }
    /**
     * allow to decrease base charisma can't be inferior from 8 at character creation
     */
    public void decreaseCharisma(){
        if(level == 1){
            if (charisma > 8 && charisma <= 10){
                charisma--;
                additionalStatPoints++;
            }
        }else{
            charisma--;
        }
    }

    /**
     * Increase the value of level by 1 and update the spell slots of the character
     */
    public void levelUp(){
        level++;
        setSpellSlots(level);
    }

    /**
     *
     * @return the amount of statistics points
     */
    public int getAdditionalStatPoints() {
        return additionalStatPoints;
    }

    /**
     * set the statistics point to parameter value
     * @param additionalStatPoints
     */
    public void setAdditionalStatPoints(int additionalStatPoints) {
        if(additionalStatPoints < 0)
            return;
        this.additionalStatPoints = additionalStatPoints;
    }

    /**
     * add an improvement to the list of improvement of the character
     * @param improvement
     */
    public void addImprovement(Improvement improvement){
        improvements.add(improvement);
    }

    /**
     * add a spell in the spellList of the character
     * @param spell
     */
    public void addSpell(Spell spell){
        spellInventory.add(spell);
    }

    /**
     * add a jobSkill in skills of the character
     * @param jobSkill
     */
    public void addJobSkills(JobSkill jobSkill){
        skills.add(jobSkill);
    }

    /**
     *
     * @return the gender of the character
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @return name of the Character
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     *
     * @return alignement of the Character
     */
    public Alignment getAlignment(){return alignment;}

    /**
     *
     * @return List of equiped equipments
     */
    public List<Equipment> getEquippedEquipments(){return equippedEquipments;}
}
