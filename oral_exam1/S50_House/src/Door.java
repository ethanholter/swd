
/**
 * Class to represent a Door
 */
public class Door {
    private String color;

    /**
     * Constructor for Door
     * @param color
     */
    public Door(String color) {
        this.setColor(color);
    }

    /**
     * Default constructor for Door. Default color is brown
     */
    public Door(){
        this("brown");
    }

    /**
     * Method to get the color of the door
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Method to set the color of the door
     * @param color
     */
    public void setColor(String color) {
        if (color.equals("Transparent")) {
            throw new IllegalArgumentException("Color cannot be 'Transparent'");
        }

        this.color = color;
    }

    /**
     * To string method for Door
     */
    @Override
    public String toString() {
        return String.format("I am a %s Door", this.color);
    }
}
