package utils.files;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.Job;
import model.spell.Spell;

import java.io.IOException;
import java.util.List;

/**
 * This class is the main Data Base for the application to pick item she need from it.
 */
public class HeroManagerDB {
    /**
     * Data that are saved
     */
    private static List<Weapon> weapons;
    private static List<Spell> spells;
    private static List<Consumable> consumables;
    private static List<Equipment> equipments;
    private static List<Job> jobs;

    /**
     * Methode witch call on FileReaders getter to initialize the DataBase
     *
     * @throws IOException
     */
    public static void init() throws IOException {
        weapons = FileReaders.getWeapons();
        spells = FileReaders.getSpells();
        consumables = FileReaders.getConsumable();
        equipments = FileReaders.getEquipement();
        jobs = FileReaders.getCaracters();
    }

    /**
     * Get a list of Weapons
     *
     * @return List of Weapon
     */
    public static List<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Get a list of Spells
     *
     * @return List of Spell
     */
    public static List<Spell> getSpells() {
        return spells;
    }

    /**
     * Get a list of Consumable
     *
     * @return List of consumable
     */
    public static List<Consumable> getConsumables() {
        return consumables;
    }

    /**
     * Get a list of Equipment
     *
     * @return List of Equipment
     */
    public static List<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * Get a list of Caracters
     *
     * @return List of Job
     */
    public static List<Job> getFiche() {
        return jobs;
    }

    /**
     * Get a specified Weapon
     *
     * @param name name of the weapon
     * @return a Weapon
     */
    public static Weapon getWeapon(String name) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equals(name))
                return weapon;
        }

        return null;
    }

    /**
     * Get a specified Spell
     *
     * @param name of the spell
     * @return a Spell
     */
    public static Spell getSpell(String name) {
        return null;
    }

    /**
     * Get a specified Consumable
     *
     * @param name of the consumable
     * @return a Consumable
     */
    public static Consumable getConsumable(String name) {
        return null;
    }

    /**
     * Get a specified Equipment
     *
     * @param name of the quipment
     * @return an Equipment
     */
    public static Equipment getEquipment(String name) {
        return null;
    }

    /**
     * Get a specified Character
     *
     * @param name of the character
     * @return a Job
     */
    public static Job getJob(String name) {
        return null;
    }

    /**
     * Find and change a specified weapon into the DB
     *
     * @param weapon the modified weapon
     */
    public static void modifyWeapon(Weapon weapon) {
    }

    /**
     * Find and change a specified spell into the DB
     *
     * @param spell the modified spell
     */
    public static void modifySpell(Spell spell) {
    }

    /**
     * Find and change a specified consumable into the DB
     *
     * @param consumable the modified consumable
     */
    public static void modifyConsumable(Consumable consumable) {
    }

    /**
     * Find and change a specified equipment into the DB
     *
     * @param equipment the modified equipment
     */
    public static void modifyEquipment(Equipment equipment) {
    }

    /**
     * Find and change a specified character into the DB
     *
     * @param job the modified job
     */
    public static void modifyJob(Job job) {
    }

    /**
     * Add the weapon to the Data Base
     *
     * @param weapon the added weapon
     */
    public static void addWeapons(Weapon weapon) {
    }

    /**
     * Add the spell to the Data Base
     *
     * @param spell the added spell
     */
    public static void addSpell(Spell spell) {
    }

    /**
     * Add the consumable to the Data Base
     *
     * @param consumable the added consumable
     */
    public static void addConsumable(Consumable consumable) {
    }

    /**
     * Add the equipment to the Data Base
     *
     * @param equipment the added equipment
     */
    public static void addEquipment(Equipment equipment) {
    }

    /**
     * Add the job to the Data Base
     *
     * @param job the added job
     */
    public static void addJob(Job job) {
    }

    /**
     * Methode that call on Writer to save all the DataBase on Json save file
     */
    public static void Save() {
    }


}
