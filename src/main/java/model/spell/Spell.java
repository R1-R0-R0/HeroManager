package model.spell;

import model.job.JobType;

import java.util.List;

public class Spell {
    private String  name,
                    description,
                    school,
                    castingTime,
                    duration;

    private int level,
        range;

    private JobType jobType;
    private boolean doDamages;
    private List<Component> components;


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
