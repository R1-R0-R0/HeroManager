package fr.univ_amu.heromanager.model;

/**
 * Dice inner representation.
 * Can create dices with wanted dice range and rolls it randomly
 */
public class Dice {
    /**
     * Number of dice faces
     */
    private int diceRange;

    /**
     * Constructor of this class. Allows to create dice with a desired number of dice faces
     *
     * @param diceRange number of dice faces
     */
    public Dice(int diceRange) {
        this.diceRange = diceRange;
    }

    /**
     * @return number of dice faces
     */
    public int getDiceRange() {
        return diceRange;
    }

    /**
     * @return random number from 1 to number of dice faces configured
     */
    public int roll() {
        return (int) (1 + (Math.random() * diceRange));
    }
}
