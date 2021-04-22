package model.items.equipments;

public class HandsEquipment implements Equipment{


    private String name;
    private String description;
    private int armorBonus;
    private EquipmentEffect effect;
    private EquipmentType type;

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
