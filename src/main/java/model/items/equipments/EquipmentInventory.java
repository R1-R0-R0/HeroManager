package model.items.equipments;


import java.util.ArrayList;
import java.util.List;

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

    public List<Equipment> getEquippedList(){
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
    public void removeHead(){
        this.head = null;
    }

    /**
     * remove hands Equipment
     */
    public void removeHands(){
        this.hands = null;
    }

    /**
     * remove belt Equipment
     */
    public void removeBelt(){
        this.belt = null;
    }
    /**
     * remove feet Equipment
     */
    public void removeFeet(){
        this.feet = null;
    }
    /**
     * remove legs Equipment
     */
    public void removeLegs(){
        this.legs = null;
    }
    /**
     * remove mantle Equipment
     */
    public void removeMantle(){
        this.mantle = null;
    }
    /**
     * remove amulet Equipment
     */
    public void removeAmulet(){
        this.amulet = null;
    }
    /**
     * remove leftRing Equipment
     */
    public void removeLeftRing(){
        this.leftRing = null;
    }
    /**
     * remove rightRing Equipment
     */
    public void removeRightRing(){
        this.rightRing = null;
    }
    /**
     * remove body Equipment
     */
    public void removeBody(){
        this.body = null;
    }

    /**
     * add head equipment if same EquipmentPart
     * @param equipment
     */
    public void addHead(Equipment equipment){
        if (equipment.getEquipmentPart() == EquipmentPart.HEAD)
            this.head = equipment;
    }
    /**
     * add hands equipment if same EquipmentPart
     * @param equipment
     */
    public void addHands(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.HANDS)
            this.hands = equipment;
    }
    /**
     * add belt equipment if same EquipmentPart
     * @param equipment
     */
    public void addBelt(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.BELT)
            this.belt = equipment;
    }
    /**
     * add feet equipment if same EquipmentPart
     * @param equipment
     */
    public void addFeet(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.FEET)
            this.feet = equipment;
    }
    /**
     * add legs equipment if same EquipmentPart
     * @param equipment
     */
    public void addLegs(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.LEGS)
            this.legs = equipment;
    }
    /**
     * add mantle equipment if same EquipmentPart
     * @param equipment
     */
    public void addMantle(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.MANTLE)
            this.mantle = equipment;
    }
    /**
     * add amulet equipment if same EquipmentPart
     * @param equipment
     */
    public void addAmulet(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.AMULET)
            this.amulet = equipment;
    }
    /**
     * add leftRing equipment if same EquipmentPart
     * @param equipment
     */
    public void addLeftRing(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.RING)
            this.leftRing = equipment;
    }
    /**
     * add RightRing equipment if same EquipmentPart
     * @param equipment
     */
    public void addRightRing(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.RING)
        this.rightRing = equipment;
    }
    /**
     * add body equipment if same EquipmentPart
     * @param equipment
     */
    public void addBody(Equipment equipment){
        if(equipment.getEquipmentPart() == EquipmentPart.BODY)
            this.body = equipment;
    }

    /**
     * remove and add head Equipment
     * @param equipment
     */
    public void replaceHead(Equipment equipment){
        removeHead();
        addHead(equipment);
    }
    /**
     * remove and add hands Equipment
     * @param equipment
     */
    public void replaceHands(Equipment equipment){
        removeHands();
        addHands(equipment);
    }
    /**
     * remove and add belt Equipment
     * @param equipment
     */
    public void replaceBelt(Equipment equipment){
        removeBelt();
        addBelt(equipment);
    }
    /**
     * remove and add feet Equipment
     * @param equipment
     */
    public void replaceFeet(Equipment equipment){
        removeFeet();
        addFeet(equipment);
    }
    /**
     * remove and add legs Equipment
     * @param equipment
     */
    public void replaceLegs(Equipment equipment){
        removeLegs();
        addLegs(equipment);
    }
    /**
     * remove and add mantle Equipment
     * @param equipment
     */
    public void replaceMantle(Equipment equipment){
        removeMantle();
        addMantle(equipment);
    }
    /**
     * remove and add amulet Equipment
     * @param equipment
     */
    public void replaceAmulet(Equipment equipment){
        removeAmulet();
        addAmulet(equipment);
    }
    /**
     * remove and add leftRing Equipment
     * @param equipment
     */
    public void replaceLeftRing(Equipment equipment){
        removeLeftRing();
        addLeftRing(equipment);
    }
    /**
     * remove and add RightRing Equipment
     * @param equipment
     */
    public void replaceRightRing(Equipment equipment){
        removeRightRing();
        addRightRing(equipment);
    }
    /**
     * remove and add body Equipment
     * @param equipment
     */
    public void replaceBody(Equipment equipment){
        removeBody();
        addBody(equipment);
    }





}
