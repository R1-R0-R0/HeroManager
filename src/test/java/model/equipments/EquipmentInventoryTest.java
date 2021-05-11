package model.equipments;

import model.items.equipments.Equipment;
import model.items.equipments.EquipmentInventory;
import model.items.equipments.EquipmentPart;
import model.items.equipments.EquipmentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentInventoryTest {
    Equipment head = new Equipment("head", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.HEAVY,
            5, 4, 3, 2, 1, -3, 4);
    Equipment helmet = new Equipment("helmet", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.LIGHT,
            2, 1, 1, 1, -1, 1, 1);
    Equipment ring = new Equipment("ring","One Ring to bring them all", EquipmentPart.RING, 1,
            EquipmentType.LIGHT, 1,1,1,1,1,1,1);
    Equipment otherRing = new Equipment("other ring","Second Ring to bring them all", EquipmentPart.RING, 1,
            EquipmentType.HEAVY, 1,1,1,1,1,1,1);
    EquipmentInventory equippedInventory = new EquipmentInventory(null,null,null,null,null,null,null,null,null,null);

    @Test
    public void getHeadTest(){
        equippedInventory.addHead(head);

        assertEquals(head,equippedInventory.getHead());

        equippedInventory.addHead(helmet); //shouldn't change because there's already a head equipped

        assertEquals(head,equippedInventory.getHead());


    }

}
