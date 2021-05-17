package model.items.equipments;

import model.items.Item;

/**
 * Creation of equippable equipments by the character
 */
public class Equipment implements Item {

    private final String name;
    private  String description;
    private  EquipmentPart equipmentPart;
    private  int armorBonus;
    private  EquipmentType type;
    private  int[] characteristicsBoost = new int[7];


    public Equipment(String name, String description, EquipmentPart equipmentPart, int armorBonus, EquipmentType type,
                     int strengthBoost, int dexterityBoost, int robustnessBoost, int intelligenceBoost, int wisdomBoost,
                     int charismaBoost, int speedBoost) {
        this.name = name;
        this.description = description;
        this.equipmentPart = equipmentPart;
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
     * give name of the equipment
     * @return name of the equipment
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * give description of the equipment
     * @return description of the equipment
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * give armor bonus given by item
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
    public EquipmentPart getEquipmentPart() {
        return equipmentPart;
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
     * give bonus given by item
     * @return strength bonus when equipped
     */
    public int getStrengthBoost() {
        return characteristicsBoost[0];
    }

    /**
     * give bonus given by item
     * @return dexterity bonus when equipped
     */
    public int getDexterityBoost() {
        return characteristicsBoost[1];
    }

    /**
     * give bonus given by item
     * @return robustness bonus when equipped
     */
    public int getRobustnessBoost() {
        return characteristicsBoost[2];
    }

    /**
     * give bonus given by item
     * @return intelligence bonus when equipped
     */
    public int getIntelligenceBoost() {
        return characteristicsBoost[3];
    }

    /**
     * give bonus given by item
     * @return wisdom bonus when equipped
     */
    public int getWisdomBoost() {
        return characteristicsBoost[4];
    }

    /**
     * give bonus given by item
     * @return charisma bonus when equipped
     */
    public int getCharismaBoost() {
        return characteristicsBoost[5];
    }

    /**
     * give bonus given by item
     * @return speed bonus when equipped
     */
    public int getSpeedBoost() {
        return characteristicsBoost[6];
    }

    /**
     * give name of equipment
     * @return name of equipment
     */
    @Override
    public String toString() {
        return name;
    }

    public EquipmentType getType() {
        return type;
    }

    public int[] getCharacteristicsBoost() {
        return characteristicsBoost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEquipmentPart(EquipmentPart equipmentPart) {
        this.equipmentPart = equipmentPart;
    }

    public void setArmorBonus(int armorBonus) {
        this.armorBonus = armorBonus;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public void setCharacteristicsBoost(int[] characteristicsBoost) {
        this.characteristicsBoost = characteristicsBoost;
    }
}
