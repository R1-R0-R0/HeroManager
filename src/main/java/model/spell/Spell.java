package model.spell;

import model.job.JobType;

import java.util.List;

/**
 * Spell used by a Job
 * @see model.job.Job
 */
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

    /**
     * give name of the spell
     * @return name of the spell
     */
    public String getName() {
        return name;
    }

    /**
     * give description of the spell
     * @return description of the spell
     */
    public String getDescription() {
        return description;
    }

    /**
     * give school of the spell
     * @return school from which you learn the spell
     */
    public String getSchool() {
        return school;
    }

    /**
     * give time to execute the spell
     * @return necessary time to execute the spell
     */
    public String getCastingTime() {
        return castingTime;
    }

    /**
     * give duration of the spell
     * @return total duration of the spell
     */
    public String getDuration() {
        return duration;
    }

    /**
     * give level of the spell
     * @return level of the spell
     */
    public int getLevel() {
        return level;
    }

    /**
     * give range of the spell
     * @return range in which you can execute the spell
     */
    public int getRange() {
        return range;
    }

    /**
     * give jobtype that can use the spell
     * @return jobType that can use the spell
     */
    public JobType getJobType() {
        return jobType;
    }

    /**
     * say if spell does damage or not
     * @return can do damages or not
     */
    public boolean isDoDamages() {
        return doDamages;
    }

    /**
     * give condition of execution of a spell
     * @return condition of execution of a spell
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * give name of the spell
     * @return name of the spell
     */
    @Override
    public String toString(){
        return name;
    }

}
