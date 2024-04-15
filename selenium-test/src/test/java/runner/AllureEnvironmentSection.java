package runner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AllureEnvironmentSection {
    private static final Path filePath = Path.of("allure-results/environment.properties");

    public static void createEnvFile() {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println("Error creating the file: " + e.getMessage());
        }
    }

    public static void addEnvVariable(String key, String value) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            // Write a string to a new line in the file
            String contentToAdd = key + " = " + value;
            writer.write(contentToAdd);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error appending to the file: " + e.getMessage());
        }
    }
}
