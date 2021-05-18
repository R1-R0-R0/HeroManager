package model.job;

import exceptions.UnsupportedItemException;
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
    private final Gender gender;
    private final Alignment alignment;
    private final Race race;
    private final JobType jobType;
    private final String name;
    private final String description;
    private final List<Spell> spellInventory;
    private final List<JobSkill> skills;
    private final List<Improvement> improvements;
    private int level,
            strength,
            dexterity,
            intelligence,
            wisdom,
            robustness,
            charisma,
            armor,
            healthPoints,
            speed,
            additionalStatPoints;
    private List<Item> inventory;
    private EquipmentInventory equippedEquipments;
    private int[] spellSlots;

    public Job(String name, String description, Gender gender, Alignment alignment, Race race, JobType jobType) {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.alignment = alignment;
        this.race = race;
        this.jobType = jobType;
        this.improvements = race.getImprovements();
        this.improvements.addAll(jobType.getImprovements());
        this.equippedEquipments = new EquipmentInventory(null, null, null, null, null, null, null, null, null, null);

        inventory = new ArrayList<>();
        spellInventory = new ArrayList<>();
        skills = new ArrayList<>();
        equippedEquipments = new EquipmentInventory(null, null, null, null, null, null, null, null, null, null);

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
     * give maximum health points
     *
     * @return the maximum health points of a character
     */
    public int getMaxHp() {
        return (int) ((jobType.getDLife() + getModificator(robustness)
                + (level - 1) * ((Math.ceil(((double) jobType.getDLife() + 1) / 2)) + getModificator(robustness))));
    }

    /**
     * give current health points
     *
     * @return current health points of a character
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * allow you to set the amount of health points of a character can't be superior than max HealthPoint
     *
     * @param healthPoints health points to set
     */
    public void setHealthPoints(int healthPoints) {
        int max = getMaxHp();
        this.healthPoints = Math.min(healthPoints, max);
    }

    /**
     * give a value proportional to the parameter statValue
     *
     * @param statValue given value
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
     * give base armor of the character
     *
     * @return amount of base armor of the character
     */
    public int getArmor() {
        return armor;
    }

    /**
     * set the armor value
     *
     * @param value armor points to set
     */
    public void setArmor(int value) {
        armor = value;
    }

    /**
     * give level of the character
     *
     * @return level of the character
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set of setter for job no final attribut
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Value used in addition to the level (for dice rolls as an example)
     *
     * @return value of proficiency level
     */
    public int getProficiencyLevel() {

        double proficiency = 1 + ((double) level / 4);
        System.out.println(proficiency);
        return (int) Math.ceil(proficiency);

    }

    /**
     * give base strength
     *
     * @return base strength of the character
     */
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * give base dexterity
     *
     * @return base dexterity of the character
     */
    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * give base intelligence
     *
     * @return base Intelligence of the character
     */
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * give base wisdom
     *
     * @return base wisdom of the character
     */
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    /**
     * give base charisma
     *
     * @return base charisma of the character
     */
    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    /**
     * give base robustness
     *
     * @return base robustness of the character
     */
    public int getRobustness() {
        return robustness;
    }

    public void setRobustness(int robustness) {
        this.robustness = robustness;
    }

    /**
     * give base speed
     *
     * @return base speed of the character
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * allow use to set the speed of the character
     *
     * @param speed speed value to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * get armorBoost coming from equipments
     *
     * @return armorBoost coming from equipments
     */
    public int getArmorBoost() {
        int result = 0;
        for (Equipment equip : equippedEquipments.getEquippedList()) {
            if (equip != null)
                result += equip.getArmorBonus();
        }
        return result;
    }

    /**
     * get strength boost coming from equipments and improvements
     *
     * @return strength boost coming from equipments and improvements
     */
    public int getStrengthBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getStrengthBoost();
        }

        result += race.getStrengthBoost();

        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getStrengthBoost();

            }
        }
        return result;

    }

    /**
     * get dexterity boost coming from equipments and improvements
     *
     * @return dexterity boost coming from equipments and improvements
     */
    public int getDexterityBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getDexterityBoost();
        }

        result += race.getDexterityBoost();

        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getDexterityBoost();
            }
        }
        return result;
    }

    /**
     * get robustness boost coming from equipments and improvements
     *
     * @return robustness boost coming from equipments and improvements
     */
    public int getRobustnessBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getRobustnessBoost();
        }

        result += race.getRobustnessBoost();

        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getRobustnessBoost();

            }
        }
        return result;
    }

    /**
     * get intelligence boost coming from equipments and improvements
     *
     * @return intelligence boost coming from equipments and improvements
     */
    public int getIntelligenceBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getIntelligenceBoost();
        }

        result += race.getIntelligenceBoost();

        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getIntelligenceBoost();

            }
        }
        return result;
    }

    /**
     * get wisdom boost coming from equipments and improvements
     *
     * @return wisdom boost coming from equipments and improvements
     */
    public int getWisdomBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getWisdomBoost();
        }

        result += race.getWisdomBoost();
        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getWisdomBoost();

            }
        }
        return result;
    }

    /**
     * get charisma boost coming from equipments and improvements
     *
     * @return charisma boost coming from equipments and improvements
     */
    public int getCharismaBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getCharismaBoost();
        }

        result += race.getCharismaBoost();
        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getCharismaBoost();

            }
        }
        return result;
    }

    /**
     * get Speed boosts from equipments and improvements
     *
     * @return speed boost coming from equipments and improvements
     */
    public int getSpeedBoost() {
        int result = 0;

        for (Improvement improve : getImprovements()) {
            result += improve.getSpeedBoost();
        }

        if (equippedEquipments.getEquippedList() != null) {
            for (Equipment equip : equippedEquipments.getEquippedList()) {
                if (equip != null)
                    result += equip.getSpeedBoost();

            }
        }
        return result;
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
     * get race of the character
     *
     * @return race of the character
     */
    public Race getRaceType() {
        return race;
    }

    /**
     * get the jobtype of the character
     *
     * @return jobtype of the character
     */
    public JobType getJobType() {
        return jobType;
    }

    /**
     * get the inventory of the character
     *
     * @return the inventory of the character
     */
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void removeFromInventory(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        List<Item> newInventory = new ArrayList<>();
        for (Item item : inventory) {
            if (item != null && !item.getName().equals(equipment.getName())) {
                newInventory.add(item);
            }

        }
        inventory = newInventory;
    }

    /**
     * get name of the character
     *
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * get description of the character
     *
     * @return description of the character
     */
    public String getDescription() {
        return description;
    }

    /**
     * get spell slots of the character
     *
     * @return spell slots of the character
     */
    public int[] getSpellSlots() {
        return spellSlots;
    }

    /**
     * allow to define the number of spell you can use for each level and each class
     *
     * @param level targeted level
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

    public void setSpellSlots(int[] spellSlots) {
        this.spellSlots = spellSlots;
    }

    /**
     * get list of spells of the character
     *
     * @return list of spells of the character
     */
    public List<Spell> getSpellInventory() {
        return spellInventory;
    }

    /**
     * get list of improvements of the character
     *
     * @return list of improvements of the character
     */
    public List<Improvement> getImprovements() {
        return improvements;
    }

    /**
     * get list of jobskills of the character
     *
     * @return list of jobskills of the character
     */
    public List<JobSkill> getSkills() {
        return skills;
    }

    /**
     * get the list of equipped equipments
     *
     * @return the list of equipped equipments
     */
    public EquipmentInventory getEquippedEquipments() {
        return equippedEquipments;
    }

    public void setEquippedEquipments(EquipmentInventory equippedEquipments) {
        this.equippedEquipments = equippedEquipments;
    }

    /**
     * get the Equipment of given EquipmentPart
     *
     * @param equipmentPart equipment part to get
     * @return Equipment of given EquipmentPart
     * @throws UnsupportedItemException If item isn't supported
     */
    public Equipment getEquipment(EquipmentPart equipmentPart) throws UnsupportedItemException {
        if (equipmentPart == null)
            throw new NullPointerException("EquipmentPart can't be null");

        switch (equipmentPart) {
            case BELT -> {
                return equippedEquipments.getBelt();
            }
            case BODY -> {
                return equippedEquipments.getBody();
            }
            case FEET -> {
                return equippedEquipments.getFeet();
            }
            case HEAD -> {
                return equippedEquipments.getHead();
            }
            case LEGS -> {
                return equippedEquipments.getLegs();
            }
            case HANDS -> {
                return equippedEquipments.getHands();
            }
            case AMULET -> {
                return equippedEquipments.getAmulet();
            }
            case MANTLE -> {
                return equippedEquipments.getMantle();
            }
            case RING -> {
                return equippedEquipments.getLeftRing();
            }
            case RING2 -> {
                return equippedEquipments.getRightRing();
            }
            default -> throw new UnsupportedItemException("Equipment Part " + equipmentPart + " isn't supported");
        }
    }

    /**
     * remove an equipped equipment from the EquipmentInventory
     *
     * @param equipment equipment to remove
     */
    public void removeEquippedEquipment(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        switch (equipment.getEquipmentPart()) {
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
    public void removeLeftRing() {
        Equipment ring = equippedEquipments.getLeftRing();
        equippedEquipments.removeLeftRing();
        if (!inventory.contains(ring))
            inventory.add(ring);
    }

    /**
     * remove Right ring equipment
     */
    public void removeRightRing() {
        Equipment ring = equippedEquipments.getRightRing();
        equippedEquipments.removeRightRing();
        inventory.add(ring);
    }

    /**
     * add new equipped equipment if there's no equipped equipment of same EquipmentPart yet and remove it from inventory
     *
     * @param equipment equipment to equip
     */
    public void addEquippedEquipment(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        switch (equipment.getEquipmentPart()) {
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
        if (equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
    }

    /**
     * add new LeftRing if there's no leftRing already equipped and remove it from inventory
     *
     * @param equipment left ring to equip
     */
    public void addLeftRing(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        equippedEquipments.addLeftRing(equipment);
        removeFromInventory(equipment);
    }

    /**
     * add new RightRing if there's not RightRing already equipped and remove it from inventory
     *
     * @param equipment right ring to equip
     */
    public void addRightRing(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        equippedEquipments.addRightRing(equipment);
        removeFromInventory(equipment);
    }

    /**
     * replace equippedEquipment by the param equipment
     *
     * @param equipment equipped equipment to replace
     */
    public void replaceEquippedEquipment(Equipment equipment) {
        Equipment equipped;
        if (equipment == null) {
            return;
        }
        switch (equipment.getEquipmentPart()) {
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
        if (equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
        inventory.add(equipped);
    }

    /**
     * Replace equipped Left ring by param
     *
     * @param equipment left ring to replace
     */
    public void replaceLeftRing(Equipment equipment) {
        if (equipment.getEquipmentPart() != EquipmentPart.RING)
            return;

        Equipment equipped = equippedEquipments.getLeftRing();
        removeLeftRing();
        addLeftRing(equipment);

        if (equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
        if (!inventory.contains(equipped)) {
            inventory.add(equipped);
        }
    }

    /**
     * Replace equipped right ring by param
     *
     * @param equipment right ring to replace
     */
    public void replaceRightRing(Equipment equipment) {

        if (equipment.getEquipmentPart() != EquipmentPart.RING)
            return;

        Equipment equipped = equippedEquipments.getRightRing();
        removeRightRing();
        addRightRing(equipment);

        if (equippedEquipments.getEquippedList().contains(equipment))
            removeFromInventory(equipment);
        inventory.add(equipped);
    }

    /**
     * allow to increase base Strength can't be superior from 15 at character creation
     */
    public void increaseStrength() {
        strength++;
    }

    /**
     * allow to decrease base Strength can't be inferior from 8 at character creation
     */
    public void decreaseStrength() {
        if (level == 1) {
            if (strength > 8 && strength <= 10) {
                strength--;
            }
        } else {
            strength--;
        }
    }

    /**
     * allow to increase base dexterity can't be superior from 15 at character creation
     */
    public void increaseDexterity() {
        dexterity++;
    }

    /**
     * allow to decrease base dexterity can't be inferior from 8 at character creation
     */
    public void decreaseDexterity() {
        if (level == 1) {
            if (dexterity > 8 && dexterity <= 10) {
                dexterity--;

            }
        } else {
            dexterity--;
        }
    }

    /**
     * allow to increase base Robustness can't be superior from 15 at character creation
     */
    public void increaseRobustness() {
        robustness++;
    }

    /**
     * allow to decrease base robustness can't be inferior from 8 at character creation
     */
    public void decreaseRobustness() {
        if (level == 1) {
            if (robustness > 8 && intelligence <= 10) {
                robustness--;
            }
        } else {
            robustness--;
        }
    }

    /**
     * allow to increase base intelligence can't be superior from 15 at character creation
     */
    public void increaseIntelligence() {
        intelligence++;
    }

    /**
     * allow to decrease base intelligence can't be inferior from 8 at character creation
     */
    public void decreaseIntelligence() {
        if (level == 1) {
            if (intelligence > 8 && intelligence <= 10) {
                intelligence--;
            }
        } else {
            intelligence--;
        }
    }

    /**
     * allow to increase base wisdom can't be superior from 15 at character creation
     */
    public void increaseWisdom() {
        wisdom++;
    }

    /**
     * allow to decrease base wisdom can't be inferior from 8 at character creation
     */
    public void decreaseWisdom() {
        if (level == 1) {
            if (wisdom > 8 && wisdom <= 10) {
                strength--;
            }
        } else {
            wisdom--;
        }
    }

    /**
     * allow to increase base charisma can't be superior from 15 at character creation
     */
    public void increaseCharisma() {
        charisma++;
    }

    /**
     * allow to decrease base charisma can't be inferior from 8 at character creation
     */
    public void decreaseCharisma() {
        if (level == 1) {
            if (charisma > 8 && charisma <= 10) {
                charisma--;
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
     * give the the Alignment of the character
     *
     * @return the Alignment of the character
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     * give the Race of the character
     *
     * @return the Race of the character
     */
    public Race getRace() {
        return race;
    }

    /**
     * give amount of additional statistics points
     *
     * @return the amount of additional statistics points
     */
    public int getAdditionalStatPoints() {
        return additionalStatPoints;
    }

    /**
     * set the statistics point to parameter value
     *
     * @param additionalStatPoints additional stats points to set
     */
    public void setAdditionalStatPoints(int additionalStatPoints) {
        if (additionalStatPoints < 0)
            return;
        this.additionalStatPoints = additionalStatPoints;
    }

    /**
     * add an improvement to the list of improvement of the character
     *
     * @param improvement improvement to add
     */
    public void addImprovement(Improvement improvement) {
        improvements.add(improvement);
    }

    /**
     * add a spell in the spellList of the character
     *
     * @param spell spell to add
     */
    public void addSpell(Spell spell) {
        spellInventory.add(spell);
    }

    /**
     * give the gender of the character
     *
     * @return the gender of the character
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * give the name of the Character
     *
     * @return name of the Character
     */
    @Override
    public String toString() {
        return name;
    }
}
