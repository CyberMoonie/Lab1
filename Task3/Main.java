package Task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Display class definition
class Display {
    private final String model;
    private final String description;
    private final double price;
    private final int width;
    private final int height;
    private final float ppi;

    // Constructor
    public Display(String model, String description, double price, int width, int height, float ppi) {
        this.model = model;
        this.description = description;
        this.price = price;
        this.width = width;
        this.height = height;
        this.ppi = ppi;
    }

    // Getter methods
    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public void compare(Display other) {
        System.out.println("Comparing " + this.model + " with " + other.model + ":");
        this.compareSize(other);
        this.compareSharpness(other);
        System.out.println();
    }

    // Method to compare sizes
    private void compareSize(Display other) {
        int area1 = this.width * this.height;
        int area2 = other.width * other.height;

        if (area1 > area2) {
            System.out.println(this.model + " is larger than " + other.model);
        } else if (area1 < area2) {
            System.out.println(this.model + " is smaller than " + other.model);
        } else {
            System.out.println(this.model + " is the same size as " + other.model);
        }
    }

    // Method to compare sharpness based on PPI
    private void compareSharpness(Display other) {
        if (this.ppi > other.ppi) {
            System.out.println(this.model + " is sharper than " + other.model);
        } else if (this.ppi < other.ppi) {
            System.out.println(this.model + " is less sharp than " + other.model);
        } else {
            System.out.println(this.model + " has the same sharpness as " + other.model);
        }
    }

    @Override
    public String toString() {
        return "Model: " + model + "\nDescription: " + description + "\nPrice: $" + price + "\nWidth: " + width + "\nHeight: " + height + "\nPPI: " + ppi + "\n";
    }
}

// Main class to handle the application logic
public class Main {
    private static final List<Display> displays = new ArrayList<>();

    public static void main(String[] args) {
        // Read displays from the file with the specified full path
        readDisplaysFromFile("C:\\Users\\crist\\Desktop\\OOP\\Lab1\\Task3\\displays.txt");

        // Print the displays for user reference
        printDisplays();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("1. Compare Displays");
            System.out.println("2. Choose a display to buy");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    compareDisplays();
                    break;
                case 2:
                    buyDisplay(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to read displays from a text file
    private static void readDisplaysFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String model = parts[0].substring(parts[0].indexOf(": ") + 2);
                String description = parts[1].substring(parts[1].indexOf(": ") + 2);
                double price = Double.parseDouble(parts[2].substring(parts[2].indexOf("$") + 1)); // Remove $ sign
                int width = Integer.parseInt(parts[3].substring(parts[3].indexOf(": ") + 2));
                int height = Integer.parseInt(parts[4].substring(parts[4].indexOf(": ") + 2));
                float ppi = Float.parseFloat(parts[5].substring(parts[5].indexOf(": ") + 2));

                displays.add(new Display(model, description, price, width, height, ppi));
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    // Method to print all displays
    private static void printDisplays() {
        System.out.println("Available Displays:");
        for (Display display : displays) {
            System.out.println(display);
        }
    }

    // Method to compare all displays
    private static void compareDisplays() {
        for (int i = 0; i < displays.size(); i++) {
            for (int j = i + 1; j < displays.size(); j++) {
                displays.get(i).compare(displays.get(j));
            }
        }
    }

    // Method to buy a display
    private static void buyDisplay(Scanner scanner) {
        System.out.print("Enter the letter of the display to buy (e.g., A, B, C...): ");
        char choice = scanner.next().charAt(0);
        int index = choice - 'A'; // Convert letter to index

        if (index >= 0 && index < displays.size()) {
            Display boughtDisplay = displays.remove(index);
            System.out.println("You bought: " + boughtDisplay.getModel());
        } else {
            System.out.println("Invalid choice. Display not found.");
        }
    }
}
