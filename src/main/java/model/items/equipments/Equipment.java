package model.items.equipments;

import model.items.Item;

public class Equipment implements Item{

    private final String name;
    private final String description;
    private final EquipmentParts equipmentParts;
    private final int armorBonus;
    private final EquipmentType type;
    private final int[] characteristicsBoost = new int[7];


    public Equipment(String name, String description,EquipmentParts equipmentParts, int armorBonus, EquipmentType type,
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public int getArmorBonus() {
        return armorBonus;
    }

    public EquipmentParts getEquipmentParts(){
        return equipmentParts;
    }


    public EquipmentType getEquipmentType() {
        return type;
    }

    public int getStrengthBoost(){
        return characteristicsBoost[0];
    }

    public int getDexterityBoost(){
        return characteristicsBoost[1];
    }

    public int getRobustnessBoost(){
        return characteristicsBoost[2];
    }

    public int getIntelligenceBoost(){
        return characteristicsBoost[3];
    }

    public int getWisdomBoost(){
        return characteristicsBoost[4];
    }

    public int getCharismaBoost(){
        return characteristicsBoost[5];
    }

    public int getSpeedBoost(){
        return characteristicsBoost[6];
    }

    @Override
    public String toString(){
        return name;
    }
}
