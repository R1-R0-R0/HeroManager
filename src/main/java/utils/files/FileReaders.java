package utils.files;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.Job;
import model.spell.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Class that use JSONObject from FileManager to change it into Java Object
 */
public class FileReaders {
    /**
     * Call for FileManager and return a List of Weapon
     *
     * @return List of weapon
     */
    public static List<Weapon> getWeapons() {
        try {
            JSONObject weaponsString = FileManager.getFile("weapons.json");
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

    /**
     * Call for FileManager and return a List of Spells
     *
     * @return List<Spell>
     */
    public static List<Spell> getSpells() {
        return null;
    }

    /**
     * Call for FileManager and return a List of Consumable
     *
     * @return List of consumable
     */
    public static List<Consumable> getConsumable() {
        try {
            JSONObject obj = FileManager.getFile("consumable");

            JSONArray jsonArray = (JSONArray) obj.get("consumable");

            Iterator<String> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }


        } catch (FileNotFoundException e) {
            FileCreator.createFile("consumable.json");
        }
        return null;
    }

    /**
     * Call for FileManager and return a List of Equipment
     *
     * @return List of Equipement
     */
    public static List<Equipment> getEquipement() {
        return null;
    }

    /**
     * Call for FileManager and return a List of Caracters
     *
     * @return Lits of Job
     */
    public static List<Job> getCaracters() {
        return null;
    }

    /*public static List<Race> getRaces() throws IOException{
        try{
            JSONObject obj = FileManager.getFile("race");



        }
        catch (FileNotFoundException e){
            FileWriter.createFile("race.json");
        }
        return null;
    }



    public static void main(String[] args) throws IOException {
        List<Consumable> consumable = getConsumable();
    }*/


}


