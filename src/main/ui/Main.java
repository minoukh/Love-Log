package ui;

import java.io.FileNotFoundException;

/**
 * Represents Main and application GUI and console can be run from here
 */
public class Main {
    public static void main(String[] args) {
        new MainGUI();
        System.out.println("Welcome to Love Log!");
        try {
            new MyJournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

