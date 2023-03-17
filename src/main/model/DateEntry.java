package model;

import java.util.*;

// Represents a date or interaction with the date having a name of associated person,
// number, successfulness, and a description of highlights and mishaps
public class DateEntry {
    private final int dateNumber;
    private final String dateName;
    private Boolean successful;
    private ArrayList<String> dateHighlightEvents;
    private ArrayList<String> dateRedFlagEvents;

    /*
     * EFFECTS: name on date entry is set to dateName; date number is a
     *          positive integer; success is set to false as default,
     *          list of description of highlight and red-flag events are
     *          initialized as empty
     */
    public DateEntry(String dateName, int dateNumber) {
        this.dateNumber = dateNumber;
        this.dateName = dateName;
        this.successful = false;
        dateHighlightEvents = new ArrayList<>();
        dateRedFlagEvents = new ArrayList<>();
    }

    public Boolean isDateSuccessful() {
        return successful;
    }

    /*
     * REQUIRES: string of length >=1
     * MODIFIES: this
     * EFFECTS: highlight description is added to dateHighlightEvents
     */
    public void addHighlightEvent(String description) {
        dateHighlightEvents.add(description);
    }

    /*
     * REQUIRES: string of length >=1
     * MODIFIES: this
     * EFFECTS: red-flag description is added to dateRedFlagEvents
     */
    public void addRedFlagEvent(String description) {
        dateRedFlagEvents.add(description);
    }

    /*
     * REQUIRES: num >= 0
     * MODIFIES: this
     * EFFECTS: highlight description is removed from dateHighlightEvents
     */
    public Boolean removeHighlightEvent(int num) {
        int index = 1;
        for (String desc : dateHighlightEvents) {
            if (index == num) {
                dateHighlightEvents.remove(desc);
                return true;
            }
            index++;
        }
        return false;
    }


    /*
     * REQUIRES: num >= 0
     * MODIFIES: this
     * EFFECTS: highlight description is removed from dateHighlightEvents
     */
    public Boolean removeRedFlagEvent(int num) {
        int index = 1;
        for (String desc : dateRedFlagEvents) {
            if (index == num) {
                dateRedFlagEvents.remove(desc);
                return true;
            }
            index++;
        }
        return false;
    }

    // getters:
    public String getDateName() {
        return dateName;
    }

    public int getDateNumber() {
        return dateNumber;
    }

    public List<String> getDateHighLightEvents() {
        return dateHighlightEvents;
    }

    public List<String> getDateRedFlagEvents() {
        return dateRedFlagEvents;
    }

    // setter:
    public void setSuccessfulness(Boolean successStatus) {
        this.successful = successStatus;
    }
}

