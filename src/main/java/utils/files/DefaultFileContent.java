package utils.files;

/**
 * Enum to create a new File when the reader can't find the searched file
 * In case if the user deleted the the save file.
 */
public enum DefaultFileContent {
    WEAPONS("[machin, bidule, chouette]");
    //SPEELS("[machin, bidule, chouette]");
    //CONSUMABLE("[machin, bidule, chouette]");
    //EQUIPMENT("");
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
