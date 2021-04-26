package model.Utils;

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
import java.util.List;

public class Writer {

    public static void WriterConsumable (List<Consumable> consumable){
        JSONArray consumableList = new JSONArray();


        for (int index = 0; index<consumable.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();
            consu.put("name" , consumable.get(index).getName());
            consu.put("description" , consumable.get(index).getDescription());
            consu.put("effect" , consumable.get(index).getEffect());

            consumax.put("consumable",consu);

            consumableList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/consumable.json")) {
            file.write(consumableList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriterWeapon (List<Weapon> weapons){
        JSONArray weaponList = new JSONArray();

        for (int index = 0; index<weapons.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("name" , weapons.get(index).getName());
            consu.put("description" , weapons.get(index).getDescription());
            consu.put("type" , weapons.get(index).getWeaponType());
            consu.put("properties" , weapons.get(index).getProperties());
            consu.put("damagetype" , weapons.get(index).getDamageType());

            consumax.put("weapon", consu);

            weaponList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/weapon.json")) {
            file.write(weaponList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriterEquipment (List<Equipment> equipements){
        JSONArray equipmentList = new JSONArray();

        for (int index = 0; index<equipements.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("name" , equipements.get(index).getName());
            consu.put("description" , equipements.get(index).getDescription());
            consu.put("armorbonus" , equipements.get(index).getArmorBonus());
            consu.put("effect" , equipements.get(index).getEquipmentEffect());
            consu.put("type" , equipements.get(index).getEquipmentType());

            consumax.put("equipment", consu);

            equipmentList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/equipment.json")) {
            file.write(equipmentList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriterSpell (List<Spell> spells){
        JSONArray spellList = new JSONArray();

        for (int index = 0; index<spells.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("name" , spells.get(index).getName());
            consu.put("description" , spells.get(index).getDescription());
            consu.put("level" , spells.get(index).getLevel());
            consu.put("school" , spells.get(index).getSchool());
            consu.put("castingtime" , spells.get(index).getCastingTime());
            consu.put("range" , spells.get(index).getRange());
            for (int x = 0 ; x < spells.get(index).getComponents().size(); x++){
                switch (spells.get(index).getComponents().get(x)){
                    case VOCAL: consu.put("components","V");
                    case MATERIAL:consu.put("components","S");
                    case MOVEMENT:consu.put("components","M");
                }
            }
            consu.put("duration" , spells.get(index).getDuration());
            consu.put("jobtype" , spells.get(index).getJobType());
            consu.put("dodamage" , spells.get(index).isDoDamages());

            consumax.put("spell",consu);


            spellList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/spells.json")) {
            file.write(spellList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriterJobSkill (List<JobSkill> jobskills){
        JSONArray jobskillList = new JSONArray();

        for (int index = 0; index<jobskills.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("name" , jobskills.get(index).getName());
            consu.put("characteristic" , jobskills.get(index).getAffectedCharacteristic();
            consu.put("mastered" , jobskills.get(index).isMastered());

            consumax.put("jobskill",consu);

            jobskillList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/jobskill.json")) {
            file.write(jobskillList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriterImprovement (List<Improvement> improvements){
        JSONArray improvementsList = new JSONArray();

        for (int index = 0; index<improvements.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("name" , improvements.get(index).getName());
            consu.put("description" , improvements.get(index).getDescription());

            consumax.put("improvement",consu);

            improvementsList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/improvement.json")) {
            file.write(improvementsList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriterJob (List<Job> jobs){
        JSONArray jobList = new JSONArray();

        for (int index = 0; index<jobs.size(); index++){

            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("hp", jobs.get(index).getHp());
            consu.put("armor", jobs.get(index).getArmor());
            consu.put("level", jobs.get(index).getLevel());
            consu.put("proficiencyLevel", jobs.get(index).getProficiencyLevel());
            consu.put("strength", jobs.get(index).getStrength());
            consu.put("dexterity", jobs.get(index).getDexterity());
            consu.put("intelligence", jobs.get(index).getIntelligence());
            consu.put("wisdom", jobs.get(index).getWisdom());
            consu.put("charism", jobs.get(index).getCharisma());
            consu.put("strengthBoost", jobs.get(index).getStrengthBoost());
            consu.put("dexterityBoost", jobs.get(index).getDexterityBoost());
            consu.put("intelligenceBoost", jobs.get(index).getIntelligenceBoost());
            consu.put("wisdomBoost", jobs.get(index).getWisdomBoost());
            consu.put("charismBoost", jobs.get(index).getCharismBoost());
            consu.put("race", jobs.get(index).getRace());
            consu.put("jobtype", jobs.get(index).getJobType());
            consu.put("inventory", jobs.get(index).getInventory());
            consu.put("name" , jobs.get(index).getName());
            consu.put("description" , jobs.get(index).getDescription());
            consu.put("spellSlots", jobs.get(index).getSpellSlots());
            consu.put("spellsInventory", jobs.get(index).getSpellInventory());
            consu.put("improvement", jobs.get(index).getImprovements());
            consu.put("jobSkill", jobs.get(index).getSkills());


            consumax.put("job",consu);

            jobList.add(consumax);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/" + jobs.get(0).getName()
                +".json")) {
            file.write(jobList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    


}
