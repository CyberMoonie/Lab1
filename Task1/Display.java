package Task1;

public class Display {
    // Attributes
    int width;
    int height;
    float ppi;
    String model;

    // Constructor
    public Display(int width, int height, float ppi, String model) {
        this.width = width;
        this.height = height;
        this.ppi = ppi;
        this.model = model;
    }

    // Method to compare size (width and height)
    public void compareSize(Display m) {
        int thisArea = this.width * this.height;
        int otherArea = m.width * m.height;
        if (thisArea > otherArea) {
            System.out.println(this.model + " is larger than " + m.model);
        } else if (thisArea < otherArea) {
            System.out.println(this.model + " is smaller than " + m.model);
        } else {
            System.out.println(this.model + " and " + m.model + " have the same size.");
        }
    }

    // Method to compare sharpness (ppi)
    public void compareSharpness(Display m) {
        if (this.ppi > m.ppi) {
            System.out.println(this.model + " is sharper than " + m.model);
        } else if (this.ppi < m.ppi) {
            System.out.println(this.model + " is less sharp than " + m.model);
        } else {
            System.out.println(this.model + " and " + m.model + " have the same sharpness.");
        }
    }

    // Method to compare both size and sharpness
    public void compareWithMonitor(Display m) {
        System.out.println("Comparing " + this.model + " with " + m.model + ":");
        compareSize(m);
        compareSharpness(m);
        System.out.println(); // Adds a line break for better output readability
    }

    public static void main(String[] args) {
        // Print starting message
        System.out.println("Comparison of Displays:");

        // Create 3 Display objects
        Display display1 = new Display(1920, 1080, 401.5f, "Model A");
        Display display2 = new Display(2560, 1440, 326.0f, "Model B");
        Display display3 = new Display(1280, 720, 294.0f, "Model C");

        // Compare the objects
        display1.compareSize(display2);
        display2.compareSharpness(display3);
        display1.compareWithMonitor(display3);

        // Print completion message
        System.out.println("Comparison complete.");
    }
}
