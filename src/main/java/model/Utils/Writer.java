package model.Utils;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
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
            consu.put("name" , consumable.get(index).getName());
            consu.put("description" , consumable.get(index).getDescription());
            consu.put("effect" , consumable.get(index).getEffect());

            consumableList.add(consu);

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
            consu.put("name" , weapons.get(index).getName());
            consu.put("description" , weapons.get(index).getDescription());
            consu.put("type" , weapons.get(index).getWeaponType());
            consu.put("properties" , weapons.get(index).getProperties());
            consu.put("damagetype" , weapons.get(index).getDamageType());

            weaponList.add(consu);

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
            consu.put("name" , equipements.get(index).getName());
            consu.put("description" , equipements.get(index).getDescription());
            consu.put("armorbonus" , equipements.get(index).getArmorBonus());
            consu.put("effect" , equipements.get(index).getEquipmentEffect());
            consu.put("type" , equipements.get(index).getEquipmentType();

            equipmentList.add(consu);

        }

        try (FileWriter file = new FileWriter("/HeroManager/resources/equipment.json")) {
            file.write(equipmentList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
