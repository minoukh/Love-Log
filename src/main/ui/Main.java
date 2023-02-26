package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Love Log!");
        try {
            new MyJournalApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

