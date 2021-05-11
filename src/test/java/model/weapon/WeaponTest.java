package model.weapon;

import model.items.weapons.DamageType;
import model.items.weapons.Weapon;
import model.items.weapons.WeaponType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Weapon axe = new Weapon("Axe", "Wood axe", "It kills everything that moves", WeaponType.WAR, DamageType.BLUDGEONING);
    Weapon sword = new Weapon("Sword", "Bloody Sword", "Can't cut paper", WeaponType.COMMON, DamageType.SLASHING);

    @Test
    public void getPropertiesTest(){

        assertEquals("It kills everything that moves", axe.getProperties());
        assertEquals("Can't cut paper", sword.getProperties());
        assertNotEquals("Can cut paper", sword.getProperties());
        assertNotEquals("It kills everything that moves", sword.getProperties());
    }

    @Test
    public void getNameTest(){
        assertEquals("Axe", axe.getName());
        assertEquals("Sword", sword.getName());
        assertNotEquals("Sword", axe.getName());
    }

    @Test
    public void getDescriptionTest(){
        assertEquals("Wood axe", axe.getDescription());
        assertEquals("Bloody Sword", sword.getDescription());
        assertNotEquals("BloodySword", sword.getDescription());
    }

    @Test
    public void getWeaponType(){
        assertEquals(WeaponType.WAR,axe.getWeaponType());
        assertEquals(WeaponType.COMMON, sword.getWeaponType());
        assertNotEquals(WeaponType.WAR, sword.getWeaponType());
    }

    @Test
    public void getDamageType(){
        assertEquals(DamageType.BLUDGEONING,axe.getDamageType());
        assertEquals(DamageType.SLASHING, sword.getDamageType());
    }
}
