
/**
 * Fish class represents a fish object with species, weight, length, defects, dock number, plant number, and truck number.
 */
public class Fish {
    private final String type;
    private final String species;
    private final double weight;
    private final double length;
    private final boolean defects;
    private int dockNumber = -1;
    private int plantNumber = -1;
    private int truckNumber = -1;
    private boolean messengerFish = false;

    /**
     * Constructor
     * @param species the species
     * @param weight the weight
     * @param length the length
     * @param defects the defects
     */
    public Fish(String species, double weight, double length, boolean defects) {
        this.species = species;
        this.type = getType(species);
        this.weight = weight;
        this.length = length;
        this.defects = defects;
    }

    /**
     * Constructor - creates a fish object directly from line entry in fish csv file
     * @param entry the fish entry
     */
    public Fish(String entry) {
        String[] fishData = entry.split(",");
        this.species = fishData[0];
        this.type = getType(species);
        this.weight = Double.parseDouble(fishData[1]);
        this.length = Double.parseDouble(fishData[2]);
        this.defects = fishData[3].equals("N");
    }

    /**
     * Constructor - creates a messenger fish object with no species, weight, or length
     * @param isMessengerFish true if messenger fish
     */
    public Fish(boolean isMessengerFish) {
        if (!isMessengerFish) {
            throw new RuntimeException("Dont use this constructor for non-messenger fish. please.");
        }

        this.messengerFish = true;
        this.species = "Messenger Fish";
        this.type = "Messenger Fish";
        this.weight = 0;
        this.length = 0;
        this.defects = false;
    }

    /**
     * Returns the type of fish based on the species
     * @param species the species
     * @return the type
     */
    private String getType(String species) {
        switch (species) {
            case "Salmon":
            case "Rainbow Trout":
            case "Lake Whitefish":
                return "Salmonidae";
            case "Tuna":
            case "Mackerel":
                return "Scombridae";
            case "Atlantic Cod":
            case "Haddock":
                return "Cod";
            case "Yellow Perch":
            case "Tilapia":
            case "Walleye":
                return "Perch";
            default:
                throw new RuntimeException("Invalid species.");
        }
    }

    /**
     * Returns a string representation of the fish object
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(species).append("    ").append(weight).append("    ").append(length);
        if (dockNumber != -1) {
            sb.append("    Dock: ").append(dockNumber);
        }
        if (plantNumber != -1) {
            sb.append("    Plant: ").append(plantNumber);
        }
        if (truckNumber != -1) {
            sb.append("    Truck: ").append(truckNumber);
        }
        return sb.toString();
    }

    /**
     * Returns the type of fish (for categorization at the docks)
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the species of fish
     * @return the species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Returns the weight of the fish
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the length of the fish
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns true if the fish has defects
     * @return true if defects
     */
    public boolean getDefects() {
        return defects;
    }

    /**
     * Returns the dock number (-1 if the fish has not been assigned to a dock)
     * @return the dock number
     */
    public int getDockNumber() {
        return dockNumber;
    }

    /**
     * Returns the truck number (-1 if the fish has not been assigned to a truck)
     * @return the truck number
     */
    public int getTruckNumber() {
        return truckNumber;
    }

    /**
     * Returns the plant number (-1 if the fish has not been assigned to a plant)
     * @return the plant number
     */
    public int getPlantNumber() {
        return plantNumber;
    }

    /**
     * Sets the dock number
     * @param dockNumber the dock number
     */
    public void setDockNumber(int dockNumber) {
        if (this.dockNumber != -1) {
            throw new RuntimeException("Dock number already set.");
        }

        if (dockNumber < 0 || dockNumber > 3) {
            throw new RuntimeException("Invalid dock number.");
        }

        this.dockNumber = dockNumber;
    }

    /**
     * Sets the plant number
     * @param plantNumber the plant number
     */
    public void setPlantNumber(int plantNumber) {
        if (this.plantNumber != -1) {
            throw new RuntimeException("Plant number already set.");
        }

        if (this.dockNumber == -1) {
            throw new RuntimeException("Dock number not set.");
        }

        if (plantNumber < 0 || plantNumber > 1) {
            throw new RuntimeException("Invalid plant number.");
        }

        this.plantNumber = plantNumber;
    }

    /**
     * Sets the truck number
     * @param truckNumber the truck number
     */
    public void setTruckNumber(int truckNumber) {
        if (this.truckNumber != -1) {
            throw new RuntimeException("Truck number already set.");
        }

        if (this.plantNumber == -1) {
            throw new RuntimeException("Plant number not set.");
        }

        if (this.dockNumber == -1) {
            throw new RuntimeException("Dock number not set.");
        }

        if (truckNumber < 0 || truckNumber > 3) {
            throw new RuntimeException("Invalid truck number.");
        }

        this.truckNumber = truckNumber;
    }

    /**
     * Returns true if the fish is a messenger fish
     * @return true if messenger fish
     */
    public boolean isMessengerFish() {
        return messengerFish;
    }

}
