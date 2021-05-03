package model.Utils;

import model.items.weapons.Weapon;
import model.spell.Spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileReaders {

    public static List<Weapon> getWeapons() throws IOException {
        try {
            String weaponsString = FileManager.getFile("weapons.json");
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

    public static List<Spell> getSpells() {
        return null;
    }
}
