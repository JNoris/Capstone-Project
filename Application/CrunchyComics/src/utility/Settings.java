package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Settings singleton class containing all the user settings and logic to load
 * and save these settings to a local file.
 *
 * @author Vinicius Smith
 */
public class Settings implements Serializable {

    private static Settings settings = null;
    private boolean timerEnable = true;
    private int timerTimeout = 120000;
    private String password = null;

    /**
     * Load the files from a file in the location as the executable.
     *
     * @return True if the file was loaded properly and a settings object was
     * created. False otherwise.
     */
    public static boolean loadSettings() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("settings.config"));
            settings = (Settings) ois.readObject();
        } catch (IOException e) {
            System.out.println("Error loading settings file.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (settings == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Saves the current settings object to a file.
     *
     * @return True if the save was successful. False otherwise.
     */
    public static boolean saveSettings() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("settings.config"));
            oos.writeObject(settings);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving settings file.");
            return false;
        }
    }

    /**
     * Gets an instance of the settings object. If an instance does not exist it
     * will try to load it from the setting file. If that fails a new object
     * will be created and the default settings will be saved to the settings
     * file.
     *
     * @return instance of a Settings object.
     */
    public static Settings getInstance() {
        if (settings == null) {
            if (!loadSettings()) {
                System.out.println("Failed to load settings from file.");
                settings = new Settings();
                System.out.println("Saving settings.");
                saveSettings();

                System.out.println("Saving settings.");
            }
        }
        return settings;
    }
}
