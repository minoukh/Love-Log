package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyJournalTest {
    private MyJournal testJournal;
    private Person jack;
    private Person joe;

    @BeforeEach
    void runBefore() {
        testJournal = new MyJournal("Jenna");
        jack = new Person("Jack", 20, "Van", "student", 0);
        joe = new Person("Joe", 22, "Tor", "student", 0);
    }

    @Test
    void testMyJournalConstructor() {
        assertEquals("Jenna", testJournal.getName());
        assertEquals(new ArrayList<>(), testJournal.getListOfPerson());
    }

    @Test
    void testAddPersonMultipleTimes() {
        testJournal.addPerson(jack);
        assertEquals(1, testJournal.numPersonsDating());
        testJournal.addPerson(joe);
        assertEquals(2, testJournal.numPersonsDating());
    }

    @Test
    void testToJson() {
        JSONObject jsonObj = testJournal.toJson();
        assertEquals("Jenna", jsonObj.get("name"));
    }

    @Test
    void testRemovePerson() {
        testJournal.addPerson(jack);
        testJournal.addPerson(joe);
        testJournal.remove(0);
        assertEquals(1, testJournal.numPersonsDating());
        assertEquals("Tor", testJournal.getListOfPerson().get(0).getPersonLocation());
    }

    @Test
    void testSetListOfPerson() {
        ArrayList list = new ArrayList<Person>();
        list.add(jack);
        list.add(joe);
        testJournal.setListOfPerson(list);
        assertEquals(2, testJournal.numPersonsDating());
    }
}
