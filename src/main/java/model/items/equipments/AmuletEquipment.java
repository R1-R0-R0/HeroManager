package model.items.equipments;

import model.Characteristics;

public class AmuletEquipment implements Equipment{

    private final String name;
    private final String description;
    private final int armorBonus;
    private final EquipmentEffect effect;
    private final EquipmentType type;
    private final int[] characteristicsBoost = new int[7];


    public AmuletEquipment(String name, String description, int armorBonus, EquipmentEffect effect, EquipmentType type,
                           int strengthBoost, int dexterityBoost, int robustnessBoost, int intelligenceBoost, int wisdomBoost,
                           int charismaBoost, int speedBoost) {
        this.name = name;
        this.description = description;
        this.armorBonus = armorBonus;
        this.effect = effect;
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

    @Override
    public int getArmorBonus() {
        return armorBonus;
    }

    @Override
    public EquipmentEffect getEquipmentEffect() {
        return effect;
    }

    @Override
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
}
