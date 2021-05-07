package model.job;

/**
 * This enum lists innate improvement for each Race
 *
 * @see model.race.Race
 */
public enum Improvement {
    DARKVISION("Darkvision",
            "Accustomed to life underground, you have superior vision in dark and dim conditions." +
                    " You can see in dim lightwithin 60 feet of you as if it were bright light, and in darkness as if" +
                    " it were dim light. You can’t discern color in darkness, only shades of gray."),

    DWARVEN_RESILIENCE("Dwarven Resilience", "You have advantage on saving throws against poison, and you have resistance against poison damage."),

    DWARVEN_COMBAT_TRAINING("Dwarven Combat Training", "You have proficiency with the battleaxe, handaxe, light hammer, and warhammer."),

    TOOL_PROFICIENCY("Tool Proficiency", "You gain proficiency with the artisan’s tools of your choice: smith’s tools, brewer’s supplies, or mason’s tools."),

    STONECUNNING("Stonecunning", "Whenever you make an Intelligence (History) check related to the origin of stonework," +
            " you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus."),
    KEEN_SENSES("Keen Senses", "You have proficiency in the Perception skill."),

    FEY_ANCESTRY("Fey Ancestry", " You have advantage on saving throws against being charmed, and magic can’t put you to sleep."),

    TRANCE("Trance", "Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. " +
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
            "White\tCold\t15 ft. cone (Con. save)"),

    BREATH_WEAPON("Breath Weapon", "You can use your action to exhale destructive energy. Your draconic ancestry determines the size, shape" +
            ", and damage type of the exhalation. When you use your breath weapon, each creature in the area of the exhalation must make a saving throw," +
            " the type of which is determined by your draconic ancestry. The DC for this saving throw equals 8 + your Constitution modifier + your proficiency bonus." +
            " A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. The damage increases to 3d6 at 6th level, 4d6 at 11th level," +
            " and 5d6 at 16th level. After you use your breath weapon, you can’t use it again until you complete a short or long rest."),

    DAMAGE_RESISTANCE("Damage Resistance", " You have resistance to the damage type associated with your draconic ancestry."),

    GNOME_CUNNING("Gnome cunning", "You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic."),

    SKILL_VERSATILITY("Skill Versatility", "You gain proficiency in two skills of your choice."),

    MENACING("Menacing", "You gain proficiency in the intimidation skill."),

    RELENTLESS_ENDURANCE("Relentless Endurance", "When you are reduced to 0 hit points but not killed outright," +
            " you can drop to 1 hit point instead. You can’t use this feature again until you finish a long rest."),

    SAVAGE_ATTACKS("Savage Attacks", "When you score a critical hit with a melee weapon attack," +
            " you can roll one of the weapon’s damage dice one additional time and add it to the extra damage of the critical hit."),

    HELLISH_RESISTANCE("Hellish Resistance", "You have resistance to fire damage."),

    INFERNAL_LEGACY("Infernal Legacy", "You know the thaumaturgy cantrip. When you reach 3rd level," +
            " you can cast the hellish rebuke spell as a 2nd-level spell once with this trait and regain the ability" +
            " to do so when you finish a long rest. When you reach 5th level, you can cast the darkness spell once with this trait" +
            " and regain the ability to do so when you finish a long rest. Charisma is your spellcasting ability for these spells."),

    FLIGHT("Flight", "You have a flying spped of 50 feet. To use this speed you can't be wearing medium or heavy armor."),

    TALONS("Talons", "You are proficient with your unarmed strikes, which deal 1d4 slashing damage on a hit."),

    RAGE("Rage", "In battle, you fight with primal ferocity. On your turn, you can enter a rage as a bonus action." +
            " While raging, you gain the following benefits if you aren’t wearing heavy armor:\n" +
            "\n" +
            "You have advantage on Strength checks and Strength saving throws.\n" +
            "When you make a melee weapon attack using Strength, you gain a bonus to the damage roll that increases as you gain" +
            " levels as a barbarian, as shown in the Rage Damage column of the Barbarian table.\n" +
            "You have resistance to bludgeoning, piercing, and slashing damage.\n" +
            "If you are able to cast spells, you can’t cast them or concentrate on them while raging.\n" +
            "\n" +
            "Your rage lasts for 1 minute. It ends early if you are knocked unconscious or if your turn ends and you haven’t attacked" +
            " a hostile creature since your last turn or taken damage since then. You can also end your rage on your turn as a bonus action." +
            " Once you have raged the number of times shown for your barbarian level in the Rages column of the Barbarian table," +
            " you must finish a long rest before you can rage again."),

    UNARMORED_DEFENSE("Unarmored Defense", "While you are not wearing any armor, your Armor Class equals" +
            " 10 + your Dexterity modifier + your Constitution modifier. You can use a shield and still gain this benefit."),

