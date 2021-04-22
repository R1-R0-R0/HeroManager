package model.items.equipments;

public class FeetsEquipment implements Equipment{


    String name;
    String description;
    int armorBonus;
    EquipmentEffect effect;
    EquipmentType type;

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
}
