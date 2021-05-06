package exceptions;

import model.items.ItemType;
import model.job.JobType;

public class UnsupportedItemException extends Exception {

    public UnsupportedItemException(ItemType itemType) {
        super("Unsupported item " + itemType.name());
    }

    public UnsupportedItemException(String err) {
        super(err);
    }
}
