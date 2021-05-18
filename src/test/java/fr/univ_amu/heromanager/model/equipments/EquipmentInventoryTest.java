package fr.univ_amu.heromanager.model.equipments;

import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentInventory;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentInventoryTest {
    Equipment head = new Equipment("head", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.HEAVY,
            5, 4, 3, 2, 1, -3, 4);
    Equipment helmet = new Equipment("helmet", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.LIGHT,
            2, 1, 1, 1, -1, 1, 1);
    Equipment ring = new Equipment("ring", "One Ring to bring them all", EquipmentPart.RING, 1,
            EquipmentType.LIGHT, 1, 1, 1, 1, 1, 1, 1);
    Equipment otherRing = new Equipment("other ring", "Second Ring to bring them all", EquipmentPart.RING, 1,
            EquipmentType.HEAVY, 1, 1, 1, 1, 1, 1, 1);

    Equipment shirt = new Equipment("shirt","classic t-shirt",EquipmentPart.BODY,1,EquipmentType.LIGHT,
            1,1,1,1,1,1,1);
    Equipment notShirt = new Equipment("Not Shirt", "not a t shirt", EquipmentPart.BODY,1,EquipmentType.MEDIUM,
            1,1,1,1,1,1,1);

    Equipment gloves = new Equipment("gloves", "MJ gloves", EquipmentPart.HANDS, 2, EquipmentType.LIGHT,
            1,1,1,1,1,1,1);
    Equipment notGloves = new Equipment("not gloves", "not MJ gloves", EquipmentPart.HANDS, 2, EquipmentType.HEAVY,
            1,1,1,1,1,1,1);

    Equipment shoe = new Equipment("Shoe", "a nice shoe", EquipmentPart.FEET, 0, EquipmentType.LIGHT,
            1,1,1,1,1,1,1);
    Equipment notShoe = new Equipment("not shoe", "not a nice shoe", EquipmentPart.FEET, 0 , EquipmentType.HEAVY,
            1,1,1,1,1,1,1);

    Equipment jean = new Equipment("Jean", "blue jean",EquipmentPart.LEGS,1,EquipmentType.HEAVY,
            1,1,1,1,1,1,1);
    Equipment leggings = new Equipment("Leggings","fit nicely", EquipmentPart.LEGS,1,EquipmentType.LIGHT,
            1,1,1,1,1,11,1);

    Equipment mantle = new Equipment("mantle", " a big mantle", EquipmentPart.MANTLE,10,EquipmentType.HEAVY,
            1,1,1,11,1,1,1);
    Equipment notMantle = new Equipment("not mantle", "not a big mantle", EquipmentPart.MANTLE,10,EquipmentType.LIGHT,
            1,1,1,11,1,1,1);
    Equipment amulet = new Equipment("amulet", "nice amulet", EquipmentPart.AMULET,10,EquipmentType.LIGHT,
            1,1,1,11,1,1,1);
    Equipment notAmulet = new Equipment("not Amulet", "not nice amulet", EquipmentPart.AMULET,10,EquipmentType.LIGHT,
            1,1,1,11,1,1,1);
    Equipment belt = new Equipment("belt", "a nice belt", EquipmentPart.BELT, 5, EquipmentType.HEAVY,
            1,1,1,1,1,1,1);
    Equipment notBelt = new Equipment("not belt", "not a nice belt", EquipmentPart.BELT, 5, EquipmentType.LIGHT,
            1,1,1,1,1,1,1);
    EquipmentInventory equippedInventory = new EquipmentInventory(null, null, null, null, null, null, null, null, null, null);

    @Test
    public void getHeadTest() {
        equippedInventory.addHead(head);

        assertEquals(head, equippedInventory.getHead());

        equippedInventory.addHead(helmet); //shouldn't change because there's already a head equipped

        assertEquals(head, equippedInventory.getHead());

        equippedInventory.replaceHead(helmet);

        assertEquals(helmet, equippedInventory.getHead());

        equippedInventory.removeHead();

        assertNull(equippedInventory.getHead());

        equippedInventory.addHead(ring); //shouldn't work because isn't a HEAD part

        assertNull(equippedInventory.getHead());
    }
    @Test
    public void getBodyTest() {
        equippedInventory.addBody(shirt);

        assertEquals(shirt, equippedInventory.getBody());

        equippedInventory.addBody(notShirt); //shouldn't change because there's already a BODY equipped

        assertEquals(shirt, equippedInventory.getBody());

        equippedInventory.replaceBody(notShirt);

        assertEquals(notShirt, equippedInventory.getBody());

        equippedInventory.removeBody();

        assertNull(equippedInventory.getBody());

        equippedInventory.addBody(ring); //shouldn't work because isn't a BODY part

        assertNull(equippedInventory.getBody());
    }
    @Test
    public void getHandsTest() {
        equippedInventory.addHands(gloves);

        assertEquals(gloves, equippedInventory.getHands());

        equippedInventory.addHands(notGloves); //shouldn't change because there's already a HANDS equipped

        assertEquals(gloves, equippedInventory.getHands());

        equippedInventory.replaceHands(notGloves);

        assertEquals(notGloves, equippedInventory.getHands());

        equippedInventory.removeHands();

        assertNull(equippedInventory.getHands());

        equippedInventory.addHands(ring); //shouldn't work because isn't a HANDS part

        assertNull(equippedInventory.getHands());
    }
    @Test
    public void getFeetTest() {
        equippedInventory.addFeet(shoe);

        assertEquals(shoe, equippedInventory.getFeet());

        equippedInventory.addFeet(notShoe); //shouldn't change because there's already a FEET equipped

        assertEquals(shoe, equippedInventory.getFeet());

        equippedInventory.replaceFeet(notShoe);

        assertEquals(notShoe, equippedInventory.getFeet());

        equippedInventory.removeFeet();

        assertNull(equippedInventory.getFeet());

        equippedInventory.addFeet(ring); //shouldn't work because isn't a FEET part

        assertNull(equippedInventory.getFeet());
    }

    @Test
    public void getLegsTest() {
        equippedInventory.addLegs(leggings);

        assertEquals(leggings, equippedInventory.getLegs());

        equippedInventory.addLegs(jean); //shouldn't change because there's already a LEGS equipped

        assertEquals(leggings, equippedInventory.getLegs());

        equippedInventory.replaceLegs(jean);

        assertEquals(jean, equippedInventory.getLegs());

        equippedInventory.removeLegs();

        assertNull(equippedInventory.getLegs());

        equippedInventory.addLegs(ring); //shouldn't work because isn't a LEGS part

        assertNull(equippedInventory.getLegs());
    }

    @Test
    public void getMantleTest() {
        equippedInventory.addMantle(mantle);

        assertEquals(mantle, equippedInventory.getMantle());

        equippedInventory.addMantle(notMantle); //shouldn't change because there's already a MANTLE equipped

        assertEquals(mantle, equippedInventory.getMantle());

        equippedInventory.replaceMantle(notMantle);

        assertEquals(notMantle, equippedInventory.getMantle());

        equippedInventory.removeMantle();

        assertNull(equippedInventory.getMantle());

        equippedInventory.addMantle(ring); //shouldn't work because isn't a MANTLE part

        assertNull(equippedInventory.getMantle());
    }
    @Test
    public void getAmuletTest() {
        equippedInventory.addAmulet(amulet);

        assertEquals(amulet, equippedInventory.getAmulet());

        equippedInventory.addMantle(notAmulet); //shouldn't change because there's already a AMULET equipped

        assertEquals(amulet, equippedInventory.getAmulet());

        equippedInventory.replaceAmulet(notAmulet);

        assertEquals(notAmulet, equippedInventory.getAmulet());

        equippedInventory.removeAmulet();

        assertNull(equippedInventory.getAmulet());

        equippedInventory.addAmulet(ring); //shouldn't work because isn't a AMULET part

        assertNull(equippedInventory.getAmulet());
    }

    @Test
    public void getBeltTest() {
        equippedInventory.addBelt(belt);

        assertEquals(belt, equippedInventory.getBelt());

        equippedInventory.addMantle(notBelt); //shouldn't change because there's already a BELT equipped

        assertEquals(belt, equippedInventory.getBelt());

        equippedInventory.replaceBelt(notBelt);

        assertEquals(notBelt, equippedInventory.getBelt());

        equippedInventory.removeBelt();

        assertNull(equippedInventory.getBelt());

        equippedInventory.addBelt(ring); //shouldn't work because isn't a BELT part

        assertNull(equippedInventory.getBelt());
    }


    @Test
    public void getLeftRingTest(){
        equippedInventory.addLeftRing(ring);

        assertEquals(ring, equippedInventory.getLeftRing());

        equippedInventory.addLeftRing(otherRing); //shouldn't change because there's already a AMULET equipped

        assertEquals(ring, equippedInventory.getLeftRing());

        equippedInventory.replaceLeftRing(otherRing);

        assertEquals(otherRing, equippedInventory.getLeftRing());

        equippedInventory.removeLeftRing();

        assertNull(equippedInventory.getLeftRing());

        equippedInventory.addLeftRing(amulet); //shouldn't work because isn't a RING part

        assertNull(equippedInventory.getLeftRing());
    }

    @Test
    public void getRightRingTest(){
        equippedInventory.addRightRing(ring);

        assertEquals(ring, equippedInventory.getRightRing());

        equippedInventory.addRightRing(otherRing); //shouldn't change because there's already a AMULET equipped

        assertEquals(ring, equippedInventory.getRightRing());

        equippedInventory.replaceRightRing(otherRing);

        assertEquals(otherRing, equippedInventory.getRightRing());

        equippedInventory.removeRightRing();

        assertNull(equippedInventory.getRightRing());

        equippedInventory.addRightRing(amulet); //shouldn't work because isn't a RING part

        assertNull(equippedInventory.getRightRing());
    }

}

