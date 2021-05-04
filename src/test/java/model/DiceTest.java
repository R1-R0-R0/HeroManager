package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DiceTest {

    int minDiceValue = 1;
    int diceRange5 = 5;
    int diceRange10 = 10;

    Dice dice5 = new Dice(diceRange5);
    Dice dice10 = new Dice(diceRange10);

    @Test
    public void getDiceTest(){

        assertEquals(dice5.getDiceRange(),5);
        assertEquals(dice10.getDiceRange(),10);

    }

    @Test
    public void rollTest(){


        int roll5 = dice5.roll();
        int roll10 = dice10.roll();

        assertTrue(roll5 >= minDiceValue && roll5 <= diceRange5);
        assertTrue(roll10 >= minDiceValue && roll10 <= diceRange10);
    }
}
