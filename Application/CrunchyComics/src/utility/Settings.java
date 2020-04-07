/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Vinicius Smith
 */
public class Settings implements Serializable {

    private static Settings settings = null;
    private boolean timerEnable = true;
    private int timerTimeout = 120000;
    private String password = null;

    private Settings() {
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("settings.config"));
//            settings = (Settings) ois.readObject();
//            System.out.println(settings);
//        } catch (IOException e) {
//            System.out.println("Settings file not found. Creating...");
//            try {
//                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("settings.config"));
//                oos.writeObject(settings);
//            } catch (IOException e2) {
//                System.out.println("Error creating/writing to settings.conf");
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        if (loadSettings()) {
//            System.out.println("Settings loaded successfully.");
//        } else {
//            System.out.println("Settings not loaded from file.");
//            if (saveSettings()) {
//                System.out.println("Default settings saved." + settings.timerTimeout);
//            }
//        }
    }

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
