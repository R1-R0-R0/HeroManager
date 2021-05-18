package model.job;

public class NewImprovement {

    /**
     * Allow you to create new Improvement in order to complete the enum
     *
     * @see Improvement
     */

    private final String name;
    private final String description;
    private final int[] statBoost = new int[7];

    public NewImprovement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public NewImprovement(String name, String description, int strBoost, int dexBoost, int robustBoost,
                          int intelBoost, int wisBoost, int charismaBoost, int speedBoost) {

        this.name = name;
        this.description = description;

        statBoost[0] = strBoost;
        statBoost[1] = dexBoost;
        statBoost[2] = robustBoost;
        statBoost[3] = intelBoost;
        statBoost[4] = wisBoost;
        statBoost[5] = charismaBoost;
        statBoost[6] = speedBoost;

    }

    /**
     * give name of the Improvement
     *
     * @return name of the Improvement
     */
    public String getName() {
        return name;
    }

    /**
     * give description of the Improvement
     *
     * @return description of the Improvement
     */
    public String getDescription() {
        return description;
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Strength given by the improvement
     */
    public int getStrengthBoost() {
        return statBoost[0];
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Dexterity given by the improvement
     */
    public int getDexterityBoost() {
        return statBoost[1];
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Robustness given by the improvement
     */
    public int getRobustnessBoost() {
        return statBoost[2];
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Intelligence given by the improvement
     */
    public int getIntelligenceBoost() {
        return statBoost[3];
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Wisdom given by the improvement
     */
    public int getWisdomBoost() {
        return statBoost[4];
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Charisma given by the improvement
     */
    public int getCharismaBoost() {
        return statBoost[5];
    }

    /**
     * give boost given by improvement
     *
     * @return boost of Speed given by the improvement
     */
    public int getSpeedBoost() {
        return statBoost[6];
    }

}
