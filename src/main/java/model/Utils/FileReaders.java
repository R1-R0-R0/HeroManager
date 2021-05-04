package model.Utils;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.Improvement;
import model.job.Job;
import model.race.Race;
import model.spell.Spell;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FileReaders {

    public static List<Weapon> getWeapons() throws IOException {
        try {
            JSONArray weaponsString = FileManager.getFile("weapons.json");
        } catch (FileNotFoundException e) {
            /*
            File file = new File(name);
            FileWriter writer = new FileWriter(file);
            writer.write(DefaultFileContent.WEAPONS.toString());
            weaponsString = DefaultFileContent.WEAPONS.toString();
             */
        }

        // TODO : Process JSONPaser sur weaponsString
        return null;
    }

    public static List<Spell> getSpells() {
        return null;
    }

    public static List<Consumable> getConsumable() {return null;}

    public static List<Equipment> getEquipement() {return null;}

    public static List<Race> getRaces() throws IOException{
        try{
            JSONArray jsonArray = FileManager.getFile("race.json");

            Iterator<String> iterator = jsonArray.iterator();

            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
        catch (FileNotFoundException e){
            FileWriter.createFile("race.json");
        }
        return null;
    }

    public static Job getCaracter (){
        return null;
    }

    /*public static void main(String[] args) throws IOException {
        List<Race> races = getRaces();
    }*/


}


