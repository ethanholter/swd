import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This program reads a survey data file and filters the data based on a product and demographic attribute.
 */
public class SurveyDriver {

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner cliScanner = new Scanner(System.in);

        Scanner fileScanner = null;

        System.out.print("""
                Enter input file path
                >>\s""");
        String inputPath = cliScanner.nextLine();

        fileScanner = openScanner(inputPath);

        if (fileScanner == null) return;

        System.out.print("""
                Product name to filter for (case insensitive)
                >>\s""");
        String product = cliScanner.nextLine().toLowerCase();

        System.out.print("""
                "Demographic attribute to filter on. Enter a value contained in any of the following columns (case insensitive):
                [Gender|Age Group|State of Residence]
                >>\s""");
        String demographic = cliScanner.nextLine().toLowerCase();

        System.out.print("""
                Enter output file path
                >>\s""");
        String outputPath = cliScanner.nextLine();

        //create output file
        Formatter outputFormatter = openFormatter(outputPath);
        if (outputFormatter == null) {
            return;
        }

        int[] ratings = compileRatings(fileScanner, product, demographic);

        writeRatingsToFile(outputFormatter, ratings, outputPath);
    }

    /**
     * Opens a file for writing
     * @param outputPath the path to the file
     * @return a Formatter object
     */
    private static Formatter openFormatter(String outputPath) {
        Formatter outputFormatter = null;
        try {
            outputFormatter = new Formatter(outputPath);
        } catch (FileNotFoundException e) {
            System.out.println("Error creating output file. Terminating.");
            return null;
        }
        return outputFormatter;
    }

    /**
     * Opens a file for reading
     * @param inputPath the path to the file
     * @return a Scanner object
     */
    private static Scanner openScanner(String inputPath) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(Paths.get(inputPath));
            fileScanner.nextLine(); // skip header

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Terminating.");
            return null;

        } catch (IOException e) {
            System.out.println("Error opening file. Terminating.");
            return null;
        }
        return fileScanner;
    }

    /**
     * Writes the ratings to the output file
     * @param outputFormatter the Formatter object
     * @param ratings the ratings array
     * @param outputPath the path to the output file
     */
    private static void writeRatingsToFile(Formatter outputFormatter, int[] ratings, String outputPath) {
        outputFormatter.format("Rating,Frequency\n");

        for (int i = 1; i < ratings.length; i++) {
            outputFormatter.format("%d,%d\n", i, ratings[i]);
        }

        System.out.println("Results written to " + outputPath);
        outputFormatter.close();
    }

    /**
     * Compiles the ratings based on the product and demographic
     * @param fileScanner the Scanner object
     * @param product the product to filter on
     * @param demographic the demographic to filter on
     * @return an array of ratings
     */
    private static int[] compileRatings(Scanner fileScanner, String product, String demographic) {
        int[] ratings = new int[6]; // 0-5
        while (fileScanner.hasNext()) {
            String[] line = fileScanner.nextLine().split(",");
            if (line[3].equals(product)
                    && (line[0].toLowerCase().equals(demographic)
                    || line[1].toLowerCase().equals(demographic)
                    || line[2].toLowerCase().equals(demographic))) {

                ratings[Integer.parseInt(line[4])]++;
            }
        }
        return ratings;
    }

}
