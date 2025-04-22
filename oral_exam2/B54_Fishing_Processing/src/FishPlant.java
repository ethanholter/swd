import java.util.concurrent.ArrayBlockingQueue;
import java.security.SecureRandom;

/**
 * a runnable fish plant node that receives fish from the dock and sends fish to the truck after filtering out defects.
 */
public class FishPlant implements Runnable{
    private final ArrayBlockingQueue<Fish> inputBuffer = new ArrayBlockingQueue<>(5, true);
    private final int plantNumber;
    private final SecureRandom generator = new SecureRandom();
    private final Truck[] destinationTrucks;
    private int messengerFishCount = 0;

    /**
     * Constructor
     * @param trucks the destination trucks
     * @param plantNumber the plant number
     */
    public FishPlant(Truck[] trucks, int plantNumber) {
        this.plantNumber = plantNumber;
        this.destinationTrucks = trucks;
    }

    /**
     * Run method. runs until 2 messenger fish are received.
     */
    @Override
    public void run() {
        while (messengerFishCount < 2) {
            // receive fish from dock
            Fish fish = receiveFish();

            if (fish.isMessengerFish()) {
                messengerFishCount++;
                continue;
            }

            // check for defects
            if (fish.getDefects()) {
                continue;
            }

            fish.setPlantNumber(plantNumber);

            // pause for 0-3 seconds for proccessing
            proccessDelay();

            // add fish to truck
            // over 5 lbs and 19 inches to the first truck and the rest to the second truck
            if (fish.getWeight() > 5 && fish.getLength() > 19) {
                sendFish(fish, 0);
            } else {
                sendFish(fish, 1);
            }
        }

        for (Truck truck : destinationTrucks) {
            truck.getInputBuffer().offer(new Fish(true));
        }
    }

    /**
     * Pause for 0-3 seconds (chosen randomly)
     */
    private void proccessDelay() {
        try {
            Thread.sleep(generator.nextInt(3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Receive fish from the input buffer (dock)
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
     * Send fish to the truck's input buffer
     * @param fish the fish
     * @param truckNumber the truck number
     */
    private void sendFish(Fish fish, int truckNumber) {
        while (true) {
            if (destinationTrucks[truckNumber].getInputBuffer().offer(fish)) {
                break;
            }
        }
    }

    /**
     * Get the input buffer
     * @return the input buffer
     */
    public ArrayBlockingQueue<Fish> getInputBuffer() {
        return inputBuffer;
    }

    /**
     * Get the plant number
     * @return the plant number
     */
    public int getPlantNumber() {
        return plantNumber;
    }

}
