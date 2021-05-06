package model.items.equipments;

import model.items.Item;

/**
 * Creation of equippable equipments by the character
 */
public class Equipment implements Item {

    private final String name;
    private final String description;
    private final EquipmentParts equipmentParts;
    private final int armorBonus;
    private final EquipmentType type;
    private final int[] characteristicsBoost = new int[7];


    public Equipment(String name, String description, EquipmentParts equipmentParts, int armorBonus, EquipmentType type,
                     int strengthBoost, int dexterityBoost, int robustnessBoost, int intelligenceBoost, int wisdomBoost,
                     int charismaBoost, int speedBoost) {
        this.name = name;
        this.description = description;
        this.equipmentParts = equipmentParts;
        this.armorBonus = armorBonus;
        this.type = type;

        characteristicsBoost[0] = strengthBoost;
        characteristicsBoost[1] = dexterityBoost;
        characteristicsBoost[2] = robustnessBoost;
        characteristicsBoost[3] = intelligenceBoost;
        characteristicsBoost[4] = wisdomBoost;
        characteristicsBoost[5] = charismaBoost;
        characteristicsBoost[6] = speedBoost;
    }

    /**
     * @return name of the equipment
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return description of the equipment
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return armor bonus given by the item
     */
    public int getArmorBonus() {
        return armorBonus;
    }

    /**
     * Says on which part of the body you can equip the equipment
     *
     * @return EquipmentParts part of body
     */
    public EquipmentParts getEquipmentParts() {
        return equipmentParts;
    }


    /**
     * Says the type of equipment
     *
     * @return type of equipment
     */
    public EquipmentType getEquipmentType() {
        return type;
    }

    /**
     * @return strength bonus when equipped
     */
    public int getStrengthBoost() {
        return characteristicsBoost[0];
    }

    /**
     * @return dexterity bonus when equipped
     */
    public int getDexterityBoost() {
        return characteristicsBoost[1];
    }

    /**
     * @return robustness bonus when equipped
     */
    public int getRobustnessBoost() {
        return characteristicsBoost[2];
    }

    /**
     * @return intelligence bonus when equipped
     */
    public int getIntelligenceBoost() {
        return characteristicsBoost[3];
    }

    /**
     * @return wisdom bonus when equipped
     */
    public int getWisdomBoost() {
        return characteristicsBoost[4];
    }

    /**
     * @return charisma bonus when equipped
     */
    public int getCharismaBoost() {
        return characteristicsBoost[5];
    }

    /**
     * @return speed bonus when equipped
     */
    public int getSpeedBoost() {
        return characteristicsBoost[6];
    }

    /**
     * @return name of equipment
     */
    @Override
    public String toString() {
        return name;
    }
}
