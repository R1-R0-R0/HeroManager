package model.spell;

import model.job.JobType;

import java.util.List;

public class Spell {
    String  name,
            description,
            school,
            castingTime,
            duration;

    int level,
        range;

    JobType jobType;
    boolean doDamages;
    List<Component> components;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSchool() {
        return school;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public String getDuration() {
        return duration;
    }

    public int getLevel() {
        return level;
    }

    public int getRange() {
        return range;
    }

    public JobType getJobType() {
        return jobType;
    }

    public boolean isDoDamages() {
        return doDamages;
    }

    public List<Component> getComponents() {
        return components;
    }
}