    BARDIC_INSPIRATION("Bardic Inspiration", "You can inspire others through stirring words or music." +
            " To do so, you use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you." +
            " That creature gains one Bardic Inspiration die, a d6. Once within the next 10 minutes, the creature can roll the die and add the number rolled to one ability check," +
            " attack roll, or saving throw it makes. The creature can wait until after it rolls the d20 before deciding to use the Bardic Inspiration die," +
            " but must decide before the GM says whether the roll succeeds or fails. Once the Bardic Inspiration die is rolled, it is lost." +
            " A creature can have only one Bardic Inspiration die at a time.\n" +
            "\n" +
            "You can use this feature a number of times equal to your Charisma modifier (a minimum of once)." +
            " You regain any expended uses when you finish a long rest. Your Bardic Inspiration die changes when you reach" +
            " certain levels in this class. The die becomes a d8 at 5th level, a d10 at 10th level, and a d12 at 15th level."),

    DIVINE_DOMAIN("Divine Domain", "Choose one domain related to your deity, such as Life. Each domain is detailed at the end of the class description," +
            " and each one provides examples of gods associated with it. Your choice grants you domain spells and other features when you choose it at 1st level." +
            " It also grants you additional ways to use Channel Divinity when you gain that feature at 2nd level, and additional benefits at 6th, 8th, and 17th levels." +
            "Domain Spells\n" +
            "Each domain has a list of spells—its domain spells— that you gain at the cleric levels noted in the domain description." +
            " Once you gain a domain spell, you always have it prepared, and it doesn’t count against the number of spells you can prepare each day." +
            " If you have a domain spell that doesn’t appear on the cleric spell list, the spell is nonetheless a cleric spell for you."),

    DRUIDIC("Druidic", "You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden messages." +
            " You and others who know this language automatically spot such a message." +
            " Others spot the message’s presence with a successful DC 15 Wisdom (Perception) check but can’t decipher it without magic."),

    FIGHTING_STYLE("Fighting Style", "You adopt a particular style of fighting as your specialty. Choose one of the following options." +
            " You can’t take a Fighting Style option more than once, even if you later get to choose again.\n" +
            "\n" +
            "Archery\n" +
            "You gain a +2 bonus to attack rolls you make with ranged weapons.\n" +
            "\n" +
            "Defense\n" +
            "While you are wearing armor, you gain a +1 bonus to AC.\n" +
            "\n" +
            "Dueling\n" +
            "When you are wielding a melee weapon in one hand and no other weapons, you gain a +2 bonus to damage rolls with that weapon.\n" +
            "\n" +
            "Great Weapon Fighting\n" +
            "When you roll a 1 or 2 on a damage die for an attack you make with a melee weapon that you are wielding with two hands, you can reroll the die and must use the new roll," +
            " even if the new roll is a 1 or a 2. The weapon must have the two-handed or versatile property for you to gain this benefit.\n" +
            "\n" +
            "Protection\n" +
            "When a creature you can see attacks a target other than you that is within 5 feet of you, you can use your reaction to impose disadvantage on the attack roll." +
            " You must be wielding a shield.\n" +
            "\n" +
            "Two-Weapon Fighting\n" +
            "When you engage in two-weapon fighting, you can add your ability modifier to the damage of the second attack."),

    SECOND_WIND("Second wind", "You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn," +
            " you can use a bonus action to regain hit points equal to 1d10 + your fighter level." +
            " Once you use this feature, you must finish a short or long rest before you can use it again."),

    MARTIAL_ARTS("Martial arts", "At 1st level, your practice of martial arts gives you mastery of combat styles that use unarmed strikes and monk weapons," +
            " which are shortswords and any simple melee weapons that don’t have the two-handed or heavy property. You gain the following benefits while you are unarmed " +
            "or wielding only monk weapons and you aren’t wearing armor or wielding a shield:\n" +
            "\n" +
            "You can use Dexterity instead of Strength for the attack and damage rolls of your unarmed strikes and monk weapons.\n" +
            "You can roll a d4 in place of the normal damage of your unarmed strike or monk weapon. This die changes as you gain monk levels, as shown in the Martial Arts " +
            "column of the Monk table.\n" +
            "When you use the Attack action with an unarmed strike or a monk weapon on your turn, you can make one unarmed strike as a bonus action. For example," +
            " if you take the Attack action and attack with a quarterstaff, you can also make an unarmed strike as a bonus action, assuming you haven’t already taken" +
            " a bonus action this turn.\n" +
            "Certain monasteries use specialized forms of the monk weapons. For example, you might use a club that is two lengths of wood connected by a short chain (called a nunchaku)" +
            " or a sickle with a shorter, straighter blade (called a kama). Whatever name you use for a monk weapon, you can use the game statistics provided" +
            " for the weapon in the Weapons section."),

