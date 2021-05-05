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

    public Spell(String name, String description, String school, String castingTime, String duration,
                 int level, int range, JobType jobType, boolean doDamages, List<Component> components) {
        this.name = name;
        this.description = description;
        this.school = school;
        this.castingTime = castingTime;
        this.duration = duration;
        this.level = level;
        this.range = range;
        this.jobType = jobType;
        this.doDamages = doDamages;
        this.components = components;
    }

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

    @Override
    public String toString(){
        return name;
    }

}
