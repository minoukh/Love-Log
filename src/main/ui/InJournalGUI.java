package ui;

import model.EventLog;
import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents application's journal window frame.
 */
public class InJournalGUI extends LogPrinter implements ActionListener {
    private static final String JSON_STORE = "./data/guiJournal.json";
    private JLabel label;
    private JFrame frame;
    private JPanel journalPanel;
    private JButton newPersonButton;
    private JButton viewLopButton;
    private JButton saveButton;
    private JButton backButton;
    private MyJournal myJournal;
    private JsonWriter jsonWriter;

    /**
     * EFFECTS: Constructor sets up new person, list of people, save, and back button
     * in a new panel window.
     */
    public InJournalGUI(MyJournal myJournal) {
        this.myJournal = myJournal;
        jsonWriter = new JsonWriter(JSON_STORE);

        frame = new JFrame();

        label = new JLabel("Lets add some people!");

        newPersonButton = new JButton("Create a New Person");
        newPersonButton.addActionListener(this);

        viewLopButton = new JButton("View the List of People");
        viewLopButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        backButton = new JButton("Go Back");
        backButton.addActionListener(this);

        setUpFrameAndPanel();

    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up details (dimensions, layout, etc.)  of the frame and panel
     */
    private void setUpFrameAndPanel() {
        journalPanel = new JPanel();
        journalPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        journalPanel.setLayout(new GridLayout(0, 1));
        journalPanel.add(label);
        journalPanel.add(newPersonButton);
        journalPanel.add(viewLopButton);
        journalPanel.add(saveButton);
        journalPanel.add(backButton);


        frame.add(journalPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS: takes the user to the new/next panel window of the application based on the
     * action the user performs (new Journal GUI or load from JSON file).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newPersonButton) {
            new PersonGUI(myJournal);
            frame.setVisible(false);
        }
        if (event.getSource() == viewLopButton) {
            new LopGUI(myJournal);
            frame.setVisible(false);
            //
        }
        if (event.getSource() == saveButton) {
            try {
                jsonWriter.open();
                jsonWriter.write(myJournal);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Saved to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
            }
        }
        if (event.getSource() == backButton) {
            new MainGUI();
            frame.setVisible(false);
        }
    }
}
