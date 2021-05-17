package utils.files;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

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
    public static JSONObject getFile(String name) throws IOException {

        JSONParser jsonParser = new JSONParser();
        try {
            return (JSONObject) jsonParser.parse(new FileReader(SAVE_DIRECTORY + name + ".json"));
        } catch (FileNotFoundException | ParseException e) {
            FileCreator.createFile(name);

        }

        return getFile(name);
    }

    /**
     * Write a specifed file
     *
     * @param name      name of the file
     * @param jsonArray contend that will be write in the file
     */
    public static void writeFile(String name, JSONObject jsonArray) {
        try {
            PrintWriter writer = new PrintWriter(SAVE_DIRECTORY + name + ".json");
            writer.print("");
            writer.close();
            FileWriter file = new FileWriter(SAVE_DIRECTORY + name + ".json");
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
