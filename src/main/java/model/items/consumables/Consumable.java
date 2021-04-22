package model.items.consumables;

import model.items.Item;

public class Consumable implements Item {

    private ConsumableEffect effect;

    public Consumable(ConsumableEffect effect) {
        this.effect = effect;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
