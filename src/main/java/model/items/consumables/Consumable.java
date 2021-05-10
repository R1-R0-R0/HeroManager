package model.items.consumables;

import model.items.Item;

/**
 * Creation of consumable items
 */
public class Consumable implements Item {

    private final String name;
    private final String description;

    public Consumable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @return name of the consumable
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the consumable
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return name of the consumable
     */
    @Override
    public String toString(){return name;}
}


