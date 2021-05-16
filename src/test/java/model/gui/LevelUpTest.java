package model.gui;

import controller.CharacterController;
import javafx.stage.Stage;
import model.items.equipments.EquipmentInventory;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.Gender;
import model.job.Job;
import model.job.JobType;
import model.race.Alignment;
import model.race.Race;
import model.spell.Spell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class LevelUpTest {

    @Start
    public void start(Stage primaryStage) {
        EquipmentInventory equippedInventory = new EquipmentInventory(null,null,null,null,null,null,null,null,null,null);
        Job job = new Job("Hatsune", "Young virtual singer", Gender.WOMAN, Alignment.CHAOTIC_EVIL, Race.HALFLING, JobType.BARD, new ArrayList<>(), new ArrayList<>(),
                15, 14, 13, 12, 11, 10, 9, 8, 50, 50, 10, new ArrayList<>(), equippedInventory, new ArrayList<>());
        job.addSpell(new Spell("A", "A spell", "School", "3sec", "3sec", 9, 10, JobType.ROGUE, true, null));
        job.addSpell(new Spell("B", "B spell", "School", "3sec", "3sec", 9, 10, JobType.ROGUE, true, null));
        job.getInventory().add(new Weapon("Weapon", "Description", "Properties", WeaponType.WAR, DamageType.SLASHING));

        new CharacterModel(job);
        CharacterController.getInstance().levelUpCharacter();
    }

    @Test
    public void test(FxRobot robot) {
        while (true);
    }
}
