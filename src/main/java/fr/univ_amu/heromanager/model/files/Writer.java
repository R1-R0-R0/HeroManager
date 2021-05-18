package fr.univ_amu.heromanager.model.files;

import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.model.spell.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of differents writer to call to write the jsonfile
 */
public class Writer {
    /**
     * Method to write the Consumable file
     *
     * @param consumable List of consumable to be write on the file
     */
    public static void writerConsumable(List<Consumable> consumable) {
        JSONObject consu = new JSONObject();
        JSONArray consumableList = new JSONArray();

        for (Consumable value : consumable) {
            consumableList.add(value.getName());
            consumableList.add(value.getDescription());
        }

        consu.put("consumable", consumableList);
        FileManager.writeFile("consumable", consu);
    }

    /**
     * Method to write the Weapons file
     *
     * @param weapons List of weapons to be write on the file
     */
    public static void writerWeapon(List<Weapon> weapons) {
        JSONArray weaponList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (Weapon weapon : weapons) {
            weaponList.add(weapon.getName());
            weaponList.add(weapon.getDescription());
            weaponList.add(weapon.getProperties());
            weaponList.add(weapon.getWeaponType().toString());
            weaponList.add(weapon.getDamageType().toString());
        }
        consu.put("weapon", weaponList);
        FileManager.writeFile("weapon", consu);

    }

    /**
     * Method to write the Equipments file
     *
     * @param equipment List of equipment to be write on the file
     */
    public static void writerEquipment(List<Equipment> equipment) {
        JSONArray equipmentList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (Equipment value : equipment) {
            equipmentList.add(value.getName());
            equipmentList.add(value.getDescription());
            equipmentList.add(value.getEquipmentPart().toString());

            equipmentList.add(value.getArmorBonus());
            equipmentList.add(value.getEquipmentType().toString());
            equipmentList.add(value.getStrengthBoost());
            equipmentList.add(value.getDexterityBoost());
            equipmentList.add(value.getRobustnessBoost());
            equipmentList.add(value.getIntelligenceBoost());
            equipmentList.add(value.getWisdomBoost());
            equipmentList.add(value.getCharismaBoost());
            equipmentList.add(value.getSpeedBoost());

        }
        consu.put("equipment", equipmentList);
        FileManager.writeFile("equipment", consu);

    }

    /**
     * Method to write the Spell file
     *
     * @param spells List of spells to be write on the file
     */
    public static void writerSpell(List<Spell> spells) {
        JSONArray spellList = new JSONArray();
        JSONObject consu = new JSONObject();
        JSONArray components;

        for (Spell spell : spells) {
            components = new JSONArray();
            spellList.add(spell.getName());
            spellList.add(spell.getDescription());
            spellList.add(spell.getSchool());
            spellList.add(spell.getCastingTime());
            spellList.add(spell.getDuration());
            spellList.add(spell.getLevel());


            spellList.add(spell.getRange());
            spellList.add(spell.getJobType().toString());
            spellList.add(spell.isDoDamages());

            for (int x = 0; x < spell.getComponents().size(); x++) {
                switch (spell.getComponents().get(x).toString().toUpperCase()) {
                    case "VOCAL" ->   {
                        if(!components.contains("V"))
                            components.add("V");
                    }
                    case "MATERIAL" -> {
                        if(!components.contains("S"))
                            components.add("S");
                    }
                    case "MOVEMENT" -> {
                        if(!components.contains("M"))
                            components.add("M");
                    }
                    default -> {}
                }
            }
            spellList.add(components);
        }

        consu.put("spell", spellList);
        FileManager.writeFile("spell", consu);
    }

    /**
     * Method to write the Characters file
     *
     * @param jobs List of jobs to be write on the file
     */
    public static void writerJob(List<Job> jobs) {
        JSONArray jobList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (Job job : jobs) {
            JSONArray listspell = new JSONArray();
            JSONArray skills = new JSONArray();
            JSONArray improvements = new JSONArray();
            JSONArray equipments = new JSONArray();
            JSONArray inventory = new JSONArray();

            jobList.add(job.getName());
            jobList.add(job.getDescription());
            jobList.add(job.getGender().toString());
            jobList.add(job.getAlignment().toString());
            jobList.add(job.getRaceType().name());
            jobList.add(job.getJobType().toString());
            for (int x = 0; x < job.getSpellInventory().size(); x++) {
                listspell.add(job.getSpellInventory().get(x).getName());
            }
            jobList.add(listspell);
            for (int x = 0; x < job.getSkills().size(); x++) {
                skills.add(job.getSkills().get(x).getName());
            }
            jobList.add(skills);
            jobList.add(job.getLevel());
            jobList.add(job.getStrength());
            jobList.add(job.getDexterity());
            jobList.add(job.getRobustness());
            jobList.add(job.getWisdom());
            jobList.add(job.getIntelligence());
            jobList.add(job.getCharisma());
            jobList.add(job.getSpeed());
            jobList.add(job.getHealthPoints());
            jobList.add(job.getArmor());
            jobList.add(job.getAdditionalStatPoints());
            for (int x = 0; x < job.getImprovements().size(); x++) {
                improvements.add(job.getImprovements().get(x).toString());
            }
            jobList.add(improvements);
            if (!job.getEquippedEquipments().getEquippedList().isEmpty()) {
                for (Equipment equiped : job.getEquippedEquipments().getEquippedList()
                ) {
                    if (equiped != null) equipments.add(equiped.getName());
                }
                jobList.add(equipments);

            } else jobList.add(new ArrayList<Equipment>());
            for (int x = 0; x < job.getInventory().size(); x++) {
                if (job.getInventory().get(x) != null)
                    inventory.add(job.getInventory().get(x).getName());
            }
            jobList.add(inventory);
        }

        consu.put("job", jobList);
        FileManager.writeFile("job", consu);
    }
}
