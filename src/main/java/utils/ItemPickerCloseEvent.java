package utils;

import model.items.Item;

public interface ItemPickerCloseEvent {
    void event(Item selectedItem);
}
