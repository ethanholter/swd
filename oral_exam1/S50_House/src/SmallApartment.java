
/**
 * Class to represent a small apartment
 * @see House
 */
public class SmallApartment extends House{
    private int floor;

    /**
     * Method to get the floor number of the apartment
     * @return floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Method to set the floor number of the apartment
     * @param floor floor number
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Constructor to create a small apartment with a given area and floor number
     * @param area area of the apartment
     */
    public SmallApartment(float area) {
        super(area);
    }

    /**
     * Default constructor to create a small apartment with a default area of 500 sq ft
     */
    public SmallApartment() {
        super(500f);
    }

    /**
     * To String method
     * @return House toString
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
