package utils.files;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to create a new File with a Enum from DefaultFileContent
 */
public class FileCreator {

    /**
     * Method to create a File if it was deleted
     *
     * @param name name of the created file
     */
    public static void createFile(String name) throws IOException {
        try {
            FileWriter file = new FileWriter(FileManager.SAVE_DIRECTORY + name + ".json");
            switch (name) {
                case "weapon":
                    file.write(DefaultFileContent.WEAPONS.toString());break;
                case "consumable":
                    file.write(DefaultFileContent.CONSUMABLE.toString());break;
                case "equipment":
                    file.write(DefaultFileContent.EQUIPMENT.toString());break;
                case "spell":
                    file.write(DefaultFileContent.SPEELS.toString());break;
                case "job":
                    file.write("{\"job\" : []}");break;
            }

            file.flush();
        }
        catch (IOException e){
            System.exit(0);
        }
    }
}
