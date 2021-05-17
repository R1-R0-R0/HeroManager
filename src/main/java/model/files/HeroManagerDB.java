package model.files;

import model.items.consumables.Consumable;
import model.items.equipments.Equipment;
import model.items.weapons.Weapon;
import model.job.*;
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
     * Method witch call on FileReaders getter to initialize the DataBase
     * @throws IOException if files doesn't exist
     */
    public static void init() throws IOException {
        weapons = FileReaders.getWeapons();
        spells = FileReaders.getSpells();
        consumables = FileReaders.getConsumable();
        equipments = FileReaders.getEquipement();
        jobs = FileReaders.getCaracters();
        initJobs();
    }

    /**
     * Found designed item into the database and set it for all jobs
     * https://www.youtube.com/watch?v=YPN0qhSyWy8
     */
    private static void initJobs() {
        for (Job job : jobs) {
            for (int i = 0; i < job.getSpellInventory().size(); i++) {
                job.getSpellInventory().set(i, getSpell(job.getSpellInventory().get(i).getName()));
            }
            for (int i = 0; i < job.getInventory().size(); i++) {
                String name = job.getInventory().get(i).getName();
                job.getInventory().set(i, getWeapon(name));
                if (job.getInventory().get(i) == null) {
                    job.getInventory().set(i, getConsumable(name));
                }
                if (job.getInventory().get(i) == null) {
                    job.getInventory().set(i, getEquipment(name));
                }
            }
            for (int i = 0; i < job.getEquippedEquipments().getEquippedList().size(); i++) {
                if (job.getEquippedEquipments().getEquippedList().get(i) != null) {
                    job.getEquippedEquipments().getEquippedList().set(i, getEquipment(job.getEquippedEquipments().getEquippedList().get(i).getName()));
                }
            }
        }
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
    public static List<Job> getJobs() {
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
        for (Spell spell : spells) {
            if (spell.getName().equals(name))
                return spell;
        }
        return null;
    }

    /**
     * Get a specified Consumable
     *
     * @param name of the consumable
     * @return a Consumable
     */
    public static Consumable getConsumable(String name) {
        for (Consumable consumable : consumables) {
            if (consumable.getName().equals(name))
                return consumable;
        }
        return null;
    }

    /**
     * Get a specified Equipment
     *
     * @param name of the quipment
     * @return an Equipment
     */
    public static Equipment getEquipment(String name) {
        for (Equipment equipment : equipments) {
            if (equipment.getName().equals(name))
                return equipment;
        }
        return null;
    }

    /**
     * Get a specified Character
     *
     * @param name of the character
     * @return a Job
     */
    public static Job getJob(String name) {
        for (Job job : jobs) {
            if (job.getName().equals(name))
                return job;
        }
        return null;
    }

    /**
     * Find and change a specified weapon into the DB
     *
     * @param weapon the modified weapon
     */
    public static void modifyWeapon(Weapon weapon) {
        for (Weapon modify : weapons) {
            if (modify.getName().equals(weapon.getName())) {
                modify.setDescription(weapon.getDescription());
                modify.setProperties(weapon.getProperties());
                modify.setWeaponType(weapon.getWeaponType());
                modify.setDamageType(weapon.getDamageType());
            }
        }
    }

    /**
     * Find and change a specified spell into the DB
     *
     * @param spell the modified spell
     */
    public static void modifySpell(Spell spell) {
        for (Spell modify : spells) {
            if (modify.getName().equals(spell.getName())) {
                modify.setDescription(spell.getDescription());
                modify.setSchool(spell.getSchool());
                modify.setCastingTime(spell.getCastingTime());
                modify.setDuration(spell.getDuration());
                modify.setComponents(spell.getComponents());
                modify.setLevel(spell.getLevel());
                modify.setRange(spell.getRange());
                modify.setDoDamages(spell.isDoDamages());
                modify.setJobType(spell.getJobType());
            }
        }
    }


    /**
     * Find and change a specified character into the DB
     *
     * @param job the modified job
     */
    public static void modifyJob(Job job) {
        for (int i = 0; i < jobs.size(); i++) {
            Job change = jobs.get(0);
            if (jobs.get(i).getName().equals(job.getName())) {
                jobs.get(i).setLevel(job.getLevel());
                jobs.get(i).setStrength(job.getStrength());
                jobs.get(i).setDexterity(job.getDexterity());
                jobs.get(i).setIntelligence(job.getIntelligence());
                jobs.get(i).setWisdom(job.getWisdom());
                jobs.get(i).setRobustness(job.getRobustness());
                jobs.get(i).setCharisma(job.getCharisma());
                jobs.get(i).setSpeed(job.getSpeed());
                jobs.get(i).setEquippedEquipments(job.getEquippedEquipments());
                jobs.get(i).setSpellSlots(job.getSpellSlots());
                jobs.get(i).setInventory(job.getInventory());

                jobs.set(0, jobs.get(i));
                jobs.set(i, change);
            }
        }
    }

    /**
     * Add the weapon to the Data Base
     *
     * @param weapon the added weapon
     */
    public static void addWeapons(Weapon weapon) {
        for (Weapon target : weapons) {
            if (target.getName().equals(weapon.getName()))
                System.exit(0);

        }
        weapons.add(weapon);
    }

    /**
     * Add the spell to the Data Base
     *
     * @param spell the added spell
     */
    public static void addSpell(Spell spell) {
        spells.add(spell);
    }

    /**
     * Add the consumable to the Data Base
     *
     * @param consumable the added consumable
     */
    public static void addConsumable(Consumable consumable) {
        for (Consumable target : consumables) {
            if (target.getName().equals(consumable.getName()))
                System.exit(0);

        }
        consumables.add(consumable);
    }

    /**
     * Add the equipment to the Data Base
     *
     * @param equipment the added equipment
     */
    public static void addEquipment(Equipment equipment) {
        for (Equipment target : equipments) {
            if (target.getName().equals(equipment.getName()))
                System.exit(0);

        }
        equipments.add(equipment);
    }

    /**
     * Add the job to the Data Base
     *
     * @param job the added job
     */
    public static void addJob(Job job) {
        for (Job target : jobs) {
            if (target.getName().equals(job.getName()))
                System.exit(0);

        }

        jobs.add(job);
    }

    /**
     * Methode that call on Writer to save all the DataBase on Json save file
     */
    public static void Save() {
        Writer.writerWeapon(weapons);
        Writer.writerSpell(spells);
        Writer.writerConsumable(consumables);
        Writer.writerEquipment(equipments);
        Writer.writerJob(jobs);
    }

    /**
     * @return the last played Character
     */
    public static Job lastPlayed() {
        return jobs.get(0);
    }

}
