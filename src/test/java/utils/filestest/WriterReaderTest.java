package utils.filestest;

import model.items.Item;
import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentInventory;
import model.items.equipments.EquipmentPart;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.*;
import model.race.Alignment;
import model.race.Race;
import model.spell.Component;
import model.spell.Spell;
import org.junit.jupiter.api.Test;

import model.files.HeroManagerDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WriterReaderTest {

    @Test
    public void testWriterReader() throws IOException {
        HeroManagerDB.init();

        List<Spell> spells = new ArrayList<>();
        List<Equipment> equipments = new ArrayList<>();
        List<JobSkill> jobSkills = JobSkill.getJobSkillList();
        List<Item> inventory = new ArrayList<>();
        List<Improvement> improvements = new ArrayList<>();
        ArrayList<Component> components = new ArrayList<>();
        components.add(Component.VOCAL);

        spells.add(new Spell("Bugfinder", "Trouve des bugs dans le code de yohan", "divination",
                "1 action", "8 hours", 2, 120, JobType.BARD, false, components));

        HeroManagerDB.addSpell(spells.get(0));

        jobSkills.get(0).setMastered(true);

        improvements.add(Improvement.BRAVE);
        improvements.add(Improvement.DARKVISION);

        Weapon weapon = new Weapon("Slash", "Slash ça mere", "slash des daronnes", WeaponType.WAR, DamageType.PIERCING);
        Equipment equipment = new Equipment("lunar Sloth", "Rend paresseux", EquipmentPart.BELT, 2, EquipmentType.LIGHT, 0, 0, 0, 0, 0, 0, 0);
        Consumable consumable = new Consumable("Potion de force", "Donne de la force");
        HeroManagerDB.addEquipment(equipment);
        HeroManagerDB.addWeapons(weapon);
        HeroManagerDB.addConsumable(consumable);
        equipments.add(equipment);
        inventory.add(consumable);
        inventory.add(weapon);

        EquipmentInventory equipmentInventory = new EquipmentInventory(equipments);


        Job test = new Job("Spirit", "Codeur surdoué", Gender.MAN, Alignment.LAWFUL_EVIL, Race.HUMAN, JobType.BARD, spells, jobSkills, 10, 14, 10, 8, 10, 16, 18, 30, 40, 15, 0, improvements,
                equipmentInventory, inventory);

        HeroManagerDB.addJob(test);

        HeroManagerDB.Save();
        HeroManagerDB.init();

        Job result = HeroManagerDB.getJobs().get(0);

        System.out.println(result.getName());
        System.out.println(result.getDescription());
        System.out.println(result.getGender());
        System.out.println(result.getAlignment());
        System.out.println(result.getRace().toString());
        System.out.println(result.getJobType());
        System.out.println(result.getSpellInventory().get(0).getDescription());
        System.out.println(result.getSkills().get(0).isMastered());
        System.out.println(result.getLevel());
        System.out.println(result.getHealthPoints());
        System.out.println(result.getStrength());
        System.out.println(result.getDexterity());
        System.out.println(result.getRobustness());
        System.out.println(result.getIntelligence());
        System.out.println(result.getWisdom());
        System.out.println(result.getCharisma());
        System.out.println(result.getArmor());
        System.out.println(result.getAdditionalStatPoints());
        System.out.println(result.getImprovements().get(1));
        for (int i = 0; i < result.getEquippedEquipments().getEquippedList().size(); i++) {
            if (result.getEquippedEquipments().getEquippedList().get(i) != null) {
                System.out.println(result.getEquippedEquipments().getEquippedList().get(i).getName());
            }
        }
        System.out.println(result.getInventory().get(0).getName());
        System.out.println(result.getInventory().get(1).getDescription());


    }
}
