package fr.univ_amu.heromanager.utils;

import fr.univ_amu.heromanager.model.items.Item;

public interface ItemPickerCloseEvent {
    void event(Item selectedItem);
}
