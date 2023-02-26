package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a journal having a collection of persons
public class MyJournal implements Writable {
    private String name;
    private List<Person> personsDating;

    // EFFECTS: constructs journal with a name and empty list of persons
    public MyJournal(String name) {
        this.name = name;
        personsDating = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds person to this journal
    public void addPerson(Person person) {
        personsDating.add(person);
    }

    // EFFECTS: returns an unmodifiable list of persons user is dating in this journal
    public List<Person> getPersonsDating() {
        return Collections.unmodifiableList(personsDating);
    }

    // EFFECTS: returns number of persons dating in this journal
    public int numPersonsDating() {
        return personsDating.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("persons", personsDatingToJson());
        return json;
    }

    // EFFECTS: returns persons in this journal as a JSON array
    private JSONArray personsDatingToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : personsDating) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
