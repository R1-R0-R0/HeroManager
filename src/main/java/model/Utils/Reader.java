package model.Utils;


import model.Characteristics;
import model.items.Item;
import model.items.consumables.Consumable;
import model.items.consumables.ConsumableEffect;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentEffect;
import model.items.equipments.EquipmentType;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.Improvement;
import model.job.JobSkill;
import model.job.JobType;
import model.race.Race;
import model.spell.Component;
import model.spell.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import model.job.Job;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    // TODO : avoir des methode pour choper l'enum sur un string
    // TODO : avec des attribut public final static de type Filename pour enlever les attribut de méthode reader

    public static ArrayList<Consumable> jsonReaderConsumable (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Consumable> effect = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  effect.add(parseConsomable((JSONObject) element)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return effect;
    }

    private static Consumable parseConsomable(JSONObject object)
    {
        JSONObject objects = (JSONObject) object.get("consomable");

        String name = (String) objects.get("name");

        String description = (String) objects.get("description");

        ConsumableEffect effect = (ConsumableEffect) objects.get("effect");

        return new Consumable(effect,name,description);
    }


    public static ArrayList<Weapon> jsonReaderWeapon (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Weapon> weapons = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  weapons.add(parseWeapon((JSONObject) element)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return weapons;
    }

    private static Weapon parseWeapon(JSONObject object)
    {
        JSONObject objects = (JSONObject) object.get("weapon");

        String name = (String) objects.get("name");

        String description = (String) objects.get("description");

        WeaponType type = (WeaponType) objects.get("type");

        String properties = (String) objects.get("properties");

        DamageType damageType = (DamageType) objects.get("damagetype");

        return new Weapon(name,description,properties,type,damageType);
    }

    public static ArrayList<Spell> jsonReaderSpell (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Spell> spells = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  spells.add(parseSpell((JSONObject) element)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return spells;
    }

    private static Spell parseSpell(JSONObject object)
    {
        JSONObject objects = (JSONObject) object.get("spell");

        String name = (String) objects.get("name");

        String description = (String) objects.get("description");

        int level = (int) objects.get("level");

        String school = (String) objects.get("school");

        String castingTime = (String) objects.get("castingtime");

        int range = (int) objects.get("range");

        JSONObject comp = (JSONObject) object.get("components");

        List<Component> components = new ArrayList<>();

        if ((boolean) comp.get("V")) components.add(Component.VOCAL);
        if ((boolean) comp.get("S")) components.add(Component.MOVEMENT);
        if ((boolean) comp.get("M")) components.add(Component.MATERIAL);

        String duration = (String) objects.get("duration");

        JobType jobType = (JobType) objects.get("jobtype");

        boolean doDamages = (boolean) objects.get("dodamage");

        return new Spell(name,description,school,castingTime,duration,level,range,jobType,doDamages,components);
    }

    public static ArrayList<JobSkill> jsonReaderJobSkill (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<JobSkill> jobskills = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  jobskills.add(parseJobSkill((JSONObject) element)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jobskills;
    }

    private static JobSkill parseJobSkill(JSONObject object)
    {
        JSONObject objects = (JSONObject) object.get("jobskill");

        String name = (String) objects.get("name");

        boolean mastered = (boolean) objects.get("mastered");

        Characteristics characteristic = (Characteristics) objects.get("characteristic");

        return new JobSkill(name,mastered,characteristic);
    }

    public static ArrayList<Equipment> jsonReaderEquipement (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Equipment> equi = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  equi.add(parseEquipment((JSONObject) element)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return equi;
    }

    private static Equipment parseEquipment(JSONObject object)
    {
        JSONObject objects = (JSONObject) object.get("equipement");

        String name = (String) objects.get("name");

        String description = (String) objects.get("description");

        int armorbonus = (int)object.get("armorbonus");

        EquipmentEffect effect = (EquipmentEffect) object.get("effect");

        EquipmentType type = (EquipmentType) object.get("type");

        return new Equipment() {
            @Override
            public int getArmorBonus() {
                return armorbonus;
            }

            @Override
            public EquipmentEffect getEquipmentEffect() {
                return effect;
            }

            @Override
            public EquipmentType getEquipmentType() {
                return type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getDescription() {
                return description;
            }
        };
    }

    public static ArrayList<Item> jsonReaderItem (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Item> items = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  items.add(parseConsomable((JSONObject) element)));
            objectList.forEach( element ->  items.add(parseWeapon((JSONObject) element)));
            objectList.forEach( element ->  items.add(parseEquipment((JSONObject) element)));

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static ArrayList<Job> jsonReaderJob (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Job> jobskills = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  jobskills.add(parseJob((JSONObject) element,Filename)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jobskills;
    }

    public static ArrayList<Improvement> jsonReaderImprovement (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Improvement> improvements = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            objectList.forEach( element ->  improvements.add(parseImprovement((JSONObject) element)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return improvements;
    }

    private static Improvement parseImprovement(JSONObject object)
    {
        JSONObject objects = (JSONObject) object.get("consomable");

        String name = (String) objects.get("name");

        String description = (String) objects.get("description");

        return new Improvement(name,description);
    }

    private static Job parseJob(JSONObject object, String Filename)
    {
        JSONObject objects = (JSONObject) object.get("job");

        int hp = (int) object.get("hp");
        int armor = (int) object.get("armor");
        int level = (int) object.get("level");
        int proficiencyLevel = (int) object.get("proficiencyLevel");
        int strength = (int) object.get("strength");
        int dexterity = (int) object.get("dexterity");
        int intelligence = (int) object.get("intelligence");
        int wisdom = (int) object.get("wisdom");
        int charisma = (int) object.get("charisma");
        int strengthBoost = (int) object.get("strengthBoost");
        int dexterityBoost = (int) object.get("dexterityBoost");
        int intelligenceBoost = (int) object.get("intelligenceBoost");
        int wisdomBoost = (int) object.get("wisdomBoost");
        int charismaBoost = (int) object.get("charismaBoost");

        Race race = (Race) object.get("race");
        JobType jobType = (JobType) object.get("race");
        List<Item> inventory = jsonReaderItem(Filename);
        String name = (String) object.get("name");
        String description = (String) object.get("description");
        int[] spellSlots = (int[]) object.get("spellSlots");
        List<Spell> spellInventory = jsonReaderSpell(Filename);
        List<Improvement> improvements = jsonReaderImprovement(Filename);
        List<JobSkill> skills = jsonReaderJobSkill(Filename);



        return new Job(hp,armor,level,proficiencyLevel,strength,dexterity,intelligence,wisdom,
                charisma,strengthBoost,dexterityBoost,intelligenceBoost,wisdomBoost,charismaBoost,
                race,jobType,inventory,name,description,spellSlots,spellInventory,improvements,skills);

    }




}
