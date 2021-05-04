package utils.files;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.spell.Spell;

import java.io.IOException;
import java.util.List;

public class HeroManagerDB {

    private static List<Weapon> weapons;
    private static List<Spell> spells;
    private static List<Consumable> consumables;
    private static List<Equipment> equipments;


    public static void init() throws IOException {
        weapons = FileReaders.getWeapons();
        spells = FileReaders.getSpells();
        consumables = FileReaders.getConsumable();
        equipments = FileReaders.getEquipement();
    }

    public static List<Weapon> getWeapons() {
        return weapons;
    }


    public static List<Spell> getSpells() {
        return spells;
    }

    public static List<Consumable> getConsumables() {
        return consumables;
    }

    public static List<Equipment> getEquipments() {
        return equipments;
    }

    public static Weapon getWeapon(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(name))
                return weapon;
        }

        return null;
    }
}
