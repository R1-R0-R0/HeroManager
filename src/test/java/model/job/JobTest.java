package model.job;


import model.items.equipments.Equipment;
import model.items.equipments.EquipmentParts;
import model.items.equipments.EquipmentType;
import model.race.Alignment;
import model.race.Race;
import model.spell.Component;
import model.spell.Spell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    Equipment head = new Equipment("helmet", "protects from arrows", EquipmentParts.HEAD,1, EquipmentType.HEAVY,
            5,4,3,2,1,5,4);



    Job wizard = new Job("Grosflan", "A big Flan", Gender.MAN, Alignment.CHAOTIC_EVIL, Race.DRAGONBORN,JobType.WIZARD);
    Job warlock = new Job("Airels", "Airels nothing more to say", Gender.MAN, Alignment.NEUTRAL_GOOD, Race.GNOME,JobType.WARLOCK);
    Job paladin = new Job("M.", "I love open shoes", Gender.MAN, Alignment.LAWFUL_EVIL, Race.HALFLING,JobType.PALADIN);
    Job alreadyExistingBard = new Job("Hatsune", "Young virtual singer",Gender.WOMAN, Alignment.CHAOTIC_EVIL, Race.DRAGONBORN, JobType.BARD,
            new ArrayList<>(),new ArrayList<>(), 15,14, 13, 12, 11, 10, 9, 40,35,5,
         0, new ArrayList<>() , new ArrayList<>(), Collections.singletonList(head));


    @Test
    public void levelUpTest(){
        int level1 = 1;
        int level2 = 2;
        int level5 = 5;

        assertEquals(wizard.getLevel(),level1);
        wizard.levelUp(); // level 2
        assertEquals(wizard.getLevel(),level2);
        wizard.levelUp(); // level 3
        wizard.levelUp(); // level 4
        assertNotEquals(wizard.getLevel(), level5);
        wizard.levelUp(); // level 5
        assertEquals(wizard.getLevel(),level5);

    }

    @Test
    public void getProficiencyLevelTest(){
        assertEquals(wizard.getProficiencyLevel(),2);
        assertEquals(alreadyExistingBard.getProficiencyLevel(),5);
    }

    @Test
    public void spellSlotsTest(){

        int level2 = 2;
        int[] spellSlotLevel1Wizard = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] spellSlotLevel2Wizard = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};

        int[] spellSlotLevel1Paladin = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] spellSlotLevel2Paladin = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};

        int[] spellSlotLevel1Warlock = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] spellSlotLevel2Warlock = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};

        assertArrayEquals(spellSlotLevel1Wizard, wizard.getSpellSlots());
        assertArrayEquals(spellSlotLevel1Paladin, paladin.getSpellSlots());
        assertArrayEquals(spellSlotLevel1Warlock, warlock.getSpellSlots());

        wizard.levelUp();
        paladin.levelUp();
        warlock.levelUp();

        assertEquals(warlock.getLevel(), level2);
        assertEquals(paladin.getLevel(), level2);
        assertEquals(wizard.getLevel(), level2);

        assertArrayEquals(spellSlotLevel2Wizard, wizard.getSpellSlots());
        assertArrayEquals(spellSlotLevel2Paladin, paladin.getSpellSlots());
        assertArrayEquals(spellSlotLevel2Warlock, warlock.getSpellSlots());
    }

    @Test
    public void getHealthPointTest(){
        //TODO: Wait implementation of JobType enum
    }

    @Test
    public void statPointsTest(){

        int actualBardPoints = alreadyExistingBard.getAdditionalStatPoints();
        wizard.setAdditionalStatPoints(15);
        alreadyExistingBard.setAdditionalStatPoints(-5); // Impossible value, statsPoints must be higher or equal than 0

        assertEquals(wizard.getAdditionalStatPoints(), 15);
        assertEquals(alreadyExistingBard.getAdditionalStatPoints(), actualBardPoints);

        alreadyExistingBard.setAdditionalStatPoints(4);
        assertEquals(alreadyExistingBard.getAdditionalStatPoints(), 4);
    }

    @Test
    public void getModificatorTest(){
        int pointInStat5 = 5;
        int pointInStat14 = 14;
        int pointInStat18 = 18;

        int wantedModifMinus3 = -3;
        int wantedModifPlus2 = 2;
        int wantedModifPlus4 = 4;

        assertEquals(wizard.getModificator(pointInStat5), wantedModifMinus3);
        assertEquals(wizard.getModificator(pointInStat14), wantedModifPlus2);
        assertEquals(wizard.getModificator(pointInStat18), wantedModifPlus4);
        assertEquals(wizard.getModificator(120),0); // 120 should not be possible default return 0
    }

    @Test
    public void getStrengthTest(){

        int wantedBaseStr14 = 14;
        int wantedBaseStr15 = 15;
        assertEquals(wantedBaseStr14,alreadyExistingBard.getStrength());

        alreadyExistingBard.increaseStrength(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedBaseStr15, alreadyExistingBard.getStrength());

        alreadyExistingBard.setAdditionalStatPoints(1);
        alreadyExistingBard.increaseStrength();

        assertEquals(wantedBaseStr15, alreadyExistingBard.getStrength());

        alreadyExistingBard.decreaseStrength();

        assertEquals(wantedBaseStr14, alreadyExistingBard.getStrength());
    }

    @Test
    public void getDexterityTest(){

        int wantedDex13 = 13;
        int wantedDex14 = 14;
        assertEquals(wantedDex13,alreadyExistingBard.getDexterity());

        alreadyExistingBard.increaseDexterity(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedDex14, alreadyExistingBard.getDexterity());

        alreadyExistingBard.setAdditionalStatPoints(1);
        alreadyExistingBard.increaseDexterity();

        assertEquals(wantedDex14, alreadyExistingBard.getDexterity());

        alreadyExistingBard.decreaseDexterity();
        assertEquals(wantedDex13, alreadyExistingBard.getDexterity());
    }

    @Test
    public void getRobustnessTest() {

        int wantedRob11 = 11;
        int wantedRob12 = 12;
        assertEquals(wantedRob11, alreadyExistingBard.getRobustness());

        alreadyExistingBard.increaseRobustness(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedRob12, alreadyExistingBard.getRobustness());

        alreadyExistingBard.setAdditionalStatPoints(1);
        alreadyExistingBard.increaseRobustness();

        assertEquals(wantedRob12, alreadyExistingBard.getRobustness());


        alreadyExistingBard.decreaseRobustness();
        assertEquals(wantedRob11, alreadyExistingBard.getRobustness());

    }
    @Test
    public void getIntelligenceTest() {

        int wantedInt12 = 12;
        int wantedInt13 = 13;
        assertEquals(wantedInt12, alreadyExistingBard.getIntelligence());

        alreadyExistingBard.increaseIntelligence(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedInt13, alreadyExistingBard.getIntelligence());

        alreadyExistingBard.setAdditionalStatPoints(1);
        alreadyExistingBard.increaseIntelligence();

        assertEquals(wantedInt13, alreadyExistingBard.getIntelligence());


        alreadyExistingBard.decreaseIntelligence();
        assertEquals(wantedInt12, alreadyExistingBard.getIntelligence());

    }

    @Test
    public void getWisdomTest() {

        int wantedWisdom10 = 10;
        int wantedWisdom11 = 11;
        assertEquals(wantedWisdom10, alreadyExistingBard.getWisdom());

        alreadyExistingBard.increaseWisdom(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedWisdom11, alreadyExistingBard.getWisdom());

        alreadyExistingBard.setAdditionalStatPoints(1);
        alreadyExistingBard.increaseWisdom();

        assertEquals(wantedWisdom11, alreadyExistingBard.getWisdom());


        alreadyExistingBard.decreaseWisdom();
        assertEquals(wantedWisdom10, alreadyExistingBard.getWisdom());

    }

    @Test
    public void getCharismaTest() {

        int wantedCha9 = 9;
        int wantedCha10 = 10;
        assertEquals(wantedCha9, alreadyExistingBard.getCharisma());

        alreadyExistingBard.increaseCharisma(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedCha10, alreadyExistingBard.getCharisma());

        alreadyExistingBard.setAdditionalStatPoints(1);
        alreadyExistingBard.increaseCharisma();

        assertEquals(wantedCha10, alreadyExistingBard.getCharisma());


        alreadyExistingBard.decreaseCharisma();
        assertEquals(wantedCha9, alreadyExistingBard.getCharisma());

    }

    @Test
    public void getSpeedTest(){
        int wantedSpeedBard = 40;
        int wantedSpeedDragonBorn = 30;

        assertEquals(alreadyExistingBard.getSpeed(),wantedSpeedBard);
        assertEquals(wizard.getSpeed(), wantedSpeedDragonBorn);
    }

    @Test
    public void getArmor(){
        int wantedArmor10 = 10;
        int wantedArmor20 = 20;

        assertEquals(wizard.getArmor(), wantedArmor10);
        alreadyExistingBard.setArmor(wantedArmor20);
        assertEquals(alreadyExistingBard.getArmor(), wantedArmor20);
    }

    @Test
    public void getRaceTest(){
        Race dragonBorn = Race.DRAGONBORN;
        Race gnome = Race.GNOME;

        assertEquals(wizard.getRaceType(),dragonBorn);
        assertEquals(warlock.getRaceType(), gnome);
    }

    @Test
    public void getJobType(){
        JobType wizard = JobType.WIZARD;
        JobType warlock = JobType.WARLOCK;
        JobType bard = JobType.BARD;

        assertEquals(this.wizard.getJobType(), wizard);
        assertEquals(this.warlock.getJobType(), warlock);
        assertEquals(alreadyExistingBard.getJobType(), bard);
    }

    @Test
    public void getNameTest(){
        String grosflan = "Grosflan";
        String airels = "Airels";
        String hatsune = "Hatsune";

        assertEquals(wizard.getName(), grosflan);
        assertEquals(warlock.getName(),airels);
        assertEquals(alreadyExistingBard.getName(), hatsune);

        assertEquals(wizard.toString(),grosflan);
    }

    @Test
    public void getGenderTest(){
        Gender man = Gender.MAN;
        Gender woman = Gender.WOMAN;

        assertEquals(warlock.getGender(), man);
        assertEquals(alreadyExistingBard.getGender(), woman);

    }

    @Test
    public void getDescriptionTest(){
        String airels = "Airels nothing more to say";
        String hatsune = "Young virtual singer";

        assertEquals(warlock.getDescription(), airels);
        assertEquals(alreadyExistingBard.getDescription(), hatsune);
    }
    @Test
    public void getInventoryTest(){

        assertTrue(alreadyExistingBard.getInventory().contains(head));

        assertFalse(wizard.getInventory().contains(head));

    }

    @Test
    public void spellInventoryTest(){

        Spell fireBall = new Spell("Fireball","Launch a ball of fire dealing magical damages","Fire","1 turn",
                "no duration",5,35, JobType.WIZARD,true,
                Collections.singletonList(Component.VOCAL));

        assertFalse(wizard.getSpellInventory().contains(fireBall)); //shouldn't contain the spell yet

        wizard.addSpell(fireBall);
        assertTrue(wizard.getSpellInventory().contains(fireBall));
    }

    @Test
    public void improvementTest(){
        Improvement dragonAncestry   = Improvement.DRACONIC_ANCESTRY;
        Improvement breathWeapon     = Improvement.BREATH_WEAPON;
        Improvement damageResistance = Improvement.DAMAGE_RESISTANCE;

        assertTrue(wizard.getImprovements().contains(dragonAncestry));
        assertTrue(wizard.getImprovements().contains(breathWeapon));
        assertTrue(wizard.getImprovements().contains(damageResistance));

        assertFalse(warlock.getImprovements().contains(breathWeapon));

        warlock.addImprovement(breathWeapon);

        assertTrue(warlock.getImprovements().contains(breathWeapon));
    }

}
