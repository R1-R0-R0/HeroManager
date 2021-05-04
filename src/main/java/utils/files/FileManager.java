package utils.files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    public final static String SAVE_DIRECTORY = "./data/";

    public static JSONArray getFile(String name) throws FileNotFoundException {
        String[] parts = name.split(".");
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/ressources/"+ name));


            return (JSONArray) jsonObject.get(parts[0]);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static String writeFile(String name, String content) {
        // TODO

        return null;
    }
}
