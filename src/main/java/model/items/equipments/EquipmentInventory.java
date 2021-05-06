package model.items.equipments;


/**
 * Contains all the equipped equipments.
 */
public class EquipmentInventory {
    private Equipment head,body,legs,feet,hands,belt,mantle,amulet,leftRing,rightRing;

    public EquipmentInventory(Equipment head, Equipment body, Equipment legs, Equipment feet,
                              Equipment hands, Equipment belt, Equipment mantle, Equipment amulet,
                              Equipment leftRing, Equipment rightRing) {
        this.head = head;
        this.body = body;
        this.legs = legs;
        this.feet = feet;
        this.hands = hands;
        this.belt = belt;
        this.mantle = mantle;
        this.amulet = amulet;
        this.leftRing = leftRing;
        this.rightRing = rightRing;
    }

    /**
     *
     * @return head equipped
     */
    public Equipment getHead() {
        return head;
    }

    /**
     *
     * @return body equipped
     */
    public Equipment getBody() {
        return body;
    }

    /**
     *
     * @return legs equipped
     */
    public Equipment getLegs() {
        return legs;
    }

    /**
     *
     * @return feet equipped
     */
    public Equipment getFeet() {
        return feet;
    }

    /**
     *
     * @return hands equipped
     */
    public Equipment getHands() {
        return hands;
    }

    /**
     *
     * @return belt equipped
     */
    public Equipment getBelt() {
        return belt;
    }

    /**
     *
     * @return mantle equipped
     */
    public Equipment getMantle() {
        return mantle;
    }

    /**
     *
     * @return amulet equipped
     */
    public Equipment getAmulet() {
        return amulet;
    }

    /**
     *
     * @return leftRing equipped
     */
    public Equipment getLeftRing() {
        return leftRing;
    }

    /**
     *
     * @return rightRing equipped
     */
    public Equipment getRightRing() {
        return rightRing;
    }
}
