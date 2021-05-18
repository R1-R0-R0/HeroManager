package fr.univ_amu.heromanager.model.items.equipments;


import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the equipped equipments.
 */
public class EquipmentInventory {
    private Equipment head, body, legs, feet, hands, belt, mantle, amulet, leftRing, rightRing;

    public EquipmentInventory(Equipment head, Equipment body, Equipment legs, Equipment feet,
                              Equipment hands, Equipment belt, Equipment mantle, Equipment amulet,
                              Equipment leftRing, Equipment rightRing) {
        if (head != null && head.getEquipmentPart() == EquipmentPart.HEAD)
            this.head = head;
        if (body != null && body.getEquipmentPart() == EquipmentPart.BODY)
            this.body = body;
        if (legs != null && legs.getEquipmentPart() == EquipmentPart.LEGS)
            this.legs = legs;
        if (feet != null && feet.getEquipmentPart() == EquipmentPart.FEET)
            this.feet = feet;
        if (hands != null && hands.getEquipmentPart() == EquipmentPart.HANDS)
            this.hands = hands;
        if (belt != null && belt.getEquipmentPart() == EquipmentPart.BELT)
            this.belt = belt;
        if (mantle != null && mantle.getEquipmentPart() == EquipmentPart.MANTLE)
            this.mantle = mantle;
        if (amulet != null && amulet.getEquipmentPart() == EquipmentPart.AMULET)
            this.amulet = amulet;
        if (leftRing != null && leftRing.getEquipmentPart() == EquipmentPart.RING)
            this.leftRing = leftRing;
        if (rightRing != null && rightRing.getEquipmentPart() == EquipmentPart.RING)
            this.rightRing = rightRing;
    }

    public EquipmentInventory(List<Equipment> adding) {
        for (Equipment equipment : adding) {
            switch (equipment.getEquipmentPart()) {
                case HEAD -> this.head = equipment;
                case BODY -> this.body = equipment;
                case BELT -> this.belt = equipment;
                case LEGS -> this.legs = equipment;
                case FEET -> this.feet = equipment;
                case AMULET -> this.amulet = equipment;
                case HANDS -> this.hands = equipment;
                case MANTLE -> this.mantle = equipment;
                case RING -> this.rightRing = equipment;
                case RING2 -> this.leftRing = equipment;
            }
        }
    }

    /**
     * @return head equipped
     */
    public Equipment getHead() {
        return head;
    }

    /**
     * @return body equipped
     */
    public Equipment getBody() {
        return body;
    }

    /**
     * @return legs equipped
     */
    public Equipment getLegs() {
        return legs;
    }

    /**
     * @return feet equipped
     */
    public Equipment getFeet() {
        return feet;
    }

    /**
     * @return hands equipped
     */
    public Equipment getHands() {
        return hands;
    }

    /**
     * @return belt equipped
     */
    public Equipment getBelt() {
        return belt;
    }

    /**
     * @return mantle equipped
     */
    public Equipment getMantle() {
        return mantle;
    }

    /**
     * @return amulet equipped
     */
    public Equipment getAmulet() {
        return amulet;
    }

    /**
     * @return leftRing equipped
     */
    public Equipment getLeftRing() {
        return leftRing;
    }

    /**
     * @return rightRing equipped
     */
    public Equipment getRightRing() {
        return rightRing;
    }

    /**
     * The list of equippedEquipment, if no equipment in the wanted Part, put null in the List
     *
     * @return List of equippedEquipment
     */
    public List<Equipment> getEquippedList() {
        List<Equipment> equipped = new ArrayList<>();
        equipped.add(head);
        equipped.add(body);
        equipped.add(legs);
        equipped.add(feet);
        equipped.add(hands);
        equipped.add(belt);
        equipped.add(mantle);
        equipped.add(amulet);
        equipped.add(leftRing);
        equipped.add(rightRing);

        return equipped;
    }

    /**
     * remove head Equipment
     */
    public void removeHead() {
        this.head = null;
    }

    /**
     * remove hands Equipment
     */
    public void removeHands() {
        this.hands = null;
    }

    /**
     * remove belt Equipment
     */
    public void removeBelt() {
        this.belt = null;
    }

    /**
     * remove feet Equipment
     */
    public void removeFeet() {
        this.feet = null;
    }

    /**
     * remove legs Equipment
     */
    public void removeLegs() {
        this.legs = null;
    }

    /**
     * remove mantle Equipment
     */
    public void removeMantle() {
        this.mantle = null;
    }

    /**
     * remove amulet Equipment
     */
    public void removeAmulet() {
        this.amulet = null;
    }

    /**
     * remove leftRing Equipment
     */
    public void removeLeftRing() {
        this.leftRing = null;
    }

    /**
     * remove rightRing Equipment
     */
    public void removeRightRing() {
        this.rightRing = null;
    }

    /**
     * remove body Equipment
     */
    public void removeBody() {
        this.body = null;
    }

    /**
     * add head equipment if same EquipmentPart
     *
     * @param equipment equipment to equip on head
     */
    public void addHead(Equipment equipment) {
        if (head == null && equipment.getEquipmentPart() == EquipmentPart.HEAD)
            this.head = equipment;
    }

