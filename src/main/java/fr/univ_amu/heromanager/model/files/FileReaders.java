package fr.univ_amu.heromanager.model.files;

import fr.univ_amu.heromanager.exceptions.UnknownJobSkillException;
import fr.univ_amu.heromanager.exceptions.UnknownRaceException;
import javafx.scene.control.Alert;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentInventory;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import fr.univ_amu.heromanager.model.items.weapons.DamageType;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.model.items.weapons.WeaponType;
import fr.univ_amu.heromanager.model.job.*;
import fr.univ_amu.heromanager.model.race.Alignment;
import fr.univ_amu.heromanager.model.race.Race;
import fr.univ_amu.heromanager.model.spell.Component;
import fr.univ_amu.heromanager.model.spell.Spell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import fr.univ_amu.heromanager.utils.gui.Dialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static fr.univ_amu.heromanager.model.job.JobType.SORCERER;

/**
 * Class that use JSONObject from FileManager to change it into Java Object
 */
public class FileReaders {
    /**
     * Call for FileManager and return a List of Weapon
     *
     * @return List of weapon
     */
    public static List<Weapon> getWeapons() throws IOException {
        List<Weapon> weapons = new ArrayList<>();
        int count = 0;
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<String> proerties = new ArrayList<>();
        List<WeaponType> weaponTypes = new ArrayList<>();
        List<DamageType> damageTypes = new ArrayList<>();

        try {
            JSONObject obj = FileManager.getFile("weapon");

            JSONArray jsonArray = (JSONArray) obj.get("weapon");

            Iterator<String> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                switch (count % 5) {
                    case 0:
                        names.add(iterator.next());
                        break;
                    case 1:
                        descriptions.add(iterator.next());
                        break;
                    case 2:
                        proerties.add(iterator.next());
                        break;
                    case 3:
                        switch (iterator.next().toLowerCase()) {
                            case "war" -> weaponTypes.add(WeaponType.WAR);
                            case "common" -> weaponTypes.add(WeaponType.COMMON);
                        }
                        break;
                    case 4:
                        switch (iterator.next().toLowerCase()) {
                            case "slashing" -> damageTypes.add(DamageType.SLASHING);
                            case "piercing" -> damageTypes.add(DamageType.PIERCING);
                            case "bludgeoning" -> damageTypes.add(DamageType.BLUDGEONING);
                        }
                        break;
                }
                count++;
            }
            for (int x = 0; x < names.size(); x++) {
                weapons.add(new Weapon(names.get(x), descriptions.get(x), proerties.get(x), weaponTypes.get(x), damageTypes.get(x)));
            }


        } catch (FileNotFoundException e) {
            FileCreator.createFile("weapon.json");
            getWeapons();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weapons;
    }

