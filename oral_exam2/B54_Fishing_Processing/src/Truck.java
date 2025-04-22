import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * a runnable truck node that receives fish from the fish plant and prints the fish it receives.
 */
public class Truck implements Runnable {
    private final ArrayBlockingQueue<Fish> inputBuffer = new ArrayBlockingQueue<>(4, true);
    private final ArrayBlockingQueue<Fish> fishStorage = new ArrayBlockingQueue<Fish>(4, true);
    private static final SecureRandom generator = new SecureRandom();
    private final int truckNumber;
    private boolean running = true;

    /**
     * Constructor
     * @param truckNumber the truck number
     */
    public Truck(int truckNumber) {
        this.truckNumber = truckNumber;
    }

    /**
     * Run method. runs until the messenger fish is received.
     */
    @Override
    public void run() {
        while (running) {
            for (int i = 0; i < 4; i++) {
                Fish fish = receiveFish();
                if (fish.isMessengerFish()) {
                    running = false;
                    break;
                }
                fish.setTruckNumber(truckNumber);
                fishStorage.offer(fish);
            }

            // wait 0 to 6 seconds (random)
            travel();

            if (fishStorage.isEmpty()) {
                break;
            }

            System.out.println("Truck " + (truckNumber+1));
            for (int i = 0; i < 4; i++) {
                Fish fish = fishStorage.poll();
                if (fish == null) {
                    break;
                }
                System.out.println("\t" + fish);
            }

        }
    }

    /**
     * Receive fish from the input buffer (fish plant)
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
     * Wait 0-6 seconds, chosen randomly
     */
    private void travel() {
        try {
            Thread.sleep(generator.nextInt(6000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get the input buffer
     * @return the input buffer
     */
    public ArrayBlockingQueue<Fish> getInputBuffer() {
        return inputBuffer;
    }
}
