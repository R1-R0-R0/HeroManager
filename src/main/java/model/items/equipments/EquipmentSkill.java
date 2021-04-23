package model.items.equipments;

import model.Characteristics;

public class EquipmentSkill {
    private String name;
    private Characteristics affectedCharasteristics;

    public EquipmentSkill(String name, Characteristics affectedCharasteristics) {
        this.name = name;
        this.affectedCharasteristics = affectedCharasteristics;
    }

    public String getName() {
        return name;
    }

    public Characteristics getAffectedCharasteristics() {
        return affectedCharasteristics;
    }
}
