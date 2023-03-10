package ui;

import model.MyJournal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MainGUI implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton newJournalButton;
    //    private JButton newPersonButton;
//    private JButton viewLopButton;
//    private JButton saveButton;
    private JButton loadButton;

    public MainGUI() {
        frame = new JFrame();

        label = new JLabel("Welcome to Love Log!");

//        newPersonButton = new JButton("Create a New Person");
//        newPersonButton.addActionListener(this);
//
//        viewLopButton = new JButton("View the List of People");
//        viewLopButton.addActionListener(this);
//
//        saveButton = new JButton("Save");
//        saveButton.addActionListener(this);

        loadButton = new JButton("Load from File");
        loadButton.addActionListener(this);

        newJournalButton = new JButton("Start a New Journal");
        newJournalButton.addActionListener(this);


        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(label);
        //panel.add(newPersonButton);
        //panel.add(viewLopButton);
        //panel.add(saveButton);
        mainPanel.add(newJournalButton);
        mainPanel.add(loadButton);


        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loadButton) {
            //
        }
        if (event.getSource() == newJournalButton) {
            new InJournalGUI();
            frame.setVisible(false);

        }

    }

}