    DIVINE_SENSE("Divine Sense", "The presence of strong evil registers on your senses like a noxious odor, and powerful good rings like heavenly music in your ears." +
            " As an action, you can open your awareness to detect such forces. Until the end of your next turn, you know the location of any celestial, fiend, or undead within 60 feet" +
            " of you that is not behind total cover. You know the type (celestial, fiend, or undead) of any being whose presence you sense, but not its identity" +
            " (the vampire Count Strahd von Zarovich, for instance). Within the same radius, you also detect the presence of any place or object that has been consecrated" +
            " or desecrated, as with the hallow spell.\n" +
            "\n" +
            "You can use this feature a number of times equal to 1 + your Charisma modifier. When you finish a long rest, you regain all expended uses."),

    LAY_ON_HANDS("Lay on Hands", "Your blessed touch can heal wounds. You have a pool of healing power that replenishes when you take a long rest." +
            " With that pool, you can restore a total number of hit points equal to your paladin level × 5." +
            " As an action, you can touch a creature and draw power from the pool to restore a number of hit points to that creature, up to the maximum amount remaining in your pool.\n" +
            "\n" +
            "Alternatively, you can expend 5 hit points from your pool of healing to cure the target of one disease or neutralize one poison affecting it." +
            " You can cure multiple diseases and neutralize multiple poisons with a single use of Lay on Hands, expending hit points separately for each one." +
            " This feature has no effect on undead and constructs."),
    FAVORED_ENEMY("Favored enemy", "Beginning at 1st level, you have significant experience studying, tracking, hunting," +
            " and even talking to a certain type of enemy. Choose a type of favored enemy: aberrations, beasts, celestials, constructs, dragons," +
            " elementals, fey, fiends, giants, monstrosities, oozes, plants, or undead. Alternatively, you can select two races of humanoid (such as gnolls and orcs)" +
            " as favored enemies. You have advantage on Wisdom (Survival) checks to track your favored enemies, as well as on Intelligence checks to recall information about them." +
            " When you gain this feature, you also learn one language of your choice that is spoken by your favored enemies, if they speak one at all.\n" +
            "\n" +
            "You choose one additional favored enemy, as well as an associated language, at 6th and 14th level." +
            " As you gain levels, your choices should reflect the types of monsters you have encountered on your adventures."),
    NATURAL_EXPLORER("Natural explorer", "You are particularly familiar with one type of natural environment and are adept at traveling and surviving in such regions." +
            " Choose one type of favored terrain: arctic, coast, desert, forest, grassland, mountain, swamp, or the Underdark. When you make an Intelligence or Wisdom check" +
            " related to your favored terrain, your proficiency bonus is doubled if you are using a skill that you’re proficient in.\n" +
            "\n" +
            "While traveling for an hour or more in your favored terrain, you gain the following benefits:\n" +
            "\n" +
            "Difficult terrain doesn’t slow your group’s travel.\n" +
            "Your group can’t become lost except by magical means.\n" +
            "Even when you are engaged in another activity while traveling (such as foraging, navigating, or tracking), you remain alert to danger.\n" +
            "If you are traveling alone, you can move stealthily at a normal pace.\n" +
            "When you forage, you find twice as much food as you normally would.\n" +
            "While tracking other creatures, you also learn their exact number, their sizes, and how long ago they passed through the area.\n" +
            "You choose additional favored terrain types at 6th and 10th level."),
    EXPERTISE("Expertise", "At 1st level, choose two of your skill proficiencies, or one of your skill proficiencies and your proficiency with thieves’ tools." +
            " Your proficiency bonus is doubled for any ability check you make that uses either of the chosen proficiencies." +
            " At 6th level, you can choose two more of your proficiencies (in skills or with thieves’ tools) to gain this benefit."),

    SNEAK_ATTACK("Sneak Attack", "Beginning at 1st level, you know how to strike subtly and exploit a foe’s distraction." +
            " Once per turn, you can deal an extra 1d6 damage to one creature you hit with an attack if you have advantage on the attack roll." +
            " The attack must use a finesse or a ranged weapon. You don’t need advantage on the attack roll if another enemy of the target is within 5 feet of it," +
            " that enemy isn’t incapacitated, and you don’t have disadvantage on the attack roll. The amount of the extra damage increases as you gain levels in this class," +
            " as shown in the Sneak Attack column of the Rogue table."),

    THIEVES_CANT("Thieves' Cant", "During your rogue training you learned thieves’ cant, a secret mix of dialect," +
            " jargon, and code that allows you to hide messages in seemingly normal conversation. Only another creature that knows thieves’" +
            " cant understands such messages. It takes four times longer to convey such a message than it does to speak the same idea plainly." +
            " In addition, you understand a set of secret signs and symbols used to convey short, simple messages, such as whether an area is dangerous" +
            " or the territory of a thieves’ guild, whether loot is nearby, or whether the people in an area are easy marks or will provide a safe house for thieves on the run."),
    SORCEROUS_ORIGIN("Sorcerous Origin", "Choose a sorcerous origin, which describes the source of your innate magical power:" +
            " Draconic Bloodline, detailed at the end of the class description, or one from another source." +
            " Your choice grants you features when you choose it at 1st level and again at 6th, 14th, and 18th level."),

