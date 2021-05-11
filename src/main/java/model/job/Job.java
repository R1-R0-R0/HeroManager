package model.job;

import model.items.Item;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentInventory;
import model.items.equipments.EquipmentPart;
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
    private final Gender gender;
    private int additionalStatPoints;
    private final Alignment alignment;
    private final Race race;
    private final JobType jobType;
    private List<Item> inventory;
    private EquipmentInventory equippedEquipments;
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
        this.improvements.addAll(jobType.getImprovements());

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
        healthPoints = getMaxHp();
        additionalStatPoints = ADDITIONAL_STATS + race.getBonusStats();
        speed = race.getSpeed();

        setSpellSlots(1);
    }

    public Job(String name, String description, Gender gender, Alignment alignment, Race race, JobType jobType, List<Spell> spellInventory, List<JobSkill> skills,
               int level, int strength, int dexterity, int intelligence, int robustness, int wisdom, int charisma, int speed, int healthPoints, int armor,
               int statsPoints, List<Improvement> improvements, EquipmentInventory equippedEquipments, List<Item> inventory) {

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
     *
     * @param level
     */
    private void setSpellSlots(int level) {

        switch (jobType) {
            case SORCERER, DRUID, CLERIC, BARD, WIZARD -> {
                switch (level) {
                    case 1 -> spellSlots = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 2 -> spellSlots = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 3 -> spellSlots = new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0};
                    case 4 -> spellSlots = new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0};
                    case 5 -> spellSlots = new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0};
                    case 6 -> spellSlots = new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0};
                    case 7 -> spellSlots = new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0};
                    case 8 -> spellSlots = new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0};
                    case 9 -> spellSlots = new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0};
                    case 10 -> spellSlots = new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0};
                    case 11, 12 -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 0, 0, 0};
                    case 13, 14 -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 1, 0, 0};
                    case 15, 16 -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 1, 1, 0};
                    case 17 -> spellSlots = new int[]{4, 3, 3, 3, 2, 1, 1, 1, 1};
                    case 18 -> spellSlots = new int[]{4, 3, 3, 3, 3, 1, 1, 1, 1};
                    case 19 -> spellSlots = new int[]{4, 3, 3, 3, 3, 2, 1, 1, 1};
                    default -> spellSlots = new int[]{4, 3, 3, 3, 3, 2, 2, 1, 1};
                }
            }
            case PALADIN, RANGER -> {
                switch (level) {
                    case 1 -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 2 -> spellSlots = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 3, 4 -> spellSlots = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 5, 6 -> spellSlots = new int[]{4, 2, 0, 0, 0, 0, 0, 0, 0};
                    case 7, 8 -> spellSlots = new int[]{4, 3, 0, 0, 0, 0, 0, 0, 0};
                    case 9, 10 -> spellSlots = new int[]{4, 3, 2, 0, 0, 0, 0, 0, 0};
                    case 11, 12 -> spellSlots = new int[]{4, 3, 3, 0, 0, 0, 0, 0, 0};
                    case 13, 14 -> spellSlots = new int[]{4, 3, 3, 1, 0, 0, 0, 0, 0};
                    case 15, 16 -> spellSlots = new int[]{4, 3, 3, 2, 0, 0, 0, 0, 0};
                    case 17, 18 -> spellSlots = new int[]{4, 3, 3, 3, 1, 0, 0, 0, 0};
                    default -> spellSlots = new int[]{4, 3, 3, 3, 2, 0, 0, 0, 0};
                }
            }
            case WARLOCK -> {
                switch (level) {
                    case 1 -> spellSlots = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 2 -> spellSlots = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
                    case 3, 4 -> spellSlots = new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0};
                    case 5, 6 -> spellSlots = new int[]{0, 0, 2, 0, 0, 0, 0, 0, 0};
                    case 7, 8 -> spellSlots = new int[]{0, 0, 0, 2, 0, 0, 0, 0, 0};
                    case 9, 10 -> spellSlots = new int[]{0, 0, 0, 0, 2, 0, 0, 0, 0};
                    case 11, 12 -> spellSlots = new int[]{0, 0, 0, 0, 0, 3, 0, 0, 0};
                    case 13, 14 -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 3, 0, 0};
                    case 15, 16 -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 3, 0};
                    default -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 4};
                }
            }
            default -> spellSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }
    }

    /**
     * @return the maximum health points of a character
     */
    public int getMaxHp() {
        return (int) ((jobType.getDLife() + getModificator(robustness)
                + (level - 1) * ((Math.ceil(((double) jobType.getDLife() + 1) / 2)) + getModificator(robustness))));
    }

    /**
     * @return current health points of a character
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * allow you to set the amount of health points of a character can't be superior than max HealthPoint
     * @param healthPoints
     */
    public void setHealthPoints(int healthPoints) {
        int max = getMaxHp();
        if(healthPoints < max) {
            this.healthPoints = healthPoints;
        }else{
            this.healthPoints = max;
        }

    }

    /**
     * @param statValue
     * @return a value proportional to the parameter
     */
    public int getModificator(int statValue) {
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
     * @return amount of base armor of the character
     */
    public int getArmor() {
        return armor;
    }

    /**
     * @return level of the character
     */
    public int getLevel() {
        return level;
    }

    /**
     * Value used in addition to the level (for dice rolls as an example)
     *
     * @return value of proficiency level
     */
    public int getProficiencyLevel() {

        double proficiency = 1 + ((double)level/4);
        System.out.println(proficiency);
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
     * @return base wisdom of the character
     */
    public int getWisdom() {
        return wisdom;
    }

    /**
     * @return base charisma of the character
     */
    public int getCharisma() {
        return charisma;
    }

    /**
     * @return base robustness of the character
     */
    public int getRobustness() {
        return robustness;
    }

    /**
     * @return base speed of the character
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * allow use to set the speed of the character
     * @param speed
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }

    /**
     * @return armorBoost coming from equipments
     */
    public int getArmorBoost() {
        int result = 0;
        for (Equipment equip : equippedEquipments.getEquippedList()) {
            result += equip.getArmorBonus();
        }
        return result;
    }

    /**
     * @return strength boost coming from equipments and improvements
     */
    public int getStrengthBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getStrengthBoost();
        }

        result += race.getStrengthBoost();

        if(equippedEquipments.getEquippedList() != null){
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip != null)
                    result += equip.getStrengthBoost();

            }
        }
        return result;

    }

    /**
     * @return dexterity boost coming from equipments and improvements
     */
    public int getDexterityBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getDexterityBoost();
        }

        result += race.getDexterityBoost();

        if(equippedEquipments.getEquippedList() != null){
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip !=null)
                    result += equip.getDexterityBoost();
            }
        }
        return result;
    }

    /**
     * @return robustness boost coming from equipments and improvements
     */
    public int getRobustnessBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getRobustnessBoost();
        }

        result += race.getRobustnessBoost();

        if(equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip!=null)
                    result += equip.getRobustnessBoost();

            }
        }
        return result;
    }

    /**
     * @return intelligence boost coming from equipments and improvements
     */
    public int getIntelligenceBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getIntelligenceBoost();
        }

        result += race.getIntelligenceBoost();

        if(equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip !=null)
                    result += equip.getIntelligenceBoost();

            }
        }
        return result;
    }

    /**
     * @return wisdom boost coming from equipments and improvements
     */
    public int getWisdomBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getWisdomBoost();
        }

        result += race.getWisdomBoost();
        if(equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip !=null)
                    result += equip.getWisdomBoost();

            }
        }
        return result;
    }

    /**
     * @return charisma boost coming from equipments and improvements
     */
    public int getCharismaBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getCharismaBoost();
        }

        result += race.getCharismaBoost();
        if(equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip != null)
                    result += equip.getCharismaBoost();

            }
        }
        return result;
    }

    /**
     * @return speed boost coming from equipments and improvements
     */
    public int getSpeedBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getSpeedBoost();
        }

        if(equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if(equip != null)
                    result += equip.getSpeedBoost();

            }
        }
        return result;
    }

    /**
     * set the armor value
     *
     * @param value
     */
    public void setArmor(int value) {
        armor = value;
    }

    /**
     * Calculate the total amount of armor
     *
     * @return base armor + armor boost
     */
    public int getTotalArmor() {
        return getArmor() + getArmorBoost();
    }

    /**
     * Calculate the total amount of strength
     *
     * @return base strength + armor strength
     */
    public int getTotalStrength() {
        return getStrength() + getStrengthBoost();
    }

    /**
     * Calculate the total amount of dexterity
     *
     * @return base dexterity + armor dexterity
     */
    public int getTotalDexterity() {
        return getDexterity() + getDexterityBoost();
    }

    /**
     * Calculate the total amount of robustness
     *
     * @return base robustness + armor robustness
     */
    public int getTotalRobustness() {
        return getRobustness() + getRobustnessBoost();
    }

    /**
     * Calculate the total amount of intelligence
     *
     * @return base intelligence + armor intelligence
     */
    public int getTotalIntelligence() {
        return getIntelligence() + getIntelligenceBoost();
    }

    /**
     * Calculate the total amount of wisdom
     *
     * @return base wisdom + armor wisdom
     */
    public int getTotalWisdom() {
        return getWisdom() + getWisdomBoost();
    }

    /**
     * Calculate the total amount of charisma
     *
     * @return base charisma + armor charisma
     */
    public int getTotalCharisma() {
        return getCharisma() + getCharismaBoost();
    }

    /**
     * Calculate the total amount of speed
     *
     * @return base speed + armor speed
     */
    public int getTotalSpeed() {
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

    public void removeFromInventory(Equipment equipment){
        if(equipment == null){
            return;
        }
        List<Item> newInventory = new ArrayList<>();
        for (Item item: inventory) {
            if(item != null && !item.getName().equals(equipment.getName())){
                newInventory.add(item);
            }

        }
        inventory = newInventory;
    }

    /**
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of the character
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return spell slots of the character
     */
    public int[] getSpellSlots() {
        return spellSlots;
    }

    /**
     * @return list of spells of the character
     */
    public List<Spell> getSpellInventory() {
        return spellInventory;
    }

    /**
     * @return list of improvements of the character
     */
    public List<Improvement> getImprovements() {
        return improvements;
    }

    /**
     * @return list of jobskills of the character
     */
    public List<JobSkill> getSkills() {
        return skills;
    }

    /**
     *
     * @return the list of equipped equipments
     */
    public EquipmentInventory getEquippedEquipments() {
        return equippedEquipments;
    }

    /**
     * remove an equipped equipment from the EquipmentInventory
     * @param equipment
     */
    public void removeEquippedEquipment(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        switch (equipment.getEquipmentPart()){
            case BELT -> equippedEquipments.removeBelt();
            case BODY -> equippedEquipments.removeBody();
            case FEET -> equippedEquipments.removeFeet();
            case HEAD -> equippedEquipments.removeHead();
            case LEGS -> equippedEquipments.removeLegs();
            case HANDS -> equippedEquipments.removeHands();
            case AMULET -> equippedEquipments.removeAmulet();
            case MANTLE -> equippedEquipments.removeMantle();
            default -> removeLeftRing();
        }

        inventory.add(equipment);
    }

    /**
     * remove Left ring equipment
     */
    public void removeLeftRing(){
        Equipment ring = equippedEquipments.getLeftRing();
        equippedEquipments.removeLeftRing();
        if(!inventory.contains(ring))
            inventory.add(ring);
    }
    /**
     * remove Right ring equipment
     */
    public void removeRightRing(){
        Equipment ring = equippedEquipments.getRightRing();
        equippedEquipments.removeRightRing();
        inventory.add(ring);
    }

    /**
     * add new equipped equipment if there's no equipped equipment of same EquipmentPart yet and remove it from inventory
     * @param equipment
     */
    public void addEquippedEquipment(Equipment equipment){
        if(equipment == null){
            return;
        }
        switch (equipment.getEquipmentPart()){
            case BELT -> equippedEquipments.addBelt(equipment);
            case BODY -> equippedEquipments.addBody(equipment);
            case FEET -> equippedEquipments.addFeet(equipment);
            case HEAD -> equippedEquipments.addHead(equipment);
            case LEGS -> equippedEquipments.addLegs(equipment);
            case HANDS -> equippedEquipments.addHands(equipment);
            case AMULET -> equippedEquipments.addAmulet(equipment);
            case MANTLE -> equippedEquipments.addMantle(equipment);
            default -> addLeftRing(equipment);
        }
        if(equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
    }

    /**
     * add new LeftRing if there's no leftRing already equipped and remove it from inventory
     * @param equipment
     */
    public void addLeftRing(Equipment equipment){
        if(equipment == null){
            return;
        }
        equippedEquipments.addLeftRing(equipment);
        removeFromInventory(equipment);
    }

    /**
     * add new RightRing if there's not RightRing already equipped and remove it from inventory
     * @param equipment
     */
    public void addRightRing(Equipment equipment){
        if(equipment == null){
            return;
        }
        equippedEquipments.addRightRing(equipment);
        removeFromInventory(equipment);
    }

    /**
     * replace equippedEquipment by the param equipment
     * @param equipment
     */
    public void replaceEquippedEquipment(Equipment equipment){
        Equipment equipped;
        if(equipment == null){
            return;
        }
        switch (equipment.getEquipmentPart()){
            case BELT -> {
                equipped = equippedEquipments.getBelt();
                equippedEquipments.removeBelt();
                equippedEquipments.addBelt(equipment);
            }
            case BODY -> {
                equipped = equippedEquipments.getBody();
                equippedEquipments.removeBody();
                equippedEquipments.addBody(equipment);
            }
            case FEET -> {
                equipped = equippedEquipments.getFeet();
                equippedEquipments.removeFeet();
                equippedEquipments.addFeet(equipment);
            }
            case HEAD -> {
                equipped = equippedEquipments.getHead();
                equippedEquipments.removeHead();
                equippedEquipments.addHead(equipment);
            }
            case LEGS -> {
                equipped = equippedEquipments.getLegs();
                equippedEquipments.removeLegs();
                equippedEquipments.addLegs(equipment);
            }
            case HANDS -> {
                equipped = equippedEquipments.getHands();
                equippedEquipments.removeHands();
                equippedEquipments.addHands(equipment);
            }
            case AMULET -> {
                equipped = equippedEquipments.getAmulet();
                equippedEquipments.removeAmulet();
                equippedEquipments.addAmulet(equipment);
            }
            case MANTLE -> {
                equipped = equippedEquipments.getMantle();
                equippedEquipments.removeMantle();
                equippedEquipments.addMantle(equipment);
            }
            default -> {
                equipped = equippedEquipments.getLeftRing();
                replaceLeftRing(equipment);
            }

        }
        if(equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
        inventory.add(equipped);
    }

    /**
     * Replace equipped Left ring by param
     * @param equipment
     */
    public void replaceLeftRing(Equipment equipment){
        if(equipment.getEquipmentPart() != EquipmentPart.RING)
            return;

        Equipment equipped = equippedEquipments.getLeftRing();
        removeLeftRing();
        addLeftRing(equipment);

        if(equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
        if(!inventory.contains(equipped)) {
            inventory.add(equipped);
        }
    }

    /**
     * Replace equipped right ring by param
     * @param equipment
     */
    public void replaceRightRing(Equipment equipment){

        if(equipment.getEquipmentPart() != EquipmentPart.RING)
            return;

        Equipment equipped = equippedEquipments.getRightRing();
        removeRightRing();
        addRightRing(equipment);

        if(equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
        inventory.add(equipped);
    }


    /**
     * allow to increase base Strength can't be superior from 15 at character creation
     */
    public void increaseStrength() {
        if (additionalStatPoints > 0) {
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
    public void decreaseStrength() {
        if (level == 1) {
            if (strength > 8 && strength <= 10) {
                strength--;
                additionalStatPoints++;
            }
        } else {
            strength--;
        }
    }

    /**
     * allow to increase base dexterity can't be superior from 15 at character creation
     */
    public void increaseDexterity() {
        if (additionalStatPoints > 0) {
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
    public void decreaseDexterity() {
        if (level == 1) {
            if (dexterity > 8 && dexterity <= 10) {
                dexterity--;
                additionalStatPoints++;
            }
        } else {
            dexterity--;
        }
    }

    /**
     * allow to increase base Robustness can't be superior from 15 at character creation
     */
    public void increaseRobustness() {
        if (additionalStatPoints > 0) {
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
    public void decreaseRobustness() {
        if (level == 1) {
            if (robustness > 8 && intelligence <= 10) {
                robustness--;
                additionalStatPoints++;
            }
        } else {
            robustness--;
        }
    }

    /**
     * allow to increase base intelligence can't be superior from 15 at character creation
     */
    public void increaseIntelligence() {
        if (additionalStatPoints > 0) {
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
    public void decreaseIntelligence() {
        if (level == 1) {
            if (intelligence > 8 && intelligence <= 10) {
                intelligence--;
                additionalStatPoints++;
            }
        } else {
            intelligence--;
        }
    }

    /**
     * allow to increase base wisdom can't be superior from 15 at character creation
     */
    public void increaseWisdom() {
        if (additionalStatPoints > 0) {
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
    public void decreaseWisdom() {
        if (level == 1) {
            if (wisdom > 8 && wisdom <= 10) {
                strength--;
                additionalStatPoints++;
            }
        } else {
            wisdom--;
        }
    }

    /**
     * allow to increase base charisma can't be superior from 15 at character creation
     */
    public void increaseCharisma() {
        if (additionalStatPoints > 0) {
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
    public void decreaseCharisma() {
        if (level == 1) {
            if (charisma > 8 && charisma <= 10) {
                charisma--;
                additionalStatPoints++;
            }
        } else {
            charisma--;
        }
    }

    /**
     * Increase the value of level by 1 and update the spell slots of the character
     */
    public void levelUp() {
        level++;
        setSpellSlots(level);
    }

    /**
     *
     * @return the Alignment of the character
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     *
     * @return the Race of the character
     */
    public Race getRace() {
        return race;
    }

    /**
     * @return the amount of statistics points
     */
    public int getAdditionalStatPoints() {
        return additionalStatPoints;
    }

    /**
     * set the statistics point to parameter value
     *
     * @param additionalStatPoints
     */
    public void setAdditionalStatPoints(int additionalStatPoints) {
        if (additionalStatPoints < 0)
            return;
        this.additionalStatPoints = additionalStatPoints;
    }

    /**
     * add an improvement to the list of improvement of the character
     *
     * @param improvement
     */
    public void addImprovement(Improvement improvement) {
        improvements.add(improvement);
    }

    /**
     * add a spell in the spellList of the character
     *
     * @param spell
     */
    public void addSpell(Spell spell) {
        spellInventory.add(spell);
    }

    /**
     * add a jobSkill in skills of the character
     *
     * @param jobSkill
     */
    public void addJobSkills(JobSkill jobSkill) {
        skills.add(jobSkill);
    }

    /**
     * @return the gender of the character
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @return name of the Character
     */
    @Override
    public String toString() {
        return name;
    }
}
