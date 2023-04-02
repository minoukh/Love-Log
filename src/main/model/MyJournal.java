package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a journal having a collection of persons
public class MyJournal implements Writable {
    private String name;
    private ArrayList<Person> listOfPerson;

    // EFFECTS: constructs journal with a name and empty list of persons
    public MyJournal(String name) {
        this.name = name;
        listOfPerson = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("New Journal created."));
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds person to this journal
    public void addPerson(Person person) {
        listOfPerson.add(person);
        EventLog.getInstance().logEvent(new Event("Person added: " + person.getName()));
    }

    // EFFECTS: returns a list of persons user is dating in this journal
    public List<Person> getListOfPerson() {
        return listOfPerson;
    }

    // EFFECTS: returns number of persons dating in this journal
    public int numPersonsDating() {
        return listOfPerson.size();
    }

    // EFFECTS: creates a JSON object for the journal
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("persons", personsToJson());
        return json;
    }

    // EFFECTS: returns persons in this journal as a JSON array
    private JSONArray personsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : listOfPerson) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: remove person at a given index from the listOfPerson in myJournal
    public void remove(int personNum) {
        int index = 0;
        ArrayList tempList = new ArrayList();
        for (Person p : listOfPerson) {
            if (index != personNum) {
                tempList.add(p);
            }
            index++;
        }
        listOfPerson = tempList;
        EventLog.getInstance().logEvent(new Event("Person removed at index: " +  personNum));
    }

    // MODIFIES: this
    // EFFECTS: passes in a list of people into the journal
    public void setListOfPerson(ArrayList<Person> listOfPerson) {
        this.listOfPerson = listOfPerson;
    }
}
