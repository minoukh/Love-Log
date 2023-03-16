package ui;

import model.MyJournal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Represents application's main window frame.
 */
public class MainGUI implements ActionListener {
    private MyJournal myJournal;
    private JLabel label;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton newJournalButton;
    private JButton loadButton;

    /**
     * EFFECTS: Constructor sets up load and new journal button and the panel window.
     */
    public MainGUI() {
        frame = new JFrame();

        label = new JLabel("Welcome to Love Log!");

        loadButton = new JButton("Load from File");
        loadButton.addActionListener(this);

        newJournalButton = new JButton("Start a New Journal");
        newJournalButton.addActionListener(this);


        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(label);
        mainPanel.add(newJournalButton);
        mainPanel.add(loadButton);


        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * MODIFIES: this
     * EFFECTS: creates the next state of the application UI based on the action be performed by the
     * user (new Journal GUI or load from JSON file).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loadButton) {
            //
        }
        if (event.getSource() == newJournalButton) {
            myJournal = new MyJournal("MINOU");
            new InJournalGUI(myJournal);
            frame.setVisible(false);

        }

    }

}

