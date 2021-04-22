package model.items.equipments;

public class RingEquipment implements Equipment{

    private String name;
    private String description;
    private int armorBonus;
    private EquipmentEffect effect;
    private EquipmentType type;

    public RingEquipment(String name, String description, int armorBonus, EquipmentEffect effect, EquipmentType type) {
        this.name = name;
        this.description = description;
        this.armorBonus = armorBonus;
        this.effect = effect;
        this.type = type;
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
}
