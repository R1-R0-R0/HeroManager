package utils.files;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of differents writer to call to write the jsonfile
 */
public class Writer {
    /**
     * Methode to write the Consumable file
     *
     * @param consumable List of consumable to be write on the file
     */
    public static void writerConsumable(List<Consumable> consumable) {
        JSONObject consu = new JSONObject();
        JSONArray consumableList = new JSONArray();


        for (int index = 0; index < consumable.size(); index++) {


            consumableList.add(consumable.get(index).getName());
            consumableList.add(consumable.get(index).getDescription());


        }

        consu.put("consumable", consumableList);
        FileManager.writeFile("consumable", consu);
    }

    /**
     * Methode to write the Weapons file
     *
     * @param weapons List of weapons to be write on the file
     */
    public static void writerWeapon(List<Weapon> weapons) {
        JSONArray weaponList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (int index = 0; index < weapons.size(); index++) {

            weaponList.add(weapons.get(index).getName());
            weaponList.add(weapons.get(index).getDescription());
            weaponList.add(weapons.get(index).getProperties());
            weaponList.add(weapons.get(index).getWeaponType().toString());

            weaponList.add(weapons.get(index).getDamageType().toString());

        }
        consu.put("weapon", weaponList);
        FileManager.writeFile("weapon", consu);

    }

    /**
     * Methode to write the Equipments file
     *
     * @param equipment List of equipment to be write on the file
     */
    public static void writerEquipment(List<Equipment> equipment) {
        JSONArray equipmentList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (int index = 0; index < equipment.size(); index++) {


            equipmentList.add(equipment.get(index).getName());
            equipmentList.add(equipment.get(index).getDescription());
            equipmentList.add(equipment.get(index).getEquipmentPart().toString());

            equipmentList.add(equipment.get(index).getArmorBonus());
            equipmentList.add(equipment.get(index).getEquipmentType().toString());
            equipmentList.add(equipment.get(index).getStrengthBoost());
            equipmentList.add(equipment.get(index).getDexterityBoost());
            equipmentList.add(equipment.get(index).getRobustnessBoost());
            equipmentList.add(equipment.get(index).getIntelligenceBoost());
            equipmentList.add(equipment.get(index).getWisdomBoost());
            equipmentList.add(equipment.get(index).getCharismaBoost());
            equipmentList.add(equipment.get(index).getSpeedBoost());

        }
        consu.put("equipment", equipmentList);
        FileManager.writeFile("equipment", consu);

    }

    /**
     * Methode to write the Spell file
     *
     * @param spells List of spells to be write on the file
     */
    public static void writerSpell(List<Spell> spells) {
        JSONArray spellList = new JSONArray();
        JSONObject consu = new JSONObject();
        JSONArray components = new JSONArray();

        for (int index = 0; index < spells.size(); index++) {

            spellList.add(spells.get(index).getName());
            spellList.add(spells.get(index).getDescription());
            spellList.add(spells.get(index).getSchool());
            spellList.add(spells.get(index).getCastingTime());
            spellList.add(spells.get(index).getDuration());
            spellList.add(spells.get(index).getLevel());


            spellList.add(spells.get(index).getRange());
            spellList.add(spells.get(index).getJobType().toString());
            spellList.add(spells.get(index).isDoDamages());
            for (int x = 0; x < spells.get(index).getComponents().size(); x++) {
                switch (spells.get(index).getComponents().get(x)) {
                    case VOCAL:
                        components.add("V");
                        break;
                    case MATERIAL:
                        components.add("S");
                        break;
                    case MOVEMENT:
                        components.add("M");
                        break;
                }

            }

            spellList.add(components);


        }
        consu.put("spell", spellList);

        FileManager.writeFile("spell", consu);

    }

    /**
     * Methode to write the Characters file
     *
     * @param jobs List of jobs to be write on the file
     */
    public static void writerJob(List<Job> jobs) {
        JSONArray jobList = new JSONArray();
        JSONObject consu = new JSONObject();


        for (int index = 0; index < jobs.size(); index++) {
            JSONArray listspell = new JSONArray();
            JSONArray skills = new JSONArray();
            JSONArray improvements = new JSONArray();
            JSONArray equipments = new JSONArray();
            JSONArray inventory = new JSONArray();

            jobList.add(jobs.get(index).getName());
            jobList.add(jobs.get(index).getDescription());
            jobList.add(jobs.get(index).getGender().toString());
            jobList.add(jobs.get(index).getAlignment().toString());
            jobList.add(jobs.get(index).getRaceType().name());
            jobList.add(jobs.get(index).getJobType().toString());
            for (int x = 0; x < jobs.get(index).getSpellInventory().size(); x++) {
                listspell.add(jobs.get(index).getSpellInventory().get(x).getName());
            }
            jobList.add(listspell);
            for (int x = 0; x < jobs.get(index).getSkills().size(); x++) {
                skills.add(jobs.get(index).getSkills().get(x).getName());
                skills.add(jobs.get(index).getSkills().get(x).isMastered());
            }
            jobList.add(skills);
            jobList.add(jobs.get(index).getLevel());
            jobList.add(jobs.get(index).getStrength());
            jobList.add(jobs.get(index).getDexterity());
            jobList.add(jobs.get(index).getRobustness());
            jobList.add(jobs.get(index).getWisdom());
            jobList.add(jobs.get(index).getIntelligence());
            jobList.add(jobs.get(index).getCharisma());
            jobList.add(jobs.get(index).getSpeed());
            jobList.add(jobs.get(index).getHealthPoints());
            jobList.add(jobs.get(index).getArmor());
            jobList.add(jobs.get(index).getAdditionalStatPoints());
            for (int x = 0; x < jobs.get(index).getImprovements().size(); x++) {
                improvements.add(jobs.get(index).getImprovements().get(x).toString());
            }
            jobList.add(improvements);
            if (!jobs.get(index).getEquippedEquipments().getEquippedList().isEmpty()) {
                for (Equipment equiped : jobs.get(index).getEquippedEquipments().getEquippedList()
                ) {
                    if (equiped != null) equipments.add(equiped.getName());
                }
                jobList.add(equipments);

            } else jobList.add(new ArrayList<Equipment>());
            for (int x = 0; x < jobs.get(index).getInventory().size(); x++) {
                inventory.add(jobs.get(index).getInventory().get(x).getName());
            }
            jobList.add(inventory);
        }

        consu.put("job", jobList);

        FileManager.writeFile("job", consu);

    }


}
