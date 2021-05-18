package fr.univ_amu.heromanager.model.equipments;

import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {

    Equipment head = new Equipment("head", "protects from arrows", EquipmentPart.HEAD, 1, EquipmentType.HEAVY,
            5, 4, 3, 2, 1, -3, 4);
    Equipment bodyArmor = new Equipment("body Armor", "protects from fire arrows", EquipmentPart.BODY, 13, EquipmentType.LIGHT,
            2, 1, 1, 1, -1, 1, 1);

    @Test
    public void getNameTest(){
        assertEquals("head",head.getName());
        assertEquals("body Armor", bodyArmor.getName());
    }

    @Test
    public void getDescriptionTest(){
        assertEquals("protects from arrows",head.getDescription());
        assertEquals("protects from fire arrows", bodyArmor.getDescription());
    }

    @Test
    public void getEquipmentPartTest(){
        assertEquals(EquipmentPart.HEAD, head.getEquipmentPart());
        assertEquals(EquipmentPart.BODY, bodyArmor.getEquipmentPart());
    }
    @Test
    public void getArmorBonusTest(){
        assertEquals(1, head.getArmorBonus());
        assertEquals(13,bodyArmor.getArmorBonus());
    }

    @Test
    public void getEquipmentTypeTest(){
        assertEquals(EquipmentType.HEAVY,head.getEquipmentType());
        assertEquals(EquipmentType.LIGHT, bodyArmor.getEquipmentType());
    }

    @Test
    public void getBoostTest(){
        assertEquals(5,head.getStrengthBoost());
        assertEquals(2,bodyArmor.getStrengthBoost());
        assertEquals(4,head.getDexterityBoost());
        assertEquals(1,bodyArmor.getDexterityBoost());
        assertEquals(3,head.getRobustnessBoost());
        assertEquals(1,bodyArmor.getRobustnessBoost());
        assertEquals(2,head.getIntelligenceBoost());
        assertEquals(1,bodyArmor.getIntelligenceBoost());
        assertEquals(1,head.getWisdomBoost());
        assertEquals(-1,bodyArmor.getWisdomBoost());
        assertEquals(-3,head.getCharismaBoost());
        assertEquals(1,bodyArmor.getCharismaBoost());
        assertEquals(4,head.getSpeedBoost());
        assertEquals(1,bodyArmor.getSpeedBoost());
    }

}
