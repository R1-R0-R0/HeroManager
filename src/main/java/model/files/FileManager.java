package model.files;

import javafx.scene.control.Alert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.gui.Dialog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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
        checkOrCreateDir();

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
        checkOrCreateDir();

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

    /**
     * Creates save directory if non-existent
     */
    private static void checkOrCreateDir() {
        try {
            if (Files.notExists(Path.of(SAVE_DIRECTORY))) {
                Files.createDirectory(Path.of(SAVE_DIRECTORY));
            }
        } catch (IOException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), e.getLocalizedMessage()).showAndWait();
            System.exit(1);
        }
    }
}
