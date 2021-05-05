package model.job;


import model.items.Item;
import model.items.equipments.Equipment;
import model.items.equipments.HeadEquipment;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobTest {


    Job wizard = new Job("Grosflan", "A big Flan", Gender.MAN, Alignment.CHAOTIC_EVIL, Race.DRAGONBORN,JobType.WIZARD);
    Job warlock = new Job("Airels", "Airels nothing more to say", Gender.MAN, Alignment.NEUTRAL_GOOD, Race.GNOME,JobType.WARLOCK);
    Job paladin = new Job("M.", "I love open shoes", Gender.MAN, Alignment.LAWFUL_EVIL, Race.HALFLING,JobType.PALADIN);
    Job alreadyExistingBard = new Job("Hatsune", "Young vitual singer",Gender.WOMAN, Alignment.CHAOTIC_EVIL, Race.DRAGONBORN, JobType.BARD,
            new ArrayList<>(),new ArrayList<>(), 15,14, 13, 12, 11, 10, 9, 8,
         0, new ArrayList<>() , new ArrayList<>(), new ArrayList<>());
    Equipment head = new HeadEquipment("helmet", "protects from arrows",1,null,null,
            5,4,3,2,1,5,4);

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
    public void setSpellSlotsTest(){

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
    public void StrengthTest(){

        int wantedBaseStr14 = 14;
        int wantedBaseStr15 = 15;
        assertEquals(wantedBaseStr14,alreadyExistingBard.getStrength());

        alreadyExistingBard.increaseStrength(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedBaseStr15, alreadyExistingBard.getStrength());

        alreadyExistingBard.setStatsPoints(1);
        alreadyExistingBard.increaseStrength();

        assertEquals(wantedBaseStr15, alreadyExistingBard.getStrength());
    }

    @Test
    public void getDexterityTest(){

        int wantedDex13 = 13;
        int wantedDex14 = 14;
        assertEquals(wantedDex13,alreadyExistingBard.getDexterity());

        alreadyExistingBard.increaseDexterity(); // not increasing because alreadyExistingBard have 0 stats points
        assertNotEquals(wantedDex14, alreadyExistingBard.getDexterity());

        alreadyExistingBard.setStatsPoints(1);
        alreadyExistingBard.increaseDexterity();

        assertEquals(wantedDex14, alreadyExistingBard.getDexterity());
    }
}
