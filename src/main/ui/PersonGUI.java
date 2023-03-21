package ui;

import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's person creation window frame.
 */
public class PersonGUI implements ActionListener {
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

        setUpLabelAndTextFiels("Name: ", 20, nameInput);

        setUpLabelAndTextFiels("Age: ", 50, ageInput);

        setUpLabelAndTextFiels("Location: ", 80, placeInput);

        setUpLabelAndTextFiels("Occupation: ", 110, jobInput);

        personPanel.add(backButton);
        personPanel.add(addButton);
    }

    private void setUpLabelAndTextFiels(String text, int y, JTextField nameInput) {
        label = new JLabel(text);
        label.setBounds(10, y, 80, 25);
        personPanel.add(label);
        nameInput = new JTextField(15);
        nameInput.setBounds(100, y, 165, 25);
        personPanel.add(nameInput);
    }

    private void setUpButtons() {
        addButton = new JButton("Add");
        addButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
    }

    private void setUpFrameAndPanel() {
        personPanel = new JPanel();
        personPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        personPanel.setLayout(new GridLayout(0, 2));

        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
