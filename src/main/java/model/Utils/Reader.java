package model.Utils;


import model.items.consumables.Consumable;
import model.items.consumables.ConsumableEffect;
import model.items.equipments.Equipment;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import model.job.JobType;
import model.spell.Component;
import model.spell.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public static ArrayList<Consumable> jsonReaderConsumable (String Filename){
        JSONParser parser = new JSONParser();
        ArrayList<Consumable> effect = new ArrayList<>();

        try (FileReader reader = new FileReader(Filename)){
            Object obj = parser.parse(reader);
            JSONArray objectList = (JSONArray) obj;

            //objectList.forEach( element ->  effect.add(parseConsomable((JSONObject) element)));
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

        return new Consumable(name,description,effect);
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

}