    /**
     * Call for FileManager and return a List of Spells
     *
     * @return List<Spell>
     */
    public static List<Spell> getSpells() throws IOException {
        List<Spell> spells = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<String> school = new ArrayList<>();
        List<String> castingTimes = new ArrayList<>();
        List<String> duration = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        List<Integer> range = new ArrayList<>();
        List<JobType> jobTypes = new ArrayList<>();
        List<Boolean> doDamages = new ArrayList<>();
        List<List<Component>> components = new ArrayList<>();

        try {
            JSONObject obj = FileManager.getFile("spell");

            JSONArray jsonArray = (JSONArray) obj.get("spell");

            for (int i = 0; i < jsonArray.size(); i++) {
                switch (i % 10) {
                    case 0:
                        names.add((String) jsonArray.get(i));
                        break;
                    case 1:
                        descriptions.add((String) jsonArray.get(i));
                        break;
                    case 2:
                        school.add((String) jsonArray.get(i));
                        break;
                    case 3:
                        castingTimes.add((String) jsonArray.get(i));
                        break;
                    case 4:
                        duration.add((String) jsonArray.get(i));
                        break;
                    case 5:
                        level.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 6:
                        range.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 7:
                        switch ((String) jsonArray.get(i)) {
                            case "BARBARIAN" -> jobTypes.add(JobType.BARBARIAN);
                            case "BARD" -> jobTypes.add(JobType.BARD);
                            case "CLERIC" -> jobTypes.add(JobType.CLERIC);
                            case "DRUID" -> jobTypes.add(JobType.DRUID);
                            case "FIGHTER" -> jobTypes.add(JobType.FIGHTER);
                            case "MONK" -> jobTypes.add(JobType.MONK);
                            case "PALADIN" -> jobTypes.add(JobType.PALADIN);
                            case "RANGER" -> jobTypes.add(JobType.RANGER);
                            case "ROGUE" -> jobTypes.add(JobType.ROGUE);
                            case "SORCERER" -> jobTypes.add(JobType.SORCERER);
                            case "WARLOCK" -> jobTypes.add(JobType.WARLOCK);
                            case "WIZARD" -> jobTypes.add(JobType.WIZARD);
                        }
                        break;
                    case 8:
                        doDamages.add((Boolean) jsonArray.get(i));
                        break;
                    case 9: {
                        JSONArray comp = (JSONArray) jsonArray.get(i);
                        List<Component> componentList = new ArrayList<>();

                        for (Object o : comp) {
                            switch ((String) o) {
                                case "V" -> componentList.add(Component.VOCAL);
                                case "S" -> componentList.add(Component.MATERIAL);
                                case "M" -> componentList.add(Component.MOVEMENT);
                            }
                        }
                        components.add(componentList);
                    }
                    break;
                }
            }
            for (int x = 0; x < names.size(); x++) {
                spells.add(new Spell(names.get(x), descriptions.get(x), school.get(x), castingTimes.get(x), duration.get(x), level.get(x),
                        range.get(x), jobTypes.get(x), doDamages.get(x), components.get(x)));
            }


        } catch (FileNotFoundException e) {
            FileCreator.createFile("spell.json");
            getSpells();
        }
        return spells;
    }

