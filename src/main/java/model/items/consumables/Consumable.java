package model.items.consumables;

import model.items.Item;

public class Consumable implements Item {

    private String name;
    private String description;

    public Consumable(ConsumableEffect effect, String name, String description) {
        this.name = name;
        this.description = description;
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


