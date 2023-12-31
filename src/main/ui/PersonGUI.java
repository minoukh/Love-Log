package ui;

import model.EventLog;
import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's person creation window frame.
 */
public class PersonGUI extends LogPrinter implements ActionListener {
    private MyJournal myJournal;
    private JFrame frame;
    private JLabel label;
    private JTextField nameInput;
    private JTextField ageInput;
    private JTextField placeInput;
    private JTextField jobInput;
    private JPanel personPanel;

    private JButton addButton;
    private JButton backButton;

    /**
     * EFFECTS: Constructor sets text boxes for person information entry as well as add and back button
     * in a new panel window.
     */
    public PersonGUI(MyJournal myJournal) {
        this.myJournal = myJournal;
        setUpFrameAndPanel();

        setUpButtons();

        addLabel("Name: ", 20);
        nameInput = new JTextField(15);
        nameInput.setBounds(100, 20, 165, 25);
        personPanel.add(nameInput);

        addLabel("Age: ", 50);
        ageInput = new JTextField(15);
        ageInput.setBounds(100, 50, 165, 25);
        personPanel.add(ageInput);

        addLabel("Location: ", 80);
        placeInput = new JTextField(15);
        placeInput.setBounds(100, 80, 165, 25);
        personPanel.add(placeInput);

        addLabel("Occupation: ", 110);
        jobInput = new JTextField(15);
        jobInput.setBounds(100, 110, 165, 25);
        personPanel.add(jobInput);

        personPanel.add(backButton);
        personPanel.add(addButton);
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up labels for the fields of the GUI
     */
    private void addLabel(String text, int y) {
        label = new JLabel(text);
        label.setBounds(10, y, 80, 25);
        personPanel.add(label);
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up buttons of the GUI
     */
    private void setUpButtons() {
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up frame and panel of the GUI
     */
    private void setUpFrameAndPanel() {
        personPanel = new JPanel();
        personPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        personPanel.setLayout(new GridLayout(0, 2));

        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
        frame.setVisible(true);
        frame.add(personPanel);
    }

    /**
     * MODIFIES: this, myJournal
     * EFFECTS: takes the user to the new/next panel window of the application based on the
     * action the user performs (new Journal GUI or load from JSON file).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {
            JOptionPane.showMessageDialog(null,
                    nameInput.getText() + " is now added to your list.");

            myJournal.addPerson(new Person(nameInput.getText(),
                    Integer.parseInt(ageInput.getText()), placeInput.getText(),
                    jobInput.getText(), 0));
            new PersonGUI(myJournal);
            frame.setVisible(false);
        }

        if (event.getSource() == backButton) {
            new InJournalGUI(myJournal);
            frame.setVisible(false);
        }
    }
}
