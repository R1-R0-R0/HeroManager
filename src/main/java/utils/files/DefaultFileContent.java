package utils.files;

/**
 * Enum to create a new File when the reader can't find the searched file,
 * In case if the user deleted the the save file.
 */
public enum DefaultFileContent {
    WEAPONS("{ \"weapon\" : [\"Dagger\" ,\"A short dagger\",\"1d4 Finesse, light, thrown (range 20/60)\",\"common\",\"piercing\"," +
            "\"Mace\",\"little hammer\",\"1d6\",\"common\",\"bludgeoning\",\"Spear\",\"a long branch with something pointy on top\",\"1d6 Thrown (range 20/60), versatile (1d8)\",\"common\",\"piercing\"," +
            "\"Shortsword\",\"a Classic sword\",\"1d6 Finesse, light\",\"war\",\"piercing\"]}"),
    SPEELS("{\"spell\" : [\"Magic missile\" ,\"You create three glowing darts of magical force. Each dart hits a creature of your choice that you can see within range. A dart deals 1d4 + 1 force damage to its target. The darts all strike simultaneously, and you can direct them to hit one creature or several.\",\"evocation\",\"1 action\",\"instantaneous\",1," +
            "120,\"SORCERER\",true,[\"V\",\"S\"],\"Fireball\",\"A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one." +
            "The fire spreads around corners. It ignites flammable objects in the area that aren't\",\"evocation\",\"1 action\",\"instantaneous\",3,150,\"SORCERER\",true,[\"V\",\"S\",\"M\"]]}"),
    CONSUMABLE("{\"consumable\" :[\"Potion of Healing\",\"You regain hit points when you drink this potion. The number of hit points depends on the potion's rarity, as shown in the Potions of Healing table. Whatever its potency, the potion's red liquid glimmers when agitated." +
            "Potion of ...  Rarity HP Regained" +
            "Healing    Common  2d4 + 2" +
            "Greater healing Uncommon 4d4 + 4" +
            "Superior healing Rare 8d4 + 8" +
            "Supreme healing Very rare 10d4 + 20\",\"Rope\",\"A 15 feet long Rope\",\"Torch\",\"Light 36 feet around you\"]}"),
    EQUIPMENT("{\"equipment\" : [\"Leather Armor\",\"A Light armor made of leather\",\"BODY\",11,\"LIGHT\",0,0,0,0,0,0,0,\"Half Plate\",\"A half plate armor , quite study but heavy\",\"BODY\",15,\"MEDIUM\",0,0,0,0,0,0,0,\"Chain Mail\",\"A Heavy armor\",\"BODY\",16,\"HEAVY\",0,0,0,0,0,0,0]}");
    /**
     * Instance of this class
     */
    private String content;

    /**
     * Set a default content
     *
     * @param content needed string
     */
    DefaultFileContent(String content) {
        this.content = content;
    }

    /**
     * Return a Enum to a string
     *
     * @return String
     */
    @Override
    public String toString() {
        return content;
    }
}