    OTHERWORDLY_PATRON("Otherwordly Patron", "At 1st level, you have struck a bargain with an otherworldly being of your choice:" +
            " the Fiend, which is detailed at the end of the class description, or one from another source." +
            " Your choice grants you features at 1st level and again at 6th, 10th, and 14th level."),
    ARCANE_RECOVERY("Arcane Recovery", "You have learned to regain some of your magical energy by studying your spellbook." +
            " Once per day when you finish a short rest, you can choose expended spell slots to recover." +
            " The spell slots can have a combined level that is equal to or less than half your wizard level (rounded up)," +
            " and none of the slots can be 6th level or higher. For example, if you’re a 4th-level wizard," +
            " you can recover up to two levels worth of spell slots. You can recover either a 2nd-level spell slot or two 1st-level spell slots."),

    PACT_MAGIC("Pact magic", "Your arcane research and the magic bestowed on you by your patron have given you facility with spells.\n" +
            "\n" +
            "Cantrips\n" +
            "You know two cantrips of your choice from the warlock spell list. You learn additional warlock cantrips of your choice at higher levels," +
            " as shown in the Cantrips Known column of the Warlock table.\n" +
            "\n" +
            "Spell Slots\n" +
            "The Warlock table shows how many spell slots you have to cast your warlock spells of 1st through 5th level. The table also shows what the level of those slots is" +
            " all of your spell slots are the same level. To cast one of your warlock spells of 1st level or higher, you must expend a spell slot." +
            " You regain all expended spell slots when you finish a short or long rest.\n" +
            "\n" +
            "For example, when you are 5th level, you have two 3rd-level spell slots. To cast the 1st-level spell witch bolt," +
            " you must spend one of those slots, and you cast it as a 3rd-level spell.\n" +
            "\n" +
            "Spells Known of 1st Level and Higher\n" +
            "At 1st level, you know two 1st-level spells of your choice from the warlock spell list. " +
            "The Spells Known column of the Warlock table shows when you learn more warlock spells of your choice of 1st level and higher." +
            " A spell you choose must be of a level no higher than what’s shown in the table’s Slot Level column for your level." +
            " When you reach 6th level, for example, you learn a new warlock spell, which can be 1st, 2nd, or 3rd level.\n" +
            "\n" +
            "Additionally, when you gain a level in this class, you can choose one of the warlock spells you know and replace it with another spell" +
            " from the warlock spell list, which also must be of a level for which you have spell slots.\n" +
            "\n" +
            "Spell casting Ability\n" +
            "Charisma is your spell casting ability for your warlock spells, so you use your Charisma whenever a spell refers to your spell casting ability." +
            " In addition, you use your Charisma modifier when setting the saving throw DC for a warlock spell you cast and when making an attack roll with one.\n" +
            "\n" +
            "Spell save DC = 8 + your proficiency bonus + your Charisma modifier\n" +
            "Spell attack modifier = your proficiency bonus + your Charisma modifier\n" +
            "\n" +
            "Spell casting Focus\n" +
            "You can use an arcane focus (see the Adventuring Gear section) as a spell casting focus for your warlock spells.");


    protected final String name;
    protected final String description;
    protected final int[] characteristicsBoost = new int[7];

    Improvement(String name, String description) {
        this.name = name;
        this.description = description;

    }

    Improvement(String name, String description, int strengthBoost, int dexterityBoost, int robustnessBoost,
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
    public int getStrengthBoost() {
        return characteristicsBoost[0];
    }

    /**
     * @return the boost in Dexterity statistic given by the improvement
     */
    public int getDexterityBoost() {
        return characteristicsBoost[1];
    }

    /**
     * @return the boost in Robustness statistic given by the improvement
     */
    public int getRobustnessBoost() {
        return characteristicsBoost[2];
    }

    /**
     * @return the boost in Intelligence statistic given by the improvement
     */
    public int getIntelligenceBoost() {
        return characteristicsBoost[3];
    }

    /**
     * @return the boost in Wisdom statistic given by the improvement
     */
    public int getWisdomBoost() {
        return characteristicsBoost[4];
    }

    /**
     * @return the boost in Charisma statistic given by the improvement
     */
    public int getCharismaBoost() {
        return characteristicsBoost[5];
    }

    /**
     * @return the boost in Speed statistic given by the improvement
     */
    public int getSpeedBoost() {
        return characteristicsBoost[6];
    }

    @Override
    public String toString() {
        return name;
    }
}
