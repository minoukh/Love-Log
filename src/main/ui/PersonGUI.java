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
     * EFFECTS: Constructor sets up new person, list of people, save, and back button
     * in a new panel window.
     */
    public PersonGUI(MyJournal myJournal) {
        this.myJournal = myJournal;
        personPanel = new JPanel();
        personPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        personPanel.setLayout(new GridLayout(0, 2));

        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(personPanel);

        addButton = new JButton("Add");
        addButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        label = new JLabel("Name: ");
        label.setBounds(10, 20, 80, 25);
        personPanel.add(label);
        nameInput = new JTextField(15);
        nameInput.setBounds(100, 20, 165, 25);
        personPanel.add(nameInput);

        label = new JLabel("Age: ");
        label.setBounds(10, 50, 80, 25);
        personPanel.add(label);
        ageInput = new JTextField(15);
        ageInput.setBounds(100, 50, 165, 25);
        personPanel.add(ageInput);

        label = new JLabel("Location: ");
        label.setBounds(10, 80, 80, 25);
        personPanel.add(label);
        placeInput = new JTextField(15);
        placeInput.setBounds(100, 80, 165, 25);
        personPanel.add(placeInput);

        label = new JLabel("Occupation: ");
        label.setBounds(10, 110, 80, 25);
        personPanel.add(label);
        jobInput = new JTextField(15);
        jobInput.setBounds(100, 110, 165, 25);
        personPanel.add(jobInput);

        personPanel.add(backButton);
        personPanel.add(addButton);
    }

//    String name = JOptionPane.showInputDialog("Name:");
//    int age = Integer.parseInt(JOptionPane.showInputDialog("Age:"));
//    String location = JOptionPane.showInputDialog("Location:");
//    String job = JOptionPane.showInputDialog("Occupation:");
//

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
