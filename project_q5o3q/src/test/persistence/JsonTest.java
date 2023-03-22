package persistence;

import model.Person;
import model.MyJournal;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkPerson(String name, int age, String location, String job, int points, Person person) {
        assertEquals(name, person.getName());
        assertEquals(age, person.getAge());
        assertEquals(location, person.getPersonLocation());
        assertEquals(job, person.getPersonJob());
        assertEquals(points, person.getPersonEarnedPoints());
    }

    protected void checkJournal(String name, ArrayList persons, MyJournal myJournal) {
        assertEquals(name, myJournal.getName());
        assertEquals(persons.size(), myJournal.getListOfPerson().size());
    }
}
