package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dmitry on 1.6.15.
 */
public abstract class PropertyLoader {
    private static Properties prop = new Properties();

    public static String getProperty(String filename, String propertyName) {
        InputStream input = null;
        input = PropertyLoader.class.getClassLoader().getResourceAsStream(filename);
        try {
            prop.load(input);
            return prop.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

