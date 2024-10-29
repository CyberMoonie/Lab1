package Task2;

import java.io.*; 
import java.nio.file.*;

// FileReader class to handle file reading
class FileReader {
    // Reads the entire file content into a string
    public static String readFileIntoString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path))); // Read bytes and convert to string
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
            return ""; 
        }
    }
}

class TextData {
    String text; 
    int numberOfVowels; 
    int numberOfConsonants; 
    int numberOfLetters; 
    int numberOfSentences; 
    String longestWord; 

    // Constructor initializes fields based on the input text
    public TextData(String text) {
        this.text = text;
        this.numberOfVowels = countVowels(text);
        this.numberOfConsonants = countConsonants(text);
        this.numberOfLetters = numberOfVowels + numberOfConsonants;
        this.numberOfSentences = countSentences(text);
        this.longestWord = findLongestWord(text);
    }

    // Counts the number of vowels in the text
    private int countVowels(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++; // Increment count for each vowel found
            }
        }
        return count; // Return total vowel count
    }

    // Counts the number of consonants in the text
    private int countConsonants(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if ("bcdfghjklmnpqrstvwxyz".indexOf(c) != -1) {
                count++; // Increment count for each consonant found
            }
        }
        return count; // Return total consonant count
    }

    // Counts the number of sentences in the text
    private int countSentences(String text) {
        String[] sentences = text.split("[.!?]"); // Split based on sentence terminators
        return sentences.length; // Return the count of sentences
    }

    // Finds the longest word in the text
    private String findLongestWord(String text) {
        String[] words = text.split("\\W+"); // Split text into words using non-word characters
        String longest = ""; // Initialize longest word variable
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word; // Update longest word if a longer one is found
            }
        }
        return longest;
    }

    // Getters for accessing class fields
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

// Main class to execute the program
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the file path as a command-line argument.");
            return; 
        }

        String filePath = args[0]; // Get the file path from command-line arguments
        String fileContent = FileReader.readFileIntoString(filePath); // Read file content

        // Handle case where the file could not be read or is empty
        if (fileContent.isEmpty()) {
            System.out.println("File content is empty or could not be read.");
            return; 
        }

        TextData textData = new TextData(fileContent); // Create TextData object from file content

        System.out.println("Loaded Text Data:");
        System.out.println("Number of Vowels: " + textData.getNumberOfVowels());
        System.out.println("Number of Consonants: " + textData.getNumberOfConsonants());
        System.out.println("Number of Letters: " + textData.getNumberOfLetters());
        System.out.println("Number of Sentences: " + textData.getNumberOfSentences());
        System.out.println("Longest Word: " + textData.getLongestWord());
    }
}
