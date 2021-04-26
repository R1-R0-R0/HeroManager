package model.Utils;

import model.items.consumables.Consumable;
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

        //Write JSON file
        try (FileWriter file = new FileWriter("/HeroManager/resources/consumable.json")) {
            file.write(consumableList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
