package commons.helpers;

import commons.configuration.ConfigurationProperties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManagingHelper {

    public static void cleanDirectory(String pathToDirectory) throws IOException {
        File directory = convertStringPathToAbsoluteFilePath(pathToDirectory);
        FileUtils.cleanDirectory(directory);
    }

    public static void deleteWildcardFiles(String pathToDirectory, String wildcard) {
        File[] filesToDelete = FileManagingHelper.searchForFileWithWildCard(pathToDirectory, wildcard);
        for (File file : filesToDelete) {
            file.delete();
        }
    }

    public static File[] searchForFileWithWildCard(String pathToDirectory, String wildCard) {
        File directory = convertStringPathToAbsoluteFilePath(pathToDirectory);
        FileFilter fileFilter = new WildcardFileFilter(wildCard);
        File[] filteredFiles = directory.listFiles(fileFilter);
        return filteredFiles;
    }

    public static File convertStringPathToAbsoluteFilePath(String path) {
        Path directoryAbsolutePath = Paths.get(path).toAbsolutePath();
        File directory = new File(directoryAbsolutePath.toString());
        return directory;
    }

    public static void cleanDownloadDirectory() throws IOException {
        cleanDirectory(ConfigurationProperties.getProperties().getProperty("download.directory.path"));
    }

    public static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        if (!directory.delete()) {
            System.err.println("Failed to delete: " + directory.getAbsolutePath());
        }
    }

    public static void fileCopier(String sourcePath, String destinationPath, String fileName) {
        try {
            copyFileUsingFileClass(sourcePath, destinationPath, fileName);
            System.out.println("Plik został skopiowany pomyślnie.");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas kopiowania pliku: " + e.getMessage());
        }
    }

    private static void copyFileUsingFileClass(String sourcePath, String destinationPath, String fileName) throws IOException {
        File sourceFile = new File(sourcePath + "/" + fileName);
        File destinationFile = new File(destinationPath + "/" + fileName);
        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}