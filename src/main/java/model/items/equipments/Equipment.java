package model.items.equipments;

import model.items.Item;

public interface Equipment extends Item {
    int getArmorBonus();
    EquipmentEffect getEquipmentEffect();
    EquipmentType getEquipmentType();
}
