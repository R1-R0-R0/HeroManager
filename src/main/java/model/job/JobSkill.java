package model.job;

import model.Characteristics;

public class JobSkill {
    String name;
    boolean mastered;
    Characteristics affectedCharacteristic;


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
