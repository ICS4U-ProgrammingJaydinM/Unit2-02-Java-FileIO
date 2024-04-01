import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * FileIO class that reads a file and writes the sum of each line's numbers to another file.
 *
 * @author Jaydin Madore
 * @version 1
 * @since 2023-04-01
 */
public final class FileIO {

    private FileIO() {
        throw new UnsupportedOperationException("Cannot instantiate");
    }

    public static void main(String[] args) {
        // Define input and output file objects.
        File inputFile = new File("Input.txt");
        File outputFile = new File("Output.txt");
        // This line sets up the output file, and the BufferedWriter 
        // is used to write the sum of numbers from each line in the input file.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Open the input file for reading.
            try (Scanner scanner = new Scanner(inputFile)) {
                // Goes through each line in the input file.
                while (scanner.hasNextLine()) {
                    // Reads the line it's on.
                    String line = scanner.nextLine();
                    //Than calculates the sum of numbers on that line.
                    double sum = getSumOfNumbers(line);
                    //Than it writes the sum to the output file.
                    writer.write(sum + System.lineSeparator());
                }
            } catch (NumberFormatException e) {
                // catchs invalid input.
                handleInvalidInput(writer);
            }
        } catch (IOException e) {
            // Handle any IOException that might occur during file operations
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to calculate the sum of numbers in a given line
    private static double getSumOfNumbers(String line) {
        // Split the line into individual number strings
        String[] numberStrings = line.split(" ");
        double sum = 0;
        // Iterate through each number string and calculate the sum
        for (String numberStr : numberStrings) {
            sum += Double.parseDouble(numberStr);
        }
        return sum;
    }

    // Method to handle the case where input file contains invalid numbers
    private static void handleInvalidInput(BufferedWriter writer) throws IOException {
        // Write a message indicating invalid input to the output file
        if (writer != null) {
            writer.write("INVALID INPUT!");
            writer.newLine();
        }
    }
}
