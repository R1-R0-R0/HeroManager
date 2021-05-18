package model.items.weapons;

import model.items.Item;

/**
 * Creation of weapons usable by the character
 */
public class Weapon implements Item {
    private String name, description, properties;
    private WeaponType weaponType;
    private DamageType damageType;

    public Weapon(String name, String description, String properties, WeaponType weaponType, DamageType damageType) {
        this.name = name;
        this.description = description;
        this.properties = properties;
        this.weaponType = weaponType;
        this.damageType = damageType;
    }

    /**
     * @return properties of the character
     */
    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    /**
     * @return type of weapon
     * @see WeaponType
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * @return damage type of the weapon
     */
    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    /**
     * @return name of the weapon
     */
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return description of the weapon
     */
    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return name of the weapon
     */
    @Override
    public String toString() {
        return name;
    }
}
