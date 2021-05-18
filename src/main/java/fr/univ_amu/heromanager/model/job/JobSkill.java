package fr.univ_amu.heromanager.model.job;

import fr.univ_amu.heromanager.exceptions.UnknownJobSkillException;

import java.util.Arrays;
import java.util.List;

/**
 * List of all the jobSkills
 */

public enum JobSkill {
    ACROBATICS("Acrobatics", "Your dexterity check covers your attempt to stay on your feet in a tricky situation"),
    ATHLETICS("Athletics", "Your Strength check covers difficult situations you encounter while climbing, jumping or swimming"),
    ANIMAL_HANDLING("Animal Handling", "When there is any question whether you can calm down a domesticated animal," +
            " keep a mount from getting spooked or intuit an animal's intention, the DM might call for wisdom Check." +
            " You also make wisdom check to control your mount when you attempts risky maneuver"),
    ARCANA("Arcana", "Your Intelligence check measures your ability to recall lore about spells, magic items," +
            " eldritch symbols, magical traditions, the planes of existence, and the inhabitants of those planes"),
    DECEPTION("Deception", "Your Charisma check determines whether you can convincingly hide the truth, either verbally or through your actions." +
            "This deception can encompass everything from misleading others through ambiguity to telling outright lies. Typical situations include trying to fast-talk a guard," +
            " con a merchant, earn money through gambling, pass yourself off in a disguise, dull someone's suspicions with false assurances," +
            " or maintain a straight face while telling a blatant lie"),
    HISTORY("History", " Your Intelligence check measures your ability to recall lore about historical" +
            " events, legendary people, ancient kingdoms, past disputes, recent wars, and lost civilizations."),
    INTIMIDATION("Intimidation", "When you attempt to influence someone through overt threats, hostile actions, and physical violence," +
            " the DM might ask you to make a Charisma check. Examples include trying to pry information out of a prisoner," +
            " convincing street thugs to back down from a confrontation, or using the edge of a broken bottle to convince a sneering vizier to reconsider a decision."),
    INVESTIGATION("Investigation", "When you look around for clues and make deductions based on those clues, you make an Intelligence check." +
            " You might deduce the location of a hidden object, discern from the appearance of a wound what kind of weapon dealt it," +
            " or determine the weakest point in a tunnel that could cause it to collapse. Poring through ancient scrolls in search of a hidden fragment of knowledge" +
            " might also call for an Intelligence (Investigation) check."),
    INSIGHT("Insight", "Your Wisdom check decides whether you can determine the true intentions of a creature, such as when searching" +
            " out a lie or predicting someone’s next move. Doing so involves gleaning clues from body language, speech habits, and changes in mannerisms."),
    MEDICINE("Medicine", "A Wisdom check lets you try to stabilize a dying companion or diagnose an illness."),
    NATURE("Nature", "Your Intelligence check measures your ability to recall lore about terrain, plants and animals, the weather, and natural cycles."),
    PERCEPTION("Perception", "Your Wisdom check lets you spot, hear, or otherwise detect the presence of something. It measures your general" +
            " awareness of your surroundings and the keenness of your senses. For example, you might try to hear a conversation through a closed door, eavesdrop under an open window," +
            " or hear monsters moving stealthily in the forest. Or you might try to spot things that are obscured or easy to miss, whether they are orcs lying in ambush on a road," +
            " thugs hiding in the shadows of an alley, or candlelight under a closed secret door."),
    PERFORMANCE("Performance", " Your Charisma check determines how well you can delight an audience with music, dance," +
            " acting, storytelling, or some other form of entertainment."),
    PERSUASION("Persuasion", "When you attempt to influence someone or a group of people with tact," +
            " social graces, or good nature, the DM might ask you to make a Charisma check." +
            " Typically, you use persuasion when acting in good faith, to foster friendships, make cordial requests, or exhibit proper etiquette." +
            " Examples of persuading others include convincing a chamberlain to let your party see the king, negotiating peace between warring tribes," +
            " or inspiring a crowd of townsfolk."),
    RELIGION("Religion", "Your Intelligence check measures your ability to recall lore about deities, rites and prayers, religious hierarchies, holy symbols," +
            " and the practices of secret cults."),
    SLEIGHT_OF_HAND("Sleight of hand", "Whenever you attempt an act of legerdemain or manual trickery, such as planting something on someone else or concealing an object" +
            " on your person, make a Dexterity check. The DM might also call for a Dexterity check to determine whether you can lift a coin purse off another person or slip something" +
            " out of another person's pocket."),
    STEALTH("Stealth", "Make a Dexteritycheck when you attempt to conceal yourself from enemies, " +
            "slink past guards, slip away without being noticed, or sneak up on someone without being seen or heard."),
    SURVIVAL("Survival", "The DM might ask you to make a Wisdom check to follow tracks, hunt wild game, " +
            "guide your group through frozen wastelands, identify signs that owlbears live nearby, predict the weather, or avoid quicksand and other natural hazards."),
    ;

    private final String name;
    private final String description;
    private boolean mastered = false;

    JobSkill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * give a list of all Jobskill
     *
     * @return List of jobSkills
     */
    public static List<JobSkill> getJobSkillList() {
        return Arrays.asList(values());
    }

    /**
     * Convert string to JobSkill element
     *
     * @return target skill
     */
    public static JobSkill getJobSkill(String name) throws UnknownJobSkillException {
        return switch (name) {
            case "ACROBATICS" -> ACROBATICS;
            case "ARCANA" -> ARCANA;
            case "ATHLETICS" -> ATHLETICS;
            case "ANIMAL_HANDLING" -> ANIMAL_HANDLING;
            case "DECEPTION" -> DECEPTION;
            case "HISTORY" -> HISTORY;
            case "INTIMIDATION" -> INTIMIDATION;
            case "INSIGHT" -> INSIGHT;
            case "INVESTIGATION" -> INVESTIGATION;
            case "MEDICINE" -> MEDICINE;
            case "NATURE" -> NATURE;
            case "PERCEPTION" -> PERCEPTION;
            case "PERFORMANCE" -> PERFORMANCE;
            case "PERSUASION" -> PERSUASION;
            case "RELIGION" -> RELIGION;
            case "SLEIGHT_OF_HAND" -> SLEIGHT_OF_HAND;
            case "STEALTH" -> STEALTH;
            case "SURVIVAL" -> SURVIVAL;
            default -> throw new UnknownJobSkillException("Unknown job skill " + name);
        };
    }

    /**
     * @return name of the jobSkill
     */
    public String getName() {
        return name;
    }

    /**
     * @return description of the jobSkill
     */
    public String getDescription() {
        return description;
    }

    /**
     * Says if the jobSkill have been mastered by the character or not
     *
     * @return boolean
     */
    public boolean isMastered() {
        return mastered;
    }

    /**
     * Allow the user to set a skill to mastered or not
     *
     * @param mastered boolean if skill is mastered
     */
    public void setMastered(boolean mastered) {
        this.mastered = mastered;
    }

    /**
     * @return name of the jobSkill
     */
    public String toString() {
        return name;
    }
}
