package ui;

import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LopGUI implements ActionListener {
    private JFrame frame;
    private JPanel lopPanel;
    private JButton backButton;
    private ArrayList<Person> candidates;

    public LopGUI(ArrayList<Person> lop) {
        this.candidates = lop;
        frame = new JFrame();

        lopPanel = new JPanel();
        lopPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        lopPanel.setLayout(new GridLayout(0, 1));
        lopPanel.add(new Label("List Of People"));

        frame.add(lopPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);


        int n = 1;
        for (Person p : lop) {
            lopPanel.add(new Label("Candidate " + n + ": " + p.getName()));
            n++;
            candidates.add(p);
        }

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        lopPanel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButton) {
            frame.setVisible(false);
            MyJournal myJournal = new MyJournal("Minou");
            myJournal.setListOfPerson(candidates);
            new InJournalGUI();
        }
    }
}
