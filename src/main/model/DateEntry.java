package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a date or interaction with the date having a name of associated person,
// number, successfulness, and a description of highlights and mishaps
public class DateEntry extends HashMap implements Writable {
    private final int dateNumber;
    private final String dateName;
    private Boolean successful;
    private String dateHighlightEvents;
    private String dateRedFlagEvents;

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
        dateHighlightEvents = "";
        dateRedFlagEvents = "";
    }

    public Boolean isDateSuccessful() {
        return successful;
    }

//    /*
//     * REQUIRES: string of length >=1
//     * MODIFIES: this
//     * EFFECTS: highlight description is added to dateHighlightEvents
//     */
//    public void addHighlightEvent(String description) {
//        dateHighlightEvents.add(description);
//    }
//
//    /*
//     * REQUIRES: string of length >=1
//     * MODIFIES: this
//     * EFFECTS: red-flag description is added to dateRedFlagEvents
//     */
//    public void addRedFlagEvent(String description) {
//        dateRedFlagEvents.add(description);
//    }
//
////    /*
//     * REQUIRES: num >= 0
//     * MODIFIES: this
//     * EFFECTS: highlight description is removed from dateHighlightEvents
//     */
//    public Boolean removeHighlightEvent(int num) {
//        int index = 1;
//        for (String desc : dateHighlightEvents) {
//            if (index == num) {
//                dateHighlightEvents.remove(desc);
//                return true;
//            }
//            index++;
//        }
//        return false;
//    }
//
//
//    /*
//     * REQUIRES: num >= 0
//     * MODIFIES: this
//     * EFFECTS: highlight description is removed from dateHighlightEvents
//     */
//    public Boolean removeRedFlagEvent(int num) {
//        int index = 1;
//        for (String desc : dateRedFlagEvents) {
//            if (index == num) {
//                dateRedFlagEvents.remove(desc);
//                return true;
//            }
//            index++;
//        }
//        return false;
//    }

    // getters:
    public String getDateName() {
        return dateName;
    }

    public int getDateNumber() {
        return dateNumber;
    }

    public String getDateHighLightEvents() {
        return dateHighlightEvents;
    }

    public String getDateRedFlagEvents() {
        return dateRedFlagEvents;
    }

    // setter:
    public void setSuccessfulness(Boolean successStatus) {
        this.successful = successStatus;
    }

    public void setDateHighlightEvents(String events) {
        this.dateHighlightEvents = events;
    }

    public void setDateRedFlagEvents(String events) {
        this.dateRedFlagEvents = events;
    }

    // EFFECTS: creates a JSON object for the Person
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dateName", dateName);
        json.put("dateNum", dateNumber);
        json.put("dateHighLightEvents", dateHighlightEvents);
        json.put("dateRedFlagEvents", dateRedFlagEvents);
        json.put("dateSuccessful", successful);
        return json;
    }

    @Override
    public String toString() {
        return "Date Number: " + dateNumber;
    }
}

