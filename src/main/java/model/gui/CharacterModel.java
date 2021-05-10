package model.gui;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.items.Item;
import model.items.ItemType;
import model.items.equipments.Equipment;
import model.items.equipments.EquipmentPart;
import model.items.weapons.Weapon;
import model.job.Job;
import utils.gui.Dialog;
import view.CharacterView;

import java.util.Optional;

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
        character.setHealthPoints(newHP);
        CharacterView.getInstance().setHP(newHP, 100); // TODO
    }

    /**
     * When new hp pop-up updater canceled
     */
    public void hpBarOnClickEventCancel() {
        // do nothing
    }

    /**
     * Event handler when user clicks on item slot in inventory tab
     *
     * @param item  selected item
     * @param event information about event click
     */
    public void inventoryClickedEvent(Item item, MouseEvent event) {
        if (item != null) {
            ContextMenu clickMenu = new ContextMenu();

            if (item instanceof Equipment) {
                MenuItem equip = new MenuItem("Equip");
                equip.setId("equipAction");
                equip.setOnAction(event1 -> {
                    // TODO wait guillaume implementation
                });

                clickMenu.getItems().add(equip);
            }

            MenuItem info = new MenuItem("Info");
            info.setId("infoAction");
            info.setOnAction(event1 -> {
                StringBuilder description = new StringBuilder();

                if (item instanceof Weapon) {
                    description.append("Weapon Type: ").append(((Weapon) item).getWeaponType())
                            .append("\nDamage type inflicted: ").append(((Weapon) item).getDamageType())
                            .append("\n\nProperties: ").append(((Weapon) item).getProperties())
                            .append("\n\n---\n\n");
                }
                else if (item instanceof Equipment) {
                    description.append("Can be worn on: ").append(((Equipment) item).getEquipmentPart())
                            .append("\nEquipment type: ").append(((Equipment) item).getEquipmentPart())
                            .append("\n\nStatistics:")
                            .append("\n\t- Armor Bonus:\t\t").append(((Equipment) item).getArmorBonus())
                            .append("\n\t- Strength Boost:\t\t").append(((Equipment) item).getStrengthBoost())
                            .append("\n\t- Dexterity Boost: \t\t").append(((Equipment) item).getDexterityBoost())
                            .append("\n\t- Robustness Boost: \t").append(((Equipment) item).getRobustnessBoost())
                            .append("\n\t- Intelligence Boost: \t").append(((Equipment) item).getIntelligenceBoost())
                            .append("\n\t- Wisdom Boost: \t\t").append(((Equipment) item).getWisdomBoost())
                            .append("\n\t- Charisma Boost: \t\t").append(((Equipment) item).getCharismaBoost())
                            .append("\n\t- Speed Boost: \t\t").append(((Equipment) item).getSpeedBoost())
                            .append("\n\n---\n\n");
                }

                description.append(item.getDescription());
                new Dialog(Alert.AlertType.INFORMATION, item.getName(), description.toString()).show();
            });

            SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
            MenuItem discard = new MenuItem("Discard");
            discard.setId("discardAction");

            discard.setOnAction(event1 -> {
                Dialog confirmation = new Dialog(
                        Alert.AlertType.CONFIRMATION,
                        "Discard " + item.getName(),
                        "Are you sure you want to drop " + item.getName() + " ?");

                confirmation.getButtons().clear();
                confirmation.getButtons().addAll(new ButtonType("Yes", ButtonBar.ButtonData.YES), new ButtonType("No", ButtonBar.ButtonData.NO));
                Optional<ButtonType> result = confirmation.showAndWait();
                if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                    getCharacter().getInventory().remove(item);
                    CharacterView.getInstance().setInventory(getCharacter().getInventory());
                }
            });

            clickMenu.getItems().addAll(info, separatorMenuItem, discard);
            clickMenu.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
        } else {
            new ItemPickerModel(CharacterView.getInstance().getStage(), () -> {
                Item selectedItem = ItemPickerModel.getInstance().getSelectedItem();
                getCharacter().getInventory().add(selectedItem);
                CharacterView.getInstance().setInventory(getCharacter().getInventory());
            });
        }
    }

    /**
     * Event handler when user clicks on equipment slot in equipment tab
     *
     * @param equipmentPart part of equipment
     */
    public void equipmentImageOnClick(EquipmentPart equipmentPart) {
        // TODO
    }

    /**
     * Used to open item picker view with a given item type
     *
     * @param itemType item type to select
     */
    public void openItemPicker(ItemType itemType) {
        // ItemPickerModel itemPicker = new ItemPickerModel(CharacterView.getInstance().getStage(), itemType);
        // Item selectedItem = itemPicker.getSelectedItem();

        // TODO
    }

    /**
     * Used to open item picker view with a given equipment type
     *
     * @param equipmentType equipment type to select
     */
    public void openItemPicker(Class<? extends Equipment> equipmentType) {
        // ItemPickerModel itemPicker = new ItemPickerModel(CharacterView.getInstance().getStage(), ItemType.EQUIPMENTS);
        // Item selectedItem = itemPicker.getSelectedItem();

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
