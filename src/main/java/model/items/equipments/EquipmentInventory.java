package model.items.equipments;

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

    public Equipment getHead() {
        return head;
    }

    public Equipment getBody() {
        return body;
    }

    public Equipment getLegs() {
        return legs;
    }

    public Equipment getFeet() {
        return feet;
    }

    public Equipment getHands() {
        return hands;
    }

    public Equipment getBelt() {
        return belt;
    }

    public Equipment getMantle() {
        return mantle;
    }

    public Equipment getAmulet() {
        return amulet;
    }

    public Equipment getLeftRing() {
        return leftRing;
    }

    public Equipment getRightRing() {
        return rightRing;
    }
}
