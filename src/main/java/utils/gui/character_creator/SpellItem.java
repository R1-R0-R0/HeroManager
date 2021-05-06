package utils.gui.character_creator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.spell.Spell;

/**
 * Utility class used to encapsulate Spell object and give them selection property for list view
 */
public class SpellItem {
    private Spell spell;
    private BooleanProperty selected;

    /**
     * @param spell spell object to attach
     */
    public SpellItem(Spell spell) {
        this.spell = spell;
        selected = new SimpleBooleanProperty(false);
    }

    /**
     * @return attached spell
     */
    public Spell getSpell() {
        return spell;
    }

    /**
     * @return BooleanProperty selection property, used for checkbox in list view
     */
    public BooleanProperty selectedProperty() {
        return selected;
    }

    /**
     * @return TRUE if item is selected, FALSE otherwise
     */
    public boolean isSelected() {
        return selected.get();
    }

    /**
     * @return toString() result of attached spell
     */
    @Override
    public String toString() {
        return spell.toString();
    }
}
