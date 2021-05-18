package fr.univ_amu.heromanager.model.spell;

import fr.univ_amu.heromanager.model.job.JobType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpellTest {


    Spell fireBall = new Spell("Fireball","Launch a ball of fire dealing magical damages","Fire","1 turn",
            "no duration",5,35, JobType.WIZARD,true,
            Collections.singletonList(Component.VOCAL));

    Spell frostSpike = new Spell("Frostspike","Launch a spike of ice dealing magical movement impairment","Ice","instant",
            "3 turns",6,40, JobType.SORCERER,false,
            Arrays.asList(Component.VOCAL,Component.MATERIAL));


    @Test
    public void getNameTest(){
        String frostSpikeName = frostSpike.getName();
        String fireBallName = fireBall.getName();

        assertSame("Fireball", fireBallName);
        assertSame("Frostspike", frostSpikeName);
    }

    @Test
    public void getDescriptionTest(){
        String frostDescription = frostSpike.getDescription();
        String fireDescription = fireBall.getDescription();

        assertSame("Launch a ball of fire dealing magical damages", fireDescription);
        assertSame("Launch a spike of ice dealing magical movement impairment", frostDescription);
    }

    @Test
    public void getSchoolTest(){
        String frostSchool = frostSpike.getSchool();
        String fireSchool = fireBall.getSchool();

        assertSame("Fire",fireSchool);
        assertSame("Ice",frostSchool);

    }
    @Test
    public void getCastingTimeTest() {
        String frostCasting = frostSpike.getCastingTime();
        String fireCasting = fireBall.getCastingTime();

        assertSame("1 turn", fireCasting);
        assertSame("instant", frostCasting);
    }

    @Test
    public void getDurationTest() {
        String frostDuration = frostSpike.getDuration();
        String fireDuration = fireBall.getDuration();

        assertSame("no duration", fireDuration);
        assertSame("3 turns", frostDuration);
    }

    @Test
    public void getLevelTest() {
        int frostLevel = frostSpike.getLevel();
        int fireLevel = fireBall.getLevel();

        assertEquals(fireLevel,5);
        assertEquals(frostLevel,6);
    }

    @Test
    public void getRangeTest() {
        int frostRange = frostSpike.getRange();
        int fireRange = fireBall.getRange();

        assertEquals(fireRange, 35);
        assertEquals(frostRange,40);
    }

    @Test
    public void getJobTypeTest() {
        JobType frostJob = frostSpike.getJobType();
        JobType fireJob = fireBall.getJobType();

        assertSame(fireJob,JobType.WIZARD);
        assertSame(frostJob, JobType.SORCERER);

    }

    @Test
    public void isDoDamagesTest() {
        boolean frostDamages = frostSpike.isDoDamages();
        boolean fireDamages = fireBall.isDoDamages();

        assertTrue(fireDamages);
        assertFalse(frostDamages);

    }

    @Test
    public void getComponentsTest() {
        Component vocal = Component.VOCAL;
        Component material = Component.MATERIAL;

        List<Component> frostList = frostSpike.getComponents();
        List<Component> fireList = fireBall.getComponents();

        assertTrue(fireList.contains(vocal));
        assertFalse(fireList.contains(material));

        assertTrue(frostList.contains(vocal));
        assertTrue(frostList.contains(material));


    }
}
