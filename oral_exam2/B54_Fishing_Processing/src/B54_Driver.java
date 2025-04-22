import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Driver class for the fishing processing simulation.
 */
public class B54_Driver {

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        final Dock[] docks = new Dock[4];
        final FishPlant[] fishPlants = new FishPlant[2];
        final Truck[] trucks = new Truck[4];

        String[] fishTypes = {"Salmonidae", "Scombridae", "Cod", "Perch"};

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(i);
            executorService.execute(trucks[i]);
        }

        for (int i = 0; i < fishPlants.length; i++) {
            fishPlants[i] = new FishPlant(new Truck[]{trucks[2*i], trucks[2*i + 1]}, i);
            executorService.execute(fishPlants[i]);
        }

        for (int i = 0; i < docks.length; i++) {
            docks[i] = new Dock(fishPlants[i/2], fishTypes[i], i);
            executorService.execute(docks[i]);
        }


        Fisherman fisherman = new Fisherman(docks, "oral_exam2/B54_Fishing_Processing/fish_test.csv");
        executorService.execute(fisherman);

        executorService.shutdown();

    }
}
