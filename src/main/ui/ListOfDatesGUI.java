package ui;

import model.DateEntry;
import model.MyJournal;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents application's List of Dates for a person object window.
 */
public class ListOfDatesGUI implements ActionListener {
    private MyJournal myJournal;
    private Person person;
    private JFrame frame;
    private JPanel lodPanel;
    private JButton backButton;
    private List<DateEntry> dates;
    JList<DateEntry> list = new JList<>();
    DefaultListModel<DateEntry> model = new DefaultListModel<>();
    private JLabel label = new JLabel();
    private JSplitPane splitPane = new JSplitPane();

    /**
     * EFFECTS: Constructor sets up the split pane representing dates with the person, numbered on the left
     * while date descriptions appear on the wight; back button is set up too
     */
    public ListOfDatesGUI(MyJournal myJournal, Person person) {
        this.myJournal = myJournal;
        this.person = person;
        this.dates = person.getDatesWeHaveBeenOn();
        for (HashMap de : dates) {
            String name = String.valueOf(de.get("dateName"));
            int num = (int) de.get("dateNum");
            String highlights = String.valueOf(de.get("dateHighLightEvents"));
            String redFlags = String.valueOf(de.get("dateRedFlagEvents"));
            Boolean success = (Boolean) de.get("dateSuccessful");

            DateEntry dateEntry = new DateEntry(name, num);
            dateEntry.setDateHighlightEvents(highlights);
            dateEntry.setDateRedFlagEvents(redFlags);
            dateEntry.setSuccessfulness(success);
            model.addElement(dateEntry);
        }

        list.setModel(model);
        list.getSelectionModel().addListSelectionListener(e -> {
            DateEntry de = list.getSelectedValue();
            label.setText("Highlights: " + de.getDateHighLightEvents() + ":::"
                    + "Red-Flags: " + de.getDateRedFlagEvents() + ":::"
                    + "Successful: " + de.isDateSuccessful());
        });

        setUpFramePanelSplitPaneButton();
    }

    /**
     * MODIFIES: this
     * EFFECTS: sets up details (dimensions, layout, etc.)  of the frame, panel, and split pane
     */
    private void setUpFramePanelSplitPaneButton() {
        frame = new JFrame();
        frame.setLayout(new GridLayout());
        frame.setPreferredSize(new Dimension(400,300));
        frame.setBounds(100, 100, 1000, 1000);

        lodPanel = new JPanel();
        lodPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        lodPanel.setLayout(new GridLayout(0, 1));

        frame.add(lodPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane);
        frame.setTitle("Love Log App");
        frame.pack();
        frame.setVisible(true);


        splitPane.setLeftComponent(new JScrollPane(list));
        splitPane.setSize(2000, 2000);
        lodPanel.add(label);
        splitPane.setRightComponent(lodPanel);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        lodPanel.add(backButton);
    }

    /**
     * MODIFIES: this
     * EFFECTS: creates the next state of the application UI based on the action be performed by the
     * user (new Journal GUI or load from JSON file).
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == backButton) {
            new LopGUI(myJournal);
            frame.setVisible(false);
        }
    }
}
