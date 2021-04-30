package model.job;

import model.items.Item;
import model.items.equipments.Equipment;
import model.race.Alignment;
import model.race.Language;
import model.race.RaceType;
import model.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private final int BASE_STATS = 10;
    private final int ADDITIONNAL_STATS = 12;
    private int armor,
                level = 1,
                strength,
                dexterity,
                intelligence,
                wisdom,
                robustness,
                charisma,
                speed;
    private Gender gender;
    private int statsPoints = ADDITIONNAL_STATS;
    private Alignment alignment;
    private List<Language> languages;
    private final RaceType race;
    private final JobType jobType;
    private final List<Item> inventory;
    private List<Equipment> equippedEquipments = new ArrayList<>();
    private final String name;
    private final String description;
    private int[] spellSlots;
    private final List<Spell> spellInventory;
    private final List<JobSkill> skills;
    private final List<Improvement> improvements;

    public Job(String name, String description,Gender gender, Alignment alignment, RaceType race, JobType jobType, List<Item> inventory,
               int[] spellSlots,List<Improvement> improvements, List<Spell> spellInventory, List<JobSkill> skills) {

        this.race = race;
        this.jobType = jobType;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.alignment = alignment;

        this.armor = BASE_STATS;
        this.strength = BASE_STATS ;
        this.dexterity = BASE_STATS;
        this.intelligence = BASE_STATS;
        this.wisdom = BASE_STATS;
        this.charisma = BASE_STATS;
        this.speed = race.getSpeed();

        this.inventory = inventory;
        this.spellSlots = spellSlots;
        this.spellInventory = spellInventory;
        this.skills = skills;
        this.improvements = improvements;



    }

    public Job(int level, String name, String description, Gender gender, int armor, int strength, int dexterity, int intelligence,
               int wisdom, int charisma, RaceType race, JobType jobType,List<Item> inventory,List<Equipment> equippedEquipments,
               List<Spell> spellInventory, List<Improvement> improvements, List<JobSkill> skills) {

        this.name = name;
        this.description = description;
        this.level = level;
        this.gender = gender;
        this.armor = armor;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.race = race;
        this.jobType = jobType;
        this.inventory = inventory;
        this.equippedEquipments = equippedEquipments;
        this.spellInventory = spellInventory;
        this.improvements = improvements;
        this.skills = skills;


    }

    public int getMaxHp(int value) {
        return (int)((value + getModificator(robustness)
                + (level - 1)*((Math.nextUp(((double) value + 1)/2)) + getModificator(robustness))));
    }

    public int getModificator(int value){
        return switch (value) {
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

    public int getSpeed(){ return speed; }

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

    public void addStrength(){
        if(level == 1 && statsPoints !=0) {
            if (strength < 15) {
                strength++;
                statsPoints--;
            }
        }

    }

    public void removeStrength(){
        if(level == 1){
            if (strength >8 && strength <= 10){
                strength--;
                statsPoints++;
            }
        }else{
            strength--;
        }
    }

    public void addDexterity(){
        if(level == 1 && statsPoints !=0) {
            if (dexterity < 15) {
                dexterity++;
                statsPoints--;
            }
        }

    }

    public void removeDexterity(){
        if(level == 1){
            if (dexterity >8 && dexterity <= 10){
                dexterity--;
                statsPoints++;
            }
        }else{
            dexterity--;
        }
    }

    public void addRobustness(){
        if(level == 1 && statsPoints !=0) {
            if (robustness < 15) {
                robustness++;
                statsPoints--;
            }
        }

    }

    public void removeRobustness(){
        if(level == 1){
            if (robustness >8 && intelligence <= 10){
                robustness--;
                statsPoints++;
            }
        }else{
            robustness--;
        }
    }

    public void addIntelligence(){
        if(level == 1 && statsPoints !=0) {
            if (intelligence < 15) {
                intelligence++;
                statsPoints--;
            }
        }

    }

    public void removeIntelligence(){
        if(level == 1){
            if (intelligence >8 && intelligence <= 10){
                intelligence--;
                statsPoints++;
            }
        }else{
            intelligence--;
        }
    }

    public void addWisdom(){
        if(level == 1 && statsPoints !=0) {
            if (wisdom < 15) {
                wisdom++;
                statsPoints--;
            }
        }

    }

    public void removeWisdom(){
        if(level == 1){
            if (wisdom >8 && wisdom <= 10){
                strength--;
                statsPoints++;
            }
        }else{
            wisdom--;
        }
    }

    public void addCharisma(){
        if(level == 1 && statsPoints !=0) {
            if (charisma < 15) {
                charisma++;
                statsPoints--;
            }
        }

    }

    public void removeCharisma(){
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
    }

    public void addImprovement(Improvement improvement){
        improvements.add(improvement);
    }

    public void addSpell(Spell spell){
        spellInventory.add(spell);
    }

    public Gender getGender() {
        return gender;
    }
}
