package utils.files;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.io.FileWriter;

/**
 * Class to manage the save files
 */
public class FileManager {
    /**
     * Default save point
     */
    public final static String SAVE_DIRECTORY = "./data/";

    /**
     * Get designed file
     *
     * @param name Name of the file
     * @return JsonObjet to be use by other method
     * @throws FileNotFoundException if file do not exist
     */
    public static JSONObject getFile(String name) throws FileNotFoundException {

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/donnee/" + name + ".json"));


            return jsonObject;
        } catch (IOException | ParseException e) {
            FileCreator.createFile(name);
        }

        return null;
    }

    /**
     * Write a specifed file
     *
     * @param name      name of the file
     * @param jsonArray contend that will be write in the file
     */
    public static void writeFile(String name, JSONObject jsonArray) {
        try {
            java.io.FileWriter file = new FileWriter("src/main/resources/donnee/" + name + ".json");
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}