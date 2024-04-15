package runner;

import commons.driver.BrowserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FeaturesCopier {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private final ArrayList<BrowserType> browserTypes = new ArrayList<>();
    private final File[] processesDir = new File("src/test/resources/features/features-templates").listFiles();
    private final File targetDir = new File("src/test/resources/features/features-runtime");

    protected Logger log() {
        return logger;
    }

    public void copyTemplatesFeatures() {
        createRuntimeFeaturesDir();
        assert processesDir != null;
        for (File processDir : processesDir) {
            copyFeatures(processDir);
        }
    }

    private void createRuntimeFeaturesDir() {
        if (!targetDir.exists()) {
            boolean directoryCreated = targetDir.mkdir();
            if (directoryCreated){
                System.out.println("Directory created!");
            }
        }
    }

    private void copyFeatures(File sourceDir) {
        if (!sourceDir.isDirectory()) {
            System.out.println("========================================= copyFeatures");
            return;
        }
        File[] features = sourceDir.listFiles();
        assert features != null;
        for (File feature : features) {
            for (BrowserType browser : browserTypes) {
                String oldName = String.valueOf(feature);
                String newPath = oldName
                    .replace(sourceDir.toPath().toString(), targetDir.toPath().toString())
                    .replace(".feature", "_" + browser.name() + ".feature");
                try {
                    Files.copy(feature.toPath(), Path.of(newPath));
                    addBrowserTag(newPath, browser);
                } catch (IOException e) {
                    log().error("Unable to copy feature: " + oldName);
                    e.printStackTrace();
                }
            }
        }
    }

    public void addBrowserType(BrowserType browserType) {
        browserTypes.add(browserType);
    }

    public void addBrowserTag(String path, BrowserType browserType) {
        String tag = " @" + browserType.name() + System.lineSeparator();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder content = new StringBuilder();
            String line;

            if ((line = reader.readLine()) != null) {
                content.append(line).append(tag);
            }

            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
