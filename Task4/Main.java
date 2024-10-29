package Task4; 

import java.io.*; // Import for file operations
import java.nio.file.*; // Import for file path operations

// Class to handle file reading
class FileReader {
    // Read the contents of a file into a String
    public static String readFileIntoString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path))); // Read all bytes and convert to String
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
            return "";
        }
    }
}

// Class to hold text data and perform analysis
class TextData {
    String text;
    int numberOfVowels; 
    int numberOfConsonants;
    int numberOfLetters;
    int numberOfSentences; 
    String longestWord;

    // Constructor initializes fields and performs calculations
    public TextData(String text) {
        this.text = text;
        this.numberOfVowels = countVowels(text);
        this.numberOfConsonants = countConsonants(text);
        this.numberOfLetters = numberOfVowels + numberOfConsonants;
        this.numberOfSentences = countSentences(text);
        this.longestWord = findLongestWord(text);
    }

    // Count vowels in the text
    private int countVowels(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count; // Return total vowel count
    }

    // Count consonants in the text
    private int countConsonants(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if ("bcdfghjklmnpqrstvwxyz".indexOf(c) != -1) {
                count++;
            }
        }
        return count; // Return total consonant count
    }

    // Count sentences based on punctuation
    private int countSentences(String text) {
        String[] sentences = text.split("[.!?]"); // Split by sentence-ending punctuation
        return sentences.length; // Return the number of sentences
    }

    // Find the longest word in the text
    private String findLongestWord(String text) {
        String[] words = text.split("\\W+"); // Split by non-word characters
        String longest = ""; // Variable to hold the longest word
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word; // Update longest word if current word is longer
            }
        }
        return longest; 
    }

    // Getters for text data fields
    public String getText() {
        return text;
    }

    public int getNumberOfVowels() {
        return numberOfVowels;
    }

    public int getNumberOfConsonants() {
        return numberOfConsonants;
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public String getLongestWord() {
        return longestWord;
    }
}

// Main class to run the application
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the file paths as command-line arguments."); // Check for input
            return; 
        }

        // Process each file provided as a command-line argument
        for (String filePath : args) {
            String fileContent = FileReader.readFileIntoString(filePath); // Read file content

            if (fileContent.isEmpty()) {
                System.out.println("File content is empty or could not be read: " + filePath); 
                continue; // Skip to next file
            }

            TextData textData = new TextData(fileContent); // Create TextData object with file content

            System.out.println("Loaded Text Data from: " + filePath);
            System.out.println("Number of Vowels: " + textData.getNumberOfVowels());
            System.out.println("Number of Consonants: " + textData.getNumberOfConsonants());
            System.out.println("Number of Letters: " + textData.getNumberOfLetters());
            System.out.println("Number of Sentences: " + textData.getNumberOfSentences());
            System.out.println("Longest Word: " + textData.getLongestWord());
            System.out.println(); 
        }
    }
}
