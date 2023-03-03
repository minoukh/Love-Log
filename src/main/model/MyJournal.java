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
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds person to this journal
    public void addPerson(Person person) {
        listOfPerson.add(person);
    }

    // EFFECTS: returns a list of persons user is dating in this journal
    public List<Person> getListOfPerson() {
        return listOfPerson;
    }

    // EFFECTS: returns number of persons dating in this journal
    public int numPersonsDating() {
        return listOfPerson.size();
    }

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

    public void remove(int personNum) {
        int index = 0;
        for (Person p : listOfPerson) {
            if (index == personNum) {
                listOfPerson.remove(p);
            } else {
                index++;
            }
        }
    }
}
