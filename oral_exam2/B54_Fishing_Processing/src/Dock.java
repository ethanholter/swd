import java.util.concurrent.ArrayBlockingQueue;

/**
 * a runnable dock node that receives fish from the fisherman and sends fish to the fish plant.
 */
public class Dock implements Runnable {

    private final ArrayBlockingQueue<Fish> inputBuffer = new ArrayBlockingQueue<>(3, true);
    private final String fishType;
    private final int dockNumber;
    private final FishPlant destinationPlant;
    private boolean running = true;

    /**
     * Constructor
     * @param destinationPlant the destination plant
     * @param fishType the fish type
     * @param dockNumber the dock number
     */
    public Dock(FishPlant destinationPlant, String fishType, int dockNumber) {
        this.destinationPlant = destinationPlant;
        this.fishType = fishType;
        this.dockNumber = dockNumber;
    }

    /**
     * Run method. runs until a messenger fish is received.
     */
    @Override
    public void run() {
        while (running) {
            Fish fish = receiveFish();

            if(fish.isMessengerFish()){
                running = false;
            }

            fish.setDockNumber(dockNumber);
            sendFish(fish);
        }
    }

    /**
     * Send fish to the destination plant
     * @param fish the fish
     */
    private void sendFish(Fish fish) {
        while (true) {
            if (destinationPlant.getInputBuffer().offer(fish)) {
                break;
            }
        }
    }

    /**
     * Receive fish from the input buffer (fisherman)
     * @return the fish
     */
    private Fish receiveFish() {
        Fish fish = null;

        while (fish == null) {
            fish = inputBuffer.poll();
        }
        return fish;
    }

    /**
     * Get the fish type
     * @return the fish type
     */
    public String getFishType() {
        return fishType;
    }

    /**
     * Get the input buffer
     * @return the input buffer
     */
    public ArrayBlockingQueue<Fish> getInputBuffer() {
        return inputBuffer;
    }

}
