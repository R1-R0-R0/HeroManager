package model.items.equipments;

public class EquipmentEffect {
    private String name, description;
    private EquipmentSkill effect;


    public EquipmentEffect(String name, String description, EquipmentSkill effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EquipmentSkill getEffect() {
        return effect;
    }
}
