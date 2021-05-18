package fr.univ_amu.heromanager.model.gui;

import fr.univ_amu.heromanager.exceptions.UnsupportedItemException;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import fr.univ_amu.heromanager.model.items.Item;
import fr.univ_amu.heromanager.model.items.ItemType;
import fr.univ_amu.heromanager.model.items.equipments.Equipment;
import fr.univ_amu.heromanager.model.items.equipments.EquipmentPart;
import fr.univ_amu.heromanager.model.items.weapons.Weapon;
import fr.univ_amu.heromanager.model.job.Job;
import fr.univ_amu.heromanager.utils.gui.Dialog;
import fr.univ_amu.heromanager.view.CharacterView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Model of Character Card fr.univ_amu.heromanager.view.
 * Called for showing Character fr.univ_amu.heromanager.view and handle all fr.univ_amu.heromanager.view logic.
 *
 * @see CharacterView associated class fr.univ_amu.heromanager.view (MVC pattern)
 * @see fr.univ_amu.heromanager.controller.CharacterController associated class fr.univ_amu.heromanager.controller (MVC pattern)
 */
public class CharacterModel implements Model {

    /**
     * Instance of this class
     */
    private static CharacterModel instance;
    private Job character;

    /**
     * Constructor of this class, called to show character fr.univ_amu.heromanager.view
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
        new HpPopUpModel(character.getMaxHp());
    }

    /**
     * Called by HpPopUpModel when event is done and gives new hp value
     *
     * @param newHP New HP to set
     */
    public void hpBarOnClickEventDone(int newHP) {
        character.setHealthPoints(newHP);
        CharacterView.getInstance().setHP(newHP, character.getMaxHp());
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
                    getCharacter().replaceEquippedEquipment(((Equipment) item));
                    CharacterView.getInstance().refreshView();
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
                } else if (item instanceof Equipment) {
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
            new ItemPickerModel(CharacterView.getInstance().getStage(), selectedItem -> {
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
    public void equipmentPaneOnClick(EquipmentPart equipmentPart) {
        Equipment equippedItem = null;
        try {
            equippedItem = getCharacter().getEquipment(equipmentPart);
        } catch (UnsupportedItemException e) {
            e.printStackTrace();
            new Dialog(Alert.AlertType.ERROR, e.getMessage(), Arrays.toString(e.getStackTrace())).showAndWait();
            System.exit(1);
        }

        if (equippedItem != null) {
            Point cursorLocation = MouseInfo.getPointerInfo().getLocation();
            ContextMenu clickMenu = new ContextMenu();
            MenuItem unequip = new MenuItem("Unequip");
            unequip.setId("unequipAction");
            Equipment finalEquippedItem = equippedItem;
            unequip.setOnAction(event1 -> {
                getCharacter().removeEquippedEquipment(finalEquippedItem);
                CharacterView.getInstance().refreshView();
            });

            SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

            MenuItem info = new MenuItem("Info");
            info.setId("infoAction");
            Equipment finalEquippedItem1 = equippedItem;
            info.setOnAction(event1 -> {
                String description = "Can be worn on: " + finalEquippedItem1.getEquipmentPart() +
                        "\nEquipment type: " + finalEquippedItem1.getEquipmentPart() +
                        "\n\nStatistics:" +
                        "\n\t- Armor Bonus:\t\t" + finalEquippedItem1.getArmorBonus() +
                        "\n\t- Strength Boost:\t\t" + finalEquippedItem1.getStrengthBoost() +
                        "\n\t- Dexterity Boost: \t\t" + finalEquippedItem1.getDexterityBoost() +
                        "\n\t- Robustness Boost: \t" + finalEquippedItem1.getRobustnessBoost() +
                        "\n\t- Intelligence Boost: \t" + finalEquippedItem1.getIntelligenceBoost() +
                        "\n\t- Wisdom Boost: \t\t" + finalEquippedItem1.getWisdomBoost() +
                        "\n\t- Charisma Boost: \t\t" + finalEquippedItem1.getCharismaBoost() +
                        "\n\t- Speed Boost: \t\t" + finalEquippedItem1.getSpeedBoost() +
                        "\n\n---\n\n" +
                        finalEquippedItem1.getDescription();
                new Dialog(Alert.AlertType.INFORMATION, finalEquippedItem1.getName(), description).show();
            });

            clickMenu.getItems().addAll(unequip, separatorMenuItem, info);
            clickMenu.show(CharacterView.getInstance().getStage(), cursorLocation.getX(), cursorLocation.getY());
            return;
        }

        ItemPickerModel pickerModel = new ItemPickerModel(CharacterView.getInstance().getStage(), ItemType.EQUIPMENTS, selectedItem -> {
            if (selectedItem == null) return;

            if (!(selectedItem instanceof Equipment)) {
                new Dialog(Alert.AlertType.ERROR, "Wrong item type", "An error occured, please select equipments only").showAndWait();
                return;
            }

            if (equipmentPart == EquipmentPart.RING)
                getCharacter().replaceLeftRing(((Equipment) selectedItem));
            else if (equipmentPart == EquipmentPart.RING2)
                getCharacter().replaceRightRing(((Equipment) selectedItem));
            else
                getCharacter().replaceEquippedEquipment(((Equipment) selectedItem));

            CharacterView.getInstance().refreshView();
        });

        List<Equipment> selectedEquipmentPart = new ArrayList<>();
        for (Item item : getCharacter().getInventory()) {
            if (item instanceof Equipment && ((Equipment) item).getEquipmentPart() == equipmentPart)
                selectedEquipmentPart.add((Equipment) item);
        }

        pickerModel.setEquipmentList(selectedEquipmentPart);
    }

    /**
     * Called to open dice window
     */
    public void openDiceWindow() {
        new DiceModel();
    }

    /**
     * @return current character handled by the fr.univ_amu.heromanager.view
     */
    public Job getCharacter() {
        return character;
    }
}