    /**
     * Call for FileManager and return a List of Consumable
     *
     * @return List of consumable
     */
    public static List<Consumable> getConsumable() throws IOException {
        List<Consumable> consumables = new ArrayList<>();
        int count = 0;
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();

        try {
            JSONObject obj = FileManager.getFile("consumable");

            JSONArray jsonArray = (JSONArray) obj.get("consumable");

            Iterator<String> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                switch (count % 2) {
                    case 0 -> names.add(iterator.next());
                    case 1 -> descriptions.add(iterator.next());
                }
                count++;
            }
            for (int x = 0; x < names.size(); x++) {
                consumables.add(new Consumable(names.get(x), descriptions.get(x)));
            }


        } catch (FileNotFoundException e) {
            FileCreator.createFile("consumable.json");
            getConsumable();
        }
        return consumables;
    }

    /**
     * Call for FileManager and return a List of Equipment
     *
     * @return List of Equipement
     */
    public static List<Equipment> getEquipement() throws IOException {
        List<Equipment> equipment = new ArrayList<>();
        int count = 0;
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<EquipmentPart> equipmentPart = new ArrayList<>();
        List<Integer> armorBonus = new ArrayList<>();
        List<EquipmentType> types = new ArrayList<>();
        List<Integer> strengthBoost = new ArrayList<>();
        List<Integer> dexterityBoost = new ArrayList<>();
        List<Integer> robustnessBoost = new ArrayList<>();
        List<Integer> intelligenceBoost = new ArrayList<>();
        List<Integer> wisdomBoost = new ArrayList<>();
        List<Integer> charismaBoost = new ArrayList<>();
        List<Integer> speedBoost = new ArrayList<>();

        try {
            JSONObject obj = FileManager.getFile("equipment");

            JSONArray jsonArray = (JSONArray) obj.get("equipment");


            for (Object o : jsonArray) {
                {
                    switch (count % 12) {
                        case 0:
                            names.add((String) o);
                            break;
                        case 1:
                            descriptions.add((String) o);
                            break;
                        case 2:
                            switch ((String) o) {
                                case "HEAD" -> equipmentPart.add(EquipmentPart.HEAD);
                                case "BODY" -> equipmentPart.add(EquipmentPart.BODY);
                                case "BELT" -> equipmentPart.add(EquipmentPart.BELT);
                                case "LEGS" -> equipmentPart.add(EquipmentPart.LEGS);
                                case "FEET" -> equipmentPart.add(EquipmentPart.FEET);
                                case "AMULET" -> equipmentPart.add(EquipmentPart.AMULET);
                                case "HANDS" -> equipmentPart.add(EquipmentPart.HANDS);
                                case "MANTLE" -> equipmentPart.add(EquipmentPart.MANTLE);
                                case "RING" -> equipmentPart.add(EquipmentPart.RING);
                            }
                            break;
                        case 3:
                            armorBonus.add(Integer.parseInt(o.toString()));
                            break;
                        case 4:
                            switch ((String) o) {
                                case "LIGHT" -> types.add(EquipmentType.LIGHT);
                                case "MEDIUM" -> types.add(EquipmentType.MEDIUM);
                                case "HEAVY" -> types.add(EquipmentType.HEAVY);
                            }
                            break;
                        case 5:
                            strengthBoost.add(Integer.parseInt(o.toString()));
                            break;
                        case 6:
                            dexterityBoost.add(Integer.parseInt(o.toString()));
                            break;
                        case 7:
                            robustnessBoost.add(Integer.parseInt(o.toString()));
                            break;
                        case 8:
                            intelligenceBoost.add(Integer.parseInt(o.toString()));
                            break;
                        case 9:
                            wisdomBoost.add(Integer.parseInt(o.toString()));
                            break;
                        case 10:
                            charismaBoost.add(Integer.parseInt(o.toString()));
                            break;
                        case 11:
                            speedBoost.add(Integer.parseInt(o.toString()));
                            break;
                    }
                }
                count++;
            }

            for (int x = 0; x < names.size(); x++) {
                equipment.add(new Equipment(names.get(x), descriptions.get(x), equipmentPart.get(x), armorBonus.get(x), types.get(x),
                        strengthBoost.get(x), dexterityBoost.get(x), robustnessBoost.get(x), intelligenceBoost.get(x), wisdomBoost.get(x),
                        charismaBoost.get(x), speedBoost.get(x)));
            }
        } catch (FileNotFoundException e) {
            FileCreator.createFile("equipment.json");
            getEquipement();
        }
        return equipment;
    }

    /**
     * Call for FileManager and return a List of Characters
     *
     * @return List of Job
     */
    public static List<Job> getCaracters() {
        List<Job> jobs = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();
        List<Gender> genders = new ArrayList<>();
        List<Alignment> alignments = new ArrayList<>();
        List<Race> races = new ArrayList<>();
        List<JobType> jobTypes = new ArrayList<>();
        List<List<Spell>> spells = new ArrayList<>();
        List<List<JobSkill>> skills = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        List<Integer> strength = new ArrayList<>();
        List<Integer> dexterity = new ArrayList<>();
        List<Integer> robustness = new ArrayList<>();
        List<Integer> wisdom = new ArrayList<>();
        List<Integer> intelligence = new ArrayList<>();
        List<Integer> charisma = new ArrayList<>();
        List<Integer> speed = new ArrayList<>();
        List<Integer> hp = new ArrayList<>();
        List<Integer> armor = new ArrayList<>();
        List<Integer> points = new ArrayList<>();
        List<List<Improvement>> improvements = new ArrayList<>();
        List<EquipmentInventory> equipments = new ArrayList<>();
        List<List<Item>> inventory = new ArrayList<>();

        try {
            JSONObject obj = FileManager.getFile("job");

            JSONArray jsonArray = (JSONArray) obj.get("job");

            for (int i = 0; i < jsonArray.size(); i++) {
                switch (i % 22) {
                    case 0:
                        names.add((String) jsonArray.get(i));
                        break;
                    case 1:
                        descriptions.add((String) jsonArray.get(i));
                        break;
                    case 2:
                        switch ((String) jsonArray.get(i)) {
                            case "MAN" -> genders.add(Gender.MAN);
                            case "WOMAN" -> genders.add(Gender.WOMAN);
                        }
                        break;
                    case 3:
                        switch ((String) jsonArray.get(i)) {
                            case "NEUTRAL_GOOD" -> alignments.add(Alignment.NEUTRAL_GOOD);
                            case "NEUTRAL_NEUTRAL" -> alignments.add(Alignment.NEUTRAL_NEUTRAL);
                            case "NEUTRAL_EVIL" -> alignments.add(Alignment.NEUTRAL_EVIL);
                            case "LAWFUL_GOOD" -> alignments.add(Alignment.LAWFUL_GOOD);
                            case "LAWFUL_NEUTRAL" -> alignments.add(Alignment.LAWFUL_NEUTRAL);
                            case "LAWFUL_EVIL" -> alignments.add(Alignment.LAWFUL_EVIL);
                            case "CHAOTIC_GOOD" -> alignments.add(Alignment.CHAOTIC_GOOD);
                            case "CHAOTIC_NEUTRAL" -> alignments.add(Alignment.CHAOTIC_NEUTRAL);
                            case "CHAOTIC_EVIL" -> alignments.add(Alignment.CHAOTIC_EVIL);
                        }
                        break;
                    case 4:
                        try {
                            races.add(Race.findRace((String) jsonArray.get(i)));
                            break;
                        } catch (UnknownRaceException e) {
                            new Dialog("Error while loading data", e).showAndWait();
                        }
                    case 5:
                        switch ((String) jsonArray.get(i)) {
                            case "BARBARIAN" -> jobTypes.add(JobType.BARBARIAN);
                            case "BARD" -> jobTypes.add(JobType.BARD);
                            case "CLERIC" -> jobTypes.add(JobType.CLERIC);
                            case "DRUID" -> jobTypes.add(JobType.DRUID);
                            case "FIGHTER" -> jobTypes.add(JobType.FIGHTER);
                            case "MONK" -> jobTypes.add(JobType.MONK);
                            case "PALADIN" -> jobTypes.add(JobType.PALADIN);
                            case "RANGER" -> jobTypes.add(JobType.RANGER);
                            case "ROGUE" -> jobTypes.add(JobType.ROGUE);
                            case "SORCERER" -> jobTypes.add(JobType.SORCERER);
                            case "WARLOCK" -> jobTypes.add(JobType.WARLOCK);
                            case "WIZARD" -> jobTypes.add(JobType.WIZARD);
                        }
                        break;
                    case 6: {
                        JSONArray spell = (JSONArray) jsonArray.get(i);
                        List<Spell> name = new ArrayList<>();

                        for (Object o : spell) {
                            name.add(new Spell((String) o, "", "", "", "", 0, 0, SORCERER, false, new ArrayList<>()));
                        }
                        spells.add(name);
                    }
                    break;

                    case 7: {
                        JSONArray skill = (JSONArray) jsonArray.get(i);
                        List<JobSkill> modfy = new ArrayList<>();

                        for (Object o : skill) {
                            try {
                                String s = (String) o;
                                modfy.add(JobSkill.getJobSkill(s.toUpperCase()));
                            } catch (UnknownJobSkillException e) {
                                e.printStackTrace();
                                new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace())).showAndWait();
                            }
                        }
                        skills.add(modfy);
                    }
                    break;
                    case 8:
                        level.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 9:
                        strength.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 10:
                        dexterity.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 11:
                        robustness.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 12:
                        intelligence.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 13:
                        wisdom.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 14:
                        charisma.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 15:
                        speed.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 16:
                        hp.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 17:
                        armor.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 18:
                        points.add(Integer.parseInt(jsonArray.get(i).toString()));
                        break;
                    case 19: {
                        JSONArray improvment = (JSONArray) jsonArray.get(i);
                        List<Improvement> name = new ArrayList<>();

                        for (Object o : improvment) {
                            switch ((String) o) {
                                case "Darkvision" -> name.add(Improvement.DARKVISION);
                                case "Dwarven Resilience" -> name.add(Improvement.DWARVEN_RESILIENCE);
                                case "Dwarven Combat Training" -> name.add(Improvement.DWARVEN_COMBAT_TRAINING);
                                case "Tool Proficiency" -> name.add(Improvement.TOOL_PROFICIENCY);
                                case "Stonecunning" -> name.add(Improvement.STONECUNNING);
                                case "Keen Senses" -> name.add(Improvement.KEEN_SENSES);
                                case "Fey Ancestry" -> name.add(Improvement.FEY_ANCESTRY);
                                case "Trance" -> name.add(Improvement.TRANCE);
                                case "Lucky" -> name.add(Improvement.LUCKY);
                                case "Brave" -> name.add(Improvement.BRAVE);
                                case "Halfling Nimbleness" -> name.add(Improvement.HALFLING_NIMBLENESS);
                                case "Draconic Ancestry" -> name.add(Improvement.DRACONIC_ANCESTRY);
                                case "Breath Weapon" -> name.add(Improvement.BREATH_WEAPON);
                                case "Damage Resistance" -> name.add(Improvement.DAMAGE_RESISTANCE);
                                case "Gnome cunning" -> name.add(Improvement.GNOME_CUNNING);
                                case "Skill Versatility" -> name.add(Improvement.SKILL_VERSATILITY);
                                case "Menacing" -> name.add(Improvement.MENACING);
                                case "Relentless Endurance" -> name.add(Improvement.RELENTLESS_ENDURANCE);
                                case "Savage Attacks" -> name.add(Improvement.SAVAGE_ATTACKS);
                                case "Hellish Resistance" -> name.add(Improvement.HELLISH_RESISTANCE);
                                case "Infernal Legacy" -> name.add(Improvement.INFERNAL_LEGACY);
                                case "Flight" -> name.add(Improvement.FLIGHT);
                                case "Talons" -> name.add(Improvement.TALONS);
                            }
                        }
                        improvements.add(name);
                    }
                    break;
                    case 20: {
                        JSONArray equipment = (JSONArray) jsonArray.get(i);
                        List<Equipment> adding = new ArrayList<>();

                        for (Object o : equipment) {
                            adding.add(new Equipment((String) o, "", EquipmentPart.HEAD, 0, EquipmentType.LIGHT, 0, 0, 0, 0, 0, 0, 0));
                        }
                        EquipmentInventory name = new EquipmentInventory(adding);
                        equipments.add(name);
                    }
                    break;
                    case 21: {
                        JSONArray item = (JSONArray) jsonArray.get(i);
                        List<Item> name = new ArrayList<>();

                        for (int y = 0; y < item.size(); y++) {
                            int finalY = y;
                            name.add(new Item() {
                                         @Override
                                         public String getName() {
                                             return (String) item.get(finalY);
                                         }

                                         @Override
                                         public String getDescription() {
                                             return null;
                                         }
                                     }
                            );
                        }
                        inventory.add(name);
                    }
                    break;
                }
            }
            for (int x = 0; x < names.size(); x++) {
                jobs.add(new Job(names.get(x), descriptions.get(x), genders.get(x), alignments.get(x), races.get(x), jobTypes.get(x), spells.get(x), skills.get(x), level.get(x), strength.get(x),
                        dexterity.get(x), robustness.get(x), wisdom.get(x), intelligence.get(x), charisma.get(x), speed.get(x), hp.get(x), armor.get(x), points.get(x), improvements.get(x),
                        equipments.get(x), inventory.get(x)));
            }


        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }


}


