package model;

public class Dice {
    private int diceRange;

    public Dice(int diceRange) {
        this.diceRange = diceRange;
    }

    public int getDiceRange() {
        return diceRange;
    }

    public int roll() {
        return (int) (1+(Math.random()*diceRange-1));
    }
}
