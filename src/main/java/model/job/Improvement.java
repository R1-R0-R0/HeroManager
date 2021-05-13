package model.job;

/**
 * This enum lists innate improvement for each Race
 * @see model.race.Race
 *
 */
public enum Improvement {
    DARKVISION("Darkvision",
            "Accustomed to life underground, you have superior vision in dark and dim conditions." +
                    " You can see in dim lightwithin 60 feet of you as if it were bright light, and in darkness as if" +
                    " it were dim light. You can’t discern color in darkness, only shades of gray."),

    DWARVEN_RESILIENCE("Dwarven Resilience","You have advantage on saving throws against poison, and you have resistance against poison damage."),

    DWARVEN_COMBAT_TRAINING("Dwarven Combat Training","You have proficiency with the battleaxe, handaxe, light hammer, and warhammer."),

    TOOL_PROFICIENCY("Tool Proficiency", "You gain proficiency with the artisan’s tools of your choice: smith’s tools, brewer’s supplies, or mason’s tools."),

    STONECUNNING("Stonecunning","Whenever you make an Intelligence (History) check related to the origin of stonework," +
            " you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus."),
    KEEN_SENSES("Keen Senses", "You have proficiency in the Perception skill."),

    FEY_ANCESTRY("Fey Ancestry", " You have advantage on saving throws against being charmed, and magic can’t put you to sleep."),

    TRANCE("Trance","Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. " +
            "(The Common word for such meditation is \"trance.\") While meditating, you can dream after a fashion; such dreams are actually" +
            " mental exercises that have become reflexive through years of practice." +
            " After resting in this way, you gain the same benefit that a human does from 8 hours of sleep."),

    LUCKY("Lucky", "When you roll a 1 on the d20 for an attack roll, ability check, or saving throw, you can reroll the die and must use the new roll."),

    BRAVE("Brave", "You have advantage on saving throws against being frightened"),

    HALFLING_NIMBLENESS("Halfling Nimbleness", "You can move through the space of any creature that is of a size larger than yours."),

    DRACONIC_ANCESTRY("Draconic Ancestry", "You have draconic ancestry." +
            " Choose one type of dragon from the Draconic Ancestry table. Your breath weapon and damage resistance are determined by the dragon type, as shown in the table.\n" +
            "Dragon\tDamage Type\tBreath Weapon\n" +
            "Black\tAcid\t5 by 30 ft. line (Dex. save)\n" +
            "Blue\tLightning\t5 by 30 ft. line (Dex. save)\n" +
            "Brass\tFire\t5 by 30 ft. line (Dex. save)\n" +
            "Bronze\tLightning\t5 by 30 ft. line (Dex. save)\n" +
            "Copper\tAcid\t5 by 30 ft. line (Dex. save)\n" +
            "Gold\tFire\t15 ft. cone (Dex. save)\n" +
            "Green\tPoison\t15 ft. cone (Con. save)\n" +
            "Red\tFire\t15 ft. cone (Dex. save)\n" +
            "Silver\tCold\t15 ft. cone (Con. save)\n" +
            "White\tCold\t15 ft. cone (Con. save)" ),

    BREATH_WEAPON("Breath Weapon", "You can use your action to exhale destructive energy. Your draconic ancestry determines the size, shape" +
            ", and damage type of the exhalation. When you use your breath weapon, each creature in the area of the exhalation must make a saving throw," +
            " the type of which is determined by your draconic ancestry. The DC for this saving throw equals 8 + your Constitution modifier + your proficiency bonus." +
            " A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. The damage increases to 3d6 at 6th level, 4d6 at 11th level," +
            " and 5d6 at 16th level. After you use your breath weapon, you can’t use it again until you complete a short or long rest."),

    DAMAGE_RESISTANCE("Damage Resistance"," You have resistance to the damage type associated with your draconic ancestry."),

    GNOME_CUNNING("Gnome cunning", "You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic."),

    SKILL_VERSATILITY("Skill Versatility","You gain proficiency in two skills of your choice."),

    MENACING("Menacing","You gain proficiency in the intimidation skill."),

    RELENTLESS_ENDURANCE("Relentless Endurance","When you are reduced to 0 hit points but not killed outright," +
            " you can drop to 1 hit point instead. You can’t use this feature again until you finish a long rest."),

    SAVAGE_ATTACKS("Savage Attacks","When you score a critical hit with a melee weapon attack," +
            " you can roll one of the weapon’s damage dice one additional time and add it to the extra damage of the critical hit."),

    HELLISH_RESISTANCE("Hellish Resistance","You have resistance to fire damage."),

    INFERNAL_LEGACY("Infernal Legacy", "You know the thaumaturgy cantrip. When you reach 3rd level," +
            " you can cast the hellish rebuke spell as a 2nd-level spell once with this trait and regain the ability" +
            " to do so when you finish a long rest. When you reach 5th level, you can cast the darkness spell once with this trait" +
            " and regain the ability to do so when you finish a long rest. Charisma is your spellcasting ability for these spells."),

    FLIGHT("Flight","You have a flying spped of 50 feet. To use this speed you can't be wearing medium or heavy armor."),

    TALONS("Talons","You are proficient with your unarmed strikes, which deal 1d4 slashing damage on a hit.")
    ;


    protected final String name;
    protected final String description;
    protected final int[] characteristicsBoost = new int[7];

    Improvement(String name, String description) {
        this.name = name;
        this.description = description;

    }

    Improvement(String name, String description, int strengthBoost,int dexterityBoost,int robustnessBoost,
                int intelligenceBoost, int wisdomBoost, int charismaBoost, int speedBoost) {
        this.name = name;
        this.description = description;
        characteristicsBoost[0] = strengthBoost;
        characteristicsBoost[1] = dexterityBoost;
        characteristicsBoost[2] = robustnessBoost;
        characteristicsBoost[3] = intelligenceBoost;
        characteristicsBoost[4] = wisdomBoost;
        characteristicsBoost[5] = charismaBoost;
        characteristicsBoost[6] = speedBoost;
    }

    /**
     * @return the boost in Strength statistic given by the improvement
     */
    public int getStrengthBoost(){
        return characteristicsBoost[0];
    }

    /**
     * @return the boost in Dexterity statistic given by the improvement
     */
    public int getDexterityBoost(){
        return characteristicsBoost[1];
    }

    /**
     * @return the boost in Robustness statistic given by the improvement
     */
    public int getRobustnessBoost(){
        return characteristicsBoost[2];
    }

    /**
     * @return the boost in Intelligence statistic given by the improvement
     */
    public int getIntelligenceBoost(){
        return characteristicsBoost[3];
    }

    /**
     * @return the boost in Wisdom statistic given by the improvement
     */
    public int getWisdomBoost(){
        return characteristicsBoost[4];
    }

    /**
     * @return the boost in Charisma statistic given by the improvement
     */
    public int getCharismaBoost(){
        return characteristicsBoost[5];
    }

    /**
     * @return the boost in Speed statistic given by the improvement
     */
    public int getSpeedBoost(){
        return characteristicsBoost[6];
    }

    /**
     *
     * @return the name of the improvement
     */
    public String getName(){return name;}

    /**
     *
     * @return description of the improvement
     */
    public String getDescription(){return description;}
}
