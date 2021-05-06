package utils.files;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.Improvement;
import model.job.Job;
import model.job.JobSkill;
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

            JSONObject test = new JSONObject();
            JSONObject consumax = new JSONObject();

            test.put("name", weapons.get(index).getName());
            test.put("description", weapons.get(index).getDescription());
            test.put("type", weapons.get(index).getWeaponType());
            test.put("properties", weapons.get(index).getProperties());
            test.put("damagetype", weapons.get(index).getDamageType());

            consumax.put("weapon", test);

            weaponList.add(consumax);

        }
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

            JSONObject test = new JSONObject();
            JSONObject consumax = new JSONObject();

            test.put("name", equipment.get(index).getName());
            test.put("description", equipment.get(index).getDescription());
            test.put("armorbonus", equipment.get(index).getArmorBonus());
            test.put("strengthboost", equipment.get(index).getStrengthBoost());
            test.put("dexterityboost", equipment.get(index).getDexterityBoost());
            test.put("robustnessboost", equipment.get(index).getRobustnessBoost());
            test.put("intelligenceboost", equipment.get(index).getIntelligenceBoost());
            test.put("wisdomboost", equipment.get(index).getWisdomBoost());
            test.put("charismaboost", equipment.get(index).getCharismaBoost());
            test.put("speedboost", equipment.get(index).getSpeedBoost());

            consumax.put("equipment", test);

            equipmentList.add(consumax);

        }

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

        for (int index = 0; index < spells.size(); index++) {

            JSONObject test = new JSONObject();
            JSONObject consumax = new JSONObject();

            test.put("name", spells.get(index).getName());
            test.put("description", spells.get(index).getDescription());
            test.put("level", spells.get(index).getLevel());
            test.put("school", spells.get(index).getSchool());
            test.put("castingtime", spells.get(index).getCastingTime());
            test.put("range", spells.get(index).getRange());
            for (int x = 0; x < spells.get(index).getComponents().size(); x++) {
                switch (spells.get(index).getComponents().get(x)) {
                    case VOCAL:
                        test.put("components", "V");
                    case MATERIAL:
                        test.put("components", "S");
                    case MOVEMENT:
                        test.put("components", "M");
                }
            }
            test.put("duration", spells.get(index).getDuration());
            test.put("jobtype", spells.get(index).getJobType());
            test.put("dodamage", spells.get(index).isDoDamages());

            consumax.put("spell", test);


            spellList.add(consumax);

        }

        FileManager.writeFile("spell", consu);

    }

    /**
     * Methode to write the Characters file
     *
     * @param jobs List of jobs to be write on the file
     */
    public static void writerJob(Job jobs) {
        JSONArray jobList = new JSONArray();


        JSONObject consu = new JSONObject();
        JSONObject consumax = new JSONObject();

        //consu.put("hp", jobs.getMaxHp(8));
        consu.put("armor", jobs.getArmor());
        consu.put("level", jobs.getLevel());
        consu.put("proficiencyLevel", jobs.getProficiencyLevel());
        consu.put("strength", jobs.getStrength());
        consu.put("dexterity", jobs.getDexterity());
        consu.put("intelligence", jobs.getIntelligence());
        consu.put("wisdom", jobs.getWisdom());
        consu.put("robustness", jobs.getRobustness());
        consu.put("charism", jobs.getCharisma());
        consu.put("strengthBoost", jobs.getStrengthBoost());
        consu.put("dexterityBoost", jobs.getDexterityBoost());
        consu.put("intelligenceBoost", jobs.getIntelligenceBoost());
        consu.put("wisdomBoost", jobs.getWisdomBoost());
        consu.put("charismBoost", jobs.getCharismaBoost());
        consu.put("race", jobs.getRaceType());
        consu.put("jobtype", jobs.getJobType());
        consu.put("inventory", jobs.getInventory());
        consu.put("name", jobs.getName());
        consu.put("description", jobs.getDescription());
        consu.put("spellSlots", jobs.getSpellSlots());
        consu.put("spellsInventory", jobs.getSpellInventory());
        consu.put("improvement", jobs.getImprovements());
        consu.put("jobSkill", jobs.getSkills());


        consumax.put("job", consu);

        jobList.add(consumax);

        FileManager.writeFile(jobs.getName(), consu);

    }


    /*public static void main(String[] args) throws IOException {
        List<Consumable> consumables = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            consumables.add(new Consumable(new ConsumableEffect(), "" + x, "ici c'est silmi " + x));
        }
        WriterConsumable(consumables);
    }*/


}
