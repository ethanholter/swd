
/**
 * Class to represent a House
 * @see SmallApartment
 */
public class House {
    private float area;
    private Door door;
    private Person person;

    /**
     * get the person who lives in the house
     * @return the person who lives in the house
     */
    public Person getPerson() {
        return person;
    }

    /**
     * set the person who lives in the house
     * @param person the person who lives in the house
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * get the door of the house
     * @return the door of the house
     */
    public Door getDoor() {
        return door;
    }

    /**
     * set the door of the house
     * @param door the door of the house
     */
    public void setDoor(Door door) {
        this.door = door;
    }

    /**
     * get the area of the house
     * @return the area of the house
     */
    public float getArea() {
        return area;
    }

    /**
     * set the area of the house
     * @param area the area of the house
     */
    public void setArea(float area) {
        this.area = area;
    }

    /**
     * create a house with the given area
     * @param area the area of the house
     */
    public House(float area) {
        this.area = area;
    }

    /**
     * To string method
     * @return I am a house, my area is [AREA HERE] square feet
     */
    @Override
    public String toString() {
        return String.format("I am a house, my area is %,.0f square feet", area);
    }
}
