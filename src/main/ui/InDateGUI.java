package ui;

import model.DateEntry;
import model.EventLog;
import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's Date creation for a persons window frame.
 */
public class InDateGUI extends LogPrinter implements ActionListener {
    private MyJournal myJournal;
    private Person person;
    private DateEntry dateEntry;
    private JFrame frame;
    private JPanel datePanel;
    private JButton backButton;
    private JButton addButton;
    private JLabel label = new JLabel();
    private JTextField highLightInput;
    private JTextField redFlagInput;
    private JRadioButton successButton;
    private JRadioButton failButton;

    /**
     * EFFECTS: Constructor sets text boxes for date information entry as well as add and back button
     * in a new panel window.
     */
    public InDateGUI(MyJournal myJournal, Person person) {
        this.myJournal = myJournal;
        this.person = person;
        this.dateEntry = new DateEntry(person.getName(), person.numOfDatesWithPerson() + 1);

        setUpPanel();
        setUpFrame();

        label = new JLabel("Highlights: ");
        label.setBounds(10, 20, 80, 25);
        datePanel.add(label);
        highLightInput = new JTextField();
        highLightInput.setBounds(100, 20, 165, 25);
        datePanel.add(highLightInput);

        label = new JLabel("Red-Flags: ");
        label.setBounds(10, 50, 80, 25);
        datePanel.add(label);
        redFlagInput = new JTextField();
        redFlagInput.setBounds(100, 50, 165, 25);
        datePanel.add(redFlagInput);

        setUpSuccessRadioButton();

        ButtonGroup successButtons = new ButtonGroup();
        successButtons.add(successButton);
        successButtons.add(failButton);

        setUpButton();
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up buttons for the panel
     */
    private void setUpButton() {
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        datePanel.add(successButton);
        datePanel.add(failButton);

        datePanel.add(backButton);
        datePanel.add(addButton);
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates and sets up radio buttons and for the panel
     */
    private void setUpSuccessRadioButton() {
        label = new JLabel("Would you go out with him again? ");
        label.setBounds(10, 80, 80, 25);
        datePanel.add(label);
        datePanel.add(new JLabel(""));
        successButton = new JRadioButton("Yes");
        failButton = new JRadioButton("No");
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up the dimensions, visibility, and default close of the frame and adds it to the panel
     */
    private void setUpFrame() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });

        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.add(datePanel);
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up the borders and layout of the panel
     */
    private void setUpPanel() {
        datePanel = new JPanel();
        datePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        datePanel.setLayout(new GridLayout(0, 2));
    }

    /**
     * MODIFIES: this, person
     * EFFECTS: Add a date with proper attributes to the person's list of dates. Also
     * takes the user to the new/next panel window of the application based on the
     * action the user performs (new Journal GUI or load from JSON file).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButton) {
            new LopGUI(myJournal);
            frame.setVisible(false);
        }

        if (event.getSource() == addButton) {
            JOptionPane.showMessageDialog(null, "A new date entry is now added to your dates with " + person.getName());
            dateEntry.put("dateName", person.getName());
            dateEntry.put("dateNum", person.numOfDatesWithPerson() + 1);
            dateEntry.put("dateHighLightEvents", highLightInput.getText());
            dateEntry.put("dateRedFlagEvents", redFlagInput.getText());

            if (successButton.isSelected()) {
                dateEntry.put("dateSuccessful", true);
            } else {
                dateEntry.put("dateSuccessful", false);
            }

            person.addDateToListOfDates(dateEntry);
            new LopGUI(myJournal);
            frame.setVisible(false);
        }

        if (event.getSource() == backButton) {
            new LopGUI(myJournal);
            frame.setVisible(false);
        }
    }

}
