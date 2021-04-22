package model.job;

import model.Characteristics;

public class JobSkill {
    private String name;
    private boolean mastered;
    private Characteristics affectedCharacteristic;


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
