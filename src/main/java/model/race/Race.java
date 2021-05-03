package model.race;

import model.job.Improvement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Race {
    DWARF(0,0,2,0,0,0,Height.MEDIUM,25,
            Improvement.DARKVISION,
            Improvement.DWARVEN_COMBAT_TRAINING,
            Improvement.DWARVEN_RESILIENCE,
            Improvement.TOOL_PROFICIENCY,
            Improvement.STONECUNNING),

    ELF(0,2,0,0,0,0,Height.MEDIUM,30,
            Improvement.DARKVISION,
            Improvement.KEEN_SENSES,
            Improvement.FEY_ANCESTRY,
            Improvement.TRANCE),
    HALFLING(0,2,0,0,0,0,Height.SMALL,25,
            Improvement.LUCKY,
            Improvement.BRAVE,
            Improvement.HALFLING_NIMBLENESS),
    HUMAN(1,1,1,1,1,1,Height.MEDIUM,30),
    DRAGONBORN(2,0,0,0,0,1,Height.MEDIUM,30,
            Improvement.DRACONIC_ANCESTRY,
            Improvement.BREATH_WEAPON,
            Improvement.DAMAGE_RESISTANCE),
    GNOME(0,0,0,2,0,0,Height.SMALL,25,
            Improvement.DARKVISION,
            Improvement.GNOME_CUNNING),
    HALF_ELF(0,0,0,0,0,2,Height.MEDIUM,30,
            Improvement.DARKVISION,
            Improvement.FEY_ANCESTRY,
            Improvement.SKILL_VERSATILITY),
    HALF_ORC(2,0,1,0,0,0,Height.MEDIUM,30,
            Improvement.DARKVISION,
            Improvement.MENACING,
            Improvement.RELENTLESS_ENDURANCE,
            Improvement.SAVAGE_ATTACKS),
    TIEFLING(0,0,0,1,0,1,Height.MEDIUM,30,
            Improvement.DARKVISION,
            Improvement.HELLISH_RESISTANCE,
            Improvement.INFERNAL_LEGACY),

    AARAKOCRA(0,2,0,0,1,0, Height.MEDIUM,25,
            Improvement.FLIGHT,
            Improvement.TALONS);



    protected final int MAXIMAL_IMPROVEMENT_VALUE = 10;
    protected final int[] valueToImprove = new int[6];
    protected final Height height;
    protected final int bonusStats;
    protected final int speed;
    protected Improvement[] improvements = new Improvement[MAXIMAL_IMPROVEMENT_VALUE];



    Race(int strengthBoost, int dexterityBoost, int robustnessBoost, int intelligenceBoost, int wisdomBoost,
         int charismaBoost, Height height, int speed, Improvement... improvements){
        valueToImprove[0] = strengthBoost;
        valueToImprove[1] = dexterityBoost;
        valueToImprove[2] = robustnessBoost;
        valueToImprove[3] = intelligenceBoost;
        valueToImprove[4] = wisdomBoost;
        valueToImprove[5] = charismaBoost;
        this.height = height;
        this.speed = speed;
        this.bonusStats=0;
        this.improvements = improvements;
    }

    Race(int strengthBoost, int dexterityBoost, int robustnessBoost, int intelligenceBoost, int wisdomBoost,
         int charismaBoost, Height height, int speed, int bonusStats){
        valueToImprove[0] = strengthBoost;
        valueToImprove[1] = dexterityBoost;
        valueToImprove[2] = robustnessBoost;
        valueToImprove[3] = intelligenceBoost;
        valueToImprove[4] = wisdomBoost;
        valueToImprove[5] = charismaBoost;
        this.height = height;
        this.speed = speed;
        this.bonusStats = bonusStats;
    }

    public List<Improvement> getImprovements() {
        return new ArrayList<>(Arrays.asList(improvements));
    }

    public int getStrengthBoost(){
        return valueToImprove[0];
    }
    public int getDexterityBoost(){
        return valueToImprove[1];
    }

    public int getRobustnessBoost(){
        return valueToImprove[2];
    }

    public int getIntelligenceBoost(){
        return valueToImprove[3];
    }

    public int getWisdomBoost(){
        return valueToImprove[4];
    }

    public int getCharismaBoost(){
        return valueToImprove[4];
    }

    public Height getHeight() {
        return height;
    }

    public int getBonusStats() {
        return bonusStats;
    }

    public int getSpeed() {
        return speed;
    }


}