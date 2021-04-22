package model.items.consumables;

import model.items.Item;

public class Consumable implements Item {

    private ConsumableEffect effect;
    private String name;
    private String description;

    public Consumable(ConsumableEffect effect, String name, String description) {
        this.effect = effect;
        this.name = name;
        this.description = description;
    }

    public ConsumableEffect getEffect() {
        return effect;
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


