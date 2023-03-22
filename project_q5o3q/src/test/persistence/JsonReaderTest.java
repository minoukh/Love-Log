package persistence;

import model.Person;
import model.MyJournal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MyJournal myJournal = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyJournal.json");
        try {
            MyJournal myJournal = reader.read();
            assertEquals("My journal", myJournal.getName());
            assertEquals(0, myJournal.numPersonsDating());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralJournal.json");
        try {
            MyJournal myJournal = reader.read();
            assertEquals("My journal", myJournal.getName());
            List<Person> personList = myJournal.getListOfPerson();
            assertEquals(2, personList.size());
            checkPerson("Jack", 22, "Vancouver", "Engineer", 10, personList.get(0));
            checkPerson("Joe", 32, "Toronto", "Janitor", 100, personList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");

        }
    }
}
