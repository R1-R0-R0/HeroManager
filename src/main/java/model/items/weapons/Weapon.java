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

    /**
     * @return type of weapon
     * @see WeaponType
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * @return damage type of the weapon
     */
    public DamageType getDamageType() {
        return damageType;
    }

    /**
     * @return name of the weapon
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return description of the weapon
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return name of the weapon
     */
    @Override
    public String toString() {
        return name;
    }
}
