package ui;

import model.MyJournal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents application's main window frame.
 */
public class MainGUI implements ActionListener {
    private static final String JSON_STORE = "./data/guiJournal.json";
    private MyJournal myJournal;
    private ImageIcon image;
    private final JLabel displayField;
    private JLabel label;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton newJournalButton;
    private JButton loadButton;
    private JsonReader jsonReader;


    /**
     * EFFECTS: Constructor sets up load and new journal button and the panel window.
     */
    public MainGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame();
        image = new ImageIcon("./data/loveimg.jpeg");
        displayField = new JLabel(image);

        label = new JLabel("Welcome to Love Log!");

        loadButton = new JButton("Load from File");
        loadButton.addActionListener(this);

        newJournalButton = new JButton("Start a New Journal");
        newJournalButton.addActionListener(this);


        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 30));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(displayField);
        mainPanel.add(label);
        mainPanel.add(newJournalButton);
        mainPanel.add(loadButton);


        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Love Log App");
        frame.setPreferredSize(new Dimension(300,500));
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
            try {
                myJournal = jsonReader.read();
                // listOfPerson = jsonReader.read().getListOfPerson();
                JOptionPane.showMessageDialog(null, "Loaded journal from " + JSON_STORE);
                new InJournalGUI(myJournal);
                frame.setVisible(false);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
            }
        }
        if (event.getSource() == newJournalButton) {
            myJournal = new MyJournal("MINOU");
            new InJournalGUI(myJournal);
            frame.setVisible(false);

        }

    }

}

