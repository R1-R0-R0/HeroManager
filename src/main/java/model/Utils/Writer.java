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
            //consu.put("effect" , consumable.get(index).getEffect());

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
            consu.put("characteristic" , jobskills.get(index).getAffectedCharacteristic());
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

            //consu.put("name" , improvements.get(index).getName());
            //consu.put("description" , improvements.get(index).getDescription());

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

    public static void WriterJob (Job jobs){
        JSONArray jobList = new JSONArray();


            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            //consu.put("hp", jobs.getHp());
            consu.put("armor", jobs.getArmor());
            consu.put("level", jobs.getLevel());
            consu.put("proficiencyLevel", jobs.getProficiencyLevel());
            consu.put("strength", jobs.getStrength());
            consu.put("dexterity", jobs.getDexterity());
            consu.put("intelligence", jobs.getIntelligence());
            consu.put("wisdom", jobs.getWisdom());
            consu.put("charism", jobs.getCharisma());
            consu.put("strengthBoost", jobs.getStrengthBoost());
            consu.put("dexterityBoost", jobs.getDexterityBoost());
            consu.put("intelligenceBoost", jobs.getIntelligenceBoost());
            consu.put("wisdomBoost", jobs.getWisdomBoost());
            //consu.put("charismBoost", jobs.getCharismBoost());
            //consu.put("race", jobs.getRace());
            consu.put("jobtype", jobs.getJobType());
            consu.put("inventory", jobs.getInventory());
            consu.put("name" , jobs.getName());
            consu.put("description" , jobs.getDescription());
            consu.put("spellSlots", jobs.getSpellSlots());
            consu.put("spellsInventory", jobs.getSpellInventory());
            consu.put("improvement", jobs.getImprovements());
            consu.put("jobSkill", jobs.getSkills());


            consumax.put("job",consu);

            jobList.add(consumax);

        try (FileWriter file = new FileWriter("/HeroManager/resources/" + jobs.getName()
                +".json")) {
            file.write(jobList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    


}
