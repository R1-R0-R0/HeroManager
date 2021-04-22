package model.job;

import model.Characteristics;

public class JobSkill {
    private String name;
    private boolean mastered;
    private Characteristics affectedCharacteristic;

    public JobSkill(String name, boolean mastered, Characteristics affectedCharacteristic) {
        this.name = name;
        this.mastered = mastered;
        this.affectedCharacteristic = affectedCharacteristic;
    }

    public String getName() {
        return name;
    }

    public boolean isMastered() {
        return mastered;
    }

    public Characteristics getAffectedCharacteristic() {
        return affectedCharacteristic;
    }


}
