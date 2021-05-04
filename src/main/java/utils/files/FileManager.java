package utils.files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    public final static String SAVE_DIRECTORY = "./data/";

    public static JSONObject getFile(String name) throws FileNotFoundException {

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/donnee/"+name+".json"));


            return jsonObject;
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
