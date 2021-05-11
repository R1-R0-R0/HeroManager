package model.equipments;

import model.items.equipments.Equipment;
import model.items.equipments.EquipmentPart;
import model.items.equipments.EquipmentType;
import org.junit.jupiter.api.Test;

public class EquipmentInventory {
    Equipment head = new Equipment("head", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.HEAVY,
            5, 4, 3, 2, 1, -3, 4);
    Equipment helmet = new Equipment("helmet", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.LIGHT,
            2, 1, 1, 1, -1, 1, 1);
    Equipment ring = new Equipment("ring","One Ring to bring them all", EquipmentPart.RING, 1,
            EquipmentType.LIGHT, 1,1,1,1,1,1,1);
    Equipment otherRing = new Equipment("other ring","Second Ring to bring them all", EquipmentPart.RING, 1,
            EquipmentType.HEAVY, 1,1,1,1,1,1,1);
    model.items.equipments.EquipmentInventory equippedInventory = new model.items.equipments.EquipmentInventory(head,null,null,null,null,null,null,null,ring,otherRing);

}
