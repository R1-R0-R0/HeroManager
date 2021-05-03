package model.Utils;

import model.items.weapons.Weapon;
import model.spell.Spell;

import java.io.FileReader;
import java.util.List;

public class HeroManagerDB {

    private static List<Weapon> weapons;
    private static List<Spell> spells;

    public static void init() {
        weapons = FileReaders.getWeapons();
        spells = FileReaders.getSpells();
    }

    public static List<Weapon> getWeapons() {
        return weapons;
    }

    public static Weapon getWeapon(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(name))
                return weapon;
        }

        return null;
    }
}
