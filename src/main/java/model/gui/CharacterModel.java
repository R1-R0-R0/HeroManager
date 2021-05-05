package model.gui;

import model.items.Item;
import model.items.ItemType;
import model.items.equipments.Equipment;
import model.job.Job;
import view.CharacterView;

/**
 * Model of Character Card view.
 * Called for showing Character view and handle all view logic.
 *
 * @see CharacterView associated class view (MVC pattern)
 * @see controller.CharacterController associated class controller (MVC pattern)
 */
public class CharacterModel {

    /**
     * Instance of this class
     */
    private static CharacterModel instance;
    private Job character;

    /**
     * Constructor of this class, called to show character view
     *
     * @param character job/character information to show
     */
    public CharacterModel(Job character) {
        instance = this;
        this.character = character;

        new CharacterView();
    }

    /**
     * @return instance of this class
     */
    public static CharacterModel getInstance() {
        return instance;
    }

    /**
     * Gets HP of current Job and send it to a new pop-up to update it
     */
    public void hpBarOnClickEvent() {
        new HpPopUpModel(100);
        // CharacterView.getInstance().blockWindow();
    }

    /**
     * Called by HpPopUpModel when event is done and gives new hp value
     *
     * @param newHP New HP to set
     */
    public void hpBarOnClickEventDone(int newHP) {
        // TODO
    }

    /**
     * When new hp pop-up updater canceled
     */
    public void hpBarOnClickEventCancel() {
        // TODO
    }

    /**
     * Event handler when user clicks on item slot in inventory tab
     *
     * @param item selected item
     */
    public void inventoryClickedEvent(Item item) {
        // TODO
    }

    /**
     * Event handler when user clicks on equipment slot in equipment tab
     *
     * @param equipment
     */
    public void equipmentImageOnClick(Class<? extends Equipment> equipment) {
        // TODO
    }

    /**
     * Used to open item picker view with a given item type
     *
     * @param itemType item type to select
     */
    public void openItemPicker(ItemType itemType) {
        ItemPickerModel itemPicker = new ItemPickerModel(CharacterView.getInstance().getStage(), itemType);
        Item selectedItem = itemPicker.getSelectedItem();

        // TODO
    }

    /**
     * Used to open item picker view with a given equipment type
     *
     * @param equipmentType equipment type to select
     */
    public void openItemPicker(Class<? extends Equipment> equipmentType) {
        ItemPickerModel itemPicker = new ItemPickerModel(CharacterView.getInstance().getStage(), ItemType.EQUIPMENTS);
        Item selectedItem = itemPicker.getSelectedItem();

        // TODO
    }

    /**
     * Called to open dice window
     */
    public void openDiceWindow() {
        new DiceModel();
    }

    /**
     * @return current character handled by the view
     */
    public Job getCharacter() {
        return character;
    }
}
