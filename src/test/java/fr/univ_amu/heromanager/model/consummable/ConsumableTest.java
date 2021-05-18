package fr.univ_amu.heromanager.model.consummable;

import fr.univ_amu.heromanager.model.items.consumables.Consumable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumableTest {

    Consumable consumable = new Consumable("potion", "restore health");

    @Test
    public void getNameTest(){
        assertEquals("potion", consumable.getName());
    }

    @Test
    public void getDescriptionTest(){
        assertEquals("restore health", consumable.getDescription());
    }
}
