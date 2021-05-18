package fr.univ_amu.heromanager.exceptions;

import fr.univ_amu.heromanager.model.items.ItemType;

/**
 * Exception for methods who cannot handle specific item type exception
 */
public class UnsupportedItemException extends Exception {

    /**
     * @param itemType unsupported item type
     */
    public UnsupportedItemException(ItemType itemType) {
        super("Unsupported item " + itemType.name());
    }

    /**
     * @param err string error
     */
    public UnsupportedItemException(String err) {
        super(err);
    }
}
