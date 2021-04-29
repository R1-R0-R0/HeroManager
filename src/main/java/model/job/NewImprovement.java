package model.job;

public class NewImprovement {

    private final String  name;
    private final String description;
    private final int[] statBoost = new int[7];

    public NewImprovement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public NewImprovement(String name, String description,int strBoost,int dexBoost,int robustBoost,
                          int intelBoost,int wisBoost,int charismaBoost,int speedBoost){

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStrengthBoost(){
        return statBoost[0];
    }

    public int getDexterityBoost(){
        return statBoost[1];
    }

    public int getRobustnessBoost(){
        return statBoost[2];
    }

    public int getIntelligenceBoost(){
        return statBoost[3];
    }

    public int getWisdomBoost(){
        return statBoost[4];
    }

    public int getCharismaBoost(){
        return statBoost[5];
    }

    public int getSpeedBoost(){
        return statBoost[6];
    }

}
