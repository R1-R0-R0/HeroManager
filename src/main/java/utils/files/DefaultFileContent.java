package utils.files;

/**
 * Enum to create a new File when the reader can't find the searched file
 * In case if the user deleted the the save file.
 */
public enum DefaultFileContent {
    WEAPONS("{ \"weapon\" : [\"Dagger\" ,\"A short dagger\",\"1d4 Finesse, light, thrown (range 20/60)\",\"common\",\"piercing\"," +
            "\"Mace\",\"little hammer\",\"1d6\",\"common\",\"bludgeoning\",\"Spear\",\"a long branch with something pointy on top\",\"1d6 Thrown (range 20/60), versatile (1d8)\",\"common\",\"piercing\"," +
            "\"Shortsword\",\"a Classic sword\",\"1d6 Finesse, light\",\"war\",\"piercing\"]}"),
    SPEELS("[machin, bidule, chouette]"),
    CONSUMABLE("[machin, bidule, chouette]"),
    EQUIPMENT("");
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
