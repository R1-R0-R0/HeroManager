package utils.files;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.Job;
import model.spell.Spell;

import java.io.IOException;
import java.util.List;

public class HeroManagerDB {

    private static List<Weapon> weapons;
    private static List<Spell> spells;
    private static List<Consumable> consumables;
    private static List<Equipment> equipments;
    private static List<Job> jobs;


    public static void init() throws IOException {
        weapons = FileReaders.getWeapons();
        spells = FileReaders.getSpells();
        consumables = FileReaders.getConsumable();
        equipments = FileReaders.getEquipement();
        jobs = FileReaders.getCaracters();
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

    public static List<Job> getFiche() {
        return jobs;
    }

    public static Weapon getWeapon(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(name))
                return weapon;
        }

        return null;
    }

    public static Spell getSpell(String name) {
        return null;
    }

    public static Consumable getConsumable(String name) {
        return null;
    }

    public static Equipment getEquipment(String name) {
        return null;
    }

    public static Job getJob(String name) {
        return null;
    }

    public static void modifyWeapon(Weapon weapon) {
    }

    public static void modifySpell(Spell spell) {
    }

    public static void modifyConsumable(Consumable consumable) {
    }

    public static void modifyEquipment(Equipment equipment) {
    }

    public static void modifyJob(Job job) {
    }

    public static void addWeapons(Weapon weapon) {
    }

    public static void addSpell(Spell spell) {
    }

    public static void addConsumable(Consumable consumable) {
    }

    public static void addEquipment(Equipment equipment) {
    }

    public static void addJob(Job job) {
    }

    public static void Save() {
    }


}
