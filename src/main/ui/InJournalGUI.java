package ui;

import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents application's journal window frame.
 */
public class InJournalGUI implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel journalPanel;
    private JButton newPersonButton;
    private JButton viewLopButton;
    private JButton saveButton;
    private JButton backButton;
    private MyJournal myJournal;

    /**
     * EFFECTS: Constructor sets up new person, list of people, save, and back button
     * in a new panel window.
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public InJournalGUI(MyJournal myJournal) {
        this.myJournal = myJournal;

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

        journalPanel = new JPanel();
        journalPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        journalPanel.setLayout(new GridLayout(0, 1));
        journalPanel.add(label);
        journalPanel.add(newPersonButton);
        journalPanel.add(viewLopButton);
        journalPanel.add(saveButton);
        journalPanel.add(backButton);



        frame.add(journalPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            //
        }
        if (event.getSource() == backButton) {
            new MainGUI();
            frame.setVisible(false);
        }
    }
}
