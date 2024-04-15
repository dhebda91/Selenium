package commons.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//– klasa będzie odczytywała plik properties
public class PropertiesLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    public Properties getPropertiesFromFile(String propertiesFileName) {

        InputStream inputStream = null;

        Properties properties = new Properties();
        try {
            LOGGER.info("Trying to load properties with file name: " + propertiesFileName);

            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

            if (inputStream != null) {
                properties.load(inputStream);
                LOGGER.info("Successfully loaded properties for file: " + propertiesFileName);
            } else {
                throw new FileNotFoundException("Property file '" + propertiesFileName + "' not found in the classpath");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load properties due to IOException!");
        } finally {
            closeResource(inputStream);
        }

        return properties;
    }


    private void closeResource(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}