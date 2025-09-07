package utils;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple properties loader.
 */
public class ConfigReader {
    private static final Properties props = new Properties();
    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

    static {
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(is);
            log.info("Loaded config.properties");
        } catch (Exception e) {
            log.warn("Could not load config.properties, continuing with defaults", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String get(String key, String def) {
        return props.getProperty(key, def);
    }
}
