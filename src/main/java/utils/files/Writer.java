package utils.files;

import model.items.consumables.Consumable;
import model.items.consumables.ConsumableEffect;
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

public class Writer {

    public static void WriterConsumable (List<Consumable> consumable) throws IOException {
        JSONObject consu = new JSONObject();
        JSONArray consumableList = new JSONArray();


        for (int index = 0; index<consumable.size(); index++){


            consumableList.add(consumable.get(index).getName());
            consumableList.add(consumable.get(index).getDescription());


        }

        consu.put("consumable",consumableList);
        FileManager.writeFile("consumable",consu);
    }

    public static void WriterWeapon (List<Weapon> weapons) throws IOException {
        JSONArray weaponList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (int index = 0; index<weapons.size(); index++){

            JSONObject test = new JSONObject();
            JSONObject consumax = new JSONObject();

            test.put("name" , weapons.get(index).getName());
            test.put("description" , weapons.get(index).getDescription());
            test.put("type" , weapons.get(index).getWeaponType());
            test.put("properties" , weapons.get(index).getProperties());
            test.put("damagetype" , weapons.get(index).getDamageType());

            consumax.put("weapon", test);

            weaponList.add(consumax);

        }
        FileManager.writeFile("weapon",consu);

    }

    public static void WriterEquipment (List<Equipment> equipements) throws IOException{
        JSONArray equipmentList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (int index = 0; index<equipements.size(); index++){

            JSONObject test = new JSONObject();
            JSONObject consumax = new JSONObject();

            test.put("name" , equipements.get(index).getName());
            test.put("description" , equipements.get(index).getDescription());
            test.put("armorbonus" , equipements.get(index).getArmorBonus());
            test.put("effect" , equipements.get(index).getEquipmentEffect());
            test.put("strengthboost" , equipements.get(index).getStrengthBoost());
            test.put("dexterityboost" , equipements.get(index).getDexterityBoost());
            test.put("robustnessboost" , equipements.get(index).getRobustnessBoost());
            test.put("intelligenceboost" , equipements.get(index).getIntelligenceBoost());
            test.put("wisdomboost" , equipements.get(index).getWisdomBoost());
            test.put("charismaboost" , equipements.get(index).getCharismaBoost());
            test.put("speedboost" , equipements.get(index).getSpeedBoost());

            consumax.put("equipment", test);

            equipmentList.add(consumax);

        }

        FileManager.writeFile("equipment",consu);

    }

    public static void WriterSpell (List<Spell> spells) throws IOException{
        JSONArray spellList = new JSONArray();
        JSONObject consu = new JSONObject();

        for (int index = 0; index<spells.size(); index++){

            JSONObject test = new JSONObject();
            JSONObject consumax = new JSONObject();

            test.put("name" , spells.get(index).getName());
            test.put("description" , spells.get(index).getDescription());
            test.put("level" , spells.get(index).getLevel());
            test.put("school" , spells.get(index).getSchool());
            test.put("castingtime" , spells.get(index).getCastingTime());
            test.put("range" , spells.get(index).getRange());
            for (int x = 0 ; x < spells.get(index).getComponents().size(); x++){
                switch (spells.get(index).getComponents().get(x)){
                    case VOCAL: test.put("components","V");
                    case MATERIAL:test.put("components","S");
                    case MOVEMENT:test.put("components","M");
                }
            }
            test.put("duration" , spells.get(index).getDuration());
            test.put("jobtype" , spells.get(index).getJobType());
            test.put("dodamage" , spells.get(index).isDoDamages());

            consumax.put("spell",test);


            spellList.add(consumax);

        }

        FileManager.writeFile("spell",consu);

    }

    public static void WriterJob (Job jobs) throws IOException{
        JSONArray jobList = new JSONArray();


            JSONObject consu = new JSONObject();
            JSONObject consumax = new JSONObject();

            consu.put("hp", jobs.getMaxHp(8));
            consu.put("armor", jobs.getArmor());
            consu.put("level", jobs.getLevel());
            consu.put("proficiencyLevel", jobs.getProficiencyLevel());
            consu.put("strength", jobs.getStrength());
            consu.put("dexterity", jobs.getDexterity());
            consu.put("intelligence", jobs.getIntelligence());
            consu.put("wisdom", jobs.getWisdom());
            consu.put("robustness",jobs.getRobustness());
            consu.put("charism", jobs.getCharisma());
            consu.put("strengthBoost", jobs.getStrengthBoost());
            consu.put("dexterityBoost", jobs.getDexterityBoost());
            consu.put("intelligenceBoost", jobs.getIntelligenceBoost());
            consu.put("wisdomBoost", jobs.getWisdomBoost());
            consu.put("charismBoost", jobs.getCharismaBoost());
            consu.put("race", jobs.getRaceType());
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

        FileManager.writeFile(jobs.getName(),consu);

    }


    public static void main(String[] args) throws IOException {
        List<Consumable> consumables = new ArrayList<>();
        for (int x = 0 ; x < 3 ; x++){
            consumables.add(new Consumable(new ConsumableEffect(), ""+x,"ici c'est silmi "+x));
        }
        WriterConsumable(consumables);
    }


    
    


}
