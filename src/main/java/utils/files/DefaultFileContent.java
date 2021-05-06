package utils.files;

public enum DefaultFileContent {
    WEAPONS("[machin, bidule, chouette]");
    //SPEELS("[machin, bidule, chouette]");
    //CONSUMABLE("[machin, bidule, chouette]");
    //EQUIPMENT("");

    private String content;

    DefaultFileContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
