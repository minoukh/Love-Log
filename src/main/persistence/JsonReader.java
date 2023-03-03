package persistence;

import model.DateEntry;
import model.Person;
import model.MyJournal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads journal from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads journal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyJournal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMyJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses journal from JSON object and returns it
    private MyJournal parseMyJournal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MyJournal myJournal = new MyJournal(name);
        addPersons(myJournal, jsonObject);
        return myJournal;
    }

    // MODIFIES: myJournal
    // EFFECTS: parses persons from JSON object and adds them to journal
    private void addPersons(MyJournal myJournal, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("persons");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPerson(myJournal, nextPerson);
        }
    }

    // MODIFIES: myJournal
    // EFFECTS: parses person from JSON object and adds it to journal
    private void addPerson(MyJournal myJournal, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String personLocation = jsonObject.getString("location");
        String personJob = jsonObject.getString("job");
        int personEarnedPoints = jsonObject.getInt("points");
        List cons = (List) jsonObject.getJSONArray("cons").toList();
        List pros = (List) jsonObject.getJSONArray("pros").toList();
        List datesWeHaveBeenOn = (List) jsonObject.getJSONArray("dates").toList();
        Person person = new Person(name, age, personLocation, personJob, personEarnedPoints);
        person.cons = (java.util.List<String>) cons;
        person.pros = (java.util.List<String>) pros;
        person.datesWeHaveBeenOn = (java.util.List<DateEntry>) datesWeHaveBeenOn;
        myJournal.addPerson(person);
    }
}
