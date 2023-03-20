package ui;

import model.DateEntry;
import model.MyJournal;
import model.Person;
import ui.LopGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Represents application's Date creation for a persons window frame.
 */
public class InDateGUI implements ActionListener {
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
        datePanel = new JPanel();
        datePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        datePanel.setLayout(new GridLayout(0, 2));

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.add(datePanel);

        addButton = new JButton("Add");
        addButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

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

        label = new JLabel("Would you go out with him again? ");
        label.setBounds(10, 80, 80, 25);
        datePanel.add(label);
        datePanel.add(new JLabel(""));
        successButton = new JRadioButton("Yes");
        failButton = new JRadioButton("No");

        ButtonGroup successButtons = new ButtonGroup();
        successButtons.add(successButton);
        successButtons.add(failButton);

        datePanel.add(successButton);
        datePanel.add(failButton);

        datePanel.add(backButton);
        datePanel.add(addButton);
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
            JOptionPane.showMessageDialog(null,
                    "A new date entry is now added to your dates with " + person.getName());
            dateEntry.put("dateName", person.getName());
            dateEntry.put("dateNum", person.numOfDatesWithPerson() + 1);
            dateEntry.put("dateHighLightEvents", highLightInput.getText());
            dateEntry.put("dateRedFlagEvents", redFlagInput.getText());
            if (event.getSource() == successButton) {
                dateEntry.put("dateSuccessful", true);
            }
            if (event.getSource() == failButton) {
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
