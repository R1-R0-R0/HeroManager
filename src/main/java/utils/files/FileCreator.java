package utils.files;

import java.io.FileWriter;

/**
 * Class to create a new File with a Enum from DefaultFileContent
 */
public class FileCreator {
    /**
     * default located save
     */
    public final static String SAVE_DIRECTORY = "./data/";

    /**
     * Method to create a File if it was deleted
     *
     * @param name name of the created file
     */
    public static void createFile(String name) {
        FileWriter file = new FileWriter("src/main/resources/donnee/" + name + ".json");
        switch (name){
            case "weapon" :file.write(DefaultFileContent.WEAPONS);;
            case "consumable":;
            case "equipment":;
            case "spell":;
        }

        file.flush();
    }
}
