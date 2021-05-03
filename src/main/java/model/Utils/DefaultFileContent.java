package model.Utils;

public enum DefaultFileContent {
    WEAPONS("[machin, bidule, chouette]");

    private String content;

    DefaultFileContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