    /**
     * add hands equipment if same EquipmentPart
     *
     * @param equipment equipment to equip on hands
     */
    public void addHands(Equipment equipment) {
        if (hands == null && equipment.getEquipmentPart() == EquipmentPart.HANDS)
            this.hands = equipment;
    }

    /**
     * add belt equipment if same EquipmentPart
     *
     * @param equipment equipment to equip to the size
     */
    public void addBelt(Equipment equipment) {
        if (belt == null && equipment.getEquipmentPart() == EquipmentPart.BELT)
            this.belt = equipment;
    }

    /**
     * add feet equipment if same EquipmentPart
     *
     * @param equipment equipment to equip on feet
     */
    public void addFeet(Equipment equipment) {
        if (feet == null && equipment.getEquipmentPart() == EquipmentPart.FEET)
            this.feet = equipment;
    }

    /**
     * add legs equipment if same EquipmentPart
     *
     * @param equipment equipment to equip on legs
     */
    public void addLegs(Equipment equipment) {
        if (legs == null && equipment.getEquipmentPart() == EquipmentPart.LEGS)
            this.legs = equipment;
    }

    /**
     * add mantle equipment if same EquipmentPart
     *
     * @param equipment mantle equipment
     */
    public void addMantle(Equipment equipment) {
        if (mantle == null && equipment.getEquipmentPart() == EquipmentPart.MANTLE)
            this.mantle = equipment;
    }

    /**
     * add amulet equipment if same EquipmentPart
     *
     * @param equipment equipment to equip on neck
     */
    public void addAmulet(Equipment equipment) {
        if (amulet == null && equipment.getEquipmentPart() == EquipmentPart.AMULET)
            this.amulet = equipment;
    }

    /**
     * add leftRing equipment if same EquipmentPart
     *
     * @param equipment left ring equipment
     */
    public void addLeftRing(Equipment equipment) {
        if (leftRing == null && equipment.getEquipmentPart() == EquipmentPart.RING)
            this.leftRing = equipment;
    }

    /**
     * add RightRing equipment if same EquipmentPart
     *
     * @param equipment right ring equipment
     */
    public void addRightRing(Equipment equipment) {
        if (rightRing == null && equipment.getEquipmentPart() == EquipmentPart.RING)
            this.rightRing = equipment;
    }

    /**
     * add body equipment if same EquipmentPart
     *
     * @param equipment equipment to equip on body
     */
    public void addBody(Equipment equipment) {
        if (body == null && equipment.getEquipmentPart() == EquipmentPart.BODY)
            this.body = equipment;
    }

    /**
     * remove and add head Equipment
     *
     * @param equipment equipment to replace on head
     */
    public void replaceHead(Equipment equipment) {
        removeHead();
        addHead(equipment);
    }

    /**
     * remove and add hands Equipment
     *
     * @param equipment equipment to replace on hands
     */
    public void replaceHands(Equipment equipment) {
        removeHands();
        addHands(equipment);
    }

    /**
     * remove and add belt Equipment
     *
     * @param equipment equipment to replace on the size
     */
    public void replaceBelt(Equipment equipment) {
        removeBelt();
        addBelt(equipment);
    }

    /**
     * remove and add feet Equipment
     *
     * @param equipment equipment to replace on feet
     */
    public void replaceFeet(Equipment equipment) {
        removeFeet();
        addFeet(equipment);
    }

    /**
     * remove and add legs Equipment
     *
     * @param equipment equipment to replace on legs
     */
    public void replaceLegs(Equipment equipment) {
        removeLegs();
        addLegs(equipment);
    }

    /**
     * remove and add mantle Equipment
     *
     * @param equipment mantle equipment to replace
     */
    public void replaceMantle(Equipment equipment) {
        removeMantle();
        addMantle(equipment);
    }

    /**
     * remove and add amulet Equipment
     *
     * @param equipment equipment to replace on neck
     */
    public void replaceAmulet(Equipment equipment) {
        removeAmulet();
        addAmulet(equipment);
    }

    /**
     * remove and add leftRing Equipment
     *
     * @param equipment equipment to replace on left hand
     */
    public void replaceLeftRing(Equipment equipment) {
        removeLeftRing();
        addLeftRing(equipment);
    }

    /**
     * remove and add RightRing Equipment
     *
     * @param equipment equipment to replace on right hand
     */
    public void replaceRightRing(Equipment equipment) {
        removeRightRing();
        addRightRing(equipment);
    }

    /**
     * remove and add body Equipment
     *
     * @param equipment equipment to replace on body
     */
    public void replaceBody(Equipment equipment) {
        removeBody();
        addBody(equipment);
    }


    /**
     * Remove an equipment by its index (0 = head, ..., rightRing = 9)
     *
     * @param index equipment index to remove
     */
    public void removeEquipment(int index) {
        switch (index) {
            case 0 -> removeHead();
            case 1 -> removeBody();
            case 2 -> removeLegs();
            case 3 -> removeFeet();
            case 4 -> removeHands();
            case 5 -> removeBelt();
            case 6 -> removeMantle();
            case 7 -> removeAmulet();
            case 8 -> removeLeftRing();
            case 9 -> removeRightRing();
        }
    }
}
