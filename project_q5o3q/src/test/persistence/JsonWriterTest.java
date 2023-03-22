package persistence;

import model.Person;
import model.MyJournal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MyJournal myJournal = new MyJournal("My journal");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyJournal() {
        try {
            MyJournal myJournal = new MyJournal("My journal");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyJournal.json");
            writer.open();
            writer.write(myJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyJournal.json");
            myJournal = reader.read();
            assertEquals("My journal", myJournal.getName());
            assertEquals(0, myJournal.numPersonsDating());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MyJournal myJournal = new MyJournal("My journal");
            myJournal.addPerson(new Person("Jill", 25, "Kits", "lawyer",10));
            myJournal.addPerson(new Person("Jane", 35, "Burnaby", "Doctor",20));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralJournal.json");
            writer.open();
            writer.write(myJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralJournal.json");
            myJournal = reader.read();
            assertEquals("My journal", myJournal.getName());
            List<Person> personList = myJournal.getListOfPerson();
            assertEquals(2, personList.size());
            checkPerson("Jill", 25, "Kits", "lawyer", 10, personList.get(0));
            checkPerson("Jane", 35, "Burnaby", "Doctor",20, personList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
