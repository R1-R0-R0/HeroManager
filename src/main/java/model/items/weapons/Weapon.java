package model.items.weapons;

import model.items.Item;

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

    public String getProperties() {
        return properties;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
