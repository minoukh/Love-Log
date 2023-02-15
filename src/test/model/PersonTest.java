package model;

import model.Person;
import model.DateEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Person testPerson;

    @BeforeEach
    void runBefore() {
        testPerson = new Person("Joe", 20, "Vancouver",
                "Student");
    }

    @Test
    void testPersonConstructor() {
        assertEquals("Joe", testPerson.getName());
        assertEquals(20, testPerson.getAge());
        assertEquals("Vancouver", testPerson.getPersonLocation());
        assertEquals("Student", testPerson.getPersonJob());
        assertEquals(new ArrayList<>(), testPerson.getListOfCons());
        assertEquals(new ArrayList<>(), testPerson.getListOfPros());
        assertEquals(new LinkedList<>(), testPerson.getDatesWeHaveBeenOn());
        assertEquals(0, testPerson.getPersonEarnedPoints());

    }

    @Test
    void testAddingOnePoint() {
        testPerson.addOnePoint();
        assertEquals(1, testPerson.getPersonEarnedPoints());
        testPerson.addOnePoint();
        assertEquals(2, testPerson.getPersonEarnedPoints());
    }

    @Test
    void tesDeductOnePoint() {
        testPerson.deductOnePoint();
        assertEquals(-1, testPerson.getPersonEarnedPoints());

        testPerson.setPersonPoints(5);
        testPerson.deductOnePoint();
        assertEquals(4, testPerson.getPersonEarnedPoints());
    }

    @Test
    void testAddingToCons() {
        testPerson.addToCons("lazy");
        assertEquals(1, testPerson.getListOfCons().size());

        testPerson.addToCons("crazy");
        assertEquals(2, testPerson.getListOfCons().size());
    }

    @Test
    void testAddingToPros() {
        testPerson.addToPros("dorky");
        assertEquals(1, testPerson.getListOfPros().size());


        testPerson.addToPros("quirky");
        assertEquals(2, testPerson.getListOfPros().size());
    }


    @Test
    void testAddDateToListOfDates(DateEntry date) {
        DateEntry testDate1 = new DateEntry("Joe", 1);
        testPerson.addDateToListOfDates(testDate1);
        assertEquals(1, testPerson.numOfDatesWithPerson());

        DateEntry testDate2 = new DateEntry("John", 1);
        testPerson.addDateToListOfDates(testDate2);
        assertEquals(2, testPerson.numOfDatesWithPerson());
    }

    @Test
    void testFailureToAddDateToListOfDates(DateEntry date) {
        DateEntry testDate = new DateEntry("Jack", 1);
        testPerson.addDateToListOfDates(testDate);
        assertEquals(0, testPerson.numOfDatesWithPerson());
    }

    @Test
    void testSuccessfulRemovalOfTheDate() {
        DateEntry testDate1 = new DateEntry("Joe", 1);
        DateEntry testDate2 = new DateEntry("Joe", 2);
        testPerson.addDateToListOfDates(testDate1);
        testPerson.addDateToListOfDates(testDate2);

        assertTrue(testPerson.removeTheDate(2));
        assertTrue(testPerson.removeTheDate(1));
        assertEquals(0, testPerson.numOfDatesWithPerson());
    }

    @Test
    void testUnsuccessfulRemovalOfTheDate() {
        DateEntry testDate1 = new DateEntry("Joe", 1);
        DateEntry testDate2 = new DateEntry("Joe", 2);
        testPerson.addDateToListOfDates(testDate1);
        testPerson.addDateToListOfDates(testDate2);

        assertFalse(testPerson.removeTheDate(3));
        assertEquals(2, testPerson.numOfDatesWithPerson());
    }

    @Test
    void testZeroSuccessfulDatesWithPerson() {
        DateEntry testDate1 = new DateEntry("Joe", 1);
        DateEntry testDate2 = new DateEntry("Joe", 2);
        testPerson.addDateToListOfDates(testDate1);
        testPerson.addDateToListOfDates(testDate2);

        assertEquals(0, testPerson.numOfSuccessfulDatesWithPerson());
    }

    @Test
    void testOneOrMoreSuccessfulDatesWithPerson() {
        DateEntry testDate1 = new DateEntry("Joe", 1);
        DateEntry testDate2 = new DateEntry("Joe", 2);
        testDate1.setSuccessfulness(true);
        testPerson.addDateToListOfDates(testDate1);
        testPerson.addDateToListOfDates(testDate2);

        assertEquals(1, testPerson.numOfSuccessfulDatesWithPerson());
        testDate2.setSuccessfulness(true);
        assertEquals(2, testPerson.numOfSuccessfulDatesWithPerson());
    }

}
