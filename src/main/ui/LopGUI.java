package ui;

import model.MyJournal;
import model.Person;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private List<Person> candidates;
    JList<Person> list = new JList<>();
    DefaultListModel<Person> model = new DefaultListModel<>();
    private JLabel label = new JLabel();
    private JSplitPane splitPane = new JSplitPane();

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
        });

        frame = new JFrame();
        frame.setSize(400, 400);

        lopPanel = new JPanel();
        lopPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        lopPanel.setLayout(new GridLayout(0, 1));

        frame.add(lopPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane);
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);


        splitPane.setLeftComponent(new JScrollPane(list));
        splitPane.setSize(2000,2000);
        lopPanel.add(label);
        splitPane.setRightComponent(lopPanel);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        lopPanel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButton) {
            new InJournalGUI(myJournal);
            frame.setVisible(false);
        }
    }
}
