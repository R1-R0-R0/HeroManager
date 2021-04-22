package model.Utils;


import model.items.consumables.Consumable;
import model.items.consumables.ConsumableEffect;
import model.items.equipments.Equipment;
import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
        JSONObject employeeObject = (JSONObject) object.get("consomable");

        String name = (String) employeeObject.get("name");

        String description = (String) employeeObject.get("description");

        ConsumableEffect effect = (ConsumableEffect) employeeObject.get("effect");

        return new Consumable(/*name,description,effect*/);
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
        JSONObject employeeObject = (JSONObject) object.get("weapon");

        String name = (String) employeeObject.get("name");

        String description = (String) employeeObject.get("description");

        WeaponType type = (WeaponType) employeeObject.get("type");

        String properties = (String) employeeObject.get("properties");

        DamageType damageType = (DamageType) employeeObject.get("damagetype");

        return new Weapon(name,description,properties,type,damageType);
    }

}
