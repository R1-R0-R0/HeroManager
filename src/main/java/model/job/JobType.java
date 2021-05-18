package model.job;

import java.util.Arrays;
import java.util.List;

/**
 * Lists all the existing JobType
 */
public enum JobType {
    BARBARIAN(12, Improvement.RAGE, Improvement.UNARMORED_DEFENSE),
    BARD(8, Improvement.BARDIC_INSPIRATION), //add spellCasting
    CLERIC(8, Improvement.DIVINE_DOMAIN), //add spellCasting
    DRUID(8, Improvement.DRUIDIC), // add spellCasting
    FIGHTER(10, Improvement.FIGHTING_STYLE, Improvement.SECOND_WIND),
    MONK(8, Improvement.UNARMORED_DEFENSE, Improvement.MARTIAL_ARTS),
    PALADIN(10, Improvement.DIVINE_SENSE, Improvement.LAY_ON_HANDS),
    RANGER(10, Improvement.FAVORED_ENEMY, Improvement.NATURAL_EXPLORER),
    ROGUE(8, Improvement.EXPERTISE, Improvement.SNEAK_ATTACK, Improvement.THIEVES_CANT),
    SORCERER(6, Improvement.SORCEROUS_ORIGIN),  //add spellCasting
    WARLOCK(8, Improvement.OTHERWORDLY_PATRON, Improvement.PACT_MAGIC),
    WIZARD(6, Improvement.ARCANE_RECOVERY); // add spellCasting


    /**
     * Jobs who can possess spells, useful to activate spell tab or not
     */
    public final static JobType[] JOBS_SPELLS_AUTHORIZED = {JobType.WARLOCK, JobType.PALADIN, JobType.RANGER, JobType.SORCERER, JobType.DRUID, JobType.CLERIC, JobType.BARD, JobType.WIZARD};
    protected int dLife;
    protected Improvement[] improvements;


    JobType(int dLife, Improvement... improvements) {
        this.dLife = dLife;
        this.improvements = improvements;

    }

    /**
     * @return the list of improvements given by the JobType
     */
    public List<Improvement> getImprovements() {
        return Arrays.asList(improvements);
    }

    /**
     * @return Dlife given by the JobType
     */
    public int getDLife() {
        return dLife;
    }
}