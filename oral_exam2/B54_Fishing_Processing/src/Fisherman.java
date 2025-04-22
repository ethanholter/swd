import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * a runnable fisherman node that "catches" fish (from a csv file) and transports them to the dock.
 */
public class Fisherman implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Dock[] docks;
    private final ArrayBlockingQueue<Fish> fishStorage = new ArrayBlockingQueue<Fish>(10, true);
    private Scanner scanner;

    /**
     * Constructor
     * @param docks the destination docks
     * @param fileName the fish file name
     */
    Fisherman(Dock[] docks, String fileName) {
        this.docks = docks;
        ArrayBlockingQueue[] outputBuffers = new ArrayBlockingQueue[4];
        for (int i = 0; i < outputBuffers.length; i++) {
            outputBuffers[i] = new ArrayBlockingQueue<Fish>(3);
        }

        // open fish file
        try {
            scanner = new Scanner(Paths.get(fileName));
            scanner.nextLine(); // skip header
        } catch (IOException e) {
            System.out.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
    @Override
    public void run() {
        while (scanner.hasNext()) {

            while (scanner.hasNext() && fishStorage.remainingCapacity() > 0) {
                catchFish();
            }

            // wait 0 to 4 seconds (random)
            travel();

            // unload fish at docks
            while (!fishStorage.isEmpty()) {
                unloadFish();
            }
            travel();
        }

        for (Dock dock : docks) {
            dock.getInputBuffer().offer(new Fish(true));
        }
    }

    /**
     * Unloads fish from the fisherman's storage to the associated dock.
     */
    private void unloadFish() {
        Fish fish = fishStorage.poll();

        // find dock with matching fish type
        for (Dock dock : docks) {
            if (dock.getFishType().equals(fish.getType())) {
                dock.getInputBuffer().offer(fish);
                break;
            }
        }
    }

    /**
     * Pause for 0-4 seconds (chosen randomly)
     */
    private void travel() {
        try {
            Thread.sleep(generator.nextInt(4000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * read fish from the input file and add to the fish storage
     */
    private void catchFish() {
        Fish fish = new Fish(scanner.nextLine());
        fishStorage.add(fish);
    }

}