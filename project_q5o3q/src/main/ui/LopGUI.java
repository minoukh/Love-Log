package ui;

import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Represents application's List of persons window frame.
 */
public class LopGUI implements ActionListener {
    private MyJournal myJournal;
    private JFrame frame;
    private JPanel lopPanel;
    private JButton backButton;
    private JButton addDateButton;
    private JButton seeDatesButton;
    private List<Person> candidates;
    JList<Person> list = new JList<>();
    DefaultListModel<Person> model = new DefaultListModel<>();
    private JLabel label = new JLabel();
    private JSplitPane splitPane = new JSplitPane();
    protected Person person;

    /**
     * EFFECTS: Constructor sets up a clickable split pane for list person as well as add date and back button
     * in a new panel window.
     */
    public LopGUI(MyJournal myJournal) {
        this.myJournal = myJournal;
        this.candidates = myJournal.getListOfPerson();
        for (Person p : candidates) {
            model.addElement(p);
        }

        list.setModel(model);
        list.getSelectionModel().addListSelectionListener(e -> {
            Person p = list.getSelectedValue();
            label.setText("Name: " + p.getName() + ":::"
                    + "Age: " + p.getAge() + ":::"
                    + "Location: " + p.getPersonLocation() + ":::"
                    + "Job: " + p.getPersonJob());
            person = p;
        });

        setUpFrameAndPanel();

        setUpSplitPane();

        setUpButtons();
    }

    private void setUpButtons() {
        seeDatesButton = new JButton("See Dates");
        seeDatesButton.addActionListener(this);
        lopPanel.add(seeDatesButton);

        addDateButton = new JButton("Add Date");
        addDateButton.addActionListener(this);
        lopPanel.add(addDateButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        lopPanel.add(backButton);
    }

    private void setUpSplitPane() {
        splitPane.setLeftComponent(new JScrollPane(list));
        splitPane.setDividerLocation(100);
        splitPane.setSize(2000, 2000);
        lopPanel.add(label);
        splitPane.setRightComponent(lopPanel);
    }

    private void setUpFrameAndPanel() {
        frame = new JFrame();
        frame.setLayout(new GridLayout());
        frame.setPreferredSize(new Dimension(400,300));
        frame.setBounds(100, 100, 1000, 1000);

        lopPanel = new JPanel();
        lopPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        lopPanel.setLayout(new GridLayout(0, 1));

        frame.add(lopPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane);
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * MODIFIES: this, myJournal
     * EFFECTS: takes the user to the new/next panel window of the application based on the
     * action the user performs (new Journal GUI or load from JSON file).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButton) {
            new InJournalGUI(myJournal);
            frame.setVisible(false);
        }
        if (event.getSource() == addDateButton) {
            new InDateGUI(myJournal, person);
            frame.setVisible(false);
        }
        if (event.getSource() == seeDatesButton) {
            new ListOfDatesGUI(myJournal, person);
            frame.setVisible(false);
        }
    }
}
