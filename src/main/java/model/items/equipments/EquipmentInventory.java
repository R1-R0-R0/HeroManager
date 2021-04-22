package model.items.equipments;

public class EquipmentInventory {
    private HeadEquipment head;
    private BodyEquipment body;
    private LegsEquipment legs;
    private FeetEquipment feet;
    private HandsEquipment hands;
    private BeltEquipment belt;
    private MantleEquipment mantle;
    private AmuletEquipment amulet;
    private RingEquipment leftRing;
    private RingEquipment rightRing;

    public EquipmentInventory(HeadEquipment head, BodyEquipment body, LegsEquipment legs, FeetEquipment feet,
                              HandsEquipment hands, BeltEquipment belt, MantleEquipment mantle, AmuletEquipment amulet,
                              RingEquipment leftRing, RingEquipment rightRing) {
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

    public HeadEquipment getHead() {
        return head;
    }

    public BodyEquipment getBody() {
        return body;
    }

    public LegsEquipment getLegs() {
        return legs;
    }

    public FeetEquipment getFeet() {
        return feet;
    }

    public HandsEquipment getHands() {
        return hands;
    }

    public BeltEquipment getBelt() {
        return belt;
    }

    public MantleEquipment getMantle() {
        return mantle;
    }

    public AmuletEquipment getAmulet() {
        return amulet;
    }

    public RingEquipment getLeftRing() {
        return leftRing;
    }

    public RingEquipment getRightRing() {
        return rightRing;
    }
}
