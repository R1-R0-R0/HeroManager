package model.gui;

import controller.CharacterController;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.Equipment;
import model.items.equipments.HeadEquipment;
import model.job.Job;
import view.CharacterView;
import view.HpPopUpView;

public class CharacterModel {

    private static CharacterModel instance;

    public CharacterModel(Job character) {
        instance = this;

        new CharacterView();
    }

    /**
     * Gets HP of current Job and send it to a new pop-up to update it
     */
    public void hpBarOnClickEvent() {
        new HpPopUpView(100);
        // CharacterView.getInstance().blockWindow();
    }

    /**
     * Result of new hp pop-up updater
     * @param newHP New HP to set
     */
    public void hpBarOnClickEventDone(int newHP) {

    }

    /**
     * When new hp pop-up updater canceled
     */
    public void hpBarOnClickEventCancel() {

    }

    public void inventoryClickedEvent(Item item) {

    }

    public void equipmentImageOnClick(Class<? extends Equipment> equipment) {
        
    }

    public void openItemPicker(ItemType itemType) {
        ItemPickerModel itemPicker = new ItemPickerModel(CharacterView.getInstance().getStage(), itemType);
        Item selectedItem = itemPicker.getSelectedItem();
    }

    public void openItemPicker(Class<? extends Equipment> equipmentType) {
        ItemPickerModel itemPicker = new ItemPickerModel(CharacterView.getInstance().getStage(), ItemType.EQUIPMENTS);
        Item selectedItem = itemPicker.getSelectedItem();
    }

    public void openDiceWindow() {
        new DiceModel();
    }

    public static CharacterModel getInstance() {
        return instance;
    }
}
