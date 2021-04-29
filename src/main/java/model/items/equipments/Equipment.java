package model.items.equipments;

import model.items.Item;

public interface Equipment extends Item {
    int getArmorBonus();
    EquipmentEffect getEquipmentEffect();
    EquipmentType getEquipmentType();
    int getStrengthBoost();
    int getDexterityBoost();
    int getRobustnessBoost();
    int getIntelligenceBoost();
    int getWisdomBoost();
    int getCharismaBoost();
    int getSpeedBoost();
}
