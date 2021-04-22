package model.race;

import model.Characteristics;

import java.util.List;


public class Race {

    private Characteristics abilityScoreIncrease;
    private int age;
    private String sex;
    private Alignment alignment;
    private int size;
    private int weight;
    private int speed;
    private List<Language> languages;

    public Race(Characteristics abilityScoreIncrease, int age, String sex, Alignment alignment,
                int size, int weight, int speed, List<Language> languages) {
        this.abilityScoreIncrease = abilityScoreIncrease;
        this.age = age;
        this.sex = sex;
        this.alignment = alignment;
        this.size = size;
        this.weight = weight;
        this.speed = speed;
        this.languages = languages;
    }

    public Characteristics getAbilityScoreIncrease() {
        return abilityScoreIncrease;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public List<Language> getLanguages() {
        return languages;
    }
}
