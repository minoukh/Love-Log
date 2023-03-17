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

    public InDateGUI(MyJournal myJournal, Person person) {
        this.myJournal = myJournal;
        this.person = person;
        this.dateEntry = new DateEntry(person.getName(), person.numOfDatesWithPerson() + 1);
        datePanel = new JPanel();
        datePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        datePanel.setLayout(new GridLayout(0, 2));

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButton) {
            new LopGUI(myJournal);
            frame.setVisible(false);
        }
        if (event.getSource() == successButton) {
            dateEntry.setSuccessfulness(true);
        }
        if (event.getSource() == failButton) {
            dateEntry.setSuccessfulness(false);
        }
        if (event.getSource() == addButton) {
            JOptionPane.showMessageDialog(null,
                    "A new date entry is now added to your dates with " + person.getName());
            dateEntry.addHighlightEvent(highLightInput.getText());
            dateEntry.addRedFlagEvent(redFlagInput.getText());

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
