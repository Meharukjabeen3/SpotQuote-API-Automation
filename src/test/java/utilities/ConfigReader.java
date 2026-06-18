package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "qa");
        String configPath = "config/" + env + ".properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + configPath, e);
        }
    }

    public static String get(String key) {
        String sysProp = System.getProperty(key);
        return sysProp != null ? sysProp : properties.getProperty(key);
    }
}
