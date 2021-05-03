package model.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {
    public final static String SAVE_DIRECTORY = "./data/";

    public static String getFile(String name) throws FileNotFoundException {
        try {
            File file = new File(SAVE_DIRECTORY + name);
            FileInputStream reader = new FileInputStream(file);

            return new String(reader.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String writeFile(String name, String content) {
        // TODO

        return null;
    }

    public static void createFile(String name) {
        // TODO

        /* Un exemple (à implémenter ailleurs)
        File file = new File(name);
        FileWriter writer = new FileWriter(file);
        writer.write(DefaultFileContent.WEAPONS.toString());
         */
    }
}
