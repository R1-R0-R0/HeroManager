package model.items.consumables;

import model.items.Item;

/**
 * Creation of consumable items
 */
public class Consumable implements Item {

    private final String name;
    private String description;

    public Consumable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * give name of the consumable
     *
     * @return name of the consumable
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * give description of the consumable
     *
     * @return description of the consumable
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Set description for the consumable
     *
     * @param description for the consumable
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * give name of the consumable
     *
     * @return name of the consumable
     */
    @Override
    public String toString() {
        return name;
    }
}


